package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Our own lutemon theme created specifically for this project by us, so we own the rights to it :DDD
        mediaPlayer = MediaPlayer.create(this, R.raw.lutemontheme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();



    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        //checking if the music was paused in the middle and then set to the start to start it over
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        //music started then immediately stopped without this :(
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void switchToMenu(View view) {
        mediaPlayer.pause();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

}