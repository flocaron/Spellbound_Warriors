public class Mage extends Ennemi {
    private int mana;

    public Mage(String nom) {
        super(nom, 80, 15); // PV et force d'attaque spécifiques au Mage
        this.mana = 100; // Initialisation du mana
    }

    public String attaqueMagique() {
        if (mana >= 30) { // Coût de l'attaque magique
            mana -= 30; // Déduit le coût du mana
            return nom + " utilise une attaque magique puissante! Dégâts infligés: " + (forceAttaque * 3);
        } else {
            return nom + " n'a pas assez de mana pour effectuer une attaque magique!";
        }
    }
}