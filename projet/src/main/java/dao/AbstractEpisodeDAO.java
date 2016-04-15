package dao;

import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Episode;
import modele.Joueur;

/**
 * Classe abstraite du DAO d'accès aux épisodes
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractEpisodeDAO extends AbstractDAO{

    public AbstractEpisodeDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Retourne les épisodes valides d'une biographie.
     * 
     * @param b La biographie
     * @return Ses épisodes valides
     * @throws DAOException
     */
    public abstract List<Episode> getEpisodes(Biographie b) 
            throws DAOException;
    
    public abstract Collection<Episode> getEpisodesEnEdition(Biographie b) 
            throws DAOException;
    
    public abstract Collection<Episode> getEpisodesAValider(Joueur mj) 
            throws DAOException; 
    
    public abstract Episode getEpisode(int id) throws DAOException; 
    
    public abstract void suppressEpisode(int pid) throws DAOException;
    
    public abstract void valideEpisode(int pid, int persoID, int joueurID) throws DAOException;

    public abstract void valideEpisodeParMj(int idEpi, int idUser) throws DAOException;

    public abstract void nouvelEpisode(boolean avtValide, int aventureID, int bioID, int date) throws DAOException;
    
    public abstract boolean hasMJ(int persoID) throws DAOException;
}
