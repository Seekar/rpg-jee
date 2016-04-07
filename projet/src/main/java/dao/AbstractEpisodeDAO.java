/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Episode;
import modele.Joueur;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractEpisodeDAO extends AbstractDAO{

    public AbstractEpisodeDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract Collection<Episode> getEpisodesEnEdition(Biographie b) 
            throws DAOException;
    
    public abstract List<Episode> getEpisodes(Biographie b) 
            throws DAOException; 
    
    public abstract Collection<Episode> getEpisodesAValider(Joueur mj) 
            throws DAOException; 
    
    public abstract Episode getEpisode(int id) throws DAOException; 
    
    public abstract void suppressEpisode(int pid) throws DAOException;
    
    public abstract void valideEpisode(int pid, int persoID, int joueurID) throws DAOException;
    
    public abstract void nouvelEpisode(boolean avtValide, int aventureID, int bioID, int date) throws DAOException;
    
    
}
