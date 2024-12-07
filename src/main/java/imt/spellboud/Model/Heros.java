package imt.spellboud.Model;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public class Heros extends Personnage {

    // public static final char[][] texture = {
    //     {'+', '-', '+'},
    //     {'|', 'O', '|'},
    //     {'+', '-', '+'},
    // };

    public static final char texture = '+';

    private int mana;
    private String nomCapaciteSpeciale;

    public Heros(String nom, int pv, int forceAttaque, String nomCapaciteSpeciale) {
        super(0, nom, pv, forceAttaque);
        this.mana = 100; // Initialisation du mana
        this.nomCapaciteSpeciale = nomCapaciteSpeciale;
    }

    public void rechargerMana() {
        mana += 20; // Recharge 20 points de mana après 3 attaques physiques
        System.out.println(super.getNom() + " recharge son mana! Mana actuel: " + mana);
        if (mana >= 100) mana = 100;
    }

    public int getForceAttaque() {
        return super.getForceAttaque();
    }

    public int getMana() {
        return mana;
    }

    public char getTexture() {
        return texture;
    }

    public String getNomCapaciteSpeciale() {
        return nomCapaciteSpeciale;
    }

    @Override
    public void acceptManaVisiteur(PersonnageVisitor visitor) {
        visitor.getManaHeros(this);
    }

    @Override
    public void acceptRechargerManaVisiteur(PersonnageVisitor visitor) {
        visitor.rechargerManaHeros(this);
    }

    @Override
    public void utiliserMana(int cout) {
        mana -= cout;
    }
        
}