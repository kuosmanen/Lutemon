package main.example.lutemon;

public class Hospital extends Storage{
    private static Hospital hospital=null;

    public Hospital(){
        name="hospital";
    }

    public static Hospital getInstance() {
        //making the singleton
        if(hospital==null) {
            hospital = new Hospital();
        }
        return hospital;
    }

    public void revive(int id) {
        Lutemon lutemon = getLutemon(id);
        if(lutemon.health<1) {
            lutemon.health=1;
        }
    }
}
