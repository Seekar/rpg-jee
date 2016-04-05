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
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractPersonnageDAO extends AbstractDAO {

    public AbstractPersonnageDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Collection<Personnage> getPersonnagesJoueur(Joueur j)
            throws DAOException;
    
    public abstract Collection<Personnage> getPersonnagesAValider(Joueur j)
            throws DAOException;
    
    public abstract Personnage getPersonnageAssocie(Joueur j, Aventure a)
            throws DAOException;
    
    public abstract Collection<Personnage> getPersonnagesMenes(Joueur j)
            throws DAOException;
    
    public abstract Collection<Personnage> getTransfertsAValider(Joueur j)
            throws DAOException;
    
    public abstract Personnage getPersonnage(int personnageID)
            throws DAOException;
    
    public abstract void creer(Personnage p, String bio)
            throws DAOException;

    public abstract void requestValidation(int idPerso, int idMJ, int idUser)
            throws DAOException;

    //public abstract void requestTransfer(int id, int mj) throws DAOException;

    public abstract void acceptValidation(int idPerso, int idUser) throws DAOException;

    //public abstract void acceptTransfer(int id) throws DAOException;

}
