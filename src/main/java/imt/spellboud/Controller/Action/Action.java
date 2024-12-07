package imt.spellboud.Controller.Action;

public abstract class Action {

    private String nom;

    public Action(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    
}
