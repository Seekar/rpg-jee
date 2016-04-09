package modele;

import controleur.Main;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Biographie
 */
public class Biographie {

    private int id;

    private String texte;
    
    public Personnage personnage;

    public List<Episode> episodes = new LinkedList<>();
    
    public Biographie(int id, String texte) {
        this.id = id;
        this.texte = texte;
    }
    
    public Biographie(String texte) {
        this(-1, texte);
    }
    
    public Biographie(int id) {
        this(id, null);
    }
    
    
    public int getID() {
        return id;
    }
    
    public String getTexte() {
        return texte;
    }

    public String showTexte() {
        return Main.CustomEscape(texte);
    }
    
    public List<Episode> getEpisodes() {
        return episodes;
    }
    
    public void addEpisodes(List<Episode> l) {
        for (Episode e : l) {
            episodes.add(e);
        }
    }
}
