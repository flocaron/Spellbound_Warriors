package imt.spellboud.Controller.Action.Attaque;

import java.util.Random;

import imt.spellboud.Controller.Action.Attaque.Visitor.ManaVisitor;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class AttaqueMagique extends Attaque {

    private int cout;

    public AttaqueMagique() {
        super("Attaque Magique", 5);
        cout = 20; // Une attaque magique coute 20 points de mana
    }

    @Override
    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        Random random = new Random();

        super.attaque(personnage, vue, cible, vueCible);
        if (super.aPorte(vue, vueCible)) {
            ManaVisitor manaVisitor = new ManaVisitor();
            personnage.acceptManaVisiteur(manaVisitor);
            if (manaVisitor.getMana() >= cout) {
                personnage.utiliserMana(cout);
                cible.recevoirDegats(personnage.getForceAttaque() * 3);
                System.out.println(cible.getNom() + " reçoit " + personnage.getForceAttaque() * random.nextInt(3, 6) + " points de dégâts"); 
                if (!cible.estVivant()) {
                    System.out.println(cible.getNom() + " est mort !");
                    vueCible.efface();
                }
                return true;
            }
            System.out.println(personnage.getNom() + " n'a pas assez de mana pour " + super.getNom());
            return false;
            
        }
        System.out.println(cible.getNom() + " n'est pas a porté de " + super.getNom());
        return false;
    }

    // public String attaqueMagique() {
    //     if (mana >= 20) { // Coût de l'attaque magique
    //         mana -= 20; // Déduit le coût du mana
    //         attaquesPhysiquesEffectuees = 0; // Réinitialise le compteur d'attaques physiques
    //         return super.getNom() + " utilise une attaque magique! Dégâts infligés: " + ((getForceAttaque()) + 10 * 2) + 
    //                ". Portée: " + porteeAttaque.getPorteeMagique(); // Dégâts et portée de l'attaque magique
    //     } else {
    //         return super.getNom() + " n'a pas assez de mana pour effectuer une attaque magique!";
    //     }
    // }
    
}
