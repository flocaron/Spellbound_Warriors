package imt.spellboud.View.Terminal;

import imt.spellboud.Controller.Carte;
import imt.spellboud.View.Entite;

public class EntiteTerminal implements Entite {

    private Carte map;
    private char texture;
    private int start_i;
    private int start_j;

    public EntiteTerminal(Carte map, char texture, int start_i, int start_j) {
        this.map = map;
        this.texture = texture;
        this.start_i = start_i;
        this.start_j = start_j;
    }

    @Override
    public void initPosition() {
        map.getCurrentSalle().start(this);
    }

    @Override
    public char getTexture() {
        return texture;
    }

    @Override
    public int getStart_i() {
        return start_i;
    }

    @Override
    public void setStart_i(int start_i) {
        this.start_i = start_i;
    }

    @Override
    public int getStart_j() {
        return start_j;
    }

    @Override
    public void setStart_j(int start_j) {
        this.start_j = start_j;
    }

    @Override
    public boolean deplacerHaut() {
        return map.getCurrentSalle().deplacerHaut(this);
    }

    @Override
    public boolean deplacerBas() {
        return map.getCurrentSalle().deplacerBas(this);
    }

    @Override
    public boolean deplacerDroite() {
        return map.getCurrentSalle().deplacerDroite(this);
    }

    @Override
    public boolean deplacerGauche() {
        return map.getCurrentSalle().deplacerGauche(this);
    }

    // dsitance de Manhattan
    @Override
    public int porte(Entite vue) {
        return Math.abs(start_i - vue.getStart_i()) + Math.abs(start_j - vue.getStart_j());
    }

    @Override
    public void efface() {
        map.getCurrentSalle().efface(this);
    }

}
