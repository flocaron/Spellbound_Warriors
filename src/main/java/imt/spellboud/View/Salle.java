package imt.spellboud.View;

public interface Salle {

    int getLargeur();
    int getLongeur();

    void start(Entite e);
    void clearEntite(Entite e);

    boolean deplacerHaut(Entite e);
    boolean deplacerGauche(Entite e);
    boolean deplacerBas(Entite e);
    boolean deplacerDroite(Entite e);

    void displayMap(int sec, boolean debug);
    
    boolean placeDisponible(int i, int j);

    void efface(Entite e);

    // pour les tests
    void placerVue(Entite vue);

}