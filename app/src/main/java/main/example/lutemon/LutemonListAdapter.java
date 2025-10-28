package main.example.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<ListViewHolder>{

    private Context context;
    private ArrayList<Lutemon> lutemons;
    private String activityType;

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons, String activityType) {
        this.context=context;
        this.lutemons=lutemons;
        this.activityType=activityType;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false), activityType);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        System.out.println(lutemon+" is position");
        holder.lutemonName.setText(lutemon.name);
        holder.lutemonType.setText(lutemon.type);
        holder.lutemonHealth.setText("HP: "+lutemon.health+"/"+lutemon.maxHealth);
        holder.lutemonExperience.setText("EXP: "+lutemon.experience);
        holder.lutemonImage.setImageResource(lutemon.getImage());
        //We only activate these statistics if we're in the stats activity, this way we can reuse the same lutemon_view.xml
        if(activityType.equals("StatsActivity")) {
            holder.lutemonBattlesWon.setText("Wins: "+lutemon.battlesWon);
            holder.lutemonBattlesHad.setText("Battles: "+lutemon.battlesHad);
            holder.lutemonTimesTrained.setText("Trained: "+lutemon.timesTrained+" times");
            if(lutemon.experience>=lutemon.evolutionLevel) {
                holder.lutemonEvolved.setText("Form: evolved");
            }
            else {
                holder.lutemonEvolved.setText("Form: basic");
            }

        }
    }



    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void updateData(ArrayList<Lutemon> newData) {
        lutemons = newData;
        notifyDataSetChanged();
    }


}
