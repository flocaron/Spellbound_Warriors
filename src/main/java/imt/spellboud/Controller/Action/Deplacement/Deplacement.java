package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Controller.Action.Action;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public abstract class Deplacement extends Action { 

    public Deplacement(String nom) {
        super(nom);
    }

    public boolean deplace(Personnage personnage, Entite vue) {
        System.out.println("Deplacement: " + personnage.getNom() + " utilise " + super.getNom());
        return true;
    }

    

}
