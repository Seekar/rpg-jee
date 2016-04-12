package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Personnage;

/**
 * Singleton du DAO d'accès aux biographies
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class BiographieDAO extends AbstractBiographieDAO {

    /**
     * Le singleton
     */
    private static BiographieDAO instance;

    /**
     * Constructeur privé du singleton
     */
    private BiographieDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Crée le singleton
     *
     * @param ds Le datasource d'accès bdd
     * @return Le singleton
     */
    public static BiographieDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new BiographieDAO(ds);
        }

        return instance;
    }

    /**
     * Getter du singleton
     *
     * @return Le singleton
     */
    public static BiographieDAO Get() {
        return instance;
    }

    @Override
    public Biographie getBiographie(Personnage p) throws DAOException {
        Connection c = null;
        Biographie b = null;
        
        try {
            c = getConnection();
            PreparedStatement ps = c.prepareStatement("select b.id, b.texte "
                    + "from Biographie b, Personnage p "
                    + "where b.id=p.biographie_id and p.id=?");

            ps.setInt(1, p.getId());
            ResultSet res = ps.executeQuery();
            
            res.next();
            b = new Biographie(res.getInt("id"), res.getString("texte"));
            
        } catch (Exception e) {
            throw new DAOException("", e);
            
        } finally {
            closeConnection(c);
        }
        
        return b;
    }

    @Override
    public Biographie getBiographie(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection link = null;
        Biographie bio = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT * "
                    + "FROM Biographie b "
                    + "where b.id = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new Exception("Aucune biographie "
                        + "correspondant à l'ID " + id);
            }

            bio = new Biographie(rs.getInt("id"), rs.getString("texte"));

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return bio;
    }
}
