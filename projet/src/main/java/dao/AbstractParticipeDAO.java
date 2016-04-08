/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.sql.DataSource;
import modele.Participe;
import modele.Personnage;
import modele.Aventure;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract  class AbstractParticipeDAO extends AbstractDAO {

    public AbstractParticipeDAO(DataSource ds) {
        super(ds);
    }
    
    public abstract void creerParticipe(Participe p) throws DAOException;
    
    public abstract void supprimerParticipe(Aventure aventure, Personnage perso) throws DAOException;

    
}
