package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Lutemon> lutemons = new ArrayList<>(Home.getInstance().getLutemons());
    private LutemonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        lutemons.addAll(Training.getInstance().getLutemons());
        lutemons.addAll(Battlefield.getInstance().getLutemons());
        lutemons.addAll(Hospital.getInstance().getLutemons());

        recyclerView=findViewById(R.id.rvStatsLutemons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(this, lutemons, "StatsActivity");
        recyclerView.setAdapter(adapter);
    }
}