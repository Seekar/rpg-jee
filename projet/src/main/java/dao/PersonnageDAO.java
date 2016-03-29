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
import java.util.Collection;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Joueur;
import modele.Personnage;

/**
 *
 * @author plouviej
 */
public final class PersonnageDAO extends AbstractPersonnageDAO {
    
    private static PersonnageDAO instance;
    
    private PersonnageDAO(DataSource ds) {
        super(ds);
    }
    
    public static PersonnageDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new PersonnageDAO(ds);
        }

        return instance;
    }
    
    public static PersonnageDAO Get()
    {
        return instance;
    }

    @Override
    public Collection<Personnage> getPersonnagesJoueur(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Personnage> getPersonnagesAValider(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Personnage> getTransfertsAValider(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnage getPersonnageAssocie(Joueur j, Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Personnage> getPersonnagesMenes(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void creer(Personnage p, String bio) throws DAOException {
        Joueur joueur = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            link.setAutoCommit(false);
            link.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            
            statement = link.prepareStatement("INSERT INTO Biographie (texte) VALUES (?)");
            statement.setString(1, bio);
            statement.executeUpdate();
            
            statement = link.prepareStatement("INSERT INTO Personnage (naissance, nom, portrait, profession, joueur_id, " +
                                              "univers_id, biographie_id) VALUES (?, ?, ?, ?, ?, ?, bio_seq.currval)");

            statement.setString(1, p.getNaissance());
            statement.setString(2, p.getNom());
            statement.setString(3, p.getPortrait());
            statement.setString(4, p.getProfession());
            statement.setInt(5, p.getJoueur().getId());
            statement.setInt(6, p.getUnivers().getId());
            statement.executeUpdate();
            
            link.commit();

        } catch (Exception e) {
            if (link != null) {
                try {
                    link.rollback();
                } catch (SQLException ex) {}
            }
            
            throw new DAOException("Erreur bdd " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }
    }
    
}
