/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Episode;
import modele.Joueur;

/**
 *
 * @author plouviej
 */
public final class EpisodeDAO extends AbstractEpisodeDAO {

    static private EpisodeDAO instance;
    
    private EpisodeDAO(DataSource ds) {
        super(ds);
    }
    
    public static EpisodeDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new EpisodeDAO(ds);
        }

        return instance;
    }

    public static EpisodeDAO Get() {
        return instance;
    }
    
    @Override
    public Collection<Episode> getEpisodesEnEdition(Biographie b) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Episode> getEpisodes(Biographie b) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Episode> getEpisodesAValider(Joueur mj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
