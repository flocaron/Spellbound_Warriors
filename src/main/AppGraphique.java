


import Interaction.Action;
import Interaction.ActionPhysique;
import Interaction.ActionMagique;
import Interaction.ActionSpeciale;

import Interaction.GestionnaireEnnemis;
import Personnage.Heros;
import Personnage.Modele.Ennemi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Classe principale
public class AppGraphique {
    public static void main(String[] args) {
        System.out.print("Decruyenaere Hugo ");
        System.out.println(" Caron Florimond");

        // Création du héros
        Heros heros1 = new Heros("Sunraku", 250, 25, "Attaque Éclair", "Guerrier");

        // Création du gestionnaire d'ennemis
        GestionnaireEnnemis gestionnaire = new GestionnaireEnnemis();

        boolean continuer = true;
        int ennemisVaincus = 0; // Compteur d'ennemis vaincus

        while (continuer && heros1.estVivant()) {
            // Génération d'un ennemi aléatoire
            Ennemi ennemi = gestionnaire.obtenirEnnemiAleatoire();
            System.out.println("Un nouvel ennemi Apparaît: " + ennemi.getNom());

            // Créer les objets d'action
            Action actionPhysique = new ActionPhysique();
            Action actionMagique = new ActionMagique();
            Action actionSpeciale = new ActionSpeciale();

            // Créer le cadre pour le menu d'actions
            JFrame frame = new JFrame("Menu d'Actions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 100);
            frame.setLayout(new FlowLayout());

            // Créer des boutons pour chaque action
            JButton boutonPhysique = new JButton(actionPhysique.getNom());
            JButton boutonMagique = new JButton(actionMagique.getNom());
            JButton boutonSpeciale = new JButton(actionSpeciale.getNom());

            // Ajouter des écouteurs d'action aux boutons
            boutonPhysique.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actionPhysique.executer(heros1, ennemi);
                    verifierEtat(heros1, ennemi);
                    frame.dispose(); // Fermer la fenêtre après l'action
                }
            });

            boutonMagique.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actionMagique.executer(heros1, ennemi);
                    verifierEtat(heros1, ennemi);
                    frame.dispose(); // Fermer la fenêtre après l'action
                }
            });

            boutonSpeciale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actionSpeciale.executer(heros1, ennemi);
                    verifierEtat(heros1, ennemi);
                    frame.dispose(); // Fermer la fenêtre après l'action
                }
            });

            // Ajouter les boutons au cadre
            frame.add(boutonPhysique);
            frame.add(boutonMagique);
            frame.add(boutonSpeciale);

            // Rendre le cadre visible
            frame.setVisible(true);

            // Attendre que l'utilisateur ferme le cadre
            while (frame.isVisible()) {
                try {
                    Thread.sleep(100); // Pause pour éviter une boucle trop rapide
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            // Vérifier si l'ennemi est vaincu
            if (!ennemi.estVivant()) {
                ennemisVaincus++;
                System.out.println("Vous avez vaincu " + ennemi.getNom() + " !");
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

        System.out.println("Fin du jeu. Vous avez vaincu " + ennemisVaincus + " ennemis.");
    }

    private static void verifierEtat(Heros heros, Ennemi ennemi) {
        if (!ennemi.estVivant()) {
            System.out.println(ennemi.getNom() + " est vaincu !");
        } else if (!heros.estVivant()) {
            System.out.println(heros.getNom() + " est tombé au combat !");
        }
    }
}
