package controleur;

import dao.AventureDAO;
import dao.DAOException;
import dao.EpisodeDAO;
import dao.ParagrapheDAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modele.*;

/**
 * Contrôleur d'episodes
 * 
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
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
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);

        // Force le login et gère les erreurs
        if (Main.badRequest(request, response)) {
            return;
        }

        EpisodeDAO ed = EpisodeDAO.Get();
        ParagrapheDAO pad = ParagrapheDAO.Get();

        switch (action) {

        // Affichage de l'édition d'un épisode
        case "edit": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                // Check sécurité
                Main.CheckOwnerOrMj(persoID, request);
                
                // Récupération de l'épisode
                Episode e = ed.getEpisode(epID);
                e.paragraphes = pad.getParagraphes(e);
                
                request.setAttribute("episode", e);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/episode/EditionEpisode.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            break;
        }

        // Affichage de la page de suppression d'un épisode
        case "suppr": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
            
                // Check sécurité
                Main.CheckOwnerOrMj(persoID, request);
                
                // Récupération de l'épisode
                Episode e = ed.getEpisode(epID);
                e.paragraphes = pad.getParagraphes(e);
                
                request.setAttribute("episode", e);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/episode/Supprimer.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            break;
        }
        
        // Affichage de la page de validation d'un épisode
        case "valider": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                // Check sécurité
                Main.CheckOwnerOrMj(persoID, request);
                
                // Récupération de l'épisode
                Episode e = ed.getEpisode(epID);
                e.paragraphes = pad.getParagraphes(e);
                
                request.setAttribute("episode", e);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/episode/Valider.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }

        case "validationList": {
            try {
                List<Episode> epi = ed.getEpisodesAValider(user);

                // Récupératon des paragraphes d'épisodes à valider
                for (Episode e : epi) {
                    e.paragraphes = pad.getParagraphes(e);
                }

                request.setAttribute("episodes", epi);
                request.getRequestDispatcher("/WEB-INF/episode/AValider.jsp").forward(request, response);

            } catch (Exception e) {
                Main.dbError(request, response, e);
            }

            break;
        }

        // Affichage du formulaire de creation d'un épisode
        case "new":
            try {
                int bioID = Integer.parseInt(request.getParameter("bioID"));
                int pid = Integer.parseInt(request.getParameter("pid"));

                // Check sécurité
                Main.CheckOwnerOrMj(pid, request);

                // Récupératon de l'aventure associée
                AventureDAO ad = AventureDAO.Get();
                List<Aventure> l = ad.getAventureAssociee(pid);
                
                request.setAttribute("aventures", l);
                request.setAttribute("bioID", bioID);
                request.setAttribute("persoID", pid);
                request.getRequestDispatcher("/WEB-INF/episode/NouvelEpisode.jsp").forward(request, response);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
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
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);

        // Force le login et gère les erreurs
        if (Main.badRequest(request, response)) {
            return;
        }
        
        EpisodeDAO ed = EpisodeDAO.Get();

        switch (action) {

        // Validation de la suppression d'un épisode
        case "validesuppr": {
            try {
                int idBio = Integer.parseInt(request.getParameter("idBio"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                int pid = Integer.parseInt(request.getParameter("pID"));

                // Check sécurité
                Main.CheckOwnerOrMj(persoID, request);
                
                // Requete SQL
                ed.suppressEpisode(pid);
                
                response.sendRedirect("biographie?action=edition&persoID="
                        + persoID + "&biographie=" + idBio);

            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }

        // Demande de validation d'un épisode
        case "validate": {
            try {
                int eid = Integer.parseInt(request.getParameter("pID"));
                int idBio = Integer.parseInt(request.getParameter("idBio"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                
                // Check sécurité
                Main.CheckOwnerOrMj(persoID, request);
                
                // Si pas de MJ, impossible de demander validation
                if (ed.hasMJ(persoID)) {
                    
                    // Requete SQL
                    ed.valideEpisode(eid, persoID, user.getId());
                    
                    response.sendRedirect("biographie?action=edition&persoID="
                            + persoID + "&biographie=" + idBio);
                }
                else {
                    throw new Exception("Pour faire valider un épisode, "
                            + "il faut d'abord faire valider le personnage");
                }
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        
        // Création d'un épisode
        case "new": {
            try {
                int date = Integer.parseInt(request.getParameter("date"));
                int idBio = Integer.parseInt(request.getParameter("IDbio"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                String avt = request.getParameter("aventure");
                
                Main.CheckOwnerOrMj(persoID, request);
                
                if (avt.equals("__NONE__")) {
                    ed.nouvelEpisode(false, 0, idBio, date);

                } else {
                    int avent = Integer.parseInt(avt);
                    ed.nouvelEpisode(true, avent, idBio, date);
                }
                
                response.sendRedirect("biographie?action=edition&persoID="
                        + persoID + "&biographie=" + idBio);
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        
        // Validation d'un épisode par un MJ
        case "validerParMJ":
            
            try {
                int epID = Integer.parseInt(request.getParameter("eID"));
                
                // Requete SQL avec vérifications de sécurité intégrées
                ed.valideEpisodeParMj(epID, user.getId());
                
                response.sendRedirect("episode?action=validationList");
            
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
    }
}
