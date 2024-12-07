package imt.spellboud.Controller;

import java.util.ArrayList;

import imt.spellboud.Utils.Constante;
import imt.spellboud.View.Salle;
import imt.spellboud.View.Terminal.SalleTerminal;

public class Carte {

    private String lieu;
    private String nom;
    private ArrayList<Salle> salles;
    private int currentSalle;
    private int animationTime;

    public Carte(String lieu, String nom, int nbSalle, int animationTime, int size_i, int size_j) {
        this.lieu = lieu;
        this.nom = nom;
        salles = new ArrayList<>();
        currentSalle = 0;
        this.animationTime = animationTime;
        for (int i = 0; i < nbSalle; i++) {
            salles.add(new SalleTerminal(size_i, size_j));
        }
    }

    public Salle getCurrentSalle() {
        return salles.get(currentSalle);
    }

    public boolean avancerSalle() {
        currentSalle++;
        return carteFinie();
    }

    private boolean carteFinie() {
        return currentSalle >= salles.size();
    }

    public void afficheSalle(boolean debug) {
        getCurrentSalle().displayMap(1, debug);
    }

    public void afficheSalle() {
        getCurrentSalle().displayMap(animationTime, Constante.DEBUG);
    }

    public String getLieu() {
        return lieu;
    }

    public String getNom() {
        return nom;
    }

    public int getCurrentSalleIndex() {
        return currentSalle;
    }

    public int nombreSalle() {
        return salles.size();
    }

}
