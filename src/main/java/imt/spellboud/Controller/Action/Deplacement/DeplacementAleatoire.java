package imt.spellboud.Controller.Action.Deplacement;

import java.util.ArrayList;
import java.util.Random;

import imt.spellboud.Model.Personnage;
import imt.spellboud.View.Entite;

public class DeplacementAleatoire extends Deplacement {

    private ArrayList<Deplacement> deplacements;

    public DeplacementAleatoire() {
        super("Deplacement Aléatoire");
        deplacements = new ArrayList<>();
        deplacements.add(new DeplacementBas());
        deplacements.add(new DeplacementHaut());
        deplacements.add(new DeplacementDroite());
        deplacements.add(new DeplacementGauche());
    }

    @Override
    public boolean deplace(Personnage personnage, Entite vue) {
        Random random = new Random();
        return deplacements.get(random.nextInt(deplacements.size())).deplace(personnage, vue);
    }


}