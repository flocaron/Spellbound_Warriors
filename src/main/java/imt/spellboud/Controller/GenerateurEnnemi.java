package imt.spellboud.Controller;

import java.util.Random;

import imt.spellboud.Model.Ennemi.Ennemi;
import imt.spellboud.Model.Ennemi.Geant;
import imt.spellboud.Model.Ennemi.Gobelin;
import imt.spellboud.Model.Ennemi.GuerrierSquelette;
import imt.spellboud.Model.Ennemi.Mage;

public class GenerateurEnnemi {
    
    private int n;

    public GenerateurEnnemi() {
        n = 0;
    }

    public void reset() {
        n = 0;
    }

    public Ennemi obtenirEnnemiAleatoire() {
        Random rand = new Random();
        return switch (rand.nextInt(4)) {
            case 0 -> new Mage(++n, "Mage Noir");
            case 1 -> new GuerrierSquelette(++n, "Guerrier Squelette");
            case 2 -> new Geant(++n, "Géant Terrifiant");
            case 3 -> new Gobelin(++n, "Gobelin Rapide");
            default -> null;
        };
    }

    
}