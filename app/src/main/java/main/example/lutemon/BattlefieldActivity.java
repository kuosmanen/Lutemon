package main.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BattlefieldActivity extends AppCompatActivity {


    private Lutemon A, B;

    private TextView battleInfo;
    private Battlefield s=Battlefield.getInstance();

    private ImageView imageA, imageB;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);
        battleInfo=findViewById(R.id.txtBattleInfo);


        A=s.getLutemon(s.getFirstFighterId());
        B=s.getLutemon(s.getSecondFighterId());

        imageA=findViewById(R.id.imgFighter1);
        imageB=findViewById(R.id.imgFighter2);

        imageA.setImageResource(A.getImage());
        imageB.setImageResource(B.getImage());

        battleInfo.setText(s.fight());

        mediaPlayer = MediaPlayer.create(this, R.raw.winsfxmadeinbeepbox);
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
}