package imt.spellboud.Controller;

import imt.spellboud.Controller.Action.Attaque.AttaqueMagique;
import imt.spellboud.Controller.Action.Attaque.AttaquePhysique;
import imt.spellboud.Controller.Action.Deplacement.DeplacementAleatoire;
import imt.spellboud.Model.Personnage;
import imt.spellboud.Model.Ennemi.Ennemi;

public class ControllerEnnemi extends Controller {

    private Ennemi modele;

    public ControllerEnnemi(Carte carte, Ennemi modele, int i, int j) {
        super(carte, modele.getTexture(), i, j);
        this.modele = modele;
    }

    public void initDeplacements() {
        super.getDeplacements().put("deplacement aleatoire", new DeplacementAleatoire());
    }

    public void initAttaques() {
        super.getAttaques().put("physique", new AttaquePhysique());
        if (modele.getMagique()) {
            super.getAttaques().put("magique", new AttaqueMagique());
        }
    }

    public boolean deplacer() {
        if (!super.getDeplacements().get("deplacement aleatoire").deplace(modele, super.getVue())) {
            System.out.println("Deplacement impossible position occupé ou sortie de carte");
            return true;
        }
        return true;
    }

    public boolean attaquer(ControllerHeros controller) {
        if (modele.getMagique()) {
            if (!super.getAttaques().get("magique").attaque(modele, super.getVue(), controller.getModele(), controller.getVue())) {
                super.getAttaques().get("physique").attaque(modele, super.getVue(), controller.getModele(), controller.getVue());
            }
        } else {
            super.getAttaques().get("physique").attaque(modele, super.getVue(), controller.getModele(), controller.getVue());
        }
        return true;
    }

    public String afficheStatEnnemi() {
        return "<" + modele.getNom() + " - " + modele.getPv() + "> ";
    }

    public String afficheIdEnnemi() {
        return "<" + modele.getId() + ": " + modele.getNom() + " - " + modele.getPv() + "> ";
    }

    public boolean getPriorise() {
        return modele.getPriorise();
    }

    public int getId() {
        return modele.getId();
    }



    public boolean estVivant() {
        return modele.getPv() > 0;
    }

    public Personnage getModele() {
        return modele;
    }

}
