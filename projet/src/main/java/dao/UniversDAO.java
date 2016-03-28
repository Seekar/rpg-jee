/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
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
    public Collection<Univers> getUnivers() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
