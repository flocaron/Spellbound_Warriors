package imt.spellboud.Personnage;

import imt.spellboud.Personnage.Modele.Ennemi;

public class Mage extends Ennemi {

    private int mana;

    public Mage(String nom) {
        super(nom, 80, 15); // PV et force d'attaque spécifiques au Mage
        this.mana = 100; // Initialisation du mana
    }

    public String attaqueMagique() {
        if (mana >= 30) { // Coût de l'attaque magique
            mana -= 30; // Déduit le coût du mana
            return super.getNom() + " utilise une attaque magique puissante! Dégâts infligés: " + (super.getForceAttaque() * 3);
        } else {
            return super.getNom() + " n'a pas assez de mana pour effectuer une attaque magique!";
        }
    }
}