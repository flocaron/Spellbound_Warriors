package imt.spellboud.Personnage.Modele;

import java.util.Random;

public abstract class Personnage {

    private String nom;
    private int pv;
    private int forceAttaque;
    private boolean utilisee;

    public Personnage(String nom, int pv, int forceAttaque) {
        this.nom = nom;
        this.pv = pv;
        this.forceAttaque = forceAttaque;
        this.utilisee = false;
    }

    public String utiliserCapacite() {
        if (!utilisee) {
            utilisee = true;
            return nom + " utilise sa capacité spéciale!";
        } else {
            return nom + " a déjà utilisé sa capacité spéciale.";
        }
    }

    public String recevoirDegats(int degats) {
        pv -= degats;
        return nom + " reçoit " + degats + " points de dégâts. PV restants : " + pv;
    }

    public boolean estVivant() {
        return pv > 0;
    }

    public int attaquer() {
        Random rand = new Random();
        return rand.nextInt(forceAttaque) + 1; // Attaque entre 1 et forceAttaque
    }

    public String getNom() {
        return nom;
    }

    public int getForceAttaque() {
        return forceAttaque;
    }

    
}
