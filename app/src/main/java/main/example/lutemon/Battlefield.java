package main.example.lutemon;

import java.util.ArrayList;

public class Battlefield extends Storage{
    private static Battlefield battlefield=null;
    private int firstFighterId=-1;
    private int secondFighterId=-1;

    public Battlefield(){
        name="battlefield";
    }

    public static Battlefield getInstance() {
        //making the singleton
        if(battlefield==null) {
            battlefield = new Battlefield();
        }
        return battlefield;
    }

    //getters and setters for the fighters id:s
    public void setFirstFighterId(int id) {
        this.firstFighterId = id;
    }
    public int getFirstFighterId() {
        return firstFighterId;
    }
    public void setSecondFighterId(int id) {
        this.secondFighterId = id;
    }
    public int getSecondFighterId() {
        return secondFighterId;
    }


    public String fight() {

        Lutemon A = getLutemon(firstFighterId);
        Lutemon B = getLutemon(secondFighterId);
        Lutemon C;

        int damage;
        StringBuilder sb = new StringBuilder("The battle starts!\n\n");
        boolean exit=false;
        while(!exit) {
            sb.append("1: "+A.type+"("+A.name+") att: "+A.attack+"; def: "+A.defense+"; exp:"+A.experience+"; health: "+A.health+"/"+A.maxHealth+"\n");
            sb.append("2: "+B.type+"("+B.name+") att: "+B.attack+"; def: "+B.defense+"; exp:"+B.experience+"; health: "+B.health+"/"+B.maxHealth+"\n");
            sb.append(A.type+"("+A.name+") attacks "+B.type+"("+B.name+")\n");
            //Chance for crit is 20% and miss is 10%
            if(Math.random()<0.2) {
                sb.append(A.type+"("+A.name+") deals a critical hit!\n");
                B.defense(A.criticalHit());
            }
            else if(Math.random()<0.1) {
                sb.append(A.type+"("+A.name+") misses!\n");
            }
            //Here we calculate the damage if it isn't a critical hit, also if the damage is 0 then we say that it's a miss
            //this next if else just checks that the attack wasn't <0, this shouldn't be possible but this is futureproofing
            else if(B.defense(A.attack())==0){
                sb.append(A.type+"("+A.name+") misses!\n");
            }

            System.out.println(B.type+"("+B.name+") health is "+B.health+"/"+B.maxHealth);

            if(B.health>0) {
                sb.append(B.type+"("+B.name+") manages to escape death.\n");
                //System.out.println("Lutemon A is "+A.type+"("+A.name+")");
                //System.out.println("Lutemon B is "+B.type+"("+B.name+")");
                C=B;
                B=A;
                A=C;
                //System.out.println("Now lutemon A is "+A.type+"("+A.name+")");
                //System.out.println("Now lutemon B is "+B.type+"("+B.name+")");

            }
            else {
                sb.append(B.type+"("+B.name+") gets killed.\n");
                sb.append("The battle is over.");
                A.experience++;
                A.battlesWon++;
                A.battlesHad++;
                B.battlesHad++;
                if(A.experience==A.evolutionLevel) {
                    sb.append("\n"+A.type+"("+A.name+") evolved!");
                }
                exit=true;
            }
        }
        return sb.toString();
    }
}
