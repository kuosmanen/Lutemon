package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private Training s=Training.getInstance();
    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;
    private LinearLayout checkboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        recyclerView=findViewById(R.id.rvTrainingLutemons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(this, s.getLutemons(), "TrainingActivity");
        recyclerView.setAdapter(adapter);

        checkboxLayout = findViewById(R.id.llTrainingChackBoxes);

        ArrayList<Lutemon> lutemons = s.getLutemons();

        for(Lutemon lutemon : lutemons) {
            int lutemonId=lutemon.id;
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(lutemon.id);
            checkBox.setText(s.getLutemon(lutemonId).type+"("+s.getLutemon(lutemonId).name+")");
            checkboxLayout.addView(checkBox);
        }
    }

    public void trainLutemons(View view) {
        Snackbar snackbar= Snackbar.make(view, "Select the lutemon(s) before training", BaseTransientBottomBar.LENGTH_LONG);;
        Lutemon lutemon;

        //going through the dynamically checked boxes
        for (int i = 0; i < checkboxLayout.getChildCount(); i++) {
            View checkboxView = checkboxLayout.getChildAt(i);
            CheckBox checkBox = (CheckBox) checkboxView;
            if (checkBox.isChecked()) {
                int lutemonId = checkBox.getId();
                lutemon = s.getLutemon(lutemonId);
                if(lutemon.health-(int)(lutemon.maxHealth*lutemon.trainingExhaustionFactor)<=0) {
                    snackbar=Snackbar.make(view, "If you train that lutemon it would die of exhaustion!", BaseTransientBottomBar.LENGTH_LONG);
                    continue;
                }
                else if(lutemon.health==0) {
                    snackbar=Snackbar.make(view, "Dead lutemons can't train! Revive them before training", BaseTransientBottomBar.LENGTH_LONG);
                    continue;
                }
                s.train(lutemonId);
                snackbar=Snackbar.make(view, "The lutemon(s) trained hard and gained experience!", BaseTransientBottomBar.LENGTH_LONG);
                adapter.notifyDataSetChanged();
            }
        }
        snackbar.show();
    }

    public void sendHome(View view) {
        Snackbar snackbar=Snackbar.make(view, "The lutemon(s) were sent home", BaseTransientBottomBar.LENGTH_LONG);
        int numberOfLutemonsBeginning = s.getLutemons().size();

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
                adapter.notifyDataSetChanged();
            }
        }
        if(s.getLutemons().size()==numberOfLutemonsBeginning) {
            snackbar=Snackbar.make(view, "Select the lutemon(s) before sending home", BaseTransientBottomBar.LENGTH_LONG);
        }
        snackbar.show();
    }


}

