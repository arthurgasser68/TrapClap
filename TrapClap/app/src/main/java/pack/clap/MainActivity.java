package pack.clap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import pack.clap.qr_reading.QrMainActivity;

public class MainActivity extends AppCompatActivity {
    //Acceuil
    Button qrButton;
    Button ulysseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.qrButton=findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QrMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        this.ulysseButton=findViewById(R.id.ulysseButton);
        ulysseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), UlysseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}
