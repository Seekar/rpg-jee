package controleur;


import dao.DAOException;
import dao.JoueurDAO;
import dao.PersonnageDAO;
import dao.AventureDAO;
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
        if (Main.notLogged(request, response)
            || action == null) {
            return;
        }

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
                AventureDAO aventureDAO = AventureDAO.Get();
                Aventure aventure;
                boolean can_add = false;

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    aventure = aventureDAO.getAventure(id);

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

                AventureDAO avDAO = AventureDAO.Get();
                Collection<Aventure> parties = null;

                try {
                    String titre = "Liste des aventures";

                    /*if (action.equals("ownedList")) {
                        persos = persoDAO.getPersonnagesJoueur(user);
                        titre = "Personnages possédés";
                    }
                    else if (action.equals("leaderList")) {
                        persos = persoDAO.getPersonnagesMenes(user);
                        titre = "Personnages menés";
                    }
                    else if (action.equals("transferList")) {
                        persos = persoDAO.getTransfertsAValider(user);
                        titre = "Demandes de transfert";
                    }
                    else if (action.equals("validationList")) {
                        persos = persoDAO.getPersonnagesAValider(user);
                        titre = "Personnages à valider";
                    }*/
                   /* if (null) {
                        null;
                    }
                    else {*/
                        parties = avDAO.getAventures();
                    //}

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
            
        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex.getMessage());
        }
    }

    public void actionAddMember(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {
        
        // @Leo : (Je suis en train de coder cette fonction)
        
            // Récupérer personnage associé à l'id
            // Récupérer l'aventure courante
            // Comparer les univers du personnage et de l'aventure
    }
}


