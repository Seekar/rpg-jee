
import java.util.Set;
import java.util.HashSet;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Episode
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected int eDate;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected boolean valid;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.ManyToOne 
	@javax.persistence.JoinColumn(nullable = false) 
	protected Biographie biographie;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "episode") 
	protected Set<Paragraphe> paragraphes;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.ManyToOne 
	protected Aventure aventure;

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
	public Episode(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void basicSetBiographie(Biographie myBiographie) {
		if (this.biographie != myBiographie) {
			if (myBiographie != null){
				if (this.biographie != myBiographie) {
					Biographie oldbiographie = this.biographie;
					this.biographie = myBiographie;
					if (oldbiographie != null)
						oldbiographie.removeEpisodes(this);
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
	public void basicSetAventure(Aventure myAventure) {
		if (this.aventure != myAventure) {
			if (myAventure != null){
				if (this.aventure != myAventure) {
					Aventure oldaventure = this.aventure;
					this.aventure = myAventure;
					if (oldaventure != null)
						oldaventure.removeEpisode(this);
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
	private int getEDate() {
		return this.eDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private boolean isValid() {
		return this.valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Biographie getBiographie() {
		return this.biographie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Set<Paragraphe> getParagraphes() {
		if(this.paragraphes == null) {
				this.paragraphes = new HashSet<Paragraphe>();
		}
		return (Set<Paragraphe>) this.paragraphes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Aventure getAventure() {
		return this.aventure;
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
	public void addAllParagraphes(Set<Paragraphe> newParagraphes) {
		if (this.paragraphes == null) {
			this.paragraphes = new HashSet<Paragraphe>();
		}
		for (Paragraphe tmp : newParagraphes)
			tmp.setEpisode(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAllParagraphes(Set<Paragraphe> newParagraphes) {
		if(this.paragraphes == null) {
			return;
		}
		
		this.paragraphes.removeAll(newParagraphes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setEDate(int myEDate) {
		this.eDate = myEDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setValid(boolean myValid) {
		this.valid = myValid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setBiographie(Biographie myBiographie) {
		this.basicSetBiographie(myBiographie);
		myBiographie.addEpisodes(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addParagraphes(Paragraphe newParagraphes) {
		if(this.paragraphes == null) {
			this.paragraphes = new HashSet<Paragraphe>();
		}
		
		if (this.paragraphes.add(newParagraphes))
			newParagraphes.basicSetEpisode(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setAventure(Aventure myAventure) {
		this.basicSetAventure(myAventure);
		myAventure.addEpisode(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetEDate() {
		this.eDate = 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetValid() {
		this.valid = false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void unsetBiographie() {
		if (this.biographie == null)
			return;
		Biographie oldbiographie = this.biographie;
		this.biographie = null;
		oldbiographie.removeEpisodes(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeParagraphes(Paragraphe oldParagraphes) {
		if(this.paragraphes == null)
			return;
		
		if (this.paragraphes.remove(oldParagraphes))
			oldParagraphes.unsetEpisode();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void unsetAventure() {
		if (this.aventure == null)
			return;
		Aventure oldaventure = this.aventure;
		this.aventure = null;
		oldaventure.removeEpisode(this);
	}

}

