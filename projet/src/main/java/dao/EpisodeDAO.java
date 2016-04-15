package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Biographie;
import modele.Episode;
import modele.Joueur;

/**
 * Singleton du DAO d'accès aux épisodes
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class EpisodeDAO extends AbstractEpisodeDAO {

    /**
     * Le singleton
     */
    static private EpisodeDAO instance;

    /**
     * Constructeur privé du singleton
     */
    private EpisodeDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Crée le singleton
     *
     * @param ds Le datasource d'accès bdd
     * @return Le singleton
     */
    public static EpisodeDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new EpisodeDAO(ds);
        }

        return instance;
    }

    /**
     * Getter du singleton
     *
     * @return Le singleton
     */
    public static EpisodeDAO Get() {
        return instance;
    }

    @Override
    public List<Episode> getEpisodesEnEdition(Biographie b) throws DAOException {
        LinkedList<Episode> epi = new LinkedList<>();
        PreparedStatement ps = null;
        Connection c = null;

        try {
            c = getConnection();
            
            ps = c.prepareStatement("select * "
                    + "from Episode e where e.biographie_id = ? "
                    + "and e.valide = 0 order by e.eDate");

            ps.setInt(1, b.getID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getObject("aventure_id") != null) {
                    epi.add(new Episode(rs.getInt("id"), rs.getInt("eDate"),
                            false, new Aventure(rs.getInt("aventure_id")),
                            new Joueur(rs.getInt("mj_id")), b));
                } else {
                    epi.add(new Episode(rs.getInt("id"), rs.getInt("eDate"),
                            false, null, new Joueur(rs.getInt("mj_id")), b));
                }
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }

        return epi;
    }

    /**
     * Retourne les épisodes valides d'une biographie.
     * 
     * @param b La biographie
     * @return Ses épisodes valides
     * @throws DAOException
     */
    @Override
    public List<Episode> getEpisodes(Biographie b) throws DAOException {
        LinkedList<Episode> epis = new LinkedList<>();
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = getConnection();
            
            ps = c.prepareStatement("select a.id as idAv, titre, "
                    + "e.id as eid, eDate, e.mj_id as mj "
                    + "from Episode e left join Aventure a "
                    + "on a.id = e.aventure_id "
                    + "where e.biographie_id = ? "
                    + "and e.valide = 1 and e.mj_id IS NULL "
                    + "order by e.eDate");

            ps.setInt(1, b.getID());
            ResultSet rs = ps.executeQuery();
            Aventure av;
            Episode epi;
            
            while (rs.next()) {
                
                if (rs.getObject("idAv") != null) {
                    av = new Aventure();
                    av.setId(rs.getInt("idAv"));
                    av.setTitre(rs.getString("titre"));
                    
                } else {
                    av = null;
                }
                
                epi = new Episode(rs.getInt("eid"), rs.getInt("eDate"), true,
                        av, new Joueur(rs.getInt("mj")), b);
                
                epis.add(epi);
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
        
        return epis;
    }

    @Override
    public List<Episode> getEpisodesAValider(Joueur mj) throws DAOException {
        LinkedList<Episode> epis = new LinkedList<>();
        PreparedStatement ps = null;
        Connection c = null;

        try {
            c = getConnection();
            ps = c.prepareStatement("select a.id as idAv, titre, valide, "
                    + "e.id as eid, eDate, e.mj_id as mj, biographie_id "
                    + "from Episode e left join Aventure a "
                    + "on a.id = e.aventure_id where e.mj_id = ? "
                    + "and e.valide = 1 order by e.eDate");

            ps.setInt(1, mj.getId());
            ResultSet rs = ps.executeQuery();
            
            BiographieDAO bioD = BiographieDAO.Get();
            Aventure av;
            Episode epi;
            
            while (rs.next()) {
                
                if (rs.getObject("idAv") != null) {
                    av = new Aventure();
                    av.setId(rs.getInt("idAv"));
                    av.setTitre(rs.getString("titre"));
                    
                } else {
                    av = null;
                }
                
                epi = new Episode(rs.getInt("eid"), rs.getInt("eDate"),
                        rs.getBoolean("valide"), av,
                        new Joueur(rs.getInt("mj")),
                        bioD.getBiographie(rs.getInt("biographie_id")));
                
                epis.add(epi);
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }

        return epis;
    }

    @Override
    public Episode getEpisode(int id) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        Episode epi = null;
        
        try {
            c = getConnection();
            
            ps = c.prepareStatement("select * "
                    + "from Episode e where e.id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            if (rs.getObject("aventure_id") != null) {
                epi = new Episode(rs.getInt("id"), rs.getInt("eDate"),
                        true, new Aventure(rs.getInt("aventure_id")),
                        new Joueur(rs.getInt("mj_id")), null);
            } else {
                epi = new Episode(rs.getInt("id"), rs.getInt("eDate"),
                        true, null, new Joueur(rs.getInt("mj_id")), null);
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
            
        return epi;
    }

    @Override
    public void suppressEpisode(int pid) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = initConnection();
            
            ps = c.prepareStatement("delete from paragraphe where episode_id =?");
            ps.setInt(1, pid);
            ps.executeUpdate();
            
            ps.close();
            ps = c.prepareStatement("delete from episode where id = ?");
            ps.setInt(1, pid);
            ps.executeUpdate();
            
            c.commit();

        } catch (Exception e) {
            rollback();
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

    @Override
    public void valideEpisode(int pid, int persoID, int joueurID) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;

        try {
            c = initConnection();
            ps = c.prepareStatement("select mj_id "
                    + "from personnage where id = ?");
            ps.setInt(1, persoID);
            
            ResultSet rs = ps.executeQuery();
            rs.next();

            int mj = rs.getInt("mj_id");
            ps.close();

            if (mj == joueurID) {
                ps = c.prepareStatement("update episode set valide = 1, "
                        + " mj_id = NULL where id = ?");
                ps.setInt(1, pid);

            } else {
                ps = c.prepareStatement("update episode set valide = 1, "
                        + "mj_id = ? where id = ?");
                ps.setInt(1, mj);
                ps.setInt(2, pid);
            }

            ps.executeUpdate();
            c.commit();
            
        } catch (Exception e) {
            rollback();
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

    @Override
    public void valideEpisodeParMj(int idEpi, int idUser) throws DAOException {
        Connection link = null;
        PreparedStatement ps = null;

        try {
            link = initConnection();

            // On vérifie que l'utilisateur est bien MJ de l'épisode
            ps = link.prepareStatement("SELECT 1 FROM Episode e "
                    + "JOIN Biographie b on e.biographie_id = b.id "
                    + "JOIN Personnage p on p.biographie_id = b.id "
                    + "JOIN Joueur j on p.mj_id = j.id "
                    + "WHERE e.id = ? and j.id = ?");

            ps.setInt(1, idEpi);
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();

            if (!rs.next())
                throw new DAOException("Accès refusé");

            ps.close();
            ps = link.prepareStatement("UPDATE Episode "
                    + "SET mj_id = NULL WHERE id = ?");
            
            ps.setInt(1, idEpi);
            ps.executeUpdate();

            link.commit();

        } catch (DAOException e) {
            throw e;

        } catch (SQLException e) {
            rollback();
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(link);
        }
    }

    @Override
    public void nouvelEpisode(boolean avtValide, int aventureID,
            int bioID, int date) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = initConnection();
            
            if (avtValide) {
                ps = c.prepareStatement("insert into episode values (default, "
                        + "?, default, ?, ?, NULL )");
                ps.setInt(1, date);
                ps.setInt(2, aventureID);
                ps.setInt(3, bioID);
                
            } else {
                ps = c.prepareStatement("insert into episode values (default, "
                        + "?, default, NULL, ?, NULL )");
                ps.setInt(1, date);
                ps.setInt(2, bioID);
            }

            ps.executeUpdate();
            commit();

        } catch (SQLException | DAOException e) {
            rollback();
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

    @Override
    public boolean hasMJ(int persoID) throws DAOException {
        PreparedStatement ps = null;
        Connection link = null;
        boolean result = false;
        
        try {
            link = getConnection();
            ps = link.prepareStatement("SELECT 1 FROM Personnage "
                                     + "WHERE id = ? AND mj_id IS NOT NULL");
            ps.setInt(1, persoID);
            
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(ps);
            closeConnection(link);
        }
        
        return result;
    }
}
