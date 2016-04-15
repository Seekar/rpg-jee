package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import modele.Univers;

/**
 * Singleton du DAO d'accès aux univers
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class UniversDAO extends AbstractUniversDAO {

    /**
     * Le singleton
     */
    private static UniversDAO instance;

    /**
     * Constructeur privé du singleton
     */
    private UniversDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Crée le singleton
     *
     * @param ds Le datasource d'accès bdd
     * @return Le singleton
     */
    public static UniversDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new UniversDAO(ds);
        }

        return instance;
    }

    /**
     * Getter du singleton
     *
     * @return Le singleton
     */
    public static UniversDAO Get() {
        return instance;
    }

    /**
     * Récupère la liste des univers ordonnés par nom
     *
     * @return La liste des univers
     * @throws dao.DAOException
     */
    @Override
    public ArrayList<Univers> getUnivers() throws DAOException {
        ArrayList<Univers> univers = new ArrayList<>();
        PreparedStatement statement = null;
        Connection link = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom FROM Univers ORDER BY nom");
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                univers.add(new Univers(res.getInt("id"), res.getString("nom")));
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des univers " + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return univers;
    }
}
