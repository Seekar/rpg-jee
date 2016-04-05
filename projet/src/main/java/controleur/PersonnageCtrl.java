package controleur;

import dao.DAOException;
import dao.PersonnageDAO;
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

    
    
     /**
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * creation.
     */
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
        String page ="";
        if (action == null){
            PersonnageDAO persoDAO = PersonnageDAO.Get();
        
                Collection<Personnage> persos;
            try {
                persos = persoDAO.getAllPersonnages();
                request.setAttribute("persos", persos);
            } catch (DAOException e) {
               Main.erreurBD(request, response, e);
            }
                 
                        page = "liste";
                
        }


        else if (action.equals("create")) {
            page = "creation";
        }
        else {
            Main.invalidParameters(request, response);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/personnage/" + page + ".jsp").forward(request, response);
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
            
            
            HttpSession session = request.getSession();
            
            if (session == null)
                throw new Exception("Erreur session");

            Joueur user = (Joueur)session.getAttribute("user");
            
            Personnage perso = new Personnage(nom, naissance, profession, portrait, univers);
            perso.setJoueur(user);
            PersonnageDAO.Get().creer(perso, bio);
            response.sendRedirect(request.getContextPath());
            
        } catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
            doGet(request, response);
        }
    }
}


