package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Episode
{
    private int id;
        public int getId()
        {return id;}    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private int eDate;
        public int getDate(){return eDate;}
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private boolean valide;
        public boolean getValide(){return valide;}
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Aventure aventure;
        public Aventure getAventure(){return aventure;}
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Joueur mj;
        public Joueur getMJ(){
            return mj;
        }
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Biographie biographie;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public List<Paragraphe> paragraphes = new LinkedList<>();
        public List<Paragraphe> getParagraphes(){return paragraphes;}

    public Episode(int id, int eDate, boolean valide, Aventure aventure, Joueur mj, Biographie biographie) {
        this.id = id;
        this.eDate = eDate;
        this.valide = valide;
        this.aventure = aventure;
        this.mj = mj;
        this.biographie = biographie;
    }
    
    public Episode(int eDate, boolean valide, Aventure aventure, Joueur mj, Biographie biographie) {
        this(-1, eDate, valide, aventure, mj, biographie);
    }

}

