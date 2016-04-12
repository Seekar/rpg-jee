package dao;

import java.util.Collection;
import javax.sql.DataSource;
import modele.Episode;
import modele.Paragraphe;

/**
 * Classe abstraite du DAO d'accès aux paragraphes
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractParagrapheDAO extends AbstractDAO {

    public AbstractParagrapheDAO(DataSource ds) {
        super(ds);
    }

    public abstract Collection<Paragraphe> getParagraphes(Episode e) throws DAOException;

    public abstract Paragraphe getParagraphe(int pid) throws DAOException;

    public abstract void reveleParagraphe(int pid) throws DAOException;

    public abstract void ajouteParagraphe(boolean secret, String texte, int episode) throws DAOException;

    public abstract void updateParagraphe(int paragid, String texte) throws DAOException;
}
