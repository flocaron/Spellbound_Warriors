package imt.spellboud.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import imt.spellboud.Model.Ennemi.Geant;
import imt.spellboud.Model.Heros;

public class ControllerEnnemisTest {

    static Carte carte;
    static Heros heros;
    static ControllerEnnemi controllerEnnemi;
    static ControllerHeros controllerHeros;

    @BeforeAll
    public static void init() {
        carte = new Carte("test lieu", "test nom", 2, 0, 8, 8);
    }

    @BeforeEach
    public void setup() {
        heros = new Heros("test heros", 100, 50, "boule de feu");
        controllerHeros = new ControllerHeros(carte, heros);
        controllerHeros.initPosition();
        controllerEnnemi = new ControllerEnnemi(carte, new Geant(1, "geant"), 3, 3);
        controllerEnnemi.initAction();
    }

    @Test
    public void attaqueTest() {
        controllerEnnemi.getVue().setStart_i(0);
        controllerEnnemi.getVue().setStart_j(1);
        controllerEnnemi.initPosition();

        carte.getCurrentSalle().placerVue(controllerEnnemi.getVue());
        int pvInitialHeros = heros.getPv();

        assertTrue(controllerEnnemi.attaquer(controllerHeros));
        assertEquals(pvInitialHeros - controllerEnnemi.getModele().getForceAttaque(), heros.getPv());
    }

    @Test
    public void attaqueHorsPorteeTest() {
        controllerEnnemi.getVue().setStart_i(5);
        controllerEnnemi.getVue().setStart_j(5);
        controllerEnnemi.initPosition();

        carte.getCurrentSalle().placerVue(controllerEnnemi.getVue());
        int pvInitialHeros = heros.getPv();

        assertTrue(controllerEnnemi.attaquer(controllerHeros));
        assertEquals(pvInitialHeros, heros.getPv());
    }

}
