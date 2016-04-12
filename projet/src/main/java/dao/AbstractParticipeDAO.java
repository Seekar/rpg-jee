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
    
    public abstract void creerParticipe(Participe p) throws DAOException;
    
    public abstract void supprimerParticipe(Aventure aventure, Personnage perso) throws DAOException;

    
}
