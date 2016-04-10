/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Episode;
import modele.Paragraphe;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
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
    public List<Paragraphe> getParagraphes(Episode e) throws DAOException {
        LinkedList<Paragraphe> pars = null;
        PreparedStatement ps = null;
        Connection c = null;

        try {
            c = getConnection();
            ps = c.prepareStatement("select * from Paragraphe p "
                    + "WHERE p.episode_id = ? ORDER BY id");

            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();

            pars = new LinkedList<>();

            while (rs.next()) {
                pars.add(new Paragraphe(rs.getInt("id"),
                        rs.getInt("secret") == 1,
                        rs.getString("texte")));
            }

        } catch (Exception ex) {
            throw new DAOException(null, ex);

        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }

        return pars;
    }

    @Override
    public Paragraphe getParagraphe(int pid) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        Paragraphe p = null;
        
        try {
            c = getConnection();
            
            ps = c.prepareStatement("select * "
                    + "from Paragraphe where id=?");
            ps.setInt(1, pid);
            ResultSet res = ps.executeQuery();
            
            res.next();
            p = new Paragraphe(res.getInt("id"), res.getBoolean("secret"),
                    res.getString("texte"));

            
        } catch (Exception e) {
            throw new DAOException("", e);
            
        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
        
        return p;
    }

    @Override
    public void reveleParagraphe(int pid) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = initConnection();
            ps = c.prepareStatement("update paragraphe "
                    + "set secret = '0' where id =?");
            
            ps.setInt(1, pid);
            ps.executeUpdate();
            commit();
            
        } catch (SQLException | DAOException e) {
            rollback();
            throw new DAOException(e.getMessage(), e);
            
        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

    @Override
    public void ajouteParagraphe(boolean secret, String texte, int episode) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = initConnection();
            
            ps = c.prepareStatement("INSERT INTO PARAGRAPHE "
                    + "(SECRET, TEXTE, EPISODE_ID) "
                    + "VALUES (?, ?, ?)");

            ps.setInt(1, secret == true ? 1 : 0);
            ps.setString(2, texte);
            ps.setInt(3, episode);
            ps.executeUpdate();
            
            commit();
            
        } catch (Exception e) {
            rollback();
            throw new DAOException("Erreur à l'insertion d'un paragraphe "
                    + "dans la base de données", e);
            
        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

    @Override
    public void updateParagraphe(int paragid, String texte) throws DAOException {
        PreparedStatement ps = null;
        Connection c = null;
        
        try {
            c = initConnection();
            
            ps = c.prepareStatement("update paragraphe set  texte =? where id =?");
            ps.setString(1, texte);
            ps.setInt(2, paragid);
            ps.executeUpdate();
            
            commit();
            
        } catch (Exception e) {
            rollback();
            throw new DAOException(e.getMessage(), e);
            
        } finally {
            CloseStatement(ps);
            closeConnection(c);
        }
    }

}
