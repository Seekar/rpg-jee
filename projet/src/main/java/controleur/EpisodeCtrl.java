package controleur;

import dao.AventureDAO;
import dao.DAOException;
import dao.EpisodeDAO;
import dao.ParagrapheDAO;
import java.io.*;
import java.rmi.ServerException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)
                || action == null) {
            return;
        }

        EpisodeDAO ed = EpisodeDAO.Get();
        ParagrapheDAO pad = ParagrapheDAO.Get();

        switch (action) {
        case "edit": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                Main.CheckOwnerOrMj(persoID, request);
                
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
        
        case "suppr": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
            
                Main.CheckOwnerOrMj(persoID, request);
                
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
        
        case "valider": {
            try {
                int epID = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                Main.CheckOwnerOrMj(persoID, request);
                
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

        case "new":
            try {
                int bioID = Integer.parseInt(request.getParameter("bioID"));
                int pid = Integer.parseInt(request.getParameter("pid"));

                AventureDAO ad = AventureDAO.Get();
            
                Main.CheckOwnerOrMj(pid, request);
                
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
        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);

        // Force le login et gère les erreurs
        if (Main.notLogged(request, response)
                || action == null) {
            return;
        }
        
        EpisodeDAO ed = EpisodeDAO.Get();

        switch (action) {
        case "validesuppr": {
            try {
                int idBio = Integer.parseInt(request.getParameter("idBio"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                if (request.getParameter("res").equals("oui")) {
                    int pid = Integer.parseInt(request.getParameter("pID"));
                    
                    Main.CheckOwnerOrMj(persoID, request);
                    ed.suppressEpisode(pid);
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

        case "validevalid": {
            try {
                int idBio = Integer.parseInt(request.getParameter("idBio"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                Main.CheckOwnerOrMj(persoID, request);
                
                if (request.getParameter("res").equals("oui")) {
                    int eid = Integer.parseInt(request.getParameter("pID"));
                    boolean hasmj = ed.hasMJ(persoID);
                    if(hasmj)
                        ed.valideEpisode(eid, persoID, user.getId());
                    else{
                        request.setAttribute("persoID", persoID);
                        request.setAttribute("biographie", idBio);
                        request.getRequestDispatcher("/WEB-INF/episode/pasdeMJ.jsp").forward(request, response);
                        return;
                    }
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
        
        case "validerParMJ":
            
            try {
                int epID = Integer.parseInt(request.getParameter("eID"));

                if (request.getParameter("res").equals("oui")) {
                    ed.valideEpisodeParMj(epID, user.getId());
                }
                
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
