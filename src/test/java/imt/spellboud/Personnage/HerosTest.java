package imt.spellboud.Personnage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


public class HerosTest {

    static Heros heros;
    static Mage ennemi1;

    @BeforeAll
	public static void init(){
		heros = new Heros("test1", 250, 25, "Boule de feu", "Guerrier");
        ennemi1 = new Mage("Mage Noir");
	}

    @Test
    public void attaqueMagiqueTest() {
        heros.attaqueMagique();
        assertEquals(heros.getMana(), 80);
    }


}
