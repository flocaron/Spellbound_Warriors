package Interaction;

import Personnage.Heros;
import Personnage.Modele.Ennemi;

public abstract class Action {

    private String nom;

    public Action(String nom) {
        this.nom = nom;
    }

    public abstract void executer(Heros heros, Ennemi ennemi);

    public String getNom() {
        return nom;
    }

}

