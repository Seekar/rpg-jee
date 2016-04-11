package modele;


/**
 * Classe de modélisation des paragraphes
 *
 * @author Jules-Eugène Demets, Léo Gouttefarde, Salim Aboubacar, Simon Rey
 */
public class Paragraphe
{
    private int id;
    private String texte;
    private boolean secret;
    public Episode episode;

    public Paragraphe(){
        super();
    }

    public Paragraphe(int id, boolean isSecret, String texte){
        this.id = id;
        secret = isSecret;
        this.texte = texte;
    }


    public int getId() {
        return id;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isSecret() {
        return secret;
    }

    public boolean getSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}

