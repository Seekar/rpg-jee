
import java.util.Set;
import java.util.HashSet;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Univers
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String nom;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "univers", cascade = javax.persistence.CascadeType.ALL) 
	protected Set<Personnage> personnages;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "univers", cascade = javax.persistence.CascadeType.ALL) 
	protected Set<Aventure> aventures;

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
	public Univers(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getNom() {
		return this.nom;
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
	public Set<Aventure> getAventures() {
		if(this.aventures == null) {
				this.aventures = new HashSet<Aventure>();
		}
		return (Set<Aventure>) this.aventures;
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
			tmp.setUnivers(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addAllAventures(Set<Aventure> newAventures) {
		if (this.aventures == null) {
			this.aventures = new HashSet<Aventure>();
		}
		for (Aventure tmp : newAventures)
			tmp.setUnivers(this);
		
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
	public void removeAllAventures(Set<Aventure> newAventures) {
		if(this.aventures == null) {
			return;
		}
		
		this.aventures.removeAll(newAventures);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void setNom(String myNom) {
		this.nom = myNom;
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
			newPersonnages.basicSetUnivers(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addAventures(Aventure newAventures) {
		if(this.aventures == null) {
			this.aventures = new HashSet<Aventure>();
		}
		
		if (this.aventures.add(newAventures))
			newAventures.basicSetUnivers(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetNom() {
		this.nom = "";
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
			oldPersonnages.unsetUnivers();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAventures(Aventure oldAventures) {
		if(this.aventures == null)
			return;
		
		if (this.aventures.remove(oldAventures))
			oldAventures.unsetUnivers();
		
	}

}

