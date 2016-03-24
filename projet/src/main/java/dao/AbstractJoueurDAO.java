/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author reysi
 */
public abstract class AbstractJoueurDAO extends AbstractDAO{

    public AbstractJoueurDAO(DataSource ds) {
        super(ds);
    }
    public abstract List getListePersos();
}
