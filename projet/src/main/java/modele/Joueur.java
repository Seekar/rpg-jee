package modele;
import java.util.Set;
import java.util.HashSet;



public class Joueur
{
        // Concerne le joueur
	private String pseudo;
	public Set<Aventure> aventures;
	public Jouer jouer;
	public Set<Episode> episodes;
	public Set<Personnage> possedes;
	// Concerne le mj
	public Set<Personnage> menes;
        public Set<Personnage> transfertsAValider;
	public Set<Personnage> aValider;
	public Joueur(){
		super();
	}
}

