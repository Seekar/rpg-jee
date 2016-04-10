/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.BiographieDAO;
import dao.DAOException;
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
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
@WebServlet(name = "BiographieCtrl", urlPatterns = {"/biographie"})
public class BiographieCtrl extends HttpServlet {

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

        String action = request.getParameter("action");

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)
                || action == null) {
            return;
        }

        if (action.equals("afficher")) {
            int persoID = Integer.parseInt(request.getParameter("id"));
            PersonnageDAO persoD = PersonnageDAO.Get();
            BiographieDAO bioD = BiographieDAO.Get();
            EpisodeDAO epiD = EpisodeDAO.Get();
            ParagrapheDAO paD = ParagrapheDAO.Get();
            try {
                Personnage p = persoD.getPersonnage(persoID);
                p.setId(persoID);
                Joueur j = Main.GetJoueurSession(request);
                p.setBiographie(bioD.getBiographie(p));
                p.getBiographie().episodes = epiD.getEpisodes(p.getBiographie());
                for (Episode e : p.getBiographie().episodes) {
                    e.paragraphes = paD.getParagraphes(e);
                }
                request.setAttribute("perso", p);
                request.setAttribute("owner", p.getJoueur().getId() == j.getId() || p.getMj().getId() == j.getId());
                request.getRequestDispatcher("/WEB-INF/Biographie/consultBio.jsp").forward(request, response);
            } catch (DAOException e) {
                Main.dbError(request, response, e);
            }
        } else if (action.equals("edition")) {

            //faire la requete SQL
            int bioID = Integer.parseInt(request.getParameter("biographie"));
            int persoID = Integer.parseInt(request.getParameter("persoID"));
            
            PersonnageDAO persoD = PersonnageDAO.Get();
            BiographieDAO bioD = BiographieDAO.Get();
            EpisodeDAO epiD = EpisodeDAO.Get();
            ParagrapheDAO paD = ParagrapheDAO.Get();
            
            try {
                Main.CheckOwnerOrMj(persoID, request);
                
                Personnage p = persoD.getPersonnage(persoID);
                //Joueur j = Main.GetJoueurSession(request);
                p.setBiographie(bioD.getBiographie(bioID));
                p.getBiographie().episodes = epiD.getEpisodesEnEdition(p.getBiographie());
                for (Episode e : p.getBiographie().episodes) {
                    e.paragraphes = paD.getParagraphes(e);
                }
                
                request.setAttribute("perso", p);
                request.getRequestDispatcher("/WEB-INF/Biographie/EditionBio.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (SecurityException e) {
                Main.invalidParameters(request, response, e);
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

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)
                || action == null) {
            return;
        }

        if (action.equals("reveler")) {
            int pid = Integer.parseInt(request.getParameter("paragID"));
            int persoID = Integer.parseInt(request.getParameter("persoID"));
            
            ParagrapheDAO paD = ParagrapheDAO.Get();
            
            try {
                Main.CheckOwnerOrMj(persoID, request);
                
                Paragraphe p = paD.getParagraphe(pid);

                request.setAttribute("parag", p);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/Paragraphe/Reveler.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (SecurityException e) {
                Main.invalidParameters(request, response, e);
            }
        }
    }
}
