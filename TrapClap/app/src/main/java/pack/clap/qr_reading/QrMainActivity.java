package pack.clap.qr_reading;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mainModel.modelRooms.Rooms;
import pack.clap.GlobalActivity;
import pack.clap.MainActivity;
import pack.clap.R;


public class QrMainActivity extends AppCompatActivity implements Scene.OnUpdateListener {

    public CustomArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_main);

        this.arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this);

        Rooms rooms = new Rooms();
        List<Rooms> roomsList=rooms.buildRoom();
        String[] Rooms = new String[55];
        Rooms[0]="Destination...";
        Rooms[1]="Aucune";
        Rooms[2]="Visite guidée";
        int i=3;
        for(Iterator<Rooms> it=roomsList.iterator();it.hasNext();)
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

        Button seek = (Button)findViewById(R.id.go);
        seek.setOnClickListener(new View.OnClickListener() {
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

    }

    public void setupDatabase(Config config, Session session) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode1);
        AugmentedImageDatabase augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage("QR1", bitmap1);
        config.setAugmentedImageDatabase(augmentedImageDatabase);

    }

    @Override
    public void onUpdate(FrameTime frameTime) {

        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : images) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                if (augmentedImage.getName().equals("QR1")) {
                    Anchor anchor = augmentedImage.createAnchor(augmentedImage.getCenterPose());
                    createModel(anchor);
                }
            }
        }
    }

    private void createModel(Anchor anchor) {
        ModelRenderable.builder()
                .setSource(this, Uri.parse(getString(R.string.model1)))
                .build()
                .thenAccept(modelRenderable -> placeModel(modelRenderable, anchor));
    }

    private void placeModel(ModelRenderable modelRenderable, Anchor anchor) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
