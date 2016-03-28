/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import modele.*;

/**
 *
 * @author reysi
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
        PreparedStatement statement;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT pseudo, pwd FROM Joueur where id = ?");
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (!res.next())
                throw new Exception("Aucun joueur d'id " + id);

            joueur = new Joueur(id, res.getString("pseudo"), res.getString("pwd"));
            statement.close();

        } catch (Exception e) {
            throw new DAOException("Erreur bdd " + e.getMessage(), e);

        } finally {
            closeConnection(link);
        }

        return joueur;
    }

    @Override
    public Joueur getJoueur(String pseudo) throws DAOException {
        Joueur joueur = null;
        Connection link = null;
        PreparedStatement statement;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, pwd FROM Joueur where pseudo = ?");
            statement.setString(1, pseudo);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);

            if (!rs.next())
                throw new Exception("Aucun joueur de pseudo " + pseudo);

            joueur = new Joueur(rs.getInt("id"), pseudo, rs.getString("pwd"));
            statement.close();

        } catch (Exception e) {
            throw new DAOException("Erreur bdd " + e.getMessage(), e);

        } finally {
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
    
}
