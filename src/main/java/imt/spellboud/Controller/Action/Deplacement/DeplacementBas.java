package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementBas extends Deplacement {

    public DeplacementBas() {
        super("Deplacement bas");
    }

    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        super.deplace(personnage, vue);
        return vue.deplacerBas();
    }
    
}
