package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BattlefieldMenuActivity extends AppCompatActivity {

    private Battlefield s = Battlefield.getInstance();
    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;
    private LinearLayout checkboxLayout;

    private int firstLutemon=-1;
    private int secondLutemon=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield_menu);

        recyclerView=findViewById(R.id.rvLutemons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(this, s.getLutemons(), "BattlefieldMenuActivity");
        recyclerView.setAdapter(adapter);

        checkboxLayout = findViewById(R.id.llCheckBoxes);

        ArrayList<Lutemon> lutemons = s.getLutemons();

        for(Lutemon lutemon : lutemons) {
            int lutemonId=lutemon.id;
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(lutemon.id);
            //System.out.println("Lutemon is "+lutemons.get(i).name+" whos id is "+lutemons.get(i).id+" and the checkbox id is "+i);
            checkBox.setText(s.getLutemon(lutemonId).type+"("+s.getLutemon(lutemonId).name+")");
            checkboxLayout.addView(checkBox);
        }
    }

    @Override
    protected void onResume() {
        //Updating the recyclerview info when the back arrow is pressed on the phone
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void sendHome(View view) {
        Snackbar snackbar=Snackbar.make(view, "The lutemon(s) were sent home", BaseTransientBottomBar.LENGTH_LONG);

        //going through the dynamically checked boxes
        for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
            View checkboxView = checkboxLayout.getChildAt(i);
            CheckBox checkBox = (CheckBox) checkboxView;
            if (checkBox.isChecked()) {
                Home home = Home.getInstance();

                home.addLutemon(s.getLutemon(checkBox.getId()));
                s.deleteLutemon(checkBox.getId());
                checkBox.setChecked(false);//THIS IS VERY IMPORTANT otherwise it thinks the hidden checkboxes are checked!!!
                checkBox.setVisibility(View.GONE);
                snackbar.show();
                adapter.notifyDataSetChanged();
            }
        }
    }



    public void chooseFighters(View view) {
        Snackbar snackbar;
        boolean tooManyLutemons=false;

        //going through the dynamically checked boxes
        firstLutemon=-1;
        secondLutemon=-1;
        for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
            View checkboxView = checkboxLayout.getChildAt(i);
            CheckBox checkBox = (CheckBox) checkboxView;
            if (checkBox.isChecked()) {
                int lutemonId = checkBox.getId();
                if(firstLutemon==-1) {
                    firstLutemon=(lutemonId);
                }
                else if (secondLutemon==-1){
                    secondLutemon=(lutemonId);
                }
                else {
                    tooManyLutemons=true;
                }
            }
        }

        if(firstLutemon==-1) {
            snackbar = Snackbar.make(view, "Choose the first fighter before battle!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else if(s.getLutemon(firstLutemon).health==0) {
            snackbar = Snackbar.make(view, "The first fighter is dead and can't fight.", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else if(secondLutemon==-1) {
            snackbar = Snackbar.make(view, "Choose the second fighter before battle!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else if(s.getLutemon(secondLutemon).health==0) {
            snackbar = Snackbar.make(view, "The second fighter is dead and can't fight.", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else if(firstLutemon==secondLutemon) {
            snackbar = Snackbar.make(view, "Lutemons can't battle themselves!\nChoose 2 different lutemons.", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else if(tooManyLutemons) {
            snackbar = Snackbar.make(view, "You can only choose 2 lutemons to fight!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            System.out.println("The first fighter is (id)"+firstLutemon+" and the second fighter is (id)"+secondLutemon);
            s.setFirstFighterId(firstLutemon);
            s.setSecondFighterId(secondLutemon);
            firstLutemon=-1;
            secondLutemon=-1;
            Intent intent = new Intent(this, BattlefieldActivity.class);
            startActivity(intent);
            adapter.updateData(s.getLutemons());

        }

    }








}