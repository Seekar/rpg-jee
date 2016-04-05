package controleur;

import dao.AventureDAO;
import dao.BiographieDAO;
import dao.DAOException;
import dao.EpisodeDAO;
import dao.JoueurDAO;
import dao.ParagrapheDAO;
import dao.PersonnageDAO;
import dao.UniversDAO;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.*;

/**
 * Le contrôleur d'application.
 */
@WebServlet(name = "Main", urlPatterns = {"/main"})
public class Main extends HttpServlet {

    @Resource(name = "jdbc/rpg")
    private DataSource ds;
    
    @Override
    public void init() {
        
        // Creation des DAO
        AventureDAO.Create(ds);
        BiographieDAO.Create(ds);
        EpisodeDAO.Create(ds);
        JoueurDAO.Create(ds);
        ParagrapheDAO.Create(ds);
        PersonnageDAO.Create(ds);
        UniversDAO.Create(ds);
    }
    
    /**
     * Récupère le joueur connecté.
     * 
     * @param request La requete
     * @return null si non connecté, le joueur sinon
     */
    public static Joueur GetJoueurSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Joueur joueur = null;

        if (session != null) {
            Object obj = session.getAttribute("user");
            
            if (obj != null)
                joueur = (Joueur)obj;
        }

        return joueur;
    }

    /* pages d'erreurs */
    protected static void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ctrlError.jsp").forward(request, response);
    }

    /* pages d'erreurs */
    protected static void invalidParameters(HttpServletRequest request,
            HttpServletResponse response, Exception e) throws ServletException, IOException {
        request.setAttribute("error", e.getMessage());
        response.setStatus(500);
        invalidParameters(request, response);
    }

    protected static void dbError(HttpServletRequest request,
            HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/dbError.jsp").forward(request, response);
    }

    /**
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * getOuvrage.
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

        String page = "accueil";

        if (request.getParameter("login") != null) {
            page = "login";
        }
        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
        }


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

        String login = request.getParameter("nickname");
        String pass = request.getParameter("password");
        String page = "login";
        Joueur joueur;

        if ((joueur = isLoginValid(login, pass)) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", joueur);

            page = "accueil";
        }
        else {
            request.setAttribute("error", "Login incorrect");
        }

        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
    }

    private Joueur isLoginValid(String login, String pwd) {

        try {
            byte[] digest;
            Joueur joueur;
            String data, hash;
            MessageDigest mdAlg;
            
            joueur = JoueurDAO.Get().getJoueur(login);

            if (joueur != null) {
                mdAlg = MessageDigest.getInstance("MD5");
                mdAlg.update(pwd.getBytes());

                digest = mdAlg.digest();
                StringBuilder hashBuilder = new StringBuilder();

                for (int i = 0; i < digest.length; i++) {
                    data = Integer.toHexString(0xFF & digest[i]);

                    if (data.length() < 2) {
                        data = "0" + data;
                    }

                    hashBuilder.append(data);
                }

                hash = hashBuilder.toString();

                //System.out.println("md5(" + pwd + ") == " + hash);
                //System.out.println("joueur.getPwd() == " + joueur.getPwd());


                if (joueur.getPwd().equals(hash)) {
                    return joueur;
                }
            }
        }
        catch (DAOException | NoSuchAlgorithmException e) {
            System.out.println("Error : " + e.getMessage());
        }
        
        return null;
    }
}


