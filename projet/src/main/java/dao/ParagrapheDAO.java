/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.sql.DataSource;
import modele.Episode;
import modele.Paragraphe;

/**
 *
 * @author plouviej
 */
public final class ParagrapheDAO extends AbstractParagrapheDAO {
    static private ParagrapheDAO instance;
    private ParagrapheDAO(DataSource ds) {
        super(ds);
    }
    
    public static ParagrapheDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new ParagrapheDAO(ds);
        }

        return instance;
    }

    public static ParagrapheDAO Get() {
        return instance;
    }

    @Override
    public Collection<Paragraphe> getParagraphes(Episode e) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
