package imt.spellboud.Model.Ennemi;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public class Mage extends Ennemi {

    // public static final char[][] texture = {
    //     {'^', '-', '^'},
    //     {'|', 'O', '|'},
    //     {'^', '-', '^'},
    // };

    public static final char texture = '^';

    private int mana;

    public Mage(int id, String nom) {
        super(id, nom, 80, 15, false, true); // PV et force d'attaque spécifiques au Mage
        this.mana = 100; // Initialisation du mana
    }

    public void rechargerMana() {
        mana += 40; // Recharge 20 points de mana après 3 attaques physiques
        System.out.println(super.getNom() + " recharge son mana! Mana actuel: " + mana);
        if (mana >= 100) mana = 100;
    }

    public char getTexture() {
        return texture;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void acceptManaVisiteur(PersonnageVisitor visitor) {
        visitor.getManaMage(this);
    }

    @Override
    public void acceptRechargerManaVisiteur(PersonnageVisitor visitor) {
        visitor.rechargerManaMage(this);
    }

    @Override
    public void utiliserMana(int cout) {
        mana -= cout;
    }
}