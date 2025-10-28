package main.example.lutemon;

public class LutemonMintigress extends Lutemon{

    public LutemonMintigress(String name) {
        //Mintigress corresponds to "Pinkki" from the original documentation
        super("Mintigress", name);
        attack=7;
        defense=2;
        health=18;
        maxHealth=18;
        image_basic=R.drawable.mintigress_1;
        image_evolved=R.drawable.mintigress_2;
        image_dead=R.drawable.mintigress_3;

    }
}
