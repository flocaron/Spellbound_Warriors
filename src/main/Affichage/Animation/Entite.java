package Affichage.Animation;

public class Entite {

    private MapAnime map;
    private char[][] texture;
    private int start_i;
    private int start_j;

    public Entite(MapAnime map, char[][] texture) {
        this.map = map;
        this.texture = texture;
        start_i = 0;
        start_j = 0;
    }

    public char[][] getTexture() {
        return texture;
    }

    public int getStart_i() {
        return start_i;
    }

    public void setStart_i(int start_i) {
        this.start_i = start_i;
    }

    public int getStart_j() {
        return start_j;
    }

    public void setStart_j(int start_j) {
        this.start_j = start_j;
    }

    public void deplacerBas() {
        map.deplacerBas(this);
    }

}
