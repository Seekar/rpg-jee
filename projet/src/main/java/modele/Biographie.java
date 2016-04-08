package modele;
import java.util.LinkedList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Biographie
{
    private int id;
        public int getID() {return id;};
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    private String texte;
        public String getTexte(){return texte;}
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public Personnage personnage;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */
    
    public List<Episode> episodes= new LinkedList<>();
           
       public List<Episode> getEpisodes(){return episodes;}
       
       public void addEpisodes(List<Episode> l){
           for (Episode e  : l){
           episodes.add(e);
           }
       }
 
    public Biographie(int id, String texte) {
        this.id = id;
        this.texte = texte;
    }
 
    public Biographie(String texte) {
        this(-1, texte);
    }
 
    public Biographie(int id) {
        this(id, null);
    }

}

