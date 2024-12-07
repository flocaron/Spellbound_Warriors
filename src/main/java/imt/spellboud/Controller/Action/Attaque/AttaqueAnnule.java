package imt.spellboud.Controller.Action.Attaque;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class AttaqueAnnule extends Attaque {
    
    public AttaqueAnnule() {
        super("Attaque annulé", 0);
    }
    
    @Override
    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        return super.attaque(personnage, vue, cible, vueCible);
    }

    @Override
    public boolean aPorte(Entite vue, Entite cible) {
        return true;
    }

}
