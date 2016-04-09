package modele;
import controleur.Main;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Aventure
{
    public int id;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String titre;
        
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String situation;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String aDate;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String lieu;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String events;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private boolean finie;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Univers univers;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    /*public Episode episode; 

    public Episode getEpisode() {
        return episode;
    }
    public void setEpisode(Episode episode) {
        this.episode = episode;
    }*/



    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Joueur mj;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public List<Personnage> personnages = new LinkedList<>();

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     */
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

    public String showSituation() {
        return Main.CustomEscape(situation);
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

    public String showEvents() {
        return Main.CustomEscape(events);
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

