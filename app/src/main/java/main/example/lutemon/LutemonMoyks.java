package main.example.lutemon;

public class LutemonMoyks extends Lutemon{

    public LutemonMoyks(String name) {
        //Möyks corresponds to "Valkoinen" from the original documentation
        super("Möyks", name);
        attack=5;
        defense=4;
        health=20;
        maxHealth=20;
        evolutionLevel=4;
        image_basic=R.drawable.moyks_1;
        image_evolved=R.drawable.moyks_2;
        image_dead=R.drawable.moyks_3;

    }

}
