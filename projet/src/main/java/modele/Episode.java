package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * Classe de modélisation des épisodes
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class Episode {

    private int id;
    private int eDate;
    private boolean valide;
    public Aventure aventure;
    public Joueur mj;
    public Biographie biographie;
    public List<Paragraphe> paragraphes = new LinkedList<>();

    public Episode(int id, int eDate, boolean valide, Aventure aventure, Joueur mj, Biographie biographie) {
        this.id = id;
        this.eDate = eDate;
        this.valide = valide;
        this.aventure = aventure;
        this.mj = mj;
        this.biographie = biographie;
    }
    
    public Episode(int eDate, boolean valide, Aventure aventure, Joueur mj, Biographie biographie) {
        this(-1, eDate, valide, aventure, mj, biographie);
    }


    public boolean getValide() {
        return valide;
    }

    public Joueur getMJ() {
        return mj;
    }

    public int getDate() {
        return eDate;
    }

    public void setDate(int eDate) {
        this.eDate = eDate;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Joueur getMj() {
        return mj;
    }

    public void setMj(Joueur mj) {
        this.mj = mj;
    }

    public Biographie getBiographie() {
        return biographie;
    }

    public void setBiographie(Biographie biographie) {
        this.biographie = biographie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int geteDate() {
        return eDate;
    }

    public void seteDate(int eDate) {
        this.eDate = eDate;
    }

    public Aventure getAventure() {
        return aventure;
    }

    public void setAventure(Aventure aventure) {
        this.aventure = aventure;
    }

    public List<Paragraphe> getParagraphes() {
        return paragraphes;
    }

    public void setParagraphes(List<Paragraphe> paragraphes) {
        this.paragraphes = paragraphes;
    }
}

