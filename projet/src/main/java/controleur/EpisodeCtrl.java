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
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                if (request.getParameter("res").equals("oui")) {
                    int pid = Integer.parseInt(request.getParameter("pID"));
                    
                    Main.CheckOwnerOrMj(persoID, request);
                    ed.suppressEpisode(pid);
                    
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);

                } else {
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);
                }

            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }

        case "validevalid": {
            try {
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                Main.CheckOwnerOrMj(persoID, request);
                
                if (request.getParameter("res").equals("oui")) {
                    int eid = Integer.parseInt(request.getParameter("pID"));

                    ed.valideEpisode(eid, persoID, user.getId());
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);

                } else {
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);
                }
                
            } catch (DAOException e) {
                Main.dbError(request, response, e);
                
            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        
        case "new": {
            try {
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                String avt = request.getParameter("aventure");
                
                Main.CheckOwnerOrMj(persoID, request);
                
                if (avt.equals("__NONE__")) {
                    int idbio = Integer.parseInt(request.getParameter("IDbio"));
                    int date = Integer.parseInt(request.getParameter("date"));
                    
                    ed.nouvelEpisode(false, 0, idbio, date);
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);

                } else {
                    int avent = Integer.parseInt(avt);
                    int idbio = Integer.parseInt(request.getParameter("IDbio"));
                    int date = Integer.parseInt(request.getParameter("date"));

                    ed.nouvelEpisode(true, avent, idbio, date);
                    response.sendRedirect("biographie?action=afficher&id=" + persoID);
                }
                
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
