package imt.spellboud.Controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import imt.spellboud.Model.Heros;
import imt.spellboud.Model.Ennemi.Ennemi;
import imt.spellboud.Utils.Constante;

public class Jeu {

    private Carte carte;
    private ControllerHeros controllerHeros;
    private ArrayList<ControllerEnnemi> controllerEnnemis;

    private GenerateurEnnemi generateurEnnemi;

    private int min;
    private int max;

    public Jeu(Heros heros, Carte carte, int min, int max) {
        this.carte = carte;
        controllerHeros = new ControllerHeros(carte, heros);
        controllerEnnemis = new ArrayList<>();

        generateurEnnemi = new GenerateurEnnemi();

        this.min = min;
        this.max = max;
    }

    public void jouerPartie() {        
        Scanner scan = new Scanner(System.in);

        boolean carteFinie = false;
        controllerHeros.initAction();

        while (!carteFinie && controllerHeros.estVivant()) {
            initSalle();

            while (!ennemisMort() && controllerHeros.estVivant()) {
                
                // joue les ennemis plus rapide que le heros
                jouerEnnemisPriorise();
                
                // afiche la carte et les stats des protagonistes
                
                afficheGeneral();

                // affiche un message indiquant que le tour débute
                controllerHeros.votreTour();

                // gere les deplacements du joueur
                controllerHeros.afficheChoixJoueureDeplacement();
                while (!controllerHeros.deplacer(scan.nextLine())) {
                    controllerHeros.mauvaisChoix();
                    controllerHeros.afficheChoixJoueureDeplacement();
                }

                // gere les attaques du joueurs
                int nbAttaque = genereNombreAttaque();
                int attaqueN = 0;

                String choixAttaque = "N";
                int choixId = - 1;

                boolean passerTour = false;
                boolean attaqueDisponibles = true;

                // tant que le joueurs a des attaques restantes && qu'il n'a pas arreté d'attaquer && qu'il a des attaques possibles
                while ( attaqueN < nbAttaque && !passerTour && attaqueDisponibles) {
                    afficheGeneral();

                    boolean attaqueReussie = false;
                    ArrayList<String> attaquesDisponibles = controllerHeros.attaqueDisponible(controllerEnnemis);
                    if (attaquesDisponibles.size() == 1) {
                        attaqueDisponibles = false;
                        controllerHeros.afficheAucuneAttaqueDisponible();
                    } else {
                        while (!attaqueReussie) {
                            controllerHeros.afficheChoixJoueurAttaque(attaqueN + 1, nbAttaque, controllerEnnemis);
                            choixAttaque = scan.nextLine();

                            if (attaquesDisponibles.contains(choixAttaque)) {
                                
                                choixId = 0;
                                if (!choixAttaque.equals("speciale") && !choixAttaque.equals("N")) {
                                    affichePromptId();
                                    choixId = scan.nextInt();
                                }
                        
                                switch(controllerHeros.gererAttaque(choixAttaque, controllerEnnemis, choixId)) {
                                    case 0 -> {
                                        attaqueReussie = false;
                                        passerTour = false;
                                    }
                                    case 1 -> {
                                        attaqueReussie = true;
                                        passerTour = false;
                                    }
                                    case 2 -> {
                                        attaqueReussie = true;
                                        passerTour = true;
                                    }
                                }

                                if (!attaqueReussie) {
                                    controllerHeros.mauvaisChoix();
                                }
                            } else {
                                controllerHeros.mauvaisChoix();
                            }
                        }

                        attaqueN++;
                    }

                }
                
                // joue les ennemis plus lent que le heros
                jouerEnnemisNonPriorise();

            }

            carteFinie = carte.avancerSalle();
            
        }

        scan.close();

    }

    public void initSalle() {
        System.out.println("<-- Bienvenue dans " + carte.getNom() + " - Salle - " + (carte.getCurrentSalleIndex() + 1) + "/" + carte.nombreSalle() + " -->");

        controllerEnnemis.clear();
        generateurEnnemi.reset();

        controllerHeros.initPosition();
        initEnnemis();


        initPositionEnnemis();
        initActionsEnnemis();
        
    }

    private void initEnnemis() {
        Random random = new Random();   

        for (int z = 0; z < random.nextInt(min, max); z ++) {
            Ennemi ennemi = generateurEnnemi.obtenirEnnemiAleatoire();

            int i = random.nextInt(0, Constante.MAP_SIZE_I);
            int j = random.nextInt(Constante.MAP_SIZE_J / 2, Constante.MAP_SIZE_J);

            while (!carte.getCurrentSalle().placeDisponible(i, j)) {
                i = random.nextInt(0, Constante.MAP_SIZE_I);
                j = random.nextInt(Constante.MAP_SIZE_J / 2, Constante.MAP_SIZE_J);
            }

            controllerEnnemis.add(new ControllerEnnemi(carte, ennemi, i, j));
        }
    }
    
    public void initPositionEnnemis() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            controllerEnnemi.initPosition();
        });
    }

    private void initActionsEnnemis() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            controllerEnnemi.initAction();
        });
    }

    private void jouerEnnemisPriorise() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            if (controllerEnnemi.getPriorise() && controllerEnnemi.estVivant()) {
                controllerEnnemi.deplacer();
                controllerEnnemi.attaquer(controllerHeros);
            }
        });
    }

    private void jouerEnnemisNonPriorise() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            if (!controllerEnnemi.getPriorise() && controllerEnnemi.estVivant()) {
                controllerEnnemi.deplacer();
                controllerEnnemi.attaquer(controllerHeros);
            }
        });
    }

    private int genereNombreAttaque() {
        Random random = new Random();
        return random.nextInt(1, Constante.NOMBRE_ATTAQUE_MAX + 1);
    }

    private boolean ennemisMort() {
        for (ControllerEnnemi controllerEnnemi : controllerEnnemis) {
            if (controllerEnnemi.estVivant()) {
                return false;
            }
        }
        return true;
    }

    private void afficheGeneral() {
        carte.afficheSalle();
        afficheIdEnnemis();
        controllerHeros.afficheStatHeros();
    }

    // private void afficheStatEnnemis() {
    //     controllerEnnemis.forEach(controllerEnnemi -> {
    //         System.out.print(controllerEnnemi.afficheStatEnnemi());
    //     });
    //     System.out.println();
    // }

    private void afficheIdEnnemis() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            if (controllerEnnemi.estVivant()) System.out.print(controllerEnnemi.afficheIdEnnemi());
        });
        System.out.println();
    }

    private void affichePromptId() {
        System.out.print("Id> ");
    }
    
    // TODO ajouter une attaque speciale soin
    // TODO ajouter une attaque speciale imortalité pendant 2 tour
    // TODO ajouter un deplacement rapide (de 5 cases par exemples)

    // TODO ajouter logs
    // TODO ajouter tests
    // TODO ajouter commentaire

    // TODO cretation Map / Heros
    // TODO creation Attaque Speciale

    // TODO gerer fin de partie
    // - victoire/defaite
    // - message
    // - nb ennemis vaincu
    // - stats deplacement/attaques

    // TODO FAIRE EN SORTE QUE SI LES TOUS LES ENNEMIS SONT MORT AU MILLIEU D UN TOUR D ATTAQUE STOP LE TOUR SANS ATTEINDRE LA FIN DES ATTAQUES RESTANTES

}
