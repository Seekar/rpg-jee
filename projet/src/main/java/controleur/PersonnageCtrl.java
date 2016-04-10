package controleur;

import dao.AventureDAO;
import dao.DAOException;
import dao.JoueurDAO;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modele.*;

/**
 * Contrôleur de personnages.
 */
@WebServlet(name = "PersonnageCtrl", urlPatterns = {"/character"})
public class PersonnageCtrl extends HttpServlet {

    // Actions possibles en GET : 
    /**
     * Requetes GET
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String page = null;
        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);
        int idUser = user.getId();

        // Force le login
        if (Main.notLogged(request, response)) {
            return;
        }

        // Affiche les personnages possédés par défaut
        else if (action == null) {
            action = "ownedList";
        }


        PersonnageDAO persoDAO = PersonnageDAO.Get();
        AventureDAO avDAO = AventureDAO.Get();

        switch (action) {
        case "create":
            page = "creation";

            UniversDAO universDAO = UniversDAO.Get();
            Collection<Univers> univers;

            try {
                univers = universDAO.getUnivers();
                request.setAttribute("listeUnivers", univers);
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
            break;
        
        case "show":
            page = "affichage";

            Personnage perso;
            boolean canEdit = false;
            boolean canTransfer = false;

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                perso = persoDAO.getPersonnage(id);
                canTransfer = !persoDAO.dansPartieEnCours(id);

                if (perso.getJoueur().getId() == user.getId())
                    canEdit = true;

                request.setAttribute("canEdit", canEdit);
                request.setAttribute("canTransfer", canTransfer);
                request.setAttribute("perso", perso);
                
                JoueurDAO joueurDAO = JoueurDAO.Get();
                Collection<Joueur> listeTransfert = new ArrayList<>();
                List<Joueur> listeMJ = joueurDAO.getAutresMeneurs(idUser);
                List<Joueur> listeJoueurs = joueurDAO.whoCanReceive(id);
                
                
                int mj_id = perso.getMj().getId();
                
                for (Joueur mj : listeMJ) {
                    if (mj.getId() != mj_id) {
                        listeTransfert.add(mj);
                    }
                }
                
                
                request.setAttribute("listeJoueurs", listeJoueurs);
                request.setAttribute("listeMJ", listeMJ);
                request.setAttribute("listeTransfert", listeTransfert);
                
            } catch (NumberFormatException e) {
               Main.invalidParameters(request, response);
               
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
            
            break;

        case "editMJ":
            actionAcceptMJ(request, response);
            break;

        case "transfer":
            actionAcceptTransfer(request, response);
            break;

        default:
            if (action.endsWith("List")) {
                page = "liste";

                Collection<Personnage> persos = null;

                try {
                    String titre = null;

                    switch (action) {
                    case "ownedList":
                        persos = persoDAO.getPersonnagesJoueur(user);
                        titre = "Personnages possédés";
                        break;
                        
                    case "leaderList":
                        persos = persoDAO.getPersonnagesMenes(user);
                        titre = "Personnages menés";
                        break;
                        
                    case "transferList":
                        persos = persoDAO.getTransfertsAValider(user);
                        titre = "Demandes de transfert";
                        break;
                        
                    case "validationList":
                        persos = persoDAO.getPersonnagesAValider(user);
                        titre = "Personnages à valider";
                        break;
                        
                    case "gameList":
                        String idParam = request.getParameter("id");
                        int idPartie = Integer.parseInt(idParam);
                        boolean persoKiller = false;
                        Aventure partie = avDAO.getAventure(idPartie);
                        persos = partie.getPersonnages();
                        titre = "Participants à \"" + partie.getTitre() + "\"";
                        if (user.getId() == partie.getMj().getId()
                                && !partie.isFinie())
                            persoKiller = true;
                        request.setAttribute("persoKiller", persoKiller);
                        break;
                    }

                    request.setAttribute("titre", titre);
                    request.setAttribute("persos", persos);

                } catch (DAOException e) {
                   Main.dbError(request, response, e);

                } catch (Exception e) {
                   Main.invalidParameters(request, response, e);
                }
            }
        }

        if (page != null) {
            request.getRequestDispatcher("/WEB-INF/personnage/" + page + ".jsp").forward(request, response);
        }

        else if (request.getAttribute("done") == null) {
            Main.invalidParameters(request, response);
        }
    }

    /**
     * Requetes POST
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // Force le login et gère les erreurs
        if (Main.badRequest(request, response)) {
            return;
        }

        switch (action) {
        case "create":
            actionCreate(request, response);
            break;
        
        case "editMJ":
            actionEditMJ(request, response);
            break;
        
        case "transfer":
            actionTransfer(request, response);
            break;
        
        case "edit":
            actionEdit(request, response);
            break;
        
        case "donate":
            actionDonate(request, response);
            break;
        }
    }

    private void actionCreate(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            String naissance = request.getParameter("naissance");
            String bio = request.getParameter("biographie");
            String nom = request.getParameter("nom");
            String portrait = request.getParameter("portrait");
            String profession = request.getParameter("profession");

            int idUnivers = Integer.parseInt(request.getParameter("univers"));
            Univers univers = new Univers(idUnivers);

            Personnage perso = new Personnage(nom, naissance,
                    profession, portrait, univers);

            perso.setJoueur(Main.GetJoueurSession(request));
            PersonnageDAO.Get().creer(perso, bio);

            // Afficher les personnages possédés si la création a réussi
            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=ownedList");

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionEditMJ(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            PersonnageDAO.Get().requestValidation(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionAcceptMJ(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            PersonnageDAO.Get().acceptValidation(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

            request.setAttribute("done", true);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionTransfer(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            PersonnageDAO.Get().requestTransfer(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionAcceptTransfer(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            PersonnageDAO.Get().acceptTransfer(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

            request.setAttribute("done", true);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionEdit(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));

            Personnage perso = new Personnage(idPerso);
            perso.setProfession(request.getParameter("newWork"));

            PersonnageDAO.Get().modifierPersonnage(perso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }

    private void actionDonate(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idDest = Integer.parseInt(request.getParameter("idDest"));

            PersonnageDAO.Get().donnerPersonnage(idPerso, idDest, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=ownedList");

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }
}
