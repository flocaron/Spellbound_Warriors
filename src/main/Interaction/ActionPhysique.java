package Interaction;

import Personnage.Heros;
import Personnage.Modele.Ennemi;

public class ActionPhysique extends Action {
    
    public ActionPhysique() {
        super("Attaque Physique");
    }

    @Override
    public void executer(Heros heros, Ennemi ennemi) {
        int degatsPhysiques = heros.attaquePhysique();
        System.out.println(heros.getNom() + " attaque " + ennemi.getNom() + " et inflige " + degatsPhysiques + " points de dégâts!");
        System.out.println(ennemi.recevoirDegats(degatsPhysiques));
    }

}