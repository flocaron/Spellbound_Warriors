package imt.spellboud.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import imt.spellboud.Model.Heros;

public class ControllerHerosTest {

    static Carte carte;
    static Heros heros;
    static ControllerHeros controllerHeros;

    @BeforeAll
    public static void init() {
        carte = new Carte("test lieu", "test nom", 2, 0, 3, 8);
        heros = new Heros("test heros", 100, 50, "boule de feu");
    }

    @BeforeEach
    public void setup() {
        controllerHeros = new ControllerHeros(carte, heros);
        controllerHeros.initPosition();
        controllerHeros.initAction();
    }

    @Test
    public void testDeplacementValide() {
        assertEquals(0, controllerHeros.getVue().getStart_i()); // Vérifie que la position a changé
        assertEquals(0, controllerHeros.getVue().getStart_j()); // Vérifie que la position a changé

        controllerHeros.deplacer("bas");
        assertEquals(1, controllerHeros.getVue().getStart_i()); // Vérifie que la position a changé
        assertEquals(0, controllerHeros.getVue().getStart_j()); // Vérifie que la position a changé
    }

    @Test
    public void testDeplacementInvalide() {
        assertEquals(0, controllerHeros.getVue().getStart_i()); // Vérifie que la position a changé
        assertEquals(0, controllerHeros.getVue().getStart_j()); // Vérifie que la position a changé

        controllerHeros.deplacer("gauche");
        assertEquals(0, controllerHeros.getVue().getStart_i()); // Vérifie que la position a changé
        assertEquals(0, controllerHeros.getVue().getStart_j()); // Vérifie que la position a changé
    }

}
