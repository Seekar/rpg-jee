
import java.util.Set;
import java.util.HashSet;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Joueur
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.Column(nullable = false) 
	protected String pseudo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "transfert") 
	protected Set<Personnage> transferts;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "joueur") 
	protected Set<Personnage> personnages;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	 
	@javax.persistence.OneToMany(mappedBy = "mj") 
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
	public Joueur(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String getPseudo() {
		return this.pseudo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Set<Personnage> getTransferts() {
		if(this.transferts == null) {
				this.transferts = new HashSet<Personnage>();
		}
		return (Set<Personnage>) this.transferts;
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
	public void addAllTransferts(Set<Personnage> newTransferts) {
		if (this.transferts == null) {
			this.transferts = new HashSet<Personnage>();
		}
		for (Personnage tmp : newTransferts)
			tmp.setTransfert(this);
		
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
			tmp.setJoueur(this);
		
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
			tmp.setMj(this);
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeAllTransferts(Set<Personnage> newTransferts) {
		if(this.transferts == null) {
			return;
		}
		
		this.transferts.removeAll(newTransferts);
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
	private void setPseudo(String myPseudo) {
		this.pseudo = myPseudo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void addTransferts(Personnage newTransferts) {
		if(this.transferts == null) {
			this.transferts = new HashSet<Personnage>();
		}
		
		if (this.transferts.add(newTransferts))
			newTransferts.basicSetTransfert(this);
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
			newPersonnages.basicSetJoueur(this);
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
			newAventures.basicSetMj(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private void unsetPseudo() {
		this.pseudo = "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void removeTransferts(Personnage oldTransferts) {
		if(this.transferts == null)
			return;
		
		if (this.transferts.remove(oldTransferts))
			oldTransferts.unsetTransfert();
		
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
			oldPersonnages.unsetJoueur();
		
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
			oldAventures.unsetMj();
		
	}

}

