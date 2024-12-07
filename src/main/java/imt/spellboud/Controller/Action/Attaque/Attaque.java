package imt.spellboud.Controller.Action.Attaque;

import imt.spellboud.Controller.Action.Action;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public abstract class Attaque extends Action {

    private int porte;

    public Attaque(String nom, int porte) {
        super(nom);
        this.porte = porte;
    }

    public int getPorte() {
        return porte;
    }

    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        System.out.println("Attaque: " +  personnage.getNom() + " utilise " + super.getNom());
        return true;
    }

    public boolean aPorte(Entite vue, Entite cible) {
        return vue.porte(cible) <= getPorte();
    }
}
