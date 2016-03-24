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
public final class JoueurDAO extends AbstractJoueurDAO {

    public JoueurDAO(DataSource ds) {
        super(ds);
    }

    @Override
    public List<Joueur> getListePersos() {
        // Requête SQL
        throw new UnsupportedOperationException("TODO : Requête SQL obtenir liste persos joueur."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
