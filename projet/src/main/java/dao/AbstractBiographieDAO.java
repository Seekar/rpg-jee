package dao;

import javax.sql.DataSource;
import modele.Biographie;
import modele.Personnage;

/**
 * Classe abstraite du DAO d'accès aux biographies
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract  class AbstractBiographieDAO extends AbstractDAO {

    public AbstractBiographieDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Retourne la biographie associée à un personnage.
     * 
     * @param p Le personnage
     * @return Sa biographie
     * @throws DAOException
     */
    public abstract Biographie getBiographie(Personnage p) throws DAOException;

    /**
     * Retourne la biographie à partir de son identifiant.
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public abstract Biographie getBiographie(int id) throws DAOException;

}
