package imt.spellboud;


import imt.spellboud.Controller.Carte;
import imt.spellboud.Controller.Jeu;
import imt.spellboud.Model.Heros;
import imt.spellboud.Utils.Constante;

public class App {
    
    public static void main(String[] args) {

        System.out.println("Decruyenaere Hugo - Caron Florimond");

        // Scanner scan = new Scanner(System.in);

        // AttaqueSpeciale attaqueSpeciale = null;

        // while (attaqueSpeciale == null) {
        //     attaqueSpeciale = switch (scan.nextLine()) {
        //         case "boule de feu" -> new AttaqueBouleDeFeu();
        //         default -> null;
        //     };
        // }

        // scan.close();

        Carte carte = new Carte("Marché", "Port Royal", Constante.NOMBRE_SALLE, Constante.ANIMATION_TIME, Constante.MAP_SIZE_I, Constante.MAP_SIZE_J);
        Heros modeleHeros = new Heros("JackSparrow", 100, 20, "boule de feu");

        Jeu jeu = new Jeu(modeleHeros, carte, Constante.MIN_ENNEMI_MAP, Constante.MAX_ENNEMI_MAP);


        if (jeu.jouerPartie()) {
            System.out.println("Bravo vous avez gagné !!");
        } else {
            System.out.println("Dommage vous avez perdu !");
        }

        System.out.println("Vous avez vaincu " + jeu.getEnnemisVaincus() + " ennemis");


    }
}