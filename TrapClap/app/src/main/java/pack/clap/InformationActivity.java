package pack.clap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mainModel.modelRooms.Rooms;

public class InformationActivity extends AppCompatActivity {

    private Button returnqr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        GlobalActivity global = (GlobalActivity) getApplicationContext();
        Rooms rooms = new Rooms();
        List<Rooms> roomsList=rooms.buildRoom();

        TextView textView = findViewById(R.id.row1);
        textView.setText(roomsList.get(global.getRoomId()).getPlanning().getLessons(0).toString());
        textView = findViewById(R.id.row2);
        textView.setText(roomsList.get(global.getRoomId()).getPlanning().getLessons(1).toString());
        textView = findViewById(R.id.row4);
        textView.setText(roomsList.get(global.getRoomId()).getPlanning().getLessons(3).toString());
        textView = findViewById(R.id.row5);
        textView.setText(roomsList.get(global.getRoomId()).getPlanning().getLessons(4).toString());

        textView=findViewById(R.id.informations);
        textView.setText(roomsList.get(global.getRoomId()).toString());

        this.returnqr=findViewById(R.id.returnqr);
        this.returnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalActivity global = (GlobalActivity) getApplicationContext();
                global.setRoomId(-1);
                Intent intent= new Intent(getApplicationContext(), QrMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
