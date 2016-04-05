/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class JoueurDAO extends AbstractJoueurDAO {

    static private JoueurDAO instance;

    private JoueurDAO(DataSource ds) {
        super(ds);
    }

    public static JoueurDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new JoueurDAO(ds);
        }

        return instance;
    }

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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return joueur;
    }

/*
    @Override
    public List<Joueur> getListeJoueurs() throws DAOException {
        //throw new UnsupportedOperationException("TODO : Requête SQL obtenir liste persos joueur.");
        List<Joueur> list = new LinkedList<>();
        Connection link = null;

        try {
            link = getConnection();
            Statement state = link.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM Joueur");

            while (result.next()) {
                Joueur joueur = new Joueur(rs.getString("pseudo"), rs.getString("pwd"));
                list.add(joueur);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur bd " + e.getMessage(), e);

        } finally {
            closeConnection(link);
        }

        return list;
    }*/
    
    @Override
    public ArrayList<Joueur> getMeneurs() throws DAOException {
        ArrayList<Joueur> meneurs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT distinct j.id, j.pseudo "
                    + "FROM Joueur j join Aventure a on j.id = a.mj_id");
            
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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return meneurs;
    }
}
