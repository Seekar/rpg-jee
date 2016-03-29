package controleur;

import dao.DAOException;
import dao.PersonnageDAO;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        
        String page = "liste";

        if (request.getParameter("create") != null) {
            page = "creation";
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

        if (request.getParameter("create") != null) {
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
                
            int idUser = (int)session.getAttribute("idUser");
            
            Personnage perso = new Personnage(nom, naissance, profession, portrait, univers);
            perso.setJoueur(new Joueur(idUser));
            
            PersonnageDAO.Get().creer(perso, bio);
            response.sendRedirect(request.getContextPath());
            
        } catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
            doGet(request, response);
        }
    }
}


