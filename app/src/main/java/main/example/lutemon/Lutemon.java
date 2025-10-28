package main.example.lutemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Lutemon implements Serializable {

    protected String name;
    protected String type;
    protected int attack;
    protected int defense;
    protected int health;
    protected int maxHealth;
    protected int experience=0;
    protected int battlesWon=0;
    protected int battlesHad=0;
    protected int timesTrained=0;
    protected int evolutionLevel=3;
    //the rate at which a lutemon loses health when training is 33% by default
    protected double trainingExhaustionFactor=0.33;

    protected int id;

    protected int image_basic;
    protected int image_evolved;
    protected int image_dead;

    private static int idCounter=0;

    protected Lutemon(String type, String name) {
        id=idCounter;
        idCounter++;
        this.type=type;
        this.name=name;
    }

    protected int defense(int attack) {
        int damage;
        damage=defense-attack;
        //damage is a negative integer, we also check that the damage is negative, because otherwise it would add to the health
        if(damage<0) {
            health=health+damage;
        }
        else {
            damage=0;
        }
        if(health<0) {
            health=0;
        }
        return damage;
    }

    protected int attack() {
        int damage;
        //We use Math.random to decrease the damage
        damage=(attack+experience-(int)Math.random()*3);
        if(damage<0) {
            damage=0;
        }
        System.out.println(name+" attacks!\nIt deals "+damage+" damage!");
        return damage;
    }
    protected int criticalHit() {
        int damage;
        damage=(attack+experience);
        return damage;
    }



    protected int heal() {
        //unused feature
        //heals 20hp
        if(health+10>maxHealth) {
            health=maxHealth;
        }
        else {
            health=health+10;
        }
        return health;
    }

    protected int getImage() {
        System.out.println("Health is "+health);
        //Checks if lutemon is dead or has evolved
        if(health==0) {
            System.out.println("Returned dead image");
            return image_dead;
        }
        //Lutemons evolve when EXP is 3 or other specified
        else if(experience<evolutionLevel) {
            System.out.println("Returned basic image");
            return image_basic;
        }
        else {
            System.out.println("Returned evolved image");
            return image_evolved;
        }
    }

    //We go through the list to find "gaps" and set the id counter to where the gap is, this is basically only done when loading lutemons
    public static void updateIdCounter() {
        System.out.println("IdCounter updater called, idCounter is "+idCounter);
        ArrayList<Lutemon> lutemons=Home.getInstance().getLutemons();
        int id=0;
        for(Lutemon lutemon : lutemons) {
            System.out.println("IdCounter updater for each, id="+id);
            id=lutemon.id+1;
        }
        idCounter=id;
        System.out.println("Idcounter updated to:"+idCounter);
    }
}




