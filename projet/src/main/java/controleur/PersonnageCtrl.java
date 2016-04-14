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
 * Contrôleur de personnages
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
@WebServlet(name = "PersonnageCtrl", urlPatterns = {"/character"})
public class PersonnageCtrl extends HttpServlet {

    /**
     * Requetes GET
     *
     * @param request  La requete
     * @param response La réponse
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

        // Force le login
        if (Main.notLogged(request, response)) {
            return;
        }


        PersonnageDAO persoDAO = PersonnageDAO.Get();
        AventureDAO avDAO = AventureDAO.Get();
        Joueur user = Main.GetJoueurSession(request);
        int idUser = user.getId();

        switch (action) {
        
        // Crée un personnage
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
        
        // Affiche un personnage
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

        // Valide un personnage
        case "editMJ":
            actionAcceptMJ(request, response);
            break;

        // Accepte le transfert d'un personnage
        case "transfer":
            actionAcceptTransfer(request, response);
            break;

        default:
            page = "liste";

            try {
                Collection<Personnage> persos;
                String titre;

                switch (action) {
                
                // Liste des personnages possédés
                case "ownedList":
                    persos = persoDAO.getPersonnagesJoueur(user);
                    titre = "Mes personnages";
                    break;

                // Liste des personnages menés
                case "leaderList":
                    persos = persoDAO.getPersonnagesMenes(user);
                    titre = "Personnages menés";
                    break;

                // Liste des transferts à valider
                case "transferList":
                    persos = persoDAO.getTransfertsAValider(user);
                    titre = "Demandes de transfert";
                    break;

                // Liste des personnages à valider
                case "validationList":
                    persos = persoDAO.getPersonnagesAValider(user);
                    titre = "Personnages à valider";
                    break;

                // Liste des participants d'une partie
                case "gameList":
                    boolean persoKiller = false;
                    String idParam = request.getParameter("id");
                    int idPartie = Integer.parseInt(idParam);
                    Aventure partie = avDAO.getAventure(idPartie);
                    
                    persos = partie.getPersonnages();
                    
                    // Si la partie est en cours on autorise le MJ
                    // à retirer des personnages
                    if (user.getId() == partie.getMj().getId()
                            && !partie.isFinie())
                        persoKiller = true;
                    
                    titre = "Participants à \"" + partie.getTitre() + "\"";
                    request.setAttribute("persoKiller", persoKiller);
                    break;

                // Par défaut on affiche la liste des personnages
                case "list":
                default:
                    persos = persoDAO.getPersonnages();
                    titre = "Liste des personnages";
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

        if (page != null) {
            request.getRequestDispatcher("/WEB-INF/personnage/" + page + ".jsp").forward(request, response);
        }

        // Si une redirection a été effectuée, on ne fait rien
        else if (request.getAttribute("done") == null) {
            Main.invalidParameters(request, response);
        }
    }

    /**
     * Requetes POST
     *
     * @param request  La requete
     * @param response La réponse
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

    /**
     * Crée un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionCreate(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            String naissance = request.getParameter("naissance");
            String bio = request.getParameter("biographie");
            String nom = request.getParameter("nom");
            String portrait = request.getParameter("portrait");
            String profession = request.getParameter("profession");

            // Encapsulation des différents paramètres
            int idUnivers = Integer.parseInt(request.getParameter("univers"));
            Univers univers = new Univers(idUnivers);

            Personnage perso = new Personnage(nom, naissance,
                    profession, portrait, univers);

            perso.setJoueur(Main.GetJoueurSession(request));
            
            // Requete SQL
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

    /**
     * Demande la validation d'un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionEditMJ(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            // Requete SQL
            PersonnageDAO.Get().requestValidation(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    /**
     * Valide un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionAcceptMJ(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            // Requete SQL
            PersonnageDAO.Get().acceptValidation(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

            // Indique qu'une réponse a déjà été donnée
            request.setAttribute("done", true);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    /**
     * Demande le transfert d'un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionTransfer(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            // Requete SQL
            PersonnageDAO.Get().requestTransfer(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    /**
     * Accepte le transfert d'un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionAcceptTransfer(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            // Requete SQL
            PersonnageDAO.Get().acceptTransfer(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

            // Indique qu'une réponse a déjà été donnée
            request.setAttribute("done", true);

        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    /**
     * Edite un personnage.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionEdit(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));

            Personnage perso = new Personnage(idPerso);
            perso.setProfession(request.getParameter("newWork"));

            // Requete SQL
            PersonnageDAO.Get().modifierPersonnage(perso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }

    /**
     * Cède un personnage à un autre joueur.
     * 
     * @param request  La requete
     * @param response La réponse
     * @throws IOException
     * @throws ServletException 
     */
    private void actionDonate(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idDest = Integer.parseInt(request.getParameter("idDest"));

            // Requete SQL
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
