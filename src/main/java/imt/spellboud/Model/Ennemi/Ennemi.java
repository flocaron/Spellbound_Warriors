package imt.spellboud.Model.Ennemi;

import imt.spellboud.Model.Personnage;

public abstract class Ennemi extends Personnage {

    private boolean priorise;
    private boolean magique;

    private boolean mort;


    public Ennemi(int id, String nom, int pv, int forceAttaque, boolean priorise, boolean magique) {
        super(id, nom, pv, forceAttaque);
        this.priorise = priorise;
        this.magique = magique;

        mort = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ennemi other = (Ennemi) obj;
        if (super.getId() != other.getId())
            return false;
        return true;
    }


    public void setMort(boolean mort) {
        this.mort = mort;
    }

    public boolean getMort() {
        return mort;
    }

    public void setPriorise(boolean priorise) {
        this.priorise = priorise;
    }

    public boolean getPriorise() {
        return priorise;
    }

    public boolean getMagique() {
        return magique;
    }
    

    
   /*  public void deplacer() {
        // Logique pour changer la position de l'ennemi
        // Par exemple, se déplacer vers le héros ou se déplacer aléatoirement
        this.position.x += (Math.random() > 0.5 ? 1 : -1); // Déplacement aléatoire
        this.position.y += (Math.random() > 0.5 ? 1 : -1); // Déplacement aléatoire
    }*/

    

}