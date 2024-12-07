package imt.spellboud.Model.Ennemi;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public class Gobelin extends Ennemi {

    private final char texture = '*';
    
    public Gobelin(int id, String nom) {
        super(id, nom, 100, 10, true, false); // Force d'attaque plus faible et attaque priorise
    }

    @Override
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
