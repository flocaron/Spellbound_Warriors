package imt.spellboud.Controller.Action.Attaque.Speciale;

import java.util.ArrayList;

import imt.spellboud.Controller.ControllerEnnemi;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class AttaqueBouleDeFeu extends AttaqueSpeciale {

    private int degat;

    public AttaqueBouleDeFeu() {
        super("Boule de Feu");
        degat = 200;
    }

    // Méthode pour utiliser la capacité Boule de Feu
    // public int utiliserBouleDeFeu(Ennemi cible) {
    //     if (!utilisee) {
    //         utilisee = true; // Marquer la capacité comme utilisée
    //         return 200; // Retourne les dégâts infligés par Boule de Feu
    //     } else {
    //         System.out.println(nom + " a déjà été utilisée.");
    //         return 0; // Aucun dégât infligé si la capacité a déjà été utilisée
    //     }
    // }

    // public String utiliserCapaciteSpeciale(int superficieSalle) {
    //     int portee = porteeAttaque.getPorteeCapaciteSpeciale(superficieSalle); // Récupère la portée de la capacité spéciale
    //     return super.getNom() + " utilise sa capacité spéciale! Portée: " + portee + " (couvre toute la salle de " + superficieSalle + " unités).";
    // }

    @Override
    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        super.attaque(personnage, vue, cible, vueCible);
        if (!super.getUtilisee()) {
            cible.recevoirDegats(degat);
            System.out.println(cible.getNom() + " reçoit " + degat + " points de dégâts");
            if (!cible.estVivant()) {
                System.out.println(cible.getNom() + " est mort !");
                vueCible.efface();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean attaqueSpeciale(Personnage personnage, Entite vue, ArrayList<ControllerEnnemi> cibles) {
        if (!super.getUtilisee()) {
            cibles.forEach(cible -> attaque(personnage, vue, cible.getModele(), cible.getVue()));
            super.setUtilisee();
            return true;
        }
        System.out.println(personnage.getNom() + " a déjà utilisée " + super.getNom());
        return false;
    }
    
}
