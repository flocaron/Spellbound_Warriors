package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementHaut extends Deplacement {

    public DeplacementHaut() {
        super("Deplacement haut");
    }

    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        super.deplace(personnage, vue);
        return vue.deplacerHaut();
    }
    
}
