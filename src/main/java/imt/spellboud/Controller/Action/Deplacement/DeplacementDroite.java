package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementDroite extends Deplacement {

    public DeplacementDroite() {
        super("Deplacement droite");
    }

    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        super.deplace(personnage, vue);
        return vue.deplacerDroite();
    }
    
}
