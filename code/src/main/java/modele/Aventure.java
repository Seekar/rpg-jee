
import java.util.Set;
import java.util.HashSet;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Aventure
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String titre;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String situation;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String aDate;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String lieu;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String events;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected boolean finie;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.ManyToMany(mappedBy = "aventures") 
	protected Set<Personnage> personnages;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "aventure") 
	protected Set<Episode> episode;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.ManyToOne 
	@javax.persistence.JoinColumn(nullable = false) 
	protected Joueur mj;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.ManyToOne 
	@javax.persistence.JoinColumn(nullable = false) 
	protected Univers univers;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@javax.persistence.Id 
	@javax.persistence.Column(nullable = false) 
	protected final Long id = 0L;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Aventure(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void basicSetMj(Joueur myMj) {
		if (this.mj != myMj) {
			if (myMj != null){
				if (this.mj != myMj) {
					Joueur oldmj = this.mj;
					this.mj = myMj;
					if (oldmj != null)
						oldmj.removeAventures(this);
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void basicSetUnivers(Univers myUnivers) {
		if (this.univers != myUnivers) {
			if (myUnivers != null){
				if (this.univers != myUnivers) {
					Univers oldunivers = this.univers;
					this.univers = myUnivers;
					if (oldunivers != null)
						oldunivers.removeAventures(this);
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getTitre() {
		return this.titre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getSituation() {
		return this.situation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getADate() {
		return this.aDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getLieu() {
		return this.lieu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getEvents() {
		return this.events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private boolean isFinie() {
		return this.finie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Set<Personnage> getPersonnages() {
		if(this.personnages == null) {
				this.personnages = new HashSet<Personnage>();
		}
		return (Set<Personnage>) this.personnages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Set<Episode> getEpisode() {
		if(this.episode == null) {
				this.episode = new HashSet<Episode>();
		}
		return (Set<Episode>) this.episode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Joueur getMj() {
		return this.mj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Univers getUnivers() {
		return this.univers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addAllPersonnages(Set<Personnage> newPersonnages) {
		if (this.personnages == null) {
			this.personnages = new HashSet<Personnage>();
		}
		for (Personnage tmp : newPersonnages)
			tmp.addAventures(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addAllEpisode(Set<Episode> newEpisode) {
		if (this.episode == null) {
			this.episode = new HashSet<Episode>();
		}
		for (Episode tmp : newEpisode)
			tmp.setAventure(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAllPersonnages(Set<Personnage> newPersonnages) {
		if(this.personnages == null) {
			return;
		}
		
		this.personnages.removeAll(newPersonnages);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAllEpisode(Set<Episode> newEpisode) {
		if(this.episode == null) {
			return;
		}
		
		this.episode.removeAll(newEpisode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setTitre(String myTitre) {
		this.titre = myTitre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setSituation(String mySituation) {
		this.situation = mySituation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setADate(String myADate) {
		this.aDate = myADate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setLieu(String myLieu) {
		this.lieu = myLieu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setEvents(String myEvents) {
		this.events = myEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setFinie(boolean myFinie) {
		this.finie = myFinie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addPersonnages(Personnage newPersonnages) {
		if(this.personnages == null) {
			this.personnages = new HashSet<Personnage>();
		}
		
		if (this.personnages.add(newPersonnages))
			newPersonnages.addAventures(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addEpisode(Episode newEpisode) {
		if(this.episode == null) {
			this.episode = new HashSet<Episode>();
		}
		
		if (this.episode.add(newEpisode))
			newEpisode.basicSetAventure(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setMj(Joueur myMj) {
		this.basicSetMj(myMj);
		myMj.addAventures(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setUnivers(Univers myUnivers) {
		this.basicSetUnivers(myUnivers);
		myUnivers.addAventures(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetTitre() {
		this.titre = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetSituation() {
		this.situation = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetADate() {
		this.aDate = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetLieu() {
		this.lieu = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetEvents() {
		this.events = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetFinie() {
		this.finie = false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removePersonnages(Personnage oldPersonnages) {
		if(this.personnages == null)
			return;
		
		if (this.personnages.remove(oldPersonnages))
			oldPersonnages.removeAventures(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeEpisode(Episode oldEpisode) {
		if(this.episode == null)
			return;
		
		if (this.episode.remove(oldEpisode))
			oldEpisode.unsetAventure();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void unsetMj() {
		if (this.mj == null)
			return;
		Joueur oldmj = this.mj;
		this.mj = null;
		oldmj.removeAventures(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void unsetUnivers() {
		if (this.univers == null)
			return;
		Univers oldunivers = this.univers;
		this.univers = null;
		oldunivers.removeAventures(this);
	}

}

