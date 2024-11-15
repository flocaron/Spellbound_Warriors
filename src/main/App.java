
import Interaction.GestionnaireEnnemis;
import Personnage.Heros;
import Personnage.Modele.Ennemi;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.print("Decruyenaere Hugo ");
        System.out.println(" Caron Florimond");
        
        // Création du héros
        Heros heros1 = new Heros("Sunraku", 250, 25, "Attaque Éclair", "Guerrier");

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
                        // Attaque physique
                        int degatsPhysiques = heros1.attaquePhysique();
                        System.out.println(heros1.getNom() + " attaque " + ennemi.getNom() + " et inflige " + degatsPhysiques + " points de dégâts!");
                        System.out.println(ennemi.recevoirDegats(degatsPhysiques));
                    }
                    case 2 -> {
                        // Attaque magique
                        String resultatMagique = heros1.attaqueMagique();
                        System.out.println(resultatMagique);
                        if (heros1.getMana() >= 0) {
                            System.out.println(ennemi.recevoirDegats(heros1.getForceAttaque() * 2)); // Dégâts de l'attaque magique
                        }
                        System.out.println("Mana actuel de " + heros1.getNom() + ": " + heros1.getMana()); // Affiche le mana après l'attaque magique
                    }
                    case 3 -> {
                        // Utiliser capacité spéciale
                        if (!capaciteUtilisee) { // Vérifie si la capacité n'a pas été utilisée
                            System.out.println(heros1.utiliserCapacite());
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
                    ennemisVaincus++; // Incrémente le compteur d'ennemis vaincus
                    System.out.println("Ennemis vaincus: " + ennemisVaincus);
                }
            }

            // Vérification si le héros est toujours vivant
            if (!heros1.estVivant()) {
                System.out.println(heros1.getNom() + " a été vaincu!");
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
