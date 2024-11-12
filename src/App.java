import java.util.Scanner;

import Interaction.GestionnaireEnnemis;
import Personnage.Heros;
import Personnage.Modele.Ennemi;

public class App {

    public static void main(String[] args) {
        // Création du héros
        Heros eren = new Heros("Eren", 100, 15, "Attaque Éclair", "Guerrier");

        // Création du gestionnaire d'ennemis
        GestionnaireEnnemis gestionnaire = new GestionnaireEnnemis();

        // Scanner pour la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        int ennemisVaincus = 0; // Compteur d'ennemis vaincus

        while (continuer && eren.estVivant()) {
            // Génération d'un ennemi aléatoire
            Ennemi ennemi = gestionnaire.obtenirEnnemiAleatoire();
            System.out.println("Un nouvel ennemi apparaît: " + ennemi.getNom());

            while (ennemi.estVivant() && eren.estVivant()) {
                System.out.println("\nQue voulez-vous faire?");
                System.out.println("1. Attaque Physique");
                System.out.println("2. Attaque Magique");
                System.out.println("3. Utiliser capacité spéciale");
                System.out.print("Choix: ");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        // Attaque physique
                        int degatsPhysiques = eren.attaquePhysique();
                        System.out.println(eren.getNom() + " attaque " + ennemi.getNom() + " et inflige " + degatsPhysiques + " points de dégâts!");
                        System.out.println(ennemi.recevoirDegats(degatsPhysiques));
                        break;

                    case 2:
                        // Attaque magique
                        String resultatMagique = eren.attaqueMagique();
                        System.out.println(resultatMagique);
                        if (eren.getMana() >= 0) {
                            System.out.println(ennemi.recevoirDegats(eren.getForceAttaque() * 2)); // Dégâts de l'attaque magique
                        }
                        System.out.println("Mana actuel de " + eren.getNom() + ": " + eren.getMana()); // Affiche le mana après l'attaque magique
                        break;

                    case 3:
                        // Utiliser capacité spéciale
                        System.out.println(eren.utiliserCapacite());
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }

                // Vérification si l'ennemi est toujours vivant
                if (ennemi.estVivant()) {
                    // L'ennemi attaque
                    int degatsEnnemi = ennemi.attaquer();
                    System.out.println(ennemi.getNom() + " attaque " + eren.getNom() + " et inflige " + degatsEnnemi + " points de dégâts!");
                    System.out.println(eren.recevoirDegats(degatsEnnemi));
                } else {
                    System.out.println(ennemi.getNom() + " a été vaincu!");
                    ennemisVaincus++; // Incrémente le compteur d'ennemis vaincus
                    System.out.println("Ennemis vaincus: " + ennemisVaincus);
                }
            }

            // Vérification si le héros est toujours vivant
            if (!eren.estVivant()) {
                System.out.println(eren.getNom() + " a été vaincu!");
                continuer = false;
            }

            // Vérification si le nombre d'ennemis vaincus a atteint 3
            if (ennemisVaincus >= 3) {
                System.out.println("Félicitations! Vous avez vaincu 3 ennemis. Vous avez gagné!");
                continuer = false; // Met fin au jeu
            }
        }

        // Fermeture du scanner
        scanner.close();
    }
}
