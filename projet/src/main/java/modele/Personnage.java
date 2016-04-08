package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Personnage
{
    private int id;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String nom;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String naissance;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String profession;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String portrait;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private boolean valide;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Univers univers;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Joueur transfert;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Joueur mj;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Joueur joueur;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Joueur validateur;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private List<Aventure> parties;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private Biographie biographie;
       

    public Personnage(int id, String nom, String naissance, String profession, String portrait, Univers univers, Joueur joueur) {
        this.id = id;
        this.nom = nom;
        this.naissance = naissance;
        this.profession = profession;
        this.portrait = portrait;
        this.univers = univers;
        this.joueur = joueur;
    }

    public Personnage(String nom, String naissance, String profession, String portrait, Univers univers, Joueur joueur) {
        this(-1, nom, naissance, profession, portrait, univers, joueur);
    }

    public Personnage(String nom, String naissance, String profession, String portrait, Univers univers) {
        this(-1, nom, naissance, profession, portrait, univers, null);
    }

    public Personnage(int id) {
        this(id, null, null, null, null, null, null);
    }

    public Personnage() {
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Univers getUnivers() {
        return univers;
    }

    public void setUnivers(Univers univers) {
        this.univers = univers;
    }

    public Joueur getTransfert() {
        return transfert;
    }

    public void setTransfert(Joueur transfert) {
        this.transfert = transfert;
    }

    public Joueur getMj() {
        return mj;
    }

    public void setMj(Joueur mj) {
        this.mj = mj;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getValidateur() {
        return validateur;
    }

    public void setValidateur(Joueur validateur) {
        this.validateur = validateur;
    }

    public List<Aventure> getParties() {
        return parties;
    }

    public void setParties(List<Aventure> parties) {
        this.parties = parties;
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


}

