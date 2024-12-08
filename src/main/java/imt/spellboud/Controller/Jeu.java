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

    private int ennemisVaincus;

    public Jeu(Heros heros, Carte carte, int min, int max) {
        this.carte = carte;
        controllerHeros = new ControllerHeros(carte, heros);
        controllerEnnemis = new ArrayList<>();

        generateurEnnemi = new GenerateurEnnemi();

        this.min = min;
        this.max = max;
        
        ennemisVaincus = 0;
    }

    public boolean jouerPartie() {        
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

                int nbEnnemisMort = nbEnnemisMort();

                String choixAttaque = "N";
                int choixId = - 1;

                boolean passerTour = false;
                boolean attaqueDisponibles = true;

                // tant que le joueurs a des attaques restantes && qu'il n'a pas arreté d'attaquer && qu'il a des attaques possibles
                while ( attaqueN < nbAttaque && !passerTour && attaqueDisponibles) {
                    
                    boolean attaqueReussie = false;
                    ArrayList<String> attaquesDisponibles = controllerHeros.attaqueDisponible(controllerEnnemis);

                    attaqueDisponibles = attaquesDisponibles.size() > 1 && !ennemisMort();
                    if (!attaqueDisponibles) {
                        controllerHeros.afficheAucuneAttaqueDisponible();
                    } else {
                        afficheGeneral();
                    }

                    while (!attaqueReussie && attaqueDisponibles) {
                        controllerHeros.afficheChoixJoueurAttaque(attaqueN + 1, nbAttaque, controllerEnnemis);
                        choixAttaque = scan.nextLine();                        

                        if (attaquesDisponibles.contains(choixAttaque)) {
                            
                            choixId = 0;
                            if (!choixAttaque.equals(controllerHeros.getAttaqueSpeciale().getNom()) && !choixAttaque.equals("N")) {
                                boolean saisieValide = false;
                                while (!saisieValide) {
                                    try {
                                        affichePromptId();
                                        choixId = Integer.parseInt(scan.nextLine());
                                        saisieValide = true;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Veuillez entrer un nombre valide.");
                                    }
                                }
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

                        attaqueDisponibles = attaquesDisponibles.size() > 1 && !ennemisMort();
                        if (!attaqueDisponibles) {
                            controllerHeros.afficheAucuneAttaqueDisponible();
                        }

                    }

                    attaqueN++;
                

                }

                ennemisVaincus += nbEnnemisMort() - nbEnnemisMort;
                
                // joue les ennemis plus lent que le heros
                jouerEnnemisNonPriorise();

            }

            carteFinie = carte.avancerSalle();
            
        }

        scan.close();

        return controllerHeros.estVivant();
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

    private int nbEnnemisMort() {
        int ennemisMorts = 0;
        for (ControllerEnnemi controllerEnnemi : controllerEnnemis) {
            if (!controllerEnnemi.estVivant()) ennemisMorts++;
        }
        return ennemisMorts;
    }

    public boolean ennemisMort() {
        return nbEnnemisMort() == controllerEnnemis.size();
    }

    private void afficheGeneral() {
        carte.afficheSalle();
        afficheIdEnnemis();
        controllerHeros.afficheStatHeros();
    }

    private void afficheIdEnnemis() {
        controllerEnnemis.forEach(controllerEnnemi -> {
            if (controllerEnnemi.estVivant()) System.out.print(controllerEnnemi.afficheIdEnnemi());
        });
        System.out.println();
    }

    private void affichePromptId() {
        System.out.print("Id> ");
    }
    
    public int getEnnemisVaincus() {
        return ennemisVaincus;
    }
    
    // TODO ajouter une attaque speciale soin
    // TODO ajouter une attaque speciale imortalité pendant 2 tour
    // TODO ajouter un deplacement rapide (de 5 cases par exemples)

    // TODO ajouter logs
    // TODO ajouter commentaire

    // TODO creation Map / Heros
    // TODO creation Attaque Speciale

    // TODO stats deplacement/attaques

    // TODO ajouter info
    // comment attaquer/se deplacer
    // fonctionnement du jeu

    // pour tests

    public ControllerHeros getControllerHeros() {
        return controllerHeros;
    }

    public ArrayList<ControllerEnnemi> getControllerEnnemis() {
        return controllerEnnemis;
    }


}
