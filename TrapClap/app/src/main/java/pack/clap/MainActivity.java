package pack.clap;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.List;

import mainModel.Building;
import mainModel.modelMapping.AnchorLibraries;
import mainModel.modelMapping.Maps;
import mainModel.modelRooms.Rooms;

public class MainActivity extends AppCompatActivity {

    private Button modeButton;
    private Button seek;
    private List<Rooms> roomsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.roomsList=Building.getINSTANCE().getRoomsList();
        GlobalActivity global = (GlobalActivity) getApplicationContext();

        Spinner list = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,global.createRooms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);

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

        /*Test Map*/

        TextView textView=findViewById(R.id.destination);
        textView.setText("Vous vous dirigez vers : "+global.getRoom());

        Maps map = new Maps();
        textView=findViewById(R.id.testMap);
        textView.setText(map.getPathFromTo(11,global.getRoom()).toString());

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_container);
        if (frag == null) {
            frag = new AnchorLibraries();
            fm.beginTransaction().add(R.id.fragment_container, frag).commit();
        }

    }
}
