package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * Classe de modélisation des univers
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class Univers
{
    private int id;
    private String nom;
    public List<Aventure> aventures;
    public List<Personnage> personnages;

    public Univers(){
        super();
    }

    public Univers(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Univers(int id) {
        this(id, null);
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

