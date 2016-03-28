/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Episode;
import modele.Joueur;

/**
 *
 * @author plouviej
 */
public final class AventureDAO extends AbstractAventureDAO {
    
    static private AventureDAO instance;
    
    private AventureDAO(DataSource ds) {
        super(ds);
    }
    
    public static AventureDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new AventureDAO(ds);
        }

        return instance;
    }

    public static AventureDAO Get() {
        return instance;
    }

    @Override
    public void editPartie(Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void creerPartie(Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aventure> getPartiesEnCours(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aventure getAventureAssociee(Episode e) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aventure> getPartiesMenees(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
