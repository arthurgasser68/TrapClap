package pack.clap;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.Iterator;
import java.util.List;
import mainModel.modelRooms.Rooms;
import pack.clap.QrMainActivity;

public class MainActivity extends AppCompatActivity {

    private Button ulysseButton;
    private Button popupButton;
    private Button modeButton;
    private Button seek;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activity=this;

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
        String[] Rooms = new String[55];
        Rooms[0]="Destination...";
        Rooms[1]="Aucune";
        Rooms[2]="Visite guidée";
        int i=3;
        for(Iterator<Rooms> it = roomsList.iterator(); it.hasNext();)
        {
            Rooms r = it.next();
            Rooms[i]=r.getName();
            i++;
        }

        Spinner list = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Rooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);

        this.popupButton=findViewById(R.id.popupButton);
        this.popupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder popupEdt = new AlertDialog.Builder(activity,R.style.MyDialogTheme);
                popupEdt.setMessage(roomsList.get(50).toString());
                popupEdt.show();
            }
        });

        GlobalActivity global = (GlobalActivity) getApplicationContext();

        this.seek=(Button)findViewById(R.id.go);
        this.seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = list.getSelectedItemPosition();
                if(index>2) global.setRoom(roomsList.get(index-3).getName());
                else
                {
                    if(index==1)
                    {
                        global.setRoom(null); //Retour
                        Intent intent= new Intent(getApplicationContext(), QrMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        if(index==2) global.setRoom("Visite guidée"); //Code visite guidée
                    }
                }
                if(index!=0&&index!=1)
                {
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        this.modeButton=findViewById(R.id.mode);
        this.modeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), Map2DActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView textView=findViewById(R.id.test);
        GlobalActivity globalActivity = (GlobalActivity) getApplicationContext();
        textView.setText(globalActivity.getRoom());

    }
}
