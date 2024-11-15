package Interaction;

import Personnage.Heros;
import Personnage.Modele.Ennemi;

public class ActionMagique extends Action {
    
    public ActionMagique() {
        super("Attaque Magique");
    }

    @Override
    public void executer(Heros heros, Ennemi ennemi) {
        String resultatMagique = heros.attaqueMagique();
        System.out.println(resultatMagique);
        if (heros.getMana() >= 0) {
            System.out.println(ennemi.recevoirDegats(heros.getForceAttaque() * 2)); // Dégâts de l'attaque magique
        }
        System.out.println("Mana actuel de " + heros.getNom() + ": " + heros.getMana());
    }

}