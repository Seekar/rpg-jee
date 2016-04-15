package dao;

import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Joueur;
import modele.Personnage;

/**
 * Classe abstraite du DAO d'accès aux aventures
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractAventureDAO extends AbstractDAO {

    public AbstractAventureDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract List<Aventure> getPartiesMenees(Joueur j) throws DAOException;
    
    public abstract void creerPartie(Aventure a) throws DAOException;
    
    public abstract Collection<Aventure> getAventures() throws DAOException;
    
    public abstract List<Aventure> getAventureAssociee(int persoID) throws DAOException;
 
    public abstract Aventure getAventure(int id) throws DAOException;
    
    public abstract Collection<Aventure> getParties(Personnage p) throws DAOException;
    
    public abstract Collection<Aventure> getParties(Joueur j) throws DAOException;
    
    public abstract void finishPartie(Aventure aventure, String events) throws DAOException;

    public abstract void deletePartie(Aventure aventure) throws DAOException;
    
}
