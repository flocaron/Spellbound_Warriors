package imt.spellboud.Model.Ennemi;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public class Geant extends Ennemi {

    // private final char[][] texture = {
    //     {'+', '¯', '+'},
    //     {'(', 'O', ')'},
    //     {'+', '_', '+'},
    // };

    private final char texture = '@';

    public Geant(int id, String nom) {
        super(id, nom, 200, 10, false, false); // PV élevés et force d'attaque plus faible
    }

    public char getTexture() {
        return texture;
    }

    @Override
    public void acceptManaVisiteur(PersonnageVisitor visitor) {
        visitor.getManaAutre(this);
    }

    @Override
    public void acceptRechargerManaVisiteur(PersonnageVisitor visitor) {
        visitor.rechargerManaAutre(this);
    }
}

