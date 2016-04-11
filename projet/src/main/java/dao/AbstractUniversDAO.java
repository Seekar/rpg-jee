package dao;

import java.util.Collection;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Personnage;
import modele.Univers;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractUniversDAO extends AbstractDAO {

    public AbstractUniversDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Récupère la liste des univers ordonnés par nom
     *
     * @return La liste des univers
     */
    public abstract Collection<Univers> getUnivers() throws DAOException;
}
