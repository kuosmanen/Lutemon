package main.example.lutemon;

public class LutemonDoczoi extends Lutemon{

    public LutemonDoczoi(String name) {
        //Doczoi corresponds to "Musta" from the original documentation
        super("Doczoi", name);
        attack=9;
        defense=0;
        health=16;
        maxHealth=16;
        evolutionLevel=2;
        image_basic=R.drawable.doczoi_1;
        image_evolved=R.drawable.doczoi_2;
        image_dead=R.drawable.doczoi_3;

    }
}
