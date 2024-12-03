package Personnage.Modele;

public abstract class Ennemi extends Personnage {

    public Ennemi(String nom, int pv, int forceAttaque) {
        super(nom, pv, forceAttaque);
    }
   /*  public void deplacer() {
        // Logique pour changer la position de l'ennemi
        // Par exemple, se déplacer vers le héros ou se déplacer aléatoirement
        this.position.x += (Math.random() > 0.5 ? 1 : -1); // Déplacement aléatoire
        this.position.y += (Math.random() > 0.5 ? 1 : -1); // Déplacement aléatoire
    }*/

}