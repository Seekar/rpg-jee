package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Aventure
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String titre;

    public String getTitre() {
    	return titre;
    }
    public void setTitre(String titre) {
    	this.titre = titre;
    }
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String situation;

	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String aDate;

	public String getDate() {
		return aDate;
	}
	public void setDate(String date) {
		this.aDate = date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String lieu;

	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String events;

	public String getEvents() {
		return events;
	}
	public void setEvents(String events) {
		this.events = events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private boolean finie;

	public boolean estFinie() {
		return finie;
	}
	public void setFinie(boolean finie) {
		this.finie = finie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Univers univers;

	public Univers getUnivers() {
		return univers;
	}
	public void setUnivers(Univers univers) {
		this.univers = univers;
	}

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

	public Joueur getMj() {
		return mj;
	}
	public void setMj(Joueur mj) {
		this.mj = mj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Joue> parties;

	public List<Joue> getJoue() {
		return parties;
	}
	public void setJoue(List<Joue> parties) {
		this.parties = parties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Aventure()
	{
		this.ID = -1;	
	}
        
	public Aventure(int id, String titre, String date, String lieu, Univers univers, String situation, Joueur mj) {
		this.titre = titre;
		this.situation = situation;
		this.aDate = date;
		this.lieu = lieu;
		this.univers = univers;
		this.finie = false;
		this.events = "";
		this.ID = id;
		this.mj = mj;
	}

	public Aventure(String titre, String date, String lieu, Univers univers, String situation, Joueur mj) {
		this(-1,titre,date,lieu,univers,situation,mj);
	}

    public Aventure(int id){
        ID = id;
    }
        
    public final int ID;


}

