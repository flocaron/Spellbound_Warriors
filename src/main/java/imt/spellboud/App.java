package imt.spellboud;

import java.util.Scanner;

import imt.spellboud.Interaction.GestionnaireEnnemis;
import imt.spellboud.Personnage.Heros;
import imt.spellboud.Personnage.Modele.Ennemi;

public class App {
    public static void main(String[] args) {
        System.out.print("Decruyenaere Hugo ");
        System.out.println(" Caron Florimond");
        
        // Création du héros
        Heros heros1 = new Heros("Pikachu", 250, 25, "Attaque Éclair", "Guerrier");

        // Création du gestionnaire d'ennemis
        GestionnaireEnnemis gestionnaire = new GestionnaireEnnemis();

        // Scanner pour la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        int ennemisVaincus = 0; // Compteur d'ennemis vaincus
        boolean capaciteUtilisee = false; // Suivre si la capacité spéciale a été utilisée

        while (continuer && heros1.estVivant()) {
            // Génération d'un ennemi aléatoire
            Ennemi ennemi = gestionnaire.obtenirEnnemiAleatoire();
            System.out.println("Un nouvel ennemi apparaît: " + ennemi.getNom());

            while (ennemi.estVivant() && heros1.estVivant()) {
                // Demander la distance entre le héros et l'ennemi
                System.out.print("Entrez la distance entre " + heros1.getNom() + " et " + ennemi.getNom() + ": ");
                int distance = scanner.nextInt();

                System.out.println("\nQue voulez-vous faire?");
                System.out.print("1. Attaque Physique ");
                System.out.print("2. Attaque Magique ");
                if (!capaciteUtilisee) { // Affiche le choix 3 seulement si la capacité n'a pas été utilisée
                    System.out.print("3. Utiliser capacité spéciale ");
                }
                System.out.print("\nChoix: ");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1 -> {
                        // Vérifie si l'ennemi est à portée pour une attaque physique
                        if (heros1.getPorteeAttaque().estDansPortee(distance, "physique")) {
                            // Effectuer l'attaque physique
                            int degatsPhysiques = heros1.attaquePhysique();
                            System.out.println(heros1.getNom() + " attaque " + ennemi.getNom() + " et inflige " + degatsPhysiques + " points de dégâts!");
                            System.out.println(ennemi.recevoirDegats(degatsPhysiques));
                        } else {
                            System.out.println(ennemi.getNom() + " est trop loin pour une attaque physique.");
                        }
                    }
                    case 2 -> {
                        // Attaque magique
                        if (heros1.getPorteeAttaque().estDansPortee(distance, "magique")) {
                            String resultatMagique = heros1.attaqueMagique();
                            System.out.println(resultatMagique);
                            System.out.println(ennemi.recevoirDegats(heros1.getForceAttaque() * 2)); // Dégâts de l'attaque magique
                            System.out.println("Mana actuel de " + heros1.getNom() + ": " + heros1.getMana()); // Affiche le mana après l'attaque magique
                        } else {
                            System.out.println(ennemi.getNom() + " est trop loin pour une attaque magique.");
                        }
                    }
                    case 3 -> {
                        // Utiliser capacité spéciale
                        if (!capaciteUtilisee) { // Vérifie si la capacité n'a pas été utilisée
                            System.out.print("Entrez la superficie de la salle: ");
                            int superficieSalle = scanner.nextInt();
                            System.out.println("Pkiachu utilise Attaque Éclaire");
                            System.out.println(heros1.utiliserCapaciteSpeciale(superficieSalle));
                            capaciteUtilisee = true; // Marque la capacité comme utilisée
                        } else {
                            System.out.println("Vous avez déjà utilisé votre capacité spéciale!");
                        }
                    }
                    default -> {
                        System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                }

                // Vérification si l'ennemi est toujours vivant
                if (ennemi.estVivant()) {
                    // L'ennemi attaque
                    int degatsEnnemi = ennemi.attaquer();
                    System.out.println(ennemi.getNom() + " attaque " + heros1.getNom() + " et inflige " + degatsEnnemi + " points de dégâts!");
                    System.out.println(heros1.recevoirDegats(degatsEnnemi));
                } else {
                    System.out.println(ennemi.getNom() + " a été vaincu!");
                    ennemisVaincus++;
                }
            }
        }

        System.out.println("Vous avez vaincu " + ennemisVaincus + " ennemis.");
        scanner.close();
    }
}