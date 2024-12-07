package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementAnnule extends Deplacement {

    public DeplacementAnnule() {
        super("Deplacement annulé");
    }
    
    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        return super.deplace(personnage, vue);
    }
    
}
