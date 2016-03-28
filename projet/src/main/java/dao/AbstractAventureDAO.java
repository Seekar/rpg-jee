/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.sql.DataSource;
import modele.Aventure;
import modele.Episode;
import modele.Joueur;

/**
 *
 * @author plouviej
 */
public abstract class AbstractAventureDAO extends AbstractDAO
{

    public AbstractAventureDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Aventure getPartiesEnCours(Joueur j) throws DAOException;
    
    public abstract Aventure getAventureAssociée(Episode e) throws DAOException;
    
    public abstract Aventure getPartieMenée(Joueur j) throws DAOException;
    
    public abstract void editPartie(Aventure a) throws DAOException;
    
    public abstract void creerPartie(Aventure a) throws DAOException;
    
}
