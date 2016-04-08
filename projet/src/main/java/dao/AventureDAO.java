/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AbstractDAO.CloseStatement;
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
import java.sql.SQLException;
import java.util.ArrayList;
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

        } catch (SQLException e) {
            rollback();
            throw new DAOException("Erreur à la création d'une aventure "
                    +  e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }
    }

    public ArrayList<Aventure> getAventures() throws DAOException {
        ArrayList<Aventure> avs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, titre, finie "
                    + "FROM Aventure ORDER BY titre, id");
            
            ResultSet res = statement.executeQuery();
            Aventure av;

            while (res.next()) {
                av = new Aventure();
                av.setId(res.getInt("id"));
                av.setTitre(res.getString("titre"));
                av.setFinie(res.getBoolean("finie"));
                
                avs.add(av);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des aventures "
                    +  e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return avs;
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
        ArrayList<Aventure> avs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT a.id, titre, finie "
                    + "FROM Aventure a JOIN Joueur j on a.mj_id = j.id "
                    + "WHERE j.id = ? ORDER BY titre");
            
            statement.setInt(1, j.getId());
            ResultSet res = statement.executeQuery();
            Aventure av;
            Personnage p;

            while (res.next()) {
                av = new Aventure();
                av.setId(res.getInt("id"));
                av.setTitre(res.getString("titre"));
                av.setFinie(res.getBoolean("finie"));
                
                avs.add(av);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des aventures "
                    + "menées " +  e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return avs;
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
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeConnection(c);
        }
    }*/

    @Override
    public List<Aventure> getAventureAssociee(int persoID) throws DAOException {
        Connection c = null;
        try {
            c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("select aventure_id "
                    + "from participe where personnage_id =?");
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
            throw new DAOException(e.getMessage(), e);
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
                    + "u.id as u_id, u.nom as u_nom, j.pseudo as meneur "
                    + "FROM Aventure a "
                    + "JOIN Univers u on a.univers_id = u.id "
                    + "LEFT JOIN Joueur j on j.id = mj_id WHERE a.id = ?");
            
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next())
                throw new Exception("Aucune aventure d'identifiant " + id);

            aventure = new Aventure(rs.getInt("id"));
            aventure.setTitre(rs.getString("titre"));
            aventure.setDate(rs.getString("adate"));
            aventure.setLieu(rs.getString("lieu"));
            aventure.setEvents(rs.getString("events"));
            aventure.setSituation(rs.getString("situation"));
            aventure.setFinie(Boolean.parseBoolean(rs.getString("finie")));
            aventure.setMj(new Joueur(rs.getInt("mj_id"), rs.getString("meneur")));
            aventure.setUnivers(new Univers(rs.getInt("u_id"),rs.getString("u_nom")));
            
            // Liste des personnages dans l'aventure
            statement = link.prepareCall("SELECT aventure_id, personnage_id " 
                    + "FROM Participe WHERE aventure_id = ?");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            List<Personnage> listPerso = new ArrayList<>();
            while (rs.next()) {
                listPerso.add(PersonnageDAO.Get().getPersonnage(rs.getInt("personnage_id")));
            }
            aventure.setPersonnages(listPerso);
            

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à une partie "
                    + e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return aventure;
    }

    public ArrayList<Aventure> getParties(Personnage p) throws DAOException {
        ArrayList<Aventure> avs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT a.id, titre, finie "
                    + "FROM Aventure a JOIN Participe r "
                    + "on a.id = r.aventure_id JOIN Personnage p "
                    + "on p.id = r.personnage_id WHERE p.id = ? ORDER BY titre");
            
            statement.setInt(1, p.getId());
            ResultSet res = statement.executeQuery();
            Aventure av;

            while (res.next()) {
                av = new Aventure();
                av.setId(res.getInt("id"));
                av.setTitre(res.getString("titre"));
                av.setFinie(res.getBoolean("finie"));
                
                avs.add(av);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des aventures "
                    + "d'un personnage " +  e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return avs;
    }

    public ArrayList<Aventure> getParties(Joueur j) throws DAOException {
        ArrayList<Aventure> avs = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT a.id, titre, finie, "
                    + "p.id as p_id, p.nom FROM Aventure a JOIN Participe r "
                    + "on a.id = r.aventure_id JOIN Personnage p "
                    + "on p.id = r.personnage_id JOIN Joueur j "
                    + "on p.joueur_id = j.id WHERE j.id = ? ORDER BY titre");
            
            statement.setInt(1, j.getId());
            ResultSet res = statement.executeQuery();
            Aventure av;
            Personnage p;

            while (res.next()) {
                av = new Aventure();
                av.setId(res.getInt("id"));
                av.setTitre(res.getString("titre"));
                av.setFinie(res.getBoolean("finie"));
                
                p = new Personnage();
                p.setId(res.getInt("p_id"));
                p.setNom(res.getString("nom"));
                av.addPersonnage(p);
                
                avs.add(av);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des aventures "
                    + "d'un personnage " +  e.getMessage(), e);

        } finally {
            CloseStatement(statement);
            closeConnection(link);
        }

        return avs;
    }

    @Override
    public void finishPartie(Aventure aventure, String events) throws DAOException {
        // TODO
    }
    
    @Override
    public void deletePartie(Aventure aventure) throws DAOException {
        // TODO
    }
    
}
