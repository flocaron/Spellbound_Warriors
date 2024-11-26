package Affichage.Animation;

public class MapAnime {

    private char[][] map;

    public MapAnime() {
        map = new char[8][40];
    }

    public void start(Entite e) {
        for (int i = e.getStart_i(); i < e.getTexture().length; i++) {
            for (int j = e.getStart_j(); j < e.getTexture()[0].length; j++) {
                map[i][j] = e.getTexture()[i][j];
            
            }
        }
    }
    
   

    public void deplacerBas(Entite e) {
        for (int i = e.getStart_i(); i < e.getTexture().length; i++) {
            for (int j = e.getStart_j(); j < e.getTexture()[0].length; j++) {
                map[i][j] = ' ';
            
            }
        }

        e.setStart_i(e.getStart_i() + 1);

        for (int i = 0; i < e.getTexture().length; i++) {
            for (int j = 0; j < e.getTexture()[0].length; j++) {
                map[i + e.getStart_i() + 1][j + e.getStart_j()] = e.getTexture()[i][j];
            
            }
        }
        
    }

    public String toString() {
        String display = "";
        for (char[] i : map) {
            for(char j : i) {
                display += j;
            }
            display += "\n";
        }
        return display + "\r";
    }
    
}
