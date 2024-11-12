package Personnage;

import Interaction.CapaciteSpeciale;
import Personnage.Modele.Personnage;

public class Heros extends Personnage {

    private String typeHeros;
    private  int mana;
    private int attaquesPhysiquesEffectuees;
    private CapaciteSpeciale capaciteSpeciale; // Agrégation de CapaciteSpeciale
    
    public Heros(String nom, int pv, int forceAttaque, String nomCapaciteSpeciale, String typeHeros) {
        super(nom, pv, forceAttaque);
        this.typeHeros = typeHeros;
        this.mana = 100; // Initialisation du mana
        this.attaquesPhysiquesEffectuees = 0; // Compteur d'attaques physiques
        this.capaciteSpeciale = new CapaciteSpeciale(nomCapaciteSpeciale); // Initialisation de la capacité spéciale
    }

    public String getTypeHeros() {
        return typeHeros;
    }

    @Override
    public String utiliserCapacite() {
        return capaciteSpeciale.utiliser(); // Utilise la capacité spéciale via l'instance
    }
    

    public int attaquePhysique() {
        int degats = super.attaquer(); // Utilisation de la méthode attaquer de Personnage
        attaquesPhysiquesEffectuees++;
        if (attaquesPhysiquesEffectuees >= 3) {
            rechargerMana();
        }
        return degats; // Retourne les dégâts infligés par l'attaque physique
    }

    public String attaqueMagique() {
        if (mana >= 20) { // Coût de l'attaque magique
           mana -= 20; // Déduit le coût du mana
            attaquesPhysiquesEffectuees = 0; // Réinitialise le compteur d'attaques physiques
            return super.getNom() + " utilise une attaque magique! Dégâts infligés: " + ((getForceAttaque()) + 1* 2); // Dégâts de l'attaque magique
        } else {
            return super.getNom() + " n'a pas assez de mana pour effectuer une attaque magique!";
        }
    }

    private void rechargerMana() {
        mana += 20; // Recharge 20 points de mana après 3 attaques physiques
        attaquesPhysiquesEffectuees = 0; // Réinitialise le compteur
        System.out.println(super.getNom() + " recharge son mana! Mana actuel: " + mana);
    }

    public int getForceAttaque() {
        return super.getForceAttaque();
    }

    public int getMana() {
        return mana;
    }

    


}