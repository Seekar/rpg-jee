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
public abstract  class AbstractBiographieDAO extends AbstractDAO {

    public AbstractBiographieDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Biographie getBiographie(Personnage p) throws DAOException;
    
    
    
}
