package pack.clap;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.List;
import mainModel.modelRooms.Rooms;
import pack.clap.QrMainActivity;

public class MainActivity extends AppCompatActivity {
    //Acceuil
    private Button qrButton;
    private Button ulysseButton;
    private Button popupButton;
    private MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activity=this;
        this.qrButton=findViewById(R.id.qrButton);
        this.qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QrMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        this.ulysseButton=findViewById(R.id.ulysseButton);
        this.ulysseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), UlysseActivity.class);
                startActivity(intent);
                finish();

            }
        });

        Rooms rooms = new Rooms();
        List<Rooms> roomsList=rooms.buildRoom();

        this.popupButton=findViewById(R.id.popupButton);
        this.popupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder popupEdt = new AlertDialog.Builder(activity,R.style.MyDialogTheme);
                /*Replace 0 with room id -> QR code*/
                popupEdt.setMessage(roomsList.get(50).toString());
                popupEdt.show();
            }
        });

    }



}
