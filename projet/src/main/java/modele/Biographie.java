package modele;

import controleur.Main;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de modélisation des biographies
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
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


    /**
     * Renvoie le texte de biographie avec protection XSS
     * + affichage html des sauts de lignes
     *
     * @return Le texte à afficher
     */
    public String showTexte() {
        return Main.CustomEscape(texte);
    }
    
    public int getID() {
        return id;
    }
    
    public String getTexte() {
        return texte;
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
