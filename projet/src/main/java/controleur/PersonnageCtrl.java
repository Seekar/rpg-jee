package controleur;

import dao.DAOException;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
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

        
        if (action == null){
            PersonnageDAO persoDAO = PersonnageDAO.Get();
            Collection<Personnage> persos;

            try {
                persos = persoDAO.getAllPersonnages();
                request.setAttribute("persos", persos);
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }

            page = "liste";
        }
        else if (action.equals("create")) {
            page = "creation";
            
            UniversDAO universDAO = UniversDAO.Get();
            Collection<Univers> univers;
            
            try {
                univers = universDAO.getUnivers();
                request.setAttribute("listeUnivers", univers);
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
        }
        else if (action.endsWith("List")) {
            page = "liste";
            
            PersonnageDAO persoDAO = PersonnageDAO.Get();
            Collection<Personnage> persos = null;
            
            try {
                String titre = null;
                
                if (action.equals("ownedList")) {
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
                }

                request.setAttribute("titre", titre);
                request.setAttribute("persos", persos);
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
        }

        if (page != null) {
            request.getRequestDispatcher("/WEB-INF/personnage/" + page + ".jsp").forward(request, response);
        }
        else {
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

        if (action.equals("create")) {
            actionCreate(request, response);
        }
    }

    public void actionCreate(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            String naissance = request.getParameter("naissance");
            String bio = request.getParameter("biographie");
            String nom = request.getParameter("nom");
            String portrait = request.getParameter("portrait");
            String profession = request.getParameter("profession");
            Univers univers = new Univers(Integer.parseInt(request.getParameter("univers")));
            
            Personnage perso = new Personnage(nom, naissance, profession, portrait, univers);
            perso.setJoueur(Main.GetJoueurSession(request));
            
            PersonnageDAO.Get().creer(perso, bio);
            response.sendRedirect(request.getContextPath());
            
        } catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
            doGet(request, response);
        }
    }
}


