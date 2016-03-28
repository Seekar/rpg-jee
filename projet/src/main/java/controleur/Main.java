package controleur;

import dao.AbstractJoueurDAO;
import dao.DAOException;
import dao.JoueurDAO;

import java.io.*;
import java.security.MessageDigest;
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

        JoueurDAO.Create(ds);
        String page = "accueil";

        if (request.getParameter("login") != null) {
            page = "login";
        }
        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
        }

        request.setAttribute("section", page);

        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("nickname");
        String pass = request.getParameter("password");
        String page = "login";

        if (isLoginValid(login, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            page = "accueil";
        }
        else {
            request.setAttribute("error", "Login incorrect");
        }

        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
    }

    private boolean isLoginValid(String login, String pwd) {

        try {
            Joueur joueur = JoueurDAO.Get().getJoueur(login);

            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
            mdAlgorithm.update(pwd.getBytes());

            byte[] digest = mdAlgorithm.digest();
            StringBuilder hexString = new StringBuilder();
            String plainText;

            for (int i = 0; i < digest.length; i++) {
                plainText = Integer.toHexString(0xFF & digest[i]);

                if (plainText.length() < 2) {
                    plainText = "0" + plainText;
                }

                hexString.append(plainText);
            }

            //System.out.println("md5(" + pwd + ") == " + hexString);
            //System.out.println("joueur.getPwd() == " + joueur.getPwd());

            return (joueur != null && joueur.getPwd().equals(hexString.toString()));
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
}


