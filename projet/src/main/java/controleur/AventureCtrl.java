package controleur;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Contrôleur d'aventures.
 */
@WebServlet(name = "AventureCtrl", urlPatterns = {"/game"})
public class AventureCtrl extends HttpServlet {

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

    }
}


