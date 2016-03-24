
import java.util.Set;
import java.util.HashSet;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Biographie
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String texte;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "biographie") 
	protected Set<Episode> episodes;

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
	public Biographie(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getTexte() {
		return this.texte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Set<Episode> getEpisodes() {
		if(this.episodes == null) {
				this.episodes = new HashSet<Episode>();
		}
		return (Set<Episode>) this.episodes;
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
	public void addAllEpisodes(Set<Episode> newEpisodes) {
		if (this.episodes == null) {
			this.episodes = new HashSet<Episode>();
		}
		for (Episode tmp : newEpisodes)
			tmp.setBiographie(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAllEpisodes(Set<Episode> newEpisodes) {
		if(this.episodes == null) {
			return;
		}
		
		this.episodes.removeAll(newEpisodes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setTexte(String myTexte) {
		this.texte = myTexte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addEpisodes(Episode newEpisodes) {
		if(this.episodes == null) {
			this.episodes = new HashSet<Episode>();
		}
		
		if (this.episodes.add(newEpisodes))
			newEpisodes.basicSetBiographie(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetTexte() {
		this.texte = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeEpisodes(Episode oldEpisodes) {
		if(this.episodes == null)
			return;
		
		if (this.episodes.remove(oldEpisodes))
			oldEpisodes.unsetBiographie();
		
	}

}

