package imt.spellboud.Interaction;

import imt.spellboud.Personnage.Heros;
import imt.spellboud.Personnage.Modele.Ennemi;

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

