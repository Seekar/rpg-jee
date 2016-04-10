package controleur;

import dao.DAOException;
import dao.ParagrapheDAO;
import java.io.*;
import java.rmi.ServerException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modele.*;

/**
 * Contrôleur de paragraphes.
 */
@WebServlet(name = "ParagrapheCtrl", urlPatterns = {"/paragraphe"})
public class ParagrapheCtrl extends HttpServlet {

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

        switch (action) {
        case "edit": {
            try {
                int paragid = Integer.parseInt(request.getParameter("ID"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                
                Main.CheckOwnerOrMj(persoID, request);

                ParagrapheDAO pad = ParagrapheDAO.Get();
                Paragraphe p = pad.getParagraphe(paragid);

                request.setAttribute("parag", p);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/Paragraphe/Editparagraphes.jsp").forward(request, response);

            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }

        case "new": {
            try {
                int eid = Integer.parseInt(request.getParameter("eID"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                Main.CheckOwnerOrMj(persoID, request);
                request.setAttribute("eID", eid);
                request.setAttribute("persoID", persoID);
                request.getRequestDispatcher("/WEB-INF/Paragraphe/NewParagraphe.jsp").forward(request, response);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
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

        switch (action) {
        case "reveler": {
            try {
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                if (request.getParameter("res").equals("oui")) {
                    int pid = Integer.parseInt(request.getParameter("pID"));
                    Main.CheckOwnerOrMj(persoID, request);

                    ParagrapheDAO pad = ParagrapheDAO.Get();
                    pad.reveleParagraphe(pid);
                }

                response.sendRedirect("biographie?action=afficher&id=" + persoID);

            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        
        case "new": {
            boolean secret = request.getParameter("secret") != null;
            String texte = request.getParameter("texte");
            
            try {
                int persoID = Integer.parseInt(request.getParameter("persoID"));
                int episode = Integer.parseInt(request.getParameter("episodeID"));

                Main.CheckOwnerOrMj(persoID, request);

                ParagrapheDAO pad = ParagrapheDAO.Get();
                pad.ajouteParagraphe(secret, texte, episode);

                response.sendRedirect("biographie?action=afficher&id=" + persoID);

            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        
        case "edit": {
            String texte = request.getParameter("texte");
            
            try {
                int pid = Integer.parseInt(request.getParameter("id"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                Main.CheckOwnerOrMj(persoID, request);

                ParagrapheDAO pad = ParagrapheDAO.Get();
                pad.updateParagraphe(pid, texte);

                response.sendRedirect("biographie?action=afficher&id=" + persoID);

            } catch (DAOException e) {
                Main.dbError(request, response, e);

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            
            break;
        }
        }
    }
}
