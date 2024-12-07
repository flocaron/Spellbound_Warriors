package imt.spellboud.Model.Ennemi;

import imt.spellboud.Controller.Action.Attaque.Visitor.PersonnageVisitor;

public class GuerrierSquelette extends Ennemi {

    // private final char[][] texture = {
    //     {'<', '-', '>'},
    //     {'|', 'O', '|'},
    //     {'<', '-', '>'},
    // };

    private final char texture = 'o';

    public GuerrierSquelette(int id, String nom) {
        super(id, nom, 120, 20, false, false); // PV et force d'attaque spécifiques au Guerrier Squelette
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