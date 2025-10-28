package main.example.lutemon;

public class LutemonBanankey extends Lutemon {

    public LutemonBanankey(String name) {
        //Banankey corresponds to "Vihreä" from the original documentation
        super("Banankey", name);
        attack=6;
        defense=3;
        health=19;
        maxHealth=19;
        image_basic=R.drawable.banankey_1;
        image_evolved=R.drawable.banankey_2;
        image_dead=R.drawable.banankey_3;

    }
}
