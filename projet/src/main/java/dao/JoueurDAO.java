package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.*;

/**
 * Singleton du DAO d'accès aux joueurs
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class JoueurDAO extends AbstractJoueurDAO {

    /**
     * Le singleton
     */
    static private JoueurDAO instance;

    /**
     * Constructeur privé du singleton
     */
    private JoueurDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Crée le singleton
     *
     * @param ds Le datasource d'accès bdd
     * @return Le singleton
     */
    public static JoueurDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new JoueurDAO(ds);
        }

        return instance;
    }

    /**
     * Getter du singleton
     *
     * @return Le singleton
     */
    public static JoueurDAO Get() {
        return instance;
    }

    @Override
    public Joueur getJoueur(int id) throws DAOException {
        Joueur joueur = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT pseudo, pwd FROM Joueur where id = ?");
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (!res.next())
                throw new Exception("Aucun joueur d'id " + id);

            joueur = new Joueur(id, res.getString("pseudo"), res.getString("pwd"));

        } catch (Exception e) {
            throw new DAOException("Erreur bdd " + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return joueur;
    }

    @Override
    public Joueur getJoueur(String pseudo) throws DAOException {
        Joueur joueur = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, pwd FROM Joueur where pseudo = ?");
            statement.setString(1, pseudo);
            ResultSet rs = statement.executeQuery();

            if (!rs.next())
                throw new Exception("Aucun joueur de pseudo " + pseudo);

            joueur = new Joueur(rs.getInt("id"), pseudo, rs.getString("pwd"));

        } catch (Exception e) {
            throw new DAOException("Erreur bdd " + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return joueur;
    }

    public ArrayList<Joueur> whoCanReceive(int idPerso) throws DAOException {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        PreparedStatement statement = null;
        Connection link = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT j.id, pseudo "
                    + "FROM Joueur j JOIN Personnage p on p.joueur_id != j.id "
                    + "WHERE p.id = ? AND p.mj_id != j.id ORDER BY pseudo");
            
            statement.setInt(1, idPerso);
            ResultSet rs = statement.executeQuery();
            Joueur joueur;

            while (rs.next()) {
                joueur = new Joueur();
                joueur.setId(rs.getInt("id"));
                joueur.setPseudo(rs.getString("pseudo"));

                joueurs.add(joueur);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des receveurs "
                    + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return joueurs;
    }
    
    @Override
    public ArrayList<Joueur> getAutresMeneurs(int idJoueur) throws DAOException {
        ArrayList<Joueur> meneurs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT distinct j.id, j.pseudo "
                    + "FROM Joueur j join Aventure a on j.id = a.mj_id "
                    + "WHERE j.id != ? ORDER BY pseudo");
            
            statement.setInt(1, idJoueur);
            ResultSet res = statement.executeQuery();
            Joueur meneur;

            while (res.next()) {
                meneur = new Joueur();
                meneur.setId(res.getInt("id"));
                meneur.setPseudo(res.getString("pseudo"));
                
                meneurs.add(meneur);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des meneurs "
                    + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return meneurs;
    }
}
