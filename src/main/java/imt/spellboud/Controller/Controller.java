package imt.spellboud.Controller;

import java.util.HashMap;

import imt.spellboud.Controller.Action.Attaque.Attaque;
import imt.spellboud.Controller.Action.Deplacement.Deplacement;
import imt.spellboud.View.Entite;
import imt.spellboud.View.Terminal.EntiteTerminal;

public abstract class Controller {

    private Entite vue;

    private HashMap<String, Deplacement> deplacements;
    private HashMap<String, Attaque> attaques;

    public Controller(Carte carte, char texture, int i, int j) {
        this.vue = new EntiteTerminal(carte, texture, i, j);

        deplacements = new HashMap<>();
        attaques = new HashMap<>();
    }

    public void initPosition() {
        vue.initPosition();
    }

    public void initAction() {
        initDeplacements();
        initAttaques();
    }

    public Entite getVue() {
        return vue;
    }
    
    public HashMap<String, Deplacement> getDeplacements() {
        return deplacements;
    }

    public HashMap<String, Attaque> getAttaques() {
        return attaques;
    }

    public abstract void initDeplacements();
    public abstract void initAttaques();

    // public abstract boolean deplacer();
    // public abstract boolean attaquer();
    
} 