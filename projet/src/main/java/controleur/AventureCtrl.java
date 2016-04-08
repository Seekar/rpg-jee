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

        case "addMember":
        {

            break;
        }

        case "delMember":
        {

            break;
        }
        case "finish":
        {

            break;
        }
        case "delete":
        {
            // Vérifier qu'il s'agit bien d'une proposition de partie (la partie n'est pas finie)

            break;
        }
        case "show":
            page = "affichage";
            Aventure aventure;
            boolean can_add = false;

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                aventure = avDAO.getAventure(id);

                if (aventure.getMj().getId() == user.getId()) {
                    can_add = true;
                }

                request.setAttribute("can_add", can_add);
                request.setAttribute("aventure", aventure);

                JoueurDAO joueurDAO = JoueurDAO.Get();
                List<Joueur> listeJoueurs = joueurDAO.getAutresJoueurs(user.getId());

                // TODO : Filtrer la liste des joueurs pour enlever ceux qui sont
                //         déja dans l'aventure

                request.setAttribute("listeJoueurs", listeJoueurs);

            } catch (NumberFormatException e) {
               Main.invalidParameters(request, response);

            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }

            break;

        default:
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

        String action = request.getParameter("action");

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)
            || action == null) {
            return;
        }


        switch(action) {
        case "create":
            actionCreate(request, response);
            break;

        case "addMember":
            actionAddMember(request, response);
            break;
        
        case "removeMember":
            actionRemoveMember(request, response);
            break;
        }
    }


    public void actionCreate(HttpServletRequest request,
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
                    + request.getServletPath() + "?action=list");
            
        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);
            
        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }

    public void actionAddMember(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {
        
        try {
            // Récupérer personnage associé à l'id
            int persoId = Integer.parseInt(request.getParameter("idJoueur"));
            Personnage perso = PersonnageDAO.Get().getPersonnage(persoId);

            // Récupérer l'aventure courante
            int aventureId = Integer.parseInt(request.getParameter("idAventure"));
            Aventure aventure = AventureDAO.Get().getAventure(aventureId);
            
            // Comparer les univers du personnage et de l'aventure
            int persoUniversId = perso.getUnivers().getId();
            int aventureUniversId = aventure.getUnivers().getId();
            if (persoUniversId != aventureUniversId) {
                // TODO
                // Définir une variable erreur_univers
                // RequestDispatcher vers l'affichage de l'aventure courante
            }
            
            // Vérifier que le personnage n'est pas déjà dans l'aventure
            for (Personnage p : aventure.getPersonnages()) {
                if (p.getId() == persoId) {
                    // TODO
                    // Définir une variable erreur_doublon
                    // RequestDispatcher vers l'affichage de l'aventure courante
                }
            }
            
            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                    // TODO
                    // Définir une variable erreur_not_mj
                    // RequestDispatcher vers l'affichage de l'aventure courante
            }
            
            // A ce stade, on a tout check et géré les erreurs
            
            // Créer un objet Participe et l'ajoute à la base
            Participe participe = new Participe(aventure, perso);
            ParticipeDAO.Get().creerParticipe(participe);

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + aventureId);
            
        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);
            
        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }
    
    
    public void actionRemoveMember(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {
        
        try {
            // Récupérer personnage associé à l'id
            int persoId = Integer.parseInt(request.getParameter("idPerso"));
            Personnage perso = PersonnageDAO.Get().getPersonnage(persoId);

            // Récupérer l'aventure courante
            int idPartie = Integer.parseInt(request.getParameter("idPartie"));
            Aventure aventure = AventureDAO.Get().getAventure(idPartie);
            
            // Vérifier que l'utilisateur est bien le MJ de l'aventure
            if (aventure.getMj().getId() != Main.GetJoueurSession(request).getId()) {
                    // TODO
                    // Définir une variable erreur_not_mj
                    // RequestDispatcher vers l'affichage de l'aventure courante
            }
            
            
            // Supprime le lien Participe de la base
            ParticipeDAO.Get().supprimerParticipe(aventure,perso);

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPartie);
            
        } catch (NumberFormatException | IOException ex) {
            Main.invalidParameters(request, response, ex);
            
        } catch (DAOException ex) {
            Main.dbError(request, response, ex);
        }
    }
        
}


