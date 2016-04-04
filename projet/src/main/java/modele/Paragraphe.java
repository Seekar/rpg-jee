package modele;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Paragraphe
{
	private int id;
        public int getID(){return id;}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String texte;
        public String getTexte(){return texte;}
        public void setTexte(String c){texte = c;}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private boolean secret;
        public boolean getSecret(){return secret;}
        public void setSecret(boolean b){secret = b;}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Episode episode;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Paragraphe(){
		super();
	}
        
        public Paragraphe(int id, boolean isSecret, String texte){
            this.id = id;
            secret = isSecret;
            this.texte = texte;
        }

}

