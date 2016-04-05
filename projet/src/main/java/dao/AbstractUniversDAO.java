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
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractUniversDAO extends AbstractDAO {

    public AbstractUniversDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Univers getUnivers(Aventure a) throws DAOException;
    public abstract Univers getUnivers(Personnage p) throws DAOException;
    
    public abstract Collection<Univers> getUnivers() throws DAOException;
    
    
}
