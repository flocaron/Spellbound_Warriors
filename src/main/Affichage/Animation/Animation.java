package Affichage.Animation;

public class Animation {

    public static void main(String[] args) {
        
        MapAnime map = new MapAnime();
        HerosAnime heros = new HerosAnime(map);
        
        System.out.println(map);

        heros.deplacerBas();
        System.out.println(map);


        // for (int i : new int[]{1, 2, 3, 4, 5}) {
            
        // }


    }

    
}
