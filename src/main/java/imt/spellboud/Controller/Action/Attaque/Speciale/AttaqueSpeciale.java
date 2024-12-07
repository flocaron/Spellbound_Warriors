package imt.spellboud.Controller.Action.Attaque.Speciale;

import java.util.ArrayList;

import imt.spellboud.Controller.ControllerEnnemi;
import imt.spellboud.Controller.Action.Attaque.Attaque;
import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public abstract class AttaqueSpeciale extends Attaque {

    private boolean utilisee;

    public AttaqueSpeciale(String nom) {
        super(nom, -1);
        utilisee = false;
    }

    public void setUtilisee() {
        this.utilisee = true;
    }

    public boolean getUtilisee() {
        return utilisee;
    }

    public String getNom() {
        return super.getNom();
    }

    @Override
    public boolean attaque(Personnage personnage, Entite vue, Personnage cible, Entite vueCible) {
        return super.attaque(personnage, vue, cible, vueCible);
    }

    @Override
    public boolean aPorte(Entite vue, Entite cible) {
        return true;
    }

    public abstract boolean attaqueSpeciale(Personnage personnage, Entite vue, ArrayList<ControllerEnnemi> cibles);



    
}