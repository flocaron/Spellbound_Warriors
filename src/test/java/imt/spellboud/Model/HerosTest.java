package imt.spellboud.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import imt.spellboud.Model.Ennemi.Mage;

import org.junit.jupiter.api.BeforeAll;


public class HerosTest {

    static Heros heros;
    static Mage ennemi1;

    @BeforeAll
	public static void init(){
		heros = new Heros("test1", 250, 25, "boule de feu");
        ennemi1 = new Mage(-1, "Mage Noir");
	}

    @Test
    public void rechargerManaTestSuperieur100() {
        heros.rechargerMana();
        assertEquals(heros.getMana(), 100);
    }


}
