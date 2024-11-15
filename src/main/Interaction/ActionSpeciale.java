package Interaction;

import Personnage.Heros;
import Personnage.Modele.Ennemi;

public class ActionSpeciale extends Action {
    
    public ActionSpeciale() {
        super("Capacité Spéciale");
    }

    @Override
    public void executer(Heros heros, Ennemi ennemi) {
        System.out.println(heros.utiliserCapacite());
    }

}