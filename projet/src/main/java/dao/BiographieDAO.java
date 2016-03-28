/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.sql.DataSource;
import modele.Biographie;
import modele.Personnage;

/**
 *
 * @author plouviej
 */
public final class BiographieDAO extends AbstractBiographieDAO {

    private static BiographieDAO instance;
    
    private  BiographieDAO(DataSource ds) {
        super(ds);
    }
    
    public static BiographieDAO Create(DataSource ds) {
        if(instance == null)
            instance = new BiographieDAO(ds);
        
        return instance;
    }
    
    public static BiographieDAO Get() {
        return instance;
    }

    @Override
    public Biographie getBiographie(Personnage p) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
