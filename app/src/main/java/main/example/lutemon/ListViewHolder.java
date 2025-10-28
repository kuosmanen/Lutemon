package main.example.lutemon;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    TextView lutemonName, lutemonType, lutemonHealth, lutemonExperience, lutemonBattlesWon, lutemonBattlesHad, lutemonTimesTrained, lutemonEvolved;
    ImageView lutemonImage;

    //This is for determining where the recycler view is located so we can show the right things when lutemons are shown and this way we don't need multiple lutemon_view.xml
    String activityType;

    public ListViewHolder(@NonNull View itemView, String activityType) {
        super(itemView);
        lutemonName=itemView.findViewById(R.id.txtLutemonName);
        lutemonType=itemView.findViewById(R.id.txtLutemonType);
        lutemonHealth=itemView.findViewById(R.id.txtLutemonHealth);
        lutemonExperience=itemView.findViewById(R.id.txtLutemonExperience);
        //Banankey basic image is used as a placeholder, the image will be switched later to correct one
        lutemonImage=itemView.findViewById(R.id.img_LutemonList);
        if(activityType.equals("StatsActivity")) {
            lutemonBattlesWon=itemView.findViewById(R.id.txtBattlesWon);
            lutemonBattlesHad=itemView.findViewById(R.id.txtBattlesHad);
            lutemonTimesTrained=itemView.findViewById(R.id.txtTimesTrained);
            lutemonEvolved=itemView.findViewById(R.id.txtEvolved);
        }
    }


}
