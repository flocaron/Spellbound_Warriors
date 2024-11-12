package Interaction;
import java.util.ArrayList;
import java.util.Random;

import Personnage.Geant;
import Personnage.GuerrierSquelette;
import Personnage.Mage;
import Personnage.Modele.Ennemi;

public class GestionnaireEnnemis {
    
    private ArrayList<Ennemi> ennemis;

    public GestionnaireEnnemis() {
        ennemis = new ArrayList<>();
        // Ajout d'ennemis à la liste
        ennemis.add(new Mage("Mage Noir"));
        ennemis.add(new GuerrierSquelette("Guerrier Squelette"));
        ennemis.add(new Geant("Géant Terrifiant"));
    }

    public Ennemi obtenirEnnemiAleatoire() {
        Random rand = new Random();
        int index = rand.nextInt(ennemis.size()); // Sélectionne un index aléatoire
        return ennemis.get(index); // Retourne l'ennemi à cet index
    }


}
