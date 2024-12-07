package imt.spellboud.View.Terminal;

import imt.spellboud.View.Entite;
import imt.spellboud.View.Salle;

public class SalleTerminal implements Salle {

    // public final static int TEXTURE_LARGEUR_2D = 3;

    private char[][] map;
    

    public SalleTerminal(int lon, int lar) {
        map = new char[lon][lar];
        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = ' ';
            }
        }
    }

    @Override
    public int getLargeur() {
        return map.length;
    }

    @Override
    public int getLongeur() {
        return map[0].length;
    }

    @Override
    public void start(Entite e) {
        map[e.getStart_i()][e.getStart_j()] = e.getTexture();
    }

    @Override
    public void clearEntite(Entite e) {
        map[e.getStart_i()][e.getStart_j()] = ' ';
    }
   
    @Override
    public boolean deplacerBas(Entite e) {
        if (e.getStart_i() < map.length - 1) {
            if (!placeDisponible(e.getStart_i(), e.getStart_j() + 1)) return false;

            clearEntite(e);
            e.setStart_i(e.getStart_i() + 1);
            start(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerHaut(Entite e) {
        if (e.getStart_i() > 0) {
            if (!placeDisponible(e.getStart_i(), e.getStart_j() - 1)) return false;

            clearEntite(e);
            e.setStart_i(e.getStart_i() - 1);
            start(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerDroite(Entite e) {
        if (e.getStart_j() < map[0].length - 1) {
            if (!placeDisponible(e.getStart_i(), e.getStart_j() + 1)) return false;

            clearEntite(e);
            e.setStart_j(e.getStart_j() + 1);
            start(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean deplacerGauche(Entite e) {
        if (e.getStart_j() > 0) {
            if (!placeDisponible(e.getStart_i(), e.getStart_j() - 1)) return false;

            clearEntite(e);
            e.setStart_j(e.getStart_j() - 1);
            start(e);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("<--SpellBoundWarriors-->\n");
        display.append("+" + "-".repeat(map[0].length) + "+\n");

        for (char[] i : map) {
            display.append("|");
            for(char j : i) {
                display.append(j);
            }
            display.append("|\n");
        }

        display.append("+" + "-".repeat(map[0].length) + "+");
        return display.toString();
    }

    @Override
    public void displayMap(int sec, boolean debug) {
        if (!debug) System.out.println("\033[H\033[2J");
        
        System.out.println(this);
        if (sec > 0) {
            try {
                Thread.sleep(sec * 1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }    
        }    
        
    }    
    
    @Override
    public boolean placeDisponible(int i, int j) {
        if (i >= map.length || j >= map[0].length) return false;
        return map[i][j] == ' ';
    }

    @Override
    public void efface(Entite e) {
        map[e.getStart_i()][e.getStart_j()] = ' ';       
    }

}    



































































































































