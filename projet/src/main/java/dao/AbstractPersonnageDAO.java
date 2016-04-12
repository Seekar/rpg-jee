package dao;

import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Joueur;
import modele.Personnage;
import modele.Univers;

/**
 * Classe abstraite du DAO d'accès aux personnages
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractPersonnageDAO extends AbstractDAO {

    public AbstractPersonnageDAO(DataSource ds) {
        super(ds);
    }


    /**
     * Récupère la liste de tous les personnages classés par nom
     *
     * @return La liste de tous les personnages
     * @throws DAOException
     */
    public abstract Collection<Personnage> getPersonnages()
            throws DAOException;

    /**
     * Récupère la liste de tous les personnages
     * du joueur demandé classés par nom
     *
     * @param  j Le joueur
     * @return La liste des personnages
     * @throws DAOException
     */    
    public abstract Collection<Personnage> getPersonnagesJoueur(Joueur j)
            throws DAOException;
    
    /**
     * Récupère la liste des personnages à valider
     * classés par nom pour le joueur demandé
     * 
     *
     * @param  j Le joueur
     * @return La liste des personnages
     * @throws DAOException
     */
    public abstract Collection<Personnage> getPersonnagesAValider(Joueur j)
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

    public abstract void requestTransfer(int idPerso, int idMJ, int idUser)
            throws DAOException;

    public abstract void acceptValidation(int idPerso, int idUser)
            throws DAOException;

    public abstract void acceptTransfer(int idPerso, int idUser)
            throws DAOException;
    
    public abstract void modifierPersonnage(Personnage p, int idUser)
            throws DAOException;
    
    public abstract void donnerPersonnage(int idPerso, int idDest, int idUser)
            throws DAOException;

    public abstract boolean dansPartieEnCours(int idPerso)
            throws DAOException;
    
    public abstract Collection<Personnage> getCandidats(Joueur mj, Univers u)
            throws DAOException;
}
