package imt.spellboud.Controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import imt.spellboud.Model.Heros;
import imt.spellboud.Model.Ennemi.Geant;
import imt.spellboud.Model.Ennemi.Mage;

public class JeuTest {

    static Carte carte;
    static Heros heros;
    static Jeu jeu;

    @BeforeAll
    public static void init() {
        carte = new Carte("test lieu", "test nom", 2, 0, 3, 8);
        heros = new Heros("test nom heros", 100, 500, "boule de feu");
    }

    @BeforeEach
    public void setup() {
        jeu = new Jeu(heros, carte, 2, 3);
        jeu.getControllerHeros().initAction();
    }


    @Test
    public void tueEnnemiTest() {
        int ennemiPositionI = 0;
        int ennemiPositionJ = 1;

        ControllerEnnemi ennemi = new ControllerEnnemi(carte, new Geant(1, "geant"), ennemiPositionI, ennemiPositionJ);
        jeu.getControllerEnnemis().add(ennemi);
        jeu.initPositionEnnemis();

        assertFalse(carte.getCurrentSalle().placeDisponible(ennemiPositionI, ennemiPositionJ));
        assertTrue(jeu.getControllerHeros().getAttaques().get("physique").attaque(heros, jeu.getControllerHeros().getVue(), ennemi.getModele(), ennemi.getVue()));
        assertTrue(carte.getCurrentSalle().placeDisponible(ennemiPositionI, ennemiPositionJ));
    }

    @Test
    public void herosMortFinPartie() {
        heros.recevoirDegats(heros.getPv());
        assertFalse(jeu.jouerPartie());
    }

    @Test
    public void ennemisMortTest() {
        ControllerEnnemi ennemi1 = new ControllerEnnemi(carte, new Geant(1, "geant"), 0, 1);
        ControllerEnnemi ennemi2 = new ControllerEnnemi(carte, new Mage(1, "sorcier"), 1, 1);
        jeu.getControllerEnnemis().add(ennemi1);
        jeu.getControllerEnnemis().add(ennemi2);

        ennemi1.getModele().recevoirDegats(ennemi1.getModele().getPv());
        ennemi2.getModele().recevoirDegats(ennemi2.getModele().getPv());

        assertTrue(jeu.ennemisMort());
    }
    
}
