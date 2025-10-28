package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ImageView imgHome;

    private RecyclerView recyclerView;
    private RadioGroup lutemonGroup;
    private TextView sendingInstructions;
    private Button buttonSend;
    private LutemonListAdapter adapter;

    private LinearLayout checkboxLayout;

    private Home s = Home.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgHome=findViewById(R.id.imgHomeBackground);
        imgHome.setImageResource(R.drawable.background_home);

        recyclerView=findViewById(R.id.rvLutemonsAtHome);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lutemonGroup=findViewById(R.id.rgHomeToX);
        sendingInstructions=findViewById(R.id.txtHomeInstructions);
        buttonSend=findViewById(R.id.buttonSendFromHome);
    }



    @Override
    protected void onResume() {
        //Updating the recyclerview info when the back arrow is pressed on the phone
        super.onResume();
        adapter = new LutemonListAdapter(getApplicationContext(), s.getLutemons(), "HomeActivity");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if(s.getLutemons().size()>0) {
            checkboxLayout = findViewById(R.id.svCheckBoxesHome);

            ArrayList<Lutemon> lutemons = s.getLutemons();

            for(Lutemon lutemon : lutemons) {
                int lutemonId=lutemon.id;
                CheckBox checkBox = new CheckBox(this);
                checkBox.setId(lutemonId);
                //System.out.println("Lutemon is "+lutemons.get(i).name+" whos id is "+lutemons.get(i).id+" and the checkbox id is "+i);
                checkBox.setText(s.getLutemon(lutemonId).type+"("+s.getLutemon(lutemonId).name+")");
                checkboxLayout.addView(checkBox);
            }
            lutemonGroup.setVisibility(View.VISIBLE);
            sendingInstructions.setVisibility(View.VISIBLE);
            buttonSend.setVisibility(View.VISIBLE);
        }
        else {
            lutemonGroup.setVisibility(View.GONE);
            sendingInstructions.setVisibility(View.GONE);
            buttonSend.setVisibility(View.GONE);
        }

    }

    public void switchToCreation(View view) {
        Intent intent = new Intent(this, LutemonCreationActivity.class);
        startActivity(intent);
    }

    public void sendLutemonsFromHome(View view) {
        lutemonGroup=findViewById(R.id.rgHomeToX);
        Snackbar snackbar;
        Lutemon lutemon;

        //going through the dynamically checked boxes
        for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
            View checkboxView = checkboxLayout.getChildAt(i);
            CheckBox checkBox = (CheckBox) checkboxView;
            if (checkBox.isChecked()) {
                lutemon = s.getLutemon(checkBox.getId());
                switch(lutemonGroup.getCheckedRadioButtonId()) {
                    case R.id.rbSleep:
                        if(lutemon.health==0) {
                            snackbar = Snackbar.make(view, "Revive the dead lutemon before sending them to sleep", BaseTransientBottomBar.LENGTH_LONG);
                        }
                        else if(lutemon.health<lutemon.maxHealth) {
                            imgHome.setImageResource(R.drawable.background_home_sleep);
                            s.sleep(checkBox.getId());
                            snackbar = Snackbar.make(view, "The selected lutemon(s) slept and are full of energy!", BaseTransientBottomBar.LENGTH_LONG);
                        }
                        else {
                            snackbar = Snackbar.make(view, "The selected lutemon(s) aren't sleepy", BaseTransientBottomBar.LENGTH_LONG);
                        }
                        snackbar.show();
                        break;
                    case R.id.rbBattlefield:
                        System.out.println("Lutemon is "+lutemon.name+" who's id is "+lutemon.id+" and the checkbox id is "+checkBox.getId());
                        Battlefield battlefield = Battlefield.getInstance();
                        battlefield.addLutemon(lutemon);
                        s.deleteLutemon(checkBox.getId());
                        checkBox.setChecked(false);//THIS IS VERY IMPORTANT otherwise it thinks the hidden checkboxes are checked!!!
                        checkBox.setVisibility(View.GONE);
                        break;
                    case R.id.rbTraining:
                        Training training = Training.getInstance();
                        training.addLutemon(lutemon);
                        s.deleteLutemon(checkBox.getId());
                        checkBox.setChecked(false);
                        checkBox.setVisibility(View.GONE);
                        break;
                    case R.id.rbHospital:
                        Hospital hospital = Hospital.getInstance();
                        hospital.addLutemon(lutemon);
                        s.deleteLutemon(checkBox.getId());
                        checkBox.setChecked(false);
                        checkBox.setVisibility(View.GONE);
                        break;
                }

            }
        }

        adapter.notifyDataSetChanged();
    }

}