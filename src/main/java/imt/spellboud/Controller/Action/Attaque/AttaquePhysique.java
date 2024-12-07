package imt.spellboud.Controller.Action.Attaque;

import imt.spellboud.Controller.Action.Attaque.Visitor.ManaVisitor;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class AttaquePhysique extends Attaque {

    private int compteur;

    public AttaquePhysique() {
        super("Attaque Physique", 2);
        compteur = 0;
    }

    public void reInitCompteur() {
        compteur = 0;
    }

    public void incrementCompteur() {
        compteur++;
    }

    @Override
    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        super.attaque(personnage, vue, cible, vueCible);
        if (super.aPorte(vue, vueCible)) {
            cible.recevoirDegats(personnage.getForceAttaque());
            System.out.println(cible.getNom() + " reçoit " + personnage.getForceAttaque() + " points de dégâts");
            incrementCompteur();
            if (compteur == 3) {
                personnage.acceptRechargerManaVisiteur(new ManaVisitor());
                reInitCompteur();
            }

            if (!cible.estVivant()) {
                System.out.println(cible.getNom() + " est mort !");
                vueCible.efface();
            }
            return true;
        }
        System.out.println(cible.getNom() + " n'est pas a porté de " + super.getNom());
        return false;
    }
 

    // public int attaquePhysique() {
    //     int degats = super.attaquer(); // Utilisation de la méthode attaquer de Personnage
    //     attaquesPhysiquesEffectuees++;
    //     if (attaquesPhysiquesEffectuees >= 3) {
    //         rechargerMana();
    //     }
    //     return degats; // Retourne les dégâts infligés par l'attaque physique
    // }
    
}
