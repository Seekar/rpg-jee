package controleur;


import dao.DAOException;
import dao.JoueurDAO;
import dao.PersonnageDAO;
import dao.AventureDAO;
import dao.UniversDAO;
import dao.ParticipeDAO;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modele.*;

/**
 * Contrôleur d'aventures.
 */
@WebServlet(name = "AventureCtrl", urlPatterns = {"/game"})
public class AventureCtrl extends HttpServlet {

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

        String action = request.getParameter("action");
        String page = null;
        Joueur user = Main.GetJoueurSession(request);

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)) {
            return;
        }
        else if (action == null) {
            action = "list";
        }

        PersonnageDAO persoDAO = PersonnageDAO.Get();
        AventureDAO avDAO = AventureDAO.Get();

        switch(action) {
        case "create":
        {
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
        }

        case "show": {
            page = "affichage";
            Aventure aventure;
            boolean canAdd = false;
            String titre = "Détails de la partie";

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                aventure = avDAO.getAventure(id);

                if (aventure.isFinie()) {
                    titre = "Détails de l'aventure";
                }
                else if (aventure.getMj().getId() == user.getId()) {
                    canAdd = true;
                    request.setAttribute("listePersonnages",
                            persoDAO.getCandidats(user, aventure.getUnivers()));
                }

                request.setAttribute("titre", titre);
                request.setAttribute("canAdd", canAdd);
                request.setAttribute("aventure", aventure);
                

            } catch (NumberFormatException e) {
               Main.invalidParameters(request, response);

            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }

            break;
        }

        default: {
            if (action.toLowerCase().endsWith("list")) {
                page = "liste";

                Collection<Aventure> parties = null;

                try {
                    String titre = "Liste des parties";

                    if (action.equals("characterList")) {
                        String idParam = request.getParameter("id");
                        int idPerso = Integer.parseInt(idParam);
                        
                        Personnage perso = persoDAO.getPersonnage(idPerso);
                        parties = avDAO.getParties(perso);
                        titre = "Parties de " + perso.getNom();
                        request.setAttribute("isPerso", true);
                    }
                    else if (action.equals("myList")) {
                        parties = avDAO.getParties(user);
                        titre = "Mes parties";
                        request.setAttribute("hasPerso", true);
                    }
                    else if (action.equals("leaderList")) {
                        parties = avDAO.getPartiesMenees(user);
                        titre = "Parties menées";
                    }
                    
                    // Liste complète par défaut
                    else {
                        parties = avDAO.getAventures();
                    }

                    request.setAttribute("titre", titre);
                    request.setAttribute("parties", parties);

                } catch (DAOException e) {
                   Main.dbError(request, response, e);
                }
            }
            }
        }

        if (page != null) {
            request.getRequestDispatcher("/WEB-INF/aventure/" + page + ".jsp").forward(request, response);
        } else if (request.getAttribute("done") == null) {
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

        switch(action) {
        case "create":
            actionCreate(request, response);
            break;
        case "finish":
            actionFinish(request, response);
            break;
        case "delete":
            actionDelete(request, response);
            break;
        case "addMember":
            actionAddMember(request, response);
            break;
        case "removeMember":
            actionRemoveMember(request, response);
            break;
        }
    }


    private void actionCreate(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            // On récupère les paramètres
            String lieu = request.getParameter("lieu");
            String date = request.getParameter("date");
            String titre = request.getParameter("titre");
            String resume = request.getParameter("resume");
            int idUnivers = Integer.parseInt(request.getParameter("univers"));
            Univers univers = new Univers(idUnivers);

            // On crée l'objet aventure
            Aventure aventure = new Aventure(titre, date, lieu, univers, resume, Main.GetJoueurSession(request));
            AventureDAO.Get().creerPartie(aventure);

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=leaderList");
            
        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);
            
        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    private void actionAddMember(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {
        
        try {
            // Récupérer personnage associé à l'id
            int persoId = Integer.parseInt(request.getParameter("idPersonnage"));
            Personnage perso = PersonnageDAO.Get().getPersonnage(persoId);

            // Récupérer l'aventure courante
            int aventureId = Integer.parseInt(request.getParameter("idAventure"));
            Aventure aventure = AventureDAO.Get().getAventure(aventureId);
            
            // Comparer les univers du personnage et de l'aventure
            int persoUniversId = perso.getUnivers().getId();
            int aventureUniversId = aventure.getUnivers().getId();
            if (persoUniversId != aventureUniversId) {
                throw new Exception("Accès refusé");
            }

            // Vérifier que le personnage n'est pas déjà dans l'aventure
            for (Personnage p : aventure.getPersonnages()) {
                if (p.getId() == persoId) {
                    throw new SecurityException("Accès refusé");
                }
            }

            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                throw new SecurityException("Accès refusé");
            }

            // A ce stade, on a tout check et géré les erreurs

            // Créer un objet Participe et l'ajouter à la base
            Participe participe = new Participe(aventure, perso);
            ParticipeDAO.Get().creerParticipe(participe);

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + aventureId);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }
    
    private void actionRemoveMember(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {

            // Récupérer personnage associé à l'id
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            Personnage perso = PersonnageDAO.Get().getPersonnage(idPerso);

            // Récupérer l'aventure courante
            int idPartie = Integer.parseInt(request.getParameter("idPartie"));
            Aventure aventure = AventureDAO.Get().getAventure(idPartie);

            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                throw new SecurityException("Accès refusé");
            }

            // Supprime le lien Participe de la base
            ParticipeDAO.Get().supprimerParticipe(aventure,perso);

            response.sendRedirect(request.getContextPath()
                    + "/character?action=gameList&id=" + idPartie);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }

    private void actionFinish(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {

        try {

            // Récupérer l'aventure courante
            int aventureId = Integer.parseInt(request.getParameter("idAventure"));
            Aventure aventure = AventureDAO.Get().getAventure(aventureId);

            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                throw new SecurityException("Accès refusé");
            }

            // Vérifier que la partie n'est pas déjà finie
            if (aventure.estFinie()) {
                throw new SecurityException("Accès refusé");
            }

            String events = request.getParameter("events");
            
            AventureDAO.Get().finishPartie(aventure, events);
            
            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + aventureId);

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {            
        
        try {
            // Récupérer l'aventure courante
            int idAventure = Integer.parseInt(request.getParameter("idAventure"));
            Aventure aventure = AventureDAO.Get().getAventure(idAventure);
            
            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                throw new SecurityException("Accès refusé");
            }

            // Vérifier que la partie n'est pas déjà finie 
            // (car on ne doit plus pouvoir la supprimer)
            if (aventure.estFinie()) {
                throw new SecurityException("Accès refusé");
            }

            AventureDAO.Get().deletePartie(aventure);

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=leaderList");

        } catch (DAOException ex) {
            Main.dbError(request, response, ex);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex);
        }
    }
}

