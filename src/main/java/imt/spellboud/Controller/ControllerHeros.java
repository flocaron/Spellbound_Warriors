package imt.spellboud.Controller;

import java.util.ArrayList;

import imt.spellboud.Controller.Action.Attaque.Attaque;
import imt.spellboud.Controller.Action.Attaque.AttaqueAnnule;
import imt.spellboud.Controller.Action.Attaque.AttaqueMagique;
import imt.spellboud.Controller.Action.Attaque.AttaquePhysique;
import imt.spellboud.Controller.Action.Attaque.Speciale.AttaqueBouleDeFeu;
import imt.spellboud.Controller.Action.Attaque.Speciale.AttaqueSpeciale;
import imt.spellboud.Controller.Action.Deplacement.DeplacementAnnule;
import imt.spellboud.Controller.Action.Deplacement.DeplacementBas;
import imt.spellboud.Controller.Action.Deplacement.DeplacementDroite;
import imt.spellboud.Controller.Action.Deplacement.DeplacementGauche;
import imt.spellboud.Controller.Action.Deplacement.DeplacementHaut;
import imt.spellboud.Model.Heros;
import imt.spellboud.Model.Personnage;

public class ControllerHeros extends Controller {

    private Heros modele;
    private AttaqueSpeciale attaqueSpeciale;

    public ControllerHeros(Carte carte, Heros modele) {
        super(carte, modele.getTexture(), 0, 0);
        this.modele = modele;
    }

    public void initDeplacements() {
        super.getDeplacements().put("bas", new DeplacementBas());
        super.getDeplacements().put("droite", new DeplacementDroite());
        super.getDeplacements().put("gauche", new DeplacementGauche());
        super.getDeplacements().put("haut", new DeplacementHaut());
        super.getDeplacements().put("N", new DeplacementAnnule());
    }

    public void initAttaques() {
        attaqueSpeciale = new AttaqueBouleDeFeu();
        super.getAttaques().put("magique", new AttaqueMagique());
        super.getAttaques().put("physique", new AttaquePhysique());
        super.getAttaques().put("N", new AttaqueAnnule());
    }

    public boolean deplacer(String deplacement) {
        if (super.getDeplacements().get(deplacement) != null) {
            if (!super.getDeplacements().get(deplacement).deplace(modele, super.getVue())) {
                System.out.println("Deplacement Impossible position occupé ou sortie de carte");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean attaquer(String attaque, ControllerEnnemi cible) {
        if (super.getAttaques().get(attaque) != null) {
            return super.getAttaques().get(attaque).attaque(modele, super.getVue(), cible.getModele(), cible.getVue());
        }
        return false;
    }

    public int gererAttaque(String choixAttaque, ArrayList<ControllerEnnemi> ennemis, int id) {
        if (choixAttaque.equals("N")) return 2;
        
        // Gérer les attaques spéciales
        if (choixAttaque.equals("speciale")) {
            return attaqueSpeciale.attaqueSpeciale(modele, getVue(), ennemis) ? 1 : 0;
        }
    
        // Gérer les attaques normales
        ControllerEnnemi cible = 0 < id && id <= ennemis.size() ? ennemis.get(id - 1) : null;
        if (cible == null || !cible.estVivant()) {
            System.out.println("Cible invalide ou déjà vaincue.");
            return 0;
        }
    
        return attaquer(choixAttaque, cible) ? 1 : 0;
    }

    public void afficheChoixJoueureDeplacement() {
        super.getDeplacements().forEach((nom, action) -> {
            System.out.print("<" + nom + "> ");
        });
        System.out.println();
        System.out.print("Deplacement> ");
    }

    public void afficheChoixJoueurAttaque(int attaque, int attaqueMax, ArrayList<ControllerEnnemi> controllerEnnemis) {
        attaqueDisponible(controllerEnnemis).forEach(nom -> {
            System.out.print("<" + nom + "> ");
        });
        System.out.println();
        System.out.print("Attaque " + attaque + "/" + attaqueMax + "> ");
    }

    public ArrayList<String> attaqueDisponible(ArrayList<ControllerEnnemi> controllerEnnemis) {
        ArrayList<String> attaquesDisponibles = new ArrayList<>();
        if (!attaqueSpeciale.getUtilisee()) attaquesDisponibles.add("speciale");
        getAttaques().forEach((nom, attaque) -> {
            if (auMoinsAPorte(attaque, controllerEnnemis)) attaquesDisponibles.add(nom);
        });
        return attaquesDisponibles;
    }

    private boolean auMoinsAPorte(Attaque attaque, ArrayList<ControllerEnnemi> controllerEnnemis) {
        for (ControllerEnnemi ennemi : controllerEnnemis) {
            if (attaque.aPorte(getVue(), ennemi.getVue())) return true;
        }
        return false;
    }


    public void afficheStatHeros() {
        System.out.println("-> " + modele.getNom());
        System.out.println("-- Pv: " + modele.getPv());
        System.out.println("-- Mana: " + modele.getMana());
    }

    public void votreTour() {
        System.out.println("C'est vote tour :");
    }

    public void mauvaisChoix() {
        System.out.println("Mauvais choix !");
    }

    public boolean estVivant() {
        return modele.getPv() > 0;
    }

    public Personnage getModele() {
        return modele;
    }

    public void afficheAucuneAttaqueDisponible() {
        System.out.println(modele.getNom() + " n'a pas d'attaques utilisables.");
    }



}
