/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Episode;
import modele.Joueur;
import modele.Personnage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modele.Biographie;
import modele.Univers;

/**
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public final class AventureDAO extends AbstractAventureDAO {
    
    static private AventureDAO instance;
    
    private AventureDAO(DataSource ds) {
        super(ds);
    }
    
    public static AventureDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new AventureDAO(ds);
        }

        return instance;
    }

    public static AventureDAO Get() {
        return instance;
    }

    @Override
    public void editPartie(Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void creerPartie(Aventure a) throws DAOException {
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = initConnection();
            
            statement = link.prepareStatement("INSERT INTO Aventure "
                    + "(adate, lieu, situation, titre, mj_id, univers_id) "
                    + " VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, a.getDate());
            statement.setString(2, a.getLieu());
            statement.setString(3, a.getSituation());
            statement.setString(4, a.getTitre());
            statement.setInt(5, a.getMj().getId());
            statement.setInt(6, a.getUnivers().getId());
            statement.executeUpdate();
            
            link.commit();

        } catch (Exception e) {
            rollback();
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }
    }

    @Override
    public List<Aventure> getPartiesEnCours(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aventure getAventureAssociee(Episode e) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aventure> getPartiesMenees(Joueur j) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public Aventure getAventure(int aID) throws DAOException{
        Connection c = null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from aventure where  id=?");
            ps.setInt(1, aID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Aventure a = getAventure(aID);
            a.setTitre(rs.getString("titre"));
            closeConnection(c);
            return a;
        } catch (Exception e) {
            throw new DAOException(null, e);
        } finally {
            closeConnection(c);
        }
    }*/

    @Override
    public List<Aventure> getAventureAssociee(int persoID) throws DAOException {
        Connection c = null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("select aventure_id from participe where personnage_id =?");
            ps.setInt(1, persoID);
            ResultSet rs = ps.executeQuery();
            LinkedList<Integer> avt = new LinkedList<Integer>();
            while (rs.next()) {
                avt.add(rs.getInt("aventure_id"));
            }
            closeConnection(c);
            LinkedList<Aventure> a = new LinkedList<>();
            for(int id :avt){
                a.add(getAventure(id));
            }
            return a;
        } catch (Exception e) {
            throw new DAOException(null, e);
        } finally {
            closeConnection(c);
        }
    }
    
    @Override
    public Aventure getAventure(int id) throws DAOException {
        Aventure aventure = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();    

            statement = link.prepareStatement("SELECT a.id, aDate, events, "
                    + "finie, lieu, situation, titre, mj_id, univers_id, "
                    + "u.id as u_id, u.nom as u_nom, j.pseudo as meneur"
                    + "FROM Aventure a"
                    + "JOIN Univers u on a.univers_id = u.id "
                    + "LEFT JOIN Joueur j on j.id = mj_id WHERE a.id = ?");
            
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next())
                throw new Exception("Aucun personnage d'identifiant " + id);

            aventure = new Aventure(rs.getInt("id"));
            aventure.setTitre(rs.getString("titre"));
            aventure.setDate(rs.getString("adate"));
            aventure.setLieu(rs.getString("lieu"));
            aventure.setEvents(rs.getString("events"));
            aventure.setSituation(rs.getString("situation"));
            aventure.setFinie(Boolean.parseBoolean(rs.getString("finie")));
            aventure.setMj(new Joueur(rs.getInt("mj_id"), rs.getString("meneur")));
            aventure.setUnivers(new Univers(rs.getInt("u_id"),rs.getString("u_nom")));

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return aventure;
    }
    
}
