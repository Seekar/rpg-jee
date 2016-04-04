/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import modele.Biographie;
import modele.Personnage;

/**
 *
 * @author plouviej
 */
public final class BiographieDAO extends AbstractBiographieDAO {

    private static BiographieDAO instance;
    
    private  BiographieDAO(DataSource ds) {
        super(ds);
    }
    
    public static BiographieDAO Create(DataSource ds) {
        if(instance == null)
            instance = new BiographieDAO(ds);
        
        return instance;
    }
    
    public static BiographieDAO Get() {
        return instance;
    }

    @Override
    public Biographie getBiographie(Personnage p) throws DAOException {
        Connection c=null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps =c.prepareStatement("select b.id, b.texte from Biographie b, Personnage p where b.id=p.biographie_id and p.id=?");
            ps.setInt(1, p.getId());
            ResultSet res = ps.executeQuery();
            res.next();
            Biographie b = new Biographie(res.getInt("id"), res.getString("texte"));
            return b;
        } catch (Exception e) {
            throw new DAOException("", e);
        }finally{
            
            closeConnection(c);
        }
    }
    
}
