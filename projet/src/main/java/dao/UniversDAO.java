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
import javax.sql.DataSource;
import modele.Aventure;
import modele.Personnage;
import modele.Univers;

/**
 *
 * @author plouviej
 */
public final class UniversDAO extends AbstractUniversDAO {

    private static UniversDAO instance;
    
    private UniversDAO(DataSource ds) {
        super(ds);
    }
    
    public static UniversDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new UniversDAO(ds);
        }

        return instance;
    }

    public static UniversDAO Get() {
        return instance;
    }
    
    @Override
    public Univers getUnivers(Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Univers getUnivers(Personnage p) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Univers> getUnivers() throws DAOException {
        ArrayList<Univers> univers = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom FROM Univers");
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                univers.add(new Univers(Integer.parseInt(res.getString("id")),
                                        res.getString("nom")));
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des univers : " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return univers;
    }
    
}
