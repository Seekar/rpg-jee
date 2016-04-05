package controleur;

import dao.EpisodeDAO;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.*;

/**
 * Contrôleur d'episodes.
 */
@WebServlet(name = "EpisodeCtrl", urlPatterns = {"/episode"})
public class EpisodeCtrl extends HttpServlet {

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
       String action = (request.getParameter("action")!= null ? request.getParameter("action") : "");
       if(action.equals("edit")){
           int epID = Integer.parseInt(request.getParameter("id"));
           EpisodeDAO ed = EpisodeDAO.Get();
           //requete DAO
          
           Episode e = new Episode(0, 35, false, null, null, null);
           Paragraphe p = new Paragraphe();
           p.setTexte("essai .....");
           e.paragraphes.add(p);
           request.setAttribute("episode", e);
           request.getRequestDispatcher("/WEB-INF/episode/EditionEpisode.jsp").forward(request, response);
       }else if(action.equals("suppr")){
           int epID = Integer.parseInt(request.getParameter("id"));
           //requete DAO
           
           Episode e = new Episode(0, 35, false, null, null, null);
           Paragraphe p = new Paragraphe();
           p.setTexte("essai .....");
           e.paragraphes.add(p);
           request.setAttribute("episode", e);
           request.getRequestDispatcher("/WEB-INF/episode/Supprimer.jsp").forward(request, response);
           
       }else if(action.equals("valider")){
           int epID = Integer.parseInt(request.getParameter("id"));
           //requete DAO
           
           Episode e = new Episode(0, 35, false, null, null, null);
           Paragraphe p = new Paragraphe();
           p.setTexte("essai .....");
           e.paragraphes.add(p);
           request.setAttribute("episode", e);
           request.getRequestDispatcher("/WEB-INF/episode/Valider.jsp").forward(request, response);
           
       }else if(action.equals("new")){
           int bioID = Integer.parseInt(request.getParameter("bioID"));
           
           //DAO : liste des aventures
           
           LinkedList<Aventure> l = new  LinkedList<>();
           Aventure a;
           l.add(a =new Aventure(0));
           a.setTitre("avt essai");
           
           request.setAttribute("aventures", l);
           request.setAttribute("bioID", bioID);
           request.getRequestDispatcher("/WEB-INF/episode/NouvelEpisode.jsp").forward(request, response);
           
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

    }
}


