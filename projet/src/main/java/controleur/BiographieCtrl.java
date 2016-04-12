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
        if (Main.badRequest(request, response)) {
            return;
        }

        switch (action) {
        case "afficher": { //affichage de la biographie d'un personnage
            try {
                int persoID = Integer.parseInt(request.getParameter("id"));
                PersonnageDAO persoD = PersonnageDAO.Get();
                BiographieDAO bioD = BiographieDAO.Get();
                EpisodeDAO epiD = EpisodeDAO.Get();
                ParagrapheDAO paD = ParagrapheDAO.Get();

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

            } catch (Exception e) {
                Main.invalidParameters(request, response, e);
            }
            break;
        }
        
        case "edition": { //afichage de la vue édition d'une biographie
            
            try {
                int bioID = Integer.parseInt(request.getParameter("biographie"));
                int persoID = Integer.parseInt(request.getParameter("persoID"));

                PersonnageDAO persoD = PersonnageDAO.Get();
                BiographieDAO bioD = BiographieDAO.Get();
                EpisodeDAO epiD = EpisodeDAO.Get();
                ParagrapheDAO paD = ParagrapheDAO.Get();
            
                Main.CheckOwnerOrMj(persoID, request);

                Personnage p = persoD.getPersonnage(persoID);
                p.setBiographie(bioD.getBiographie(bioID));
                p.getBiographie().episodes = epiD.getEpisodesEnEdition(p.getBiographie());
                
                for (Episode e : p.getBiographie().episodes) {
                    e.paragraphes = paD.getParagraphes(e);
                }

                request.setAttribute("perso", p);
                request.getRequestDispatcher("/WEB-INF/Biographie/EditionBio.jsp").forward(request, response);

            } catch (DAOException e) {
                Main.dbError(request, response, e);

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
        
        Main.invalidParameters(request, response);
        
    }
}
