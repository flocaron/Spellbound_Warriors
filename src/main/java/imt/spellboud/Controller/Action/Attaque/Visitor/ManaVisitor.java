package imt.spellboud.Controller.Action.Attaque.Visitor;

import imt.spellboud.Model.Heros;
import imt.spellboud.Model.Personnage;
import imt.spellboud.Model.Ennemi.Mage;

public class ManaVisitor implements PersonnageVisitor {

    private int mana = 0;

    public int getMana() {
        return mana;
    }

    @Override
    public void getManaHeros(Heros heros) {
        mana = heros.getMana();
    }

    @Override
    public void rechargerManaHeros(Heros heros) {
        heros.rechargerMana();
    }


    @Override
    public void getManaMage(Mage mage) {
        mana = mage.getMana();
    }

    @Override
    public void rechargerManaMage(Mage mage) {
        mage.rechargerMana();
    }




    @Override
    public void getManaAutre(Personnage personnage) {
        mana = 0; // Par défaut, pas de mana pour les autres personnages
    }

    @Override
    public void rechargerManaAutre(Personnage heros) {}




}

