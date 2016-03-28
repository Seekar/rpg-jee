/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import modele.*;

/**
 *
 * @author reysi
 */
public abstract class AbstractJoueurDAO extends AbstractDAO {

    public AbstractJoueurDAO(DataSource ds) {
        super(ds);
    }

    public abstract Joueur getJoueur(String pseudo) throws DAOException;

    public abstract Joueur getJoueur(int id) throws DAOException;

    
}
