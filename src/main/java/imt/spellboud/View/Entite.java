package imt.spellboud.View;

public interface Entite {

    // char[][] getTexture2D();
    char getTexture();

    void initPosition();

    int getStart_i();
    void setStart_i(int i);
    
    int getStart_j();
    void setStart_j(int j);
    
    boolean deplacerHaut();
    boolean deplacerGauche();
    boolean deplacerBas();
    boolean deplacerDroite();

    int porte(Entite vue);

    void efface();

}
