package controleur;

import dao.AventureDAO;
import dao.BiographieDAO;
import dao.DAOException;
import dao.EpisodeDAO;
import dao.JoueurDAO;
import dao.ParagrapheDAO;
import dao.ParticipeDAO;
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
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Le contrôleur d'application
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
        ParticipeDAO.Create(ds);
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
    
    /**
     * Lance une exception de sécurité si l'utilisateur
     * n'est ni propriétaire ni meneur du personnage indiqué.
     * 
     * @param persoID L'id du personnage
     * @param request La requete
     * @return null si non connecté, le joueur sinon
     * @throws SecurityException
     */
    public static void CheckOwnerOrMj(int persoID,
            HttpServletRequest request) throws SecurityException {
        Joueur user = Main.GetJoueurSession(request);
        PersonnageDAO persoD = PersonnageDAO.Get();
        SecurityException se = new SecurityException("Accès refusé");
        
        try {
            Personnage p = persoD.getPersonnage(persoID);
            
            if (p.getJoueur().getId() != user.getId()
                && p.getMj().getId() != user.getId()) {
                throw se;
            }
            
        } catch (Exception e) {
            throw se;
        }
    }

    /**
     * Vérifie la validité d'une requete.
     * Pour être valide il faut d'abord que l'utilisateur soit connecté,
     * mais également que la paramètre action soit défini.
     * 
     * @param request  La requete
     * @param response La réponse
     * @return true si la requete est valide, false sinon
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    protected static boolean badRequest(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)) {
            return true;
        }
        
        if (action == null) {
            invalidParameters(request, response);
            return true;
        }
        
        return false;
    }

    protected static boolean isLogged(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        return !notLogged(request, response);
    }

    protected static boolean notLogged(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean fail = GetJoueurSession(request) == null;

        if (fail) {
            response.sendRedirect(request.getContextPath() + "?login");
        }

        return fail;
    }

    /* pages d'erreurs */
    protected static void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(500);
        request.getRequestDispatcher("/WEB-INF/ctrlError.jsp").forward(request, response);
    }

    /* pages d'erreurs */
    protected static void invalidParameters(HttpServletRequest request,
            HttpServletResponse response, String mess)
            throws ServletException, IOException {
        request.setAttribute("error", mess);
        invalidParameters(request, response);
    }

    /* pages d'erreurs */
    protected static void invalidParameters(HttpServletRequest request,
            HttpServletResponse response, Exception ex)
            throws ServletException, IOException {
        invalidParameters(request, response, ex.getMessage());
    }

    static void dbError(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(500);
        request.getRequestDispatcher("/WEB-INF/dbError.jsp").forward(request, response);
    }

    static void dbError(HttpServletRequest request,
            HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        dbError(request, response, e.getMessage());
    }

    static void dbError(HttpServletRequest request,
            HttpServletResponse response, String mess)
            throws ServletException, IOException {
        request.setAttribute("error", mess);
        dbError(request, response);
    }


    /**
     * Requetes GET
     *
     * @param request  La requete
     * @param response La réponse
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
            
            response.sendRedirect(request.getContextPath() + "?login");
            
            return;
        }


        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
    }

    /**
     * Requetes POST
     *
     * @param request  La requete
     * @param response La réponse
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

        try {
            if ((joueur = isLoginValid(login, pass)) != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", joueur);

                page = "accueil";
            }
            else {
                request.setAttribute("error", "Login incorrect");
            }
            
        } catch (Exception e) {
            invalidParameters(request, response, e);
        }

        request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
    }

    private Joueur isLoginValid(String login, String pwd)
            throws SecurityException {

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


                if (joueur.getPwd().equals(hash)) {
                    return joueur;
                }
            }
        }
        catch (Exception e) {
            throw new SecurityException(e.getMessage(), e);
        }
        
        return null;
    }
    
    public static String CustomEscape(String data) {
        if (data == null)
            return null;
        
        return StringEscapeUtils.escapeHtml(data).replace("\n", "<br/>");
    }
}

