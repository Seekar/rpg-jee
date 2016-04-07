package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Joueur
{
	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String pseudo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String pwd;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Aventure> aventures;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Episode> episodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Personnage> transferts;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Personnage> supervisions;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Personnage> possessions;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Personnage> validations;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Joueur(int id, String pseudo, String pwd) {
		this.id = id;
		this.pseudo = pseudo;
		this.pwd = pwd;
	}

	public Joueur(int id, String pseudo) {
            this(id, pseudo, null);
	}

	public Joueur(int id) {
            this(id, null, null);
	}

	public Joueur() {
	}
        
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Aventure> getAventures() {
        return aventures;
    }

    public void setAventures(List<Aventure> aventures) {
        this.aventures = aventures;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Personnage> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<Personnage> transferts) {
        this.transferts = transferts;
    }

    public List<Personnage> getSupervisions() {
        return supervisions;
    }

    public void setSupervisions(List<Personnage> supervisions) {
        this.supervisions = supervisions;
    }

    public List<Personnage> getPossessions() {
        return possessions;
    }

    public void setPossessions(List<Personnage> possessions) {
        this.possessions = possessions;
    }

    public List<Personnage> getValidations() {
        return validations;
    }

    public void setValidations(List<Personnage> validations) {
        this.validations = validations;
    }
}

