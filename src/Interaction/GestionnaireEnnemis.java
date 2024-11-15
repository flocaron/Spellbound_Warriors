package Interaction;

import Personnage.Modele.Ennemi;
import java.util.ArrayList;
import java.util.Random;

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

    // Classe interne pour Mage
    public static class Mage extends Ennemi {
        private int mana;

        public Mage(String nom) {
            super(nom, 80, 15); // PV et force d'attaque spécifiques au Mage
            this.mana = 100; // Initialisation du mana
        }

        public String attaqueMagique() {
            if (mana >= 30) { // Coût de l'attaque magique
                mana -= 30; // Déduit le coût du mana
                return super.getNom() + " utilise une attaque magique puissante! Dégâts infligés: " + (super.getForceAttaque() * 3);
            } else {
                return super.getNom() + " n'a pas assez de mana pour effectuer une attaque magique!";
            }
        }
    }

    // Classe interne pour GuerrierSquelette
    public static class GuerrierSquelette extends Ennemi {
        public GuerrierSquelette(String nom) {
            super(nom, 120, 20); // PV et force d'attaque spécifiques au Guerrier Squelette
        }
    }

    // Classe interne pour Geant
    public static class Geant extends Ennemi {
        public Geant(String nom) {
            super(nom, 200, 10); // PV élevés et force d'attaque plus faible
        }
    }
}