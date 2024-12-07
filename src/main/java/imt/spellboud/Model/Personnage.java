package imt.spellboud.Model;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public abstract class Personnage {

    private final char texture = 'E';

    private int id;
    private String nom;
    private int pv;
    private int forceAttaque;

    public Personnage(int id, String nom, int pv, int forceAttaque) {
        this.id = id;
        this.nom = nom;
        this.pv = pv;
        this.forceAttaque = forceAttaque;
    }
    
    public void recevoirDegats(int degats) {
        pv -= degats;
        if (pv < 0) pv = 0;
    }

    public boolean estVivant() {
        return pv > 0;
    }

    public String getNom() {
        return nom;
    }

    public int getForceAttaque() {
        return forceAttaque;
    }
    
    public char getTexture() {
        return texture;
    }

    public int getPv() {
        return pv;
    }

    public int getId() {
        return id;
    }

    public abstract void acceptManaVisiteur(PersonnageVisitor visitor);
    public abstract void acceptRechargerManaVisiteur(PersonnageVisitor visitor);

    public void utiliserMana(int cout) {}

}
