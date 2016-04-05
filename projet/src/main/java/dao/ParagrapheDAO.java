/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection c = null;
        try{
            c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select * from Paragraphe p where p.episode_id = ?");
        ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            LinkedList<Paragraphe> pars = new LinkedList<>();
            while(rs.next()){
                pars.add(new Paragraphe(rs.getInt("id"), rs.getInt("secret")==1, rs.getString("texte")));
            }
            closeConnection(c);
            return pars;
        }catch(Exception ex){
            throw new DAOException(null, ex);
        }finally{
            closeConnection(c);
        }
    }

    @Override
    public Paragraphe getParagraphe(int pid) throws DAOException {
        Connection c=null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps =c.prepareStatement("select * from Paragraphe where id=?");
            ps.setInt(1, pid);
            ResultSet res = ps.executeQuery();
            res.next();
            Paragraphe p = new Paragraphe(res.getInt("id"), res.getInt("secret") == 1 , res.getString("texte"));
            closeConnection(c);
            return p;
        } catch (Exception e) {
            throw new DAOException("", e);
        }finally{
            
            closeConnection(c);
        }
    }

    @Override
    public void reveleParagraphe(int pid) throws DAOException {
        Connection c=null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("update paragraphe set secret = '0' where id =?");
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("", e);
        }finally{
            
            closeConnection(c);
        }
    }
    
    @Override
    public void ajouteParagraphe(boolean secret, String texte, int episode) throws DAOException {
        Connection c=null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO PARAGRAPHE ( SECRET, TEXTE, EPISODE_ID) \n" +
"	VALUES (?, ?, ?)");
            ps.setInt(1, secret==true?1:0);
            ps.setString(2, texte);
            ps.setInt(3, episode);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("L'insertion du paragraphe dans la base de données n'a pas fonctionnée", e);
        }finally{
            
            closeConnection(c);
        }
    }

    @Override
    public void updateParagraphe(int paragid, String texte) throws DAOException{
        Connection c=null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("update paragraphe set  texte =? where id =?");
            ps.setString(1, texte);
            ps.setInt(2, paragid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("", e);
        }finally{
            
            closeConnection(c);
        }
    }
    
}
