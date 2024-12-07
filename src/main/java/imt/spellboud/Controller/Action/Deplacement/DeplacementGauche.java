package imt.spellboud.Controller.Action.Deplacement;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementGauche extends Deplacement {
    
    public DeplacementGauche() {
        super("Deplacement gauche");
    }

    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        super.deplace(personnage, vue);
        return vue.deplacerGauche();
    }
}
