package main.example.lutemon;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Storage {

    protected String name;
    protected ArrayList<Lutemon> lutemons = new ArrayList<>();

    protected Lutemon getLutemon(int id) {
        for(Lutemon lutemon : lutemons) {
            if(lutemon.id==id) {
                return lutemon;
            }
        }
        //it shouldn't be possible to get here but just in case let's print to console
        System.out.println("NO LUTEMON WITH THAT ID WAS FOUND!!! WRONG LUTEMON RETURNED");
        //we return this lutemon to avoid a crash
        return new LutemonBanankey("ERROR LUTEMON");
    }

    protected ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }
    protected void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

    protected void deleteLutemon(int id) {
        Iterator<Lutemon> iterator = lutemons.iterator();
        while (iterator.hasNext()) {
            Lutemon lutemon = iterator.next();
            if (lutemon.id == id) {
                iterator.remove();
                return;
                // We've found and deleted the lutemon, so we can return now.
            }
        }
        // If we get here, we didn't find the lutemon.
        System.out.println("NO LUTEMON WITH THAT ID WAS FOUND!!! NO LUTEMON DELETED!");
    }


    //Saving and loading Lutemons

    public void saveLutemons(Context context) {
        System.out.println("Number of lutemons in the ArrayList is "+lutemons.size());
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Lutemons weren't saved properly");
        }
    }


    public void loadLutemons(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons=(ArrayList<Lutemon>) lutemonReader.readObject();
            //We update the counter so that we don't have repeated id:s when making new lutemons after loading old ones
            Lutemon.updateIdCounter();
            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loading the lutemons failed");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Loading the lutemons failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Loading the lutemons failed");
            e.printStackTrace();
        }
    }







}
