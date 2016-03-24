package controleur;

import dao.DAOException;
import dao.OuvrageDAO;
import java.io.*;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Aventure;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

    @Resource(name = "jdbc/bibliography")
    private DataSource ds;

    /* pages d’erreurs */
    private void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);        
    }

    private void erreurBD(HttpServletRequest request,
                HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("erreurMessage", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
    }
  
    /**
     * Actions possibles en GET : afficher, getOuvrage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, ouvrageDAO);
            } else if (action.equals("getOuvrage")){
                actionGetOuvrage(request, response, ouvrageDAO);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            OuvrageDAO ouvrageDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("ouvrages", ouvrageDAO.getListeOuvrages());                
        request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
    }

    private void actionGetOuvrage(HttpServletRequest request, 
            HttpServletResponse response, 
            OuvrageDAO ouvrageDAO) throws DAOException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String vue = request.getParameter("view");
        if (vue.equals("modifier") || vue.equals("supprimer")) {
            Ouvrage ouvrage = ouvrageDAO.getOuvrage(id);
            request.setAttribute("ouvrage", ouvrage);
            getServletContext().getRequestDispatcher("/WEB-INF/" + vue + ".jsp").forward(request, response);
        }
        else invalidParameters(request, response);
    }

    
    /**
     * Actions possibles en POST : ajouter, supprimer, modifier.
     */

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // le paramètre "action" est obligatoire en POST
        if (action == null) {
            invalidParameters(request, response);
            return;
        }
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);

        try {
            if (action.equals("ajouter")) {
                actionAjouter(request, response, ouvrageDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, ouvrageDAO);
            } else if (action.equals("modifier")) {
                actionModifier(request, response, ouvrageDAO);
            } else {
                invalidParameters(request, response);
            }
            
            actionAfficher(request, response, ouvrageDAO);
            
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }
    
    /**
     * Ajout d'un ouvrage.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {

        String auteur = request.getParameter("auteur");
        String titre = request.getParameter("titre");
        ouvrageDAO.ajouterOuvrage(auteur, titre);
    }

    /**
     * Suppression d'un ouvrage.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ouvrageDAO.supprimerOuvrage(id);
    }

    /**
     * Modification d'un ouvrage.
     */
    private void actionModifier(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {

        String auteur = request.getParameter("auteur");
        String titre = request.getParameter("titre");
        int id = Integer.parseInt(request.getParameter("id"));
        ouvrageDAO.modifierOuvrage(id, auteur, titre);
    }

}
