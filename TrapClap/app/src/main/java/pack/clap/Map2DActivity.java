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
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mainModel.Building;
import mainModel.modelMapping.Graph;
import mainModel.modelMapping.Maps;
import mainModel.modelRooms.Rooms;

public class Map2DActivity extends AppCompatActivity {

    private Button modeButton;
    private Button seek;

    private Map<Integer,Point> link;

    private List<Rooms> roomsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        link = new HashMap();

        link.put(1,new Point(74,15));
        link.put(2,new Point(24,50));
        link.put(3,new Point(24,62));
        link.put(4,new Point(24,92));
        link.put(5,new Point(24,125));
        link.put(6,new Point(24,138));
        link.put(7,new Point(24,193));
        link.put(8,new Point(24,205));
        link.put(9,new Point(24,248));
        link.put(10,new Point(24,292));
        link.put(11,new Point(24,307));
        link.put(12,new Point(70,307));
        link.put(13,new Point(125,307));
        link.put(14,new Point(145,307));
        link.put(15,new Point(171,307));
        link.put(16,new Point(208,307));
        link.put(17,new Point(263,307));
        link.put(18,new Point(285,307));
        link.put(19,new Point(307,307));
        link.put(20,new Point(350,307));
        link.put(21,new Point(24,322));
        link.put(22,new Point(24,402));
        link.put(23,new Point(24,470));
        link.put(24,new Point(24,495));
        link.put(25,new Point(47,495));
        link.put(26,new Point(24,527));
        link.put(27,new Point(24,575));
        link.put(28,new Point(24,595));
        link.put(29,new Point(24,662));
        link.put(30,new Point(72,581));
        link.put(31,new Point(67,531));
        link.put(32,new Point(103,581));
        link.put(33,new Point(133,581));
        link.put(34,new Point(165,581));
        link.put(35,new Point(202,581));
        link.put(36,new Point(224,581));
        link.put(37,new Point(264,581));
        link.put(38,new Point(284,581));
        link.put(39,new Point(305,581));
        link.put(40,new Point(345,581));





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2_d);

        GlobalActivity global = (GlobalActivity) getApplicationContext();
        this.roomsList=Building.getINSTANCE().getRoomsList();

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
                if(index>1)
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
        //TextView textView=findViewById(R.id.destination);
        //textView.setText("Vous vous dirigez vers : "+global.getRoom());

        Maps map = new Maps();
        //textView=findViewById(R.id.testMap);
        //textView.setText(map.getPathFromTo(11,global.getRoom()).toString());

        if(global.getRoom()!="Visite guidée") this.maj((map.getPathFromTo(1,global.getRoom())));
        else this.maj((map.getPathFromTo(1,30)));
    }

    public void maj(LinkedList m){
        ImageView im= (ImageView) findViewById( R.id.Map2D);
        Paint mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.color_LightBlue));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);

        Point mPoint = new Point();


        Bitmap bm=  BitmapFactory.decodeResource(getResources(),R.drawable.map_finale );
        bm=bm.copy(bm.getConfig(),true);
        Canvas c=new Canvas(bm);
        im.draw(c);
        Iterator i=m.iterator();
        Graph.Vertex n;
        Graph.Vertex t=null;
       /* for (int j=2;j<12;j++){
            c.drawLine(200,200,((Point)this.link.get(j)).x,((Point)this.link.get(j)).y,mPaint);
        }*/

        n=(Graph.Vertex)i.next();
        float x=((Point)this.link.get(Integer.parseInt(n.getId()))).x;
        float y=((Point)this.link.get(Integer.parseInt(n.getId()))).y;
        c.drawOval(x-3,y+3,x+3,y-3,mPaint);
        while(i.hasNext()){
            t=(Graph.Vertex)i.next();
            //System.out.println("||"+Integer.parseInt(n.getId()));
            //System.out.println(t.getId() + "||");

            c.drawLine(((Point)this.link.get(Integer.parseInt(n.getId()))).x,((Point)this.link.get(Integer.parseInt(n.getId()))).y,((Point)this.link.get(Integer.parseInt(t.getId()))).x,((Point)this.link.get(Integer.parseInt(t.getId()))).y,mPaint);
            n=t.clone();

        }

        x=((Point)this.link.get(Integer.parseInt(t.getId()))).x;
        y=((Point)this.link.get(Integer.parseInt(t.getId()))).y;
        c.drawOval(x,y+5,x+10,y-5,mPaint);

        //bm.setPixel(10,10, Color.red(1));

        /*c.drawLine(((Point)this.link.get(2)).x,((Point)this.link.get(2)).y,((Point)this.link.get(12)).x,((Point)this.link.get(12)).y,mPaint);
        for (int j = 3;j<35;j++){
            c.drawLine(((Point)this.link.get(2)).x,((Point)this.link.get(2)).y,((Point)this.link.get(i)).x,((Point)this.link.get(i)).y,mPaint);
        }*/

        im.setImageBitmap(bm);


    }



}
