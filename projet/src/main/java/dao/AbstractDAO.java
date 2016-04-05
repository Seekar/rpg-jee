/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * Classe de DAO générique
 * 
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public abstract class AbstractDAO {
    
    protected final DataSource dataSource;
    protected Connection curLink;

    protected AbstractDAO(DataSource ds) {
        this.dataSource = ds;
    }

    protected Connection getConnection() throws SQLException {
        return (curLink = dataSource.getConnection());
    }

    public Connection initConnection() throws SQLException {
        getConnection();
        curLink.setAutoCommit(false);
        curLink.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        
        return curLink;
    }

    /**
     * Fermeture d'une connexion
     *
     * @param c La connexion à fermer
     * @throws DAOException si problème lors de la fermeture de la connexion
     */
    protected void closeConnection(Connection link) throws DAOException {
        if (link != null) {
            try {
                link.close();
                
            } catch (SQLException sqle) {
                throw new DAOException("Erreur fermeture de connexion bd", sqle);
            }
        }
    }

    /**
     * Fermeture de la connexion
     *
     * @throws DAOException si problème lors de la fermeture de la connexion
     */
    protected void closeConnection() throws DAOException {
        if (curLink != null) {
            try {
                curLink.close();
                curLink = null;
                
            } catch (SQLException sqle) {
                throw new DAOException("Erreur fermeture de connexion bd", sqle);
            }
        }
    }

    /**
     * Annulation de transaction
     *
     * @param link La connexion
     * @throws DAOException si problème lors du rollback
     */
    protected void rollback(Connection link) throws DAOException {
        if (link != null) {
            try {
                link.rollback();
                
            } catch (SQLException sqle) {
                throw new DAOException("Erreur de rollback", sqle);
            }
        }
    }
    
    /**
     * Annulation de transaction
     *
     * @throws DAOException si problème lors du rollback
     */
    protected void rollback() throws DAOException {
        if (curLink != null) {
            try {
                curLink.rollback();
                
            } catch (SQLException sqle) {
                throw new DAOException("Erreur de rollback", sqle);
            }
        }
    }
    
    public static void CloseStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {}
        }
    }
}
