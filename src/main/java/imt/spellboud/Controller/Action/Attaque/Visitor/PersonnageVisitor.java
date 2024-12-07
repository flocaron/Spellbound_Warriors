package imt.spellboud.Controller.Action.Attaque.Visitor;

import imt.spellboud.Model.Heros;
import imt.spellboud.Model.Personnage;
import imt.spellboud.Model.Ennemi.Mage;

public interface PersonnageVisitor {

    void getManaHeros(Heros heros);
    void rechargerManaHeros(Heros heros);
    // void utiliserManaHeros(Heros heros);

    void getManaMage(Mage mage);
    void rechargerManaMage(Mage mage);
    // void utiliserManaMage(Mage mage);

    
    void getManaAutre(Personnage personnage);
    void rechargerManaAutre(Personnage personnage);
    // void utiliserManaAutre(Personnage personnage);
}
