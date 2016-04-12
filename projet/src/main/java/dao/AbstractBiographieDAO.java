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
    
    public abstract Biographie getBiographie(Personnage p) throws DAOException;
    
    public abstract Biographie getBiographie(int id) throws DAOException;

}
