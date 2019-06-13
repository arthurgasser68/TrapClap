package pack.clap;

import android.content.Intent;
import android.media.MediaPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import mainModel.Building;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Building.getInstance();
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.bonjour_clap);


        Runnable runnable = new Runnable() {


            @Override
            public void run() {
                mp.start();
                Intent intent = new Intent(getApplicationContext(), QrMainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        new Handler().postDelayed(runnable,2000);
    }
}
