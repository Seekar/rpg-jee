package controleur;

import dao.AventureDAO;
import dao.EpisodeDAO;
import dao.ParagrapheDAO;
import java.io.*;
import java.rmi.ServerException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
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
        switch (action) {
            case "edit":
                {
                    int epID = Integer.parseInt(request.getParameter("id"));
                    EpisodeDAO ed = EpisodeDAO.Get();
                    //requete DAO
                    EpisodeDAO edd = EpisodeDAO.Get();
                    ParagrapheDAO pad =ParagrapheDAO.Get();
                    try{
                        Episode e = edd.getEpisode(epID);
                        e.paragraphes = pad.getParagraphes(e);
                        request.setAttribute("episode", e);
                        request.getRequestDispatcher("/WEB-INF/episode/EditionEpisode.jsp").forward(request, response);
                    }catch(Exception e){
                        throw new ServerException(null,e);
                    }        break;
                }
            case "suppr":
            {
                int epID = Integer.parseInt(request.getParameter("id"));
                    EpisodeDAO ed = EpisodeDAO.Get();
                    //requete DAO
                    EpisodeDAO edd = EpisodeDAO.Get();
                    ParagrapheDAO pad =ParagrapheDAO.Get();
                    try{
                        Episode e = edd.getEpisode(epID);
                        e.paragraphes = pad.getParagraphes(e);
                        request.setAttribute("episode", e);
                        request.getRequestDispatcher("/WEB-INF/episode/Supprimer.jsp").forward(request, response);
                    }catch(Exception e){
                        throw new ServerException(null,e);
                    }      break;  
            }
            case "valider":
            {
                int epID = Integer.parseInt(request.getParameter("id"));
                //requete DAO
                EpisodeDAO ed = EpisodeDAO.Get();
                ParagrapheDAO pad =ParagrapheDAO.Get();
                try{
                    Episode e = ed.getEpisode(epID);
                    e.paragraphes = pad.getParagraphes(e);
                    request.setAttribute("episode", e);
                    request.getRequestDispatcher("/WEB-INF/episode/Valider.jsp").forward(request, response);
                }catch(Exception e){
                    throw new ServerException(null,e);
                }        break;
            }
            case "new":
                int bioID = Integer.parseInt(request.getParameter("bioID"));
                int pid = Integer.parseInt(request.getParameter("pid"));
                //DAO : liste des aventures
                AventureDAO ad = AventureDAO.Get();
                try{
                List<Aventure> l = ad.getAventureAssociee(pid);
                request.setAttribute("aventures", l);
                request.setAttribute("bioID", bioID);
                request.getRequestDispatcher("/WEB-INF/episode/NouvelEpisode.jsp").forward(request, response);
                }catch(Exception e){
                    throw new ServerException(null,e);
                }
                break;
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


