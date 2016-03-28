/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Joueur;
import modele.Personnage;

/**
 *
 * @author plouviej
 */
public abstract class AbstractPersonnageDAO extends AbstractDAO {

    public AbstractPersonnageDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Collection<Personnage> getPersonnagesJoueur(Joueur j)throws DAOException;
    
    public abstract Collection<Personnage> getPersonnagesAValider(Joueur j)throws DAOException;
    
    public abstract Personnage getPersonnageAssocié(Joueur j, Aventure a)throws DAOException;
    
    public abstract Collection<Personnage> getPersonnageMené(Joueur j)
 throws DAOException;
    
    public abstract Collection<Personnage> getTransfertsAValider(Joueur j) throws
            DAOException;
    
    
}
