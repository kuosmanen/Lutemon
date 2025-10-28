package main.example.lutemon;

public class Home extends Storage{


    private static Home home=null;

    public Home(){
        name="home";
    }

    public static Home getInstance() {
        //making the singleton
        if(home==null) {
            home = new Home();
        }
        return home;
    }

    public void sleep(int id) {
        Lutemon lutemon=getLutemon(id);
        lutemon.health=lutemon.maxHealth;
    }



}
