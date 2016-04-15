package dao;

import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import modele.*;

/**
 * Classe abstraite du DAO d'accès aux joueurs
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractJoueurDAO extends AbstractDAO {

    public AbstractJoueurDAO(DataSource ds) {
        super(ds);
    }

    public abstract Joueur getJoueur(String pseudo) throws DAOException;

    public abstract Joueur getJoueur(int id) throws DAOException;

    /**
     * Récupère la liste des joueurs à qui
     * l'on peut céder un personnage donné.
     * 
     * @param idPerso Le personnage à céder
     * @return La liste des joueurs
     * @throws DAOException
     */
    public abstract Collection<Joueur> whoCanReceive(int idPerso) throws DAOException;
    
    /**
     * Récupère la liste des meneurs potentiels pour les personnages d'un joueur.
     * 
     * @param idJoueur Le joueur
     * @return La liste des meneurs potentiels
     * @throws DAOException
     */
    public abstract Collection<Joueur> getAutresMeneurs(int idJoueur) throws DAOException;
}
