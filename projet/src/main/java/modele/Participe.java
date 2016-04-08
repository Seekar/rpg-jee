package modele;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Participe
{
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Aventure aventure;
        
        public Aventure getAventure() {
            return aventure;
        }
        public void setAventure(Aventure aventure) {
            this.aventure = aventure;
        }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Personnage personnage;
        
        public Personnage getPersonnage() {
            return personnage;
        }
        public void setPersonnage(Personnage personnage) {
            this.personnage = personnage;
        }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
        
    public Participe(Aventure aventure, Personnage personnage) {
        this.aventure = aventure;
        this.personnage = personnage;
    }


}

