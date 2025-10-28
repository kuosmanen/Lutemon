package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LutemonCreationActivity extends AppCompatActivity {
    private Home s=Home.getInstance();
    private EditText name;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_creation);

        name=findViewById(R.id.inputFieldName);

    }

    public void saveLutemonsButton(View view) {

        if(s.getLutemons().size()>0) {
            s.saveLutemons(this);
            snackbar = Snackbar.make(view, "Lutemons saved!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            snackbar = Snackbar.make(view, "Add at least one lutemon to save!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void loadLutemonsButton(View view) {
        s.loadLutemons(this);
        snackbar = Snackbar.make(view, "Lutemons loaded!", BaseTransientBottomBar.LENGTH_LONG);
        if(s.getLutemons().size()<=0) {
            snackbar = Snackbar.make(view, "No lutemons were loaded", BaseTransientBottomBar.LENGTH_LONG);
        }
        snackbar.show();
    }

    public void addLutemon(View view) {
        String type="";
        RadioGroup lutemonGroup=findViewById(R.id.LutemonGroup);
        Lutemon lutemon;


        switch(lutemonGroup.getCheckedRadioButtonId()) {
            case R.id.rbMoyks:
                //Type is written with the "ö" but in the code Möyks is referred to without the ö, so Moyks
                type="Möyks";
                break;
            case R.id.rbBanankey:
                type="Banankey";
                break;
            case R.id.rbMintigress:
                type="Mintigress";
                break;
            case R.id.rbBrawlberry:
                type="Brawlberry";
                break;
            case R.id.rbDoczoi:
                type="Doczoi";
                break;
        }
        //Checking if user has chosen a type for the Lutemon
        if(type.equals("")) {
            System.out.println("Choose the type first!");
            snackbar = Snackbar.make(view, "Choose a type for your Lutemon before adding!", BaseTransientBottomBar.LENGTH_LONG);
        }
        //Checking if user has written a name for the Lutemon
        else if (name.getText().toString().equals("")) {
            System.out.println("Choose the name first!");
            snackbar = Snackbar.make(view, "Write a name for your Lutemon before adding!", BaseTransientBottomBar.LENGTH_LONG);
        }
        //Adding the lutemon
        else {
            //Choosing what lutemon to make
            switch(type) {
                    case "Banankey":
                        lutemon = new LutemonBanankey(name.getText().toString());
                        break;
                    case "Möyks":
                        lutemon = new LutemonMoyks(name.getText().toString());
                        break;
                    case "Mintigress":
                        lutemon = new LutemonMintigress(name.getText().toString());
                        break;
                    case "Brawlberry":
                        lutemon = new LutemonBrawlberry(name.getText().toString());
                        break;
                    case "Doczoi":
                        lutemon = new LutemonDoczoi(name.getText().toString());
                        break;
                    default:
                        lutemon = new Lutemon(type, name.getText().toString()+"no builder yet made");
                        break;
            }
            //Lutemons are put in Home
            Home.getInstance().addLutemon(lutemon);
            System.out.println("Added lutemon "+name.getText().toString()+", which is of type "+type+" whose id is "+lutemon.id+" and health is "+lutemon.health);
            snackbar = Snackbar.make(view, "Added lutemon "+name.getText().toString()+", which is of type "+type, BaseTransientBottomBar.LENGTH_LONG);
            }

        snackbar.show();
        }

    }