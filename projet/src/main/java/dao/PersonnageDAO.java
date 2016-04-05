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
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import modele.Aventure;
import modele.Biographie;
import modele.Joueur;
import modele.Personnage;
import modele.Univers;

/**
 *
 * @author plouviej
 */
public final class PersonnageDAO extends AbstractPersonnageDAO {
    
    private static PersonnageDAO instance;
    
    private PersonnageDAO(DataSource ds) {
        super(ds);
    }
    
    public static PersonnageDAO Create(DataSource ds) {
        if (instance == null) {
            instance = new PersonnageDAO(ds);
        }

        return instance;
    }
    
    public static PersonnageDAO Get()
    {
        return instance;
    }

    @Override
    public ArrayList<Personnage> getPersonnagesJoueur(Joueur j) throws DAOException {
        ArrayList<Personnage> persos = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom "
                    + "FROM Personnage where joueur_id = ?");
            
            statement.setInt(1, j.getId());
            ResultSet res = statement.executeQuery();
            Personnage perso;

            while (res.next()) {
                perso = new Personnage();
                perso.setId(res.getInt("id"));
                perso.setNom(res.getString("nom"));
                
                persos.add(perso);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des personnages "
                    + "possédés : " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return persos;
    }

    @Override
    public Collection<Personnage> getPersonnagesAValider(Joueur j) throws DAOException {
        ArrayList<Personnage> persos = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom FROM Personnage "
                    + "where valide = 0 and validateur_id = ?");
            
            statement.setInt(1, j.getId());
            ResultSet res = statement.executeQuery();
            Personnage perso;

            while (res.next()) {
                perso = new Personnage();
                perso.setId(res.getInt("id"));
                perso.setNom(res.getString("nom"));
                
                persos.add(perso);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des personnages "
                    + "à valider : " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return persos;
    }

    @Override
    public Collection<Personnage> getTransfertsAValider(Joueur j) throws DAOException {
        ArrayList<Personnage> persos = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom FROM Personnage "
                    + "where transfert_id = ? and mj_id != ? and valide = 1");
            
            statement.setInt(1, j.getId());
            statement.setInt(2, j.getId());
            ResultSet res = statement.executeQuery();
            Personnage perso;

            while (res.next()) {
                perso = new Personnage();
                perso.setId(res.getInt("id"));
                perso.setNom(res.getString("nom"));
                
                persos.add(perso);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des transferts "
                    + "à valider : " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return persos;
    }

    @Override
    public Personnage getPersonnageAssocie(Joueur j, Aventure a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Personnage> getPersonnagesMenes(Joueur j) throws DAOException {
        ArrayList<Personnage> persos = new ArrayList<>();
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT id, nom FROM Personnage "
                    + "where mj_id = ? and valide = 1");
            
            statement.setInt(1, j.getId());
            ResultSet res = statement.executeQuery();
            Personnage perso;

            while (res.next()) {
                perso = new Personnage();
                perso.setId(res.getInt("id"));
                perso.setNom(res.getString("nom"));
                
                persos.add(perso);
            }

        } catch (Exception e) {
            throw new DAOException("Erreur d'accès à la liste des personnages "
                    + "menés : " + e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }

        return persos;
    }

    @Override
    public void creer(Personnage p, String bio) throws DAOException {
        Joueur joueur = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            link.setAutoCommit(false);
            link.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            
            statement = link.prepareStatement("INSERT INTO Biographie (texte) VALUES (?)");
            statement.setString(1, bio);
            statement.executeUpdate();
            
            statement = link.prepareStatement("INSERT INTO Personnage "
                    + "(naissance, nom, portrait, profession, joueur_id, "
                    + "univers_id, biographie_id) VALUES (?, ?, ?, ?, ?, ?, "
                    + "bio_seq.currval)");

            statement.setString(1, p.getNaissance());
            statement.setString(2, p.getNom());
            statement.setString(3, p.getPortrait());
            statement.setString(4, p.getProfession());
            statement.setInt(5, p.getJoueur().getId());
            statement.setInt(6, p.getUnivers().getId());
            statement.executeUpdate();
            
            link.commit();

        } catch (Exception e) {
            if (link != null) {
                try {
                    link.rollback();
                } catch (SQLException ex) {}
            }
            
            throw new DAOException(e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }
            
            closeConnection(link);
        }
    }

    @Override
    public Personnage getPersonnage(int id) throws DAOException {
        Personnage perso = null;
        Connection link = null;
        PreparedStatement statement = null;

        try {
            link = getConnection();
            statement = link.prepareStatement("SELECT p.id, p.nom, naissance, "
                    + "profession, portrait, valide, biographie_id, mj_id, "
                    + "transfert_id, validateur_id, joueur_id, u.id as u_id, "
                    + "u.nom as u_nom, j.pseudo as meneur FROM Personnage p "
                    + "join Univers u on p.univers_id = u.id "
                    + "left join Joueur j on j.id = mj_id where p.id = ?");
            
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next())
                throw new Exception("Aucun personnage d'identifiant " + id);

            perso = new Personnage();
            perso.setId(rs.getInt("id"));
            perso.setNom(rs.getString("nom"));
            perso.setNaissance(rs.getString("naissance"));
            perso.setProfession(rs.getString("profession"));
            perso.setPortrait(rs.getString("portrait"));
            perso.setValide(rs.getBoolean("valide"));
            perso.setBiographie(new Biographie(rs.getInt("biographie_id")));
            perso.setMj(new Joueur(rs.getInt("mj_id"), rs.getString("meneur")));
            perso.setJoueur(new Joueur(rs.getInt("joueur_id")));
            perso.setTransfert(new Joueur(rs.getInt("transfert_id")));
            perso.setValidateur(new Joueur(rs.getInt("validateur_id")));

            Univers univers = new Univers(rs.getInt("u_id"),
                                          rs.getString("u_nom"));
            perso.setUnivers(univers);

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {}
            }

            closeConnection(link);
        }

        return perso;
    }
}
