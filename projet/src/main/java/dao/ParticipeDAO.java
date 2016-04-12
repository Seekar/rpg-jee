package dao;

import static dao.AbstractDAO.CloseStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Participe;
import modele.Personnage;

/**
 * Singleton du DAO d'accès aux Personnages
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class ParticipeDAO extends AbstractParticipeDAO {

    /**
     * Le singleton
     */
    private static ParticipeDAO instance;

    /**
     * Constructeur privé du singleton
     */
    private ParticipeDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * Crée le singleton
     *
     * @param ds Le datasource d'accès bdd
     * @return Le singleton
     */
    public static ParticipeDAO Create(DataSource ds) {
        if(instance == null)
            instance = new ParticipeDAO(ds);
        
        return instance;
    }
    
    /**
     * Getter du singleton
     *
     * @return Le singleton
     */
    public static ParticipeDAO Get() {
        return instance;
    }

    
    @Override
    public void creerParticipe(Participe p) throws DAOException {
        Connection link = null;
        PreparedStatement statement = null;
        Personnage perso = p.getPersonnage();

        try {
            link = initConnection();
            
            // On vérifie que ce personnage est bien mené par ce MJ 
            // dans l'univers indiqué et ne participe à aucune partie en cours.
            statement = link.prepareStatement("SELECT 1 FROM Personnage p "
                    + "JOIN Univers u on p.univers_id = u.id "
                    + "LEFT JOIN Participe r on r.personnage_id = p.id "
                    + "WHERE mj_id = ? AND u.id = ? AND p.id = ? AND valide = 1 "
                    + "AND NOT EXISTS (SELECT 1 FROM Aventure a "
                    + "WHERE r.aventure_id = a.id and finie = 0) "
                    + "GROUP BY p.id, p.nom "
                    + "HAVING COUNT(p.id) = (SELECT COUNT(p2.id) FROM Personnage p2 "
                    + "LEFT JOIN Participe s on s.personnage_id = p2.id "
                    + "WHERE p2.id = p.id) ORDER BY p.nom ");
            
            statement.setInt(1, perso.getMj().getId());
            statement.setInt(2, perso.getUnivers().getId());
            statement.setInt(3, perso.getId());
            ResultSet rs = statement.executeQuery();
            
            if (!rs.next())
                throw new DAOException("Accès refusé");
            
            
            statement = link.prepareStatement("INSERT INTO Participe "
                    + "(aventure_id, personnage_id) VALUES (?, ?)");

            statement.setInt(1, p.getAventure().getId());
            statement.setInt(2, perso.getId());
            statement.executeUpdate();
            
            link.commit();

        } catch (DAOException e) {
            throw new DAOException(e.getMessage(), e);

        } catch (Exception e) {
            rollback();
            throw new DAOException("Erreur à l'ajout d'un participant "
                    + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }
    }

    @Override
    public void supprimerParticipe(Aventure aventure, Personnage perso) throws DAOException {
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = initConnection();
            
            statement = link.prepareStatement("DELETE FROM Participe p"
                    + " WHERE p.aventure_id=? AND p.personnage_id = ?");

            statement.setInt(1, aventure.getId());
            statement.setInt(2, perso.getId());
            statement.executeUpdate();
            
            link.commit();

        } catch (Exception e) {
            rollback();
            throw new DAOException("Erreur à la suppression d'un participant "
                    + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }
    }
    
}
