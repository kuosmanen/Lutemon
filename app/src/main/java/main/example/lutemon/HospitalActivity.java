package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Hospital s = Hospital.getInstance();
    private LutemonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        recyclerView=findViewById(R.id.rvHospitalLutemons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), s.getLutemons(), "HospitalActivity");
        recyclerView.setAdapter(adapter);

    }


    public void reviveDeadLutemons(View view) {
        ArrayList<Lutemon> lutemons;
        ArrayList<Lutemon> lutemonsToMove = new ArrayList<>();
        //We do this 2 list thing because else we get a ConcurrentModificationException error :(
        lutemons = s.getLutemons();
        Snackbar snackbar;
        Home home = Home.getInstance();

        for (Lutemon lutemon : lutemons) {
            s.revive(lutemon.id);
        }
        adapter.updateData(lutemons);

        for (Lutemon lutemon : lutemons) {
            lutemonsToMove.add(lutemon);
        }

        for (Lutemon lutemon : lutemonsToMove) {
            home.addLutemon(lutemon);
            s.deleteLutemon(lutemon.id);
        }

        snackbar = Snackbar.make(view, "Dead lutemon(s) were revived and everyone was sent home!", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();


    }


}