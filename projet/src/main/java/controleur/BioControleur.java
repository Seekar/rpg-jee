/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.BiographieDAO;
import dao.EpisodeDAO;
import dao.ParagrapheDAO;
import dao.PersonnageDAO;
import java.io.IOException;
import java.rmi.ServerError;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Biographie;
import modele.Episode;
import modele.Joueur;
import modele.Paragraphe;
import modele.Personnage;

/**
 *
 * @author plouviej
 */
@WebServlet(name = "BioControleur", urlPatterns = {"/biographie"})
public class BioControleur extends HttpServlet{
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
        if(request.getParameter("action").equals("afficher")){
            /*//TEST
            Personnage p = new Personnage("essai",null , null, null, null);
            Biographie b = new Biographie(0, "");
            p.setBiographie(b);
            Episode e;
            b.episodes.add(e = new Episode(35, true, null, null, b));
            Paragraphe pa;
            e.paragraphes.add(pa =new Paragraphe());
            pa.setTexte("ceci n'est qu'un essai");
            pa.setSecret(false);*/
            int persoID = Integer.parseInt(request.getParameter("id"));
            PersonnageDAO persoD = PersonnageDAO.Get();
            BiographieDAO bioD = BiographieDAO.Get();
            EpisodeDAO epiD = EpisodeDAO.Get();
            ParagrapheDAO paD = ParagrapheDAO.Get();
            try{
            Personnage p = persoD.getPersonnage(persoID);
            Joueur j = (Joueur)request.getSession().getAttribute("user");
            p.setBiographie(bioD.getBiographie(p));
            p.getBiographie().episodes = epiD.getEpisodes(p.getBiographie());
            for(Episode e : p.getBiographie().episodes){
                e.paragraphes = paD.getParagraphes(e);
            }
            request.setAttribute("perso", p);
            request.setAttribute("owner", p.getJoueur().getId() == j.getId());
            request.getRequestDispatcher("/WEB-INF/Biographie/consultBio.jsp").forward(request, response);
            }catch(Exception e){
                //Aficher la page d'erreur BD
                throw new ServerException(null, e);
            }
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
        String action = request.getParameter("action");
        if(action.equals("reveler")){
            int pid = Integer.parseInt(request.getParameter("paragID"));
            //DAO get paragraphe
            ParagrapheDAO paD = ParagrapheDAO.Get();
            try{
            Paragraphe p = paD.getParagraphe(pid);
            
            request.setAttribute("parag", p);
            request.getRequestDispatcher("/WEB-INF/Paragraphe/Reveler.jsp").forward(request, response);
            }catch(Exception e){
                throw new ServletException(null,e);
            }
        }else if(action.equals("edition")){
            int idPerso = Integer.parseInt(request.getParameter("perso"));
            //faire la requete SQL
            
            //POUR LE TEST UNIQUEMENT
            Personnage p = new Personnage("essai",null , null, null, null);
            Biographie b = new Biographie(0, "");
            p.setBiographie(b);
            Episode e;
            b.episodes.add(e = new Episode(35, false, null, null, b));
            Paragraphe pa;
            e.paragraphes.add(pa =new Paragraphe());
            pa.setTexte("ceci n'est qu'un essai en édition");
            pa.setSecret(false);
            b.episodes.add(e = new Episode(37,false, null, null, b));
            e.paragraphes.add(pa =new Paragraphe());
            pa.setTexte("ceci n'est qu'un essai secret en édition");
            pa.setSecret(true);
            
            //A GARDER
            request.setAttribute("perso", p);
            request.getRequestDispatcher("/WEB-INF/Biographie/EditionBio.jsp").forward(request, response);
        }

    }
}
