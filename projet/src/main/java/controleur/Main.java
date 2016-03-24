package controleur;

import dao.AbstractJoueurDAO;
import dao.DAOException;
import dao.JoueurDAO;

import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.*;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "Main", urlPatterns = {"/main"})
public class Main extends HttpServlet {

    @Resource(name = "jdbc/rpg")
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
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * getOuvrage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        AbstractJoueurDAO joueurs = new JoueurDAO(ds);
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    /**
     *
     * Affiche la page d’accueil avec la liste de tous les ouvrages.
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response) throws DAOException, ServletException, IOException {


    }


    /**
     * Actions possibles en POST : ajouter, supprimer, modifier. Une fois
     * l’action demandée effectuée, on retourne à la page d’accueil avec
     * actionAfficher(...)
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

    }

   

}
