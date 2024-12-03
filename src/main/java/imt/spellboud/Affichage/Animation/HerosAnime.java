package imt.spellboud.Affichage.Animation;

public class HerosAnime extends Entite {

    protected final static char[][] texture = {
        {'+', '-', '+'},
        {'|', 'O', '|'},
        {'+', '-', '+'},
    };

    public HerosAnime(MapAnime map) {
        super(map, texture);
        map.start(this);
    }
    
}
