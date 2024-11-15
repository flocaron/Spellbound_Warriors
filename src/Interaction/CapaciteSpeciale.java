package Interaction;
import Personnage.Modele.Ennemi;

public class CapaciteSpeciale {

    private String nom;
    private boolean utilisee;

    public CapaciteSpeciale(String nom) {
        this.nom = nom;
        this.utilisee = false;
    }

    public String utiliser() {
        if (!utilisee) {
            utilisee = true;
            return nom + " est utilisée!";
        } else {
            return nom + " a déjà été utilisée.";
        }
    }

    public boolean estUtilisee() {
        return utilisee;
    }

    public String getNom() {
        return nom;
    }

    // Méthode pour utiliser la capacité Boule de Feu
    public int utiliserBouleDeFeu(Ennemi cible) {
        if (!utilisee) {
            utilisee = true; // Marquer la capacité comme utilisée
            return 200; // Retourne les dégâts infligés par Boule de Feu
        } else {
            System.out.println(nom + " a déjà été utilisée.");
            return 0; // Aucun dégât infligé si la capacité a déjà été utilisée
        }
    }
    
}