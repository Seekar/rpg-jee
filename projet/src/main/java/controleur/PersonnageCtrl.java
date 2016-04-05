package controleur;

import dao.DAOException;
import dao.JoueurDAO;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.*;

/**
 * Contrôleur de personnages.
 */
@WebServlet(name = "PersonnageCtrl", urlPatterns = {"/character"})
public class PersonnageCtrl extends HttpServlet {


    // Actions possibles en GET : 
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
        
        String page = null;
        String action = request.getParameter("action");
        Joueur user = Main.GetJoueurSession(request);


        if (action.equals("create")) {
            page = "creation";
            
            UniversDAO universDAO = UniversDAO.Get();
            Collection<Univers> univers;
            
            try {
                univers = universDAO.getUnivers();
                request.setAttribute("listeUnivers", univers);
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
        }
        else if (action.endsWith("List")) {
            page = "liste";

            PersonnageDAO persoDAO = PersonnageDAO.Get();
            Collection<Personnage> persos = null;

            try {
                String titre = null;
                
                if (action.equals("ownedList")) {
                    persos = persoDAO.getPersonnagesJoueur(user);
                    titre = "Personnages possédés";
                }
                else if (action.equals("leaderList")) {
                    persos = persoDAO.getPersonnagesMenes(user);
                    titre = "Personnages menés";
                }
                else if (action.equals("transferList")) {
                    persos = persoDAO.getTransfertsAValider(user);
                    titre = "Demandes de transfert";
                }
                else if (action.equals("validationList")) {
                    persos = persoDAO.getPersonnagesAValider(user);
                    titre = "Personnages à valider";
                }

                request.setAttribute("titre", titre);
                request.setAttribute("persos", persos);
                
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
        }
        else if (action.equals("show")) {
            page = "affichage";

            PersonnageDAO persoDAO = PersonnageDAO.Get();
            Personnage perso;
            boolean canEdit = false;
            
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                perso = persoDAO.getPersonnage(id);
                
                if (perso.getJoueur().getId() == user.getId()
                    || perso.getMj().getId() == user.getId())
                    canEdit = true;

                request.setAttribute("canEdit", canEdit);
                request.setAttribute("perso", perso);
                
                Collection<Joueur> listeMJ = JoueurDAO.Get().getMeneurs();
                Collection<Joueur> listeTransfert = new ArrayList<>();
                
                
                Joueur toDelete = null;
                int mj_id = perso.getMj().getId();
                
                for (Joueur mj : listeMJ) {
                    if (mj.getId() == user.getId()) {
                        toDelete = mj;
                    }
                    else if (mj.getId() != mj_id) {
                        listeTransfert.add(mj);
                    }
                }

                if (toDelete != null)
                    listeMJ.remove(toDelete);
                
                
                request.setAttribute("listeMJ", listeMJ);
                request.setAttribute("listeTransfert", listeTransfert);
                
            } catch (NumberFormatException e) {
               Main.invalidParameters(request, response);
               
            } catch (DAOException e) {
               Main.dbError(request, response, e);
            }
        }
        else if (action.equals("editMJ")) {
            actionAcceptMJ(request, response);
        }
        else if (action.equals("transfer")) {
            actionAcceptTransfer(request, response);
        }

        if (page != null) {
            request.getRequestDispatcher("/WEB-INF/personnage/" + page + ".jsp").forward(request, response);
        }

        else if (request.getAttribute("done") == null) {
            Main.invalidParameters(request, response);
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

        if (action.equals("create")) {
            actionCreate(request, response);
        }
        else if (action.equals("editMJ")) {
            actionEditMJ(request, response);
        }
        else if (action.equals("transfer")) {
            actionTransfer(request, response);
        }
    }

    public void actionCreate(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            String naissance = request.getParameter("naissance");
            String bio = request.getParameter("biographie");
            String nom = request.getParameter("nom");
            String portrait = request.getParameter("portrait");
            String profession = request.getParameter("profession");
            
            int idUnivers = Integer.parseInt(request.getParameter("univers"));
            Univers univers = new Univers(idUnivers);
            
            Personnage perso = new Personnage(nom, naissance,
                    profession, portrait, univers);
            
            perso.setJoueur(Main.GetJoueurSession(request));
            PersonnageDAO.Get().creer(perso, bio);
            
            // Afficher les personnages possédés si la création a réussi
            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=ownedList");
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            doGet(request, response);
        }
    }

    public void actionEditMJ(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            PersonnageDAO.Get().requestValidation(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id="+idPerso);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Main.invalidParameters(request, response, ex.getMessage());
        }
    }

    public void actionAcceptMJ(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            PersonnageDAO.Get().acceptValidation(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);
            
            request.setAttribute("done", true);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex.getMessage());
        }
    }

    public void actionTransfer(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));

            PersonnageDAO.Get().requestTransfer(idPerso, idMJ, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id="+idPerso);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Main.invalidParameters(request, response, ex.getMessage());
        }
    }

    public void actionAcceptTransfer(HttpServletRequest request,
           HttpServletResponse response) throws IOException, ServletException {

        try {
            Joueur user = Main.GetJoueurSession(request);
            int idPerso = Integer.parseInt(request.getParameter("id"));

            PersonnageDAO.Get().acceptTransfer(idPerso, user.getId());

            response.sendRedirect(request.getContextPath()
                    + request.getServletPath() + "?action=show&id=" + idPerso);

            request.setAttribute("done", true);

        } catch (Exception ex) {
            Main.invalidParameters(request, response, ex.getMessage());
        }
    }
}

