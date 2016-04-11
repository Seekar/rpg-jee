package modele;


/**
 * Classe de modélisation des participations
 * de personnages à des parties
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class Participe
{
    public Aventure aventure;
    public Personnage personnage;
        
    public Participe(Aventure aventure, Personnage personnage) {
        this.aventure = aventure;
        this.personnage = personnage;
    }


    public Aventure getAventure() {
        return aventure;
    }

    public void setAventure(Aventure aventure) {
        this.aventure = aventure;
    }
    
    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
}

