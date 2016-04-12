package modele;
import controleur.Main;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Classe de modélisation des aventures
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class Aventure {

    public int id;
    private String titre;
    private String situation;
    private String aDate;
    private String lieu;
    private String events;
    private boolean finie;
    public Univers univers;
    public Joueur mj;
    public List<Personnage> personnages = new LinkedList<>();

    public Aventure() {
        this.id = -1;   
    }

    public Aventure(int id, String titre, String date, String lieu,
                Univers univers, String situation, Joueur mj) {
        this.titre = titre;
        this.situation = situation;
        this.aDate = date;
        this.lieu = lieu;
        this.univers = univers;
        this.finie = false;
        this.events = "";
        this.id = id;
        this.mj = mj;
    }

    public Aventure(String titre, String date, String lieu,
                Univers univers, String situation, Joueur mj) {
        this(-1,titre,date,lieu,univers,situation,mj);
    }

    public Aventure(int id){
        this.id = id;
    }


    /**
     * Renvoie le texte descriptif des événements avec protection XSS
     * + affichage html des sauts de lignes
     *
     * @return Le texte des événements à afficher
     */
    public String showEvents() {
        return Main.CustomEscape(events);
    }

    /**
     * Renvoie la situation initiale avec protection XSS
     * + affichage html des sauts de lignes
     *
     * @return La situation à afficher
     */
    public String showSituation() {
        return Main.CustomEscape(situation);
    }

    public String getDate() {
        return aDate;
    }

    public void setDate(String aDate) {
        this.aDate = aDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public boolean isFinie() {
        return finie;
    }

    public boolean estFinie() {
        return finie;
    }

    public void setFinie(boolean finie) {
        this.finie = finie;
    }

    public Univers getUnivers() {
        return univers;
    }

    public void setUnivers(Univers univers) {
        this.univers = univers;
    }

    public Joueur getMj() {
        return mj;
    }

    public void setMj(Joueur mj) {
        this.mj = mj;
    }

    public List<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(List<Personnage> personnages) {
        this.personnages = personnages;
    }

    public void addPersonnage(Personnage p) {
        this.personnages.add(p);
    }

    public Personnage getPersonnage(int index) {
        if (personnages == null || personnages.size() <= index)
            return null;
        
        return personnages.get(index);
    }

    public Personnage getPersonnage() {
        return getPersonnage(0);
    }
}

