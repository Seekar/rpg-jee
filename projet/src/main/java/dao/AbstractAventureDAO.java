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
import modele.Personnage;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractAventureDAO extends AbstractDAO
{

    public AbstractAventureDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract List<Aventure> getPartiesEnCours(Joueur j) throws DAOException;
    
    public abstract Aventure getAventureAssociee(Episode e) throws DAOException;
    
    public abstract List<Aventure> getPartiesMenees(Joueur j) throws DAOException;
    
    public abstract void editPartie(Aventure a) throws DAOException;
    
    public abstract void creerPartie(Aventure a) throws DAOException;
    
    public abstract List<Aventure> getAventureAssociee(int persoID) throws DAOException;
 
    public abstract Aventure getAventure(int id) throws DAOException;
    
    public abstract void finishPartie(Aventure aventure, String events) throws DAOException;
    public abstract void deletePartie(Aventure aventure) throws DAOException;
    
}
