package dao;

import javax.sql.DataSource;
import modele.Participe;
import modele.Personnage;
import modele.Aventure;

/**
 * Classe abstraite du DAO d'accès aux participations
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract  class AbstractParticipeDAO extends AbstractDAO {

    public AbstractParticipeDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * Ajoute un personnage à une partie en cours
     * avec vérification des droits.
     *
     * @param p La participation à ajouter
     * @throws DAOException
     */
    public abstract void creerParticipe(Participe p) throws DAOException;
    
    /**
     * Retire un participant d'une partie en cours.
     * 
     * @param aventure La partie concernée
     * @param perso    Le participant
     * @throws DAOException
     */
    public abstract void supprimerParticipe(Aventure aventure, Personnage perso) throws DAOException;
    
}
