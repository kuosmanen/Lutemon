package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MenuActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private Snackbar snackbar;

    private Home home = Home.getInstance();
    private Battlefield battlefield = Battlefield.getInstance();
    private Hospital hospital = Hospital.getInstance();
    private Training training = Training.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mediaPlayer = MediaPlayer.create(this, R.raw.backroundmusicbeepbox);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.6f, 0.6f);
        mediaPlayer.start();
    }
    protected void onDestroy() {
        //music started then immediately stopped without this :(
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void switchToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void switchToTraining(View view) {
        if(training.getLutemons().size()>0) {
            Intent intent = new Intent(this, TrainingActivity.class);
            startActivity(intent);
        }
        else {
            snackbar= Snackbar.make(view, "Send at least 1 lutemon to train before going there", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
    }
    public void switchToHospital(View view) {
        if(hospital.getLutemons().size()>0) {
            Intent intent = new Intent(this, HospitalActivity.class);
            startActivity(intent);
        }
        else {
            snackbar= Snackbar.make(view, "Send at least 1 lutemon to the hospital before going there", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }

    }
    public void switchToBattlefieldMenu(View view) {
        if(battlefield.getLutemons().size()>0) {
            Intent intent = new Intent(this, BattlefieldMenuActivity.class);
            startActivity(intent);
        }
        else {
            snackbar= Snackbar.make(view, "Send at least 1 lutemon to the battlefield before going there", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void switchToStats(View view) {
        if(home.getLutemons().size()+training.getLutemons().size()+hospital.getLutemons().size()+battlefield.getLutemons().size()>0) {
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        }
        else {
            snackbar= Snackbar.make(view, "Create at least 1 lutemon before going to stats", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
    }

}