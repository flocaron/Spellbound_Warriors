package Personnage.Modele;

public class PorteeAttaque {
    
    private int porteePhysique;
    private int porteeMagique;

    public PorteeAttaque() {
        this.porteePhysique = 1; // Portée pour une attaque physique
        this.porteeMagique = 3; // Portée pour une attaque magique
    }

    public int getPorteePhysique() {
        return porteePhysique;
    }

    public int getPorteeMagique() {
        return porteeMagique;
    }

    public int getPorteeCapaciteSpeciale(int superficieSalle) {
        // La portée de la capacité spéciale dépend de la superficie de la salle
        return superficieSalle; // La capacité spéciale couvre toute la salle
    }

    // Nouvelle méthode pour vérifier si l'ennemi est à portée
    public boolean estDansPortee(int distance, String typeAttaque) {
        switch (typeAttaque.toLowerCase()) {
            case "physique":
                return distance <= porteePhysique;
            case "magique":
                return distance <= porteeMagique;
            case "speciale":
                // Pour la capacité spéciale, on considère qu'elle couvre toute la salle
                return true; 
            default:
                throw new IllegalArgumentException("Type d'attaque inconnu: " + typeAttaque);
        }
    }
}