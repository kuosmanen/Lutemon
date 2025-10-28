package main.example.lutemon;

public class Training extends Storage{
    private static Training training=null;

    public Training(){
        name="training";
    }

    public static Training getInstance() {
        //making the singleton
        if(training==null) {
            training = new Training();
        }
        return training;
    }

    public void train(int id) {
        Lutemon lutemon = getLutemon(id);
        lutemon.experience++;
        lutemon.timesTrained++;
        //depending on which lutemon it is, the hp is drained differently, 33% by default
        lutemon.health=lutemon.health-(int)(lutemon.maxHealth*lutemon.trainingExhaustionFactor);
    }
}
