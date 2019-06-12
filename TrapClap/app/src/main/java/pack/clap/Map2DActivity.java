package pack.clap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mainModel.modelRooms.Rooms;
import pack.clap.QrMainActivity;

public class Map2DActivity extends AppCompatActivity {

    private Button modeButton;
    private Button seek;
    private Map<Integer,Point> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        link = new HashMap();

        link.put(1,new Point(74,15));
        link.put(2,new Point(33,47));
        link.put(3,new Point(32,13));
        link.put(4,new Point(24,43));
        link.put(5,new Point(24,72));
        link.put(6,new Point(24,86));
        link.put(7,new Point(24,140));
        link.put(8,new Point(24,155));
        link.put(9,new Point(24,198));
        link.put(10,new Point(24,244));
        link.put(11,new Point(36,262));
        link.put(12,new Point(70,259));
        link.put(13,new Point(125,277));
        link.put(14,new Point(145,277));
        link.put(15,new Point(171,277));
        link.put(16,new Point(208,277));
        link.put(17,new Point(263,277));
        link.put(18,new Point(285,277));
        link.put(19,new Point(307,277));
        link.put(20,new Point(350,277));
        link.put(21,new Point(24,274));
        link.put(22,new Point(24,352));
        link.put(23,new Point(24,414));
        link.put(24,new Point(24,457));
        link.put(25,new Point(47,457));
        link.put(26,new Point(24,477));
        link.put(27,new Point(24,531));
        link.put(28,new Point(24,547));
        link.put(29,new Point(24,613));
        link.put(30,new Point(72,531));
        link.put(31,new Point(67,483));
        link.put(32,new Point(103,531));
        link.put(33,new Point(128,531));
        link.put(34,new Point(172,531));
        link.put(35,new Point(199,531));
        link.put(36,new Point(218,531));
        link.put(37,new Point(257,531));
        link.put(38,new Point(274,531));
        link.put(39,new Point(298,531));
        link.put(40,new Point(355,531));




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2_d);

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
                    Intent intent= new Intent(getApplicationContext(), Map2DActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        this.modeButton=findViewById(R.id.mode);
        this.modeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.maj();

    }

    public void maj(){
        ImageView im= (ImageView) findViewById( R.id.Map2D);
        Paint mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);

        Point mPoint = new Point();


        Bitmap bm=  BitmapFactory.decodeResource(getResources(),R.drawable.map_blanche);
        bm=bm.copy(bm.getConfig(),true);
        Canvas c=new Canvas(bm);
        im.draw(c);



        //bm.setPixel(10,10, Color.red(1));

        c.drawLine(((Point)this.link.get(2)).x,((Point)this.link.get(2)).y,((Point)this.link.get(12)).x,((Point)this.link.get(12)).y,mPaint);
        for (int i = 3;i<35;i++){
            c.drawLine(((Point)this.link.get(2)).x,((Point)this.link.get(2)).y,((Point)this.link.get(i)).x,((Point)this.link.get(i)).y,mPaint);
        }

        im.setImageBitmap(bm);


    }


}
