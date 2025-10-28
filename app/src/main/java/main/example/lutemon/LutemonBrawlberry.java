package main.example.lutemon;

public class LutemonBrawlberry extends Lutemon{

    public LutemonBrawlberry(String name) {
        //Brawlberry corresponds to "Oranssi" from the original documentation
        super("Brawlberry", name);
        attack=8;
        defense=1;
        health=17;
        maxHealth=17;
        evolutionLevel=2;
        image_basic=R.drawable.brawlberry_1;
        image_evolved=R.drawable.brawlberry_2;
        image_dead=R.drawable.brawlberry_3;

    }
}
