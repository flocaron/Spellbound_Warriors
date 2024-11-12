public class Map {
    private String nom;
    private String debut;
    private String fin;
    private int longueur;

    public Map(String nom, String debut, String fin, int longueur) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.longueur = longueur;
    }

    public String getNom() {
        return nom;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    public int getLongueur() {
        return longueur;
    }
}
