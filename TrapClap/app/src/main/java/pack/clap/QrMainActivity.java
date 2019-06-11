package pack.clap;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;

import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mainModel.QrCode;
import mainModel.QrCodes;

import mainModel.modelRooms.Rooms;
import pack.clap.qr_reading.CustomArFragment;


public class QrMainActivity extends AppCompatActivity implements Scene.OnUpdateListener {

    private static final String TAG = QrMainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    QrCodes qrCodes;
    public CustomArFragment arFragment;
    private Button seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_main);
        if(!checkIsSupportedDeviceOrFinish(this)){
            return;
        }

        this.arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this);

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
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

    public void setupDatabase(Config config, Session session){
        this.qrCodes=new QrCodes(session);

        qrCodes.add(new QrCode("ambs", BitmapFactory.decodeResource(getResources(), R.drawable.ambs),0));
        qrCodes.add(new QrCode("annexe_sanitaire",BitmapFactory.decodeResource(getResources(), R.drawable.annexe_sanitaire),1));
        qrCodes.add(new QrCode("aubry",BitmapFactory.decodeResource(getResources(), R.drawable.aubry),2));
        qrCodes.add(new QrCode("basset",BitmapFactory.decodeResource(getResources(), R.drawable.basset),3));
        qrCodes.add(new QrCode("ben_souissi",BitmapFactory.decodeResource(getResources(), R.drawable.ben_souissi),4));
        qrCodes.add(new QrCode("binder",BitmapFactory.decodeResource(getResources(), R.drawable.binder),5));
        qrCodes.add(new QrCode("birouche_mourllion",BitmapFactory.decodeResource(getResources(), R.drawable.birouche_mourllion),6));
        qrCodes.add(new QrCode("bureau_chercheur_lsi",BitmapFactory.decodeResource(getResources(), R.drawable.bureau_chercheur_lsi),7));
        qrCodes.add(new QrCode("bureau_chercheur_miam_1",BitmapFactory.decodeResource(getResources(), R.drawable.bureau_chercheur_miam_1),8));
        qrCodes.add(new QrCode("bureau_chercheur_miam_2",BitmapFactory.decodeResource(getResources(), R.drawable.bureau_chercheur_miam_2),9));
        qrCodes.add(new QrCode("bureau_chercheur_miam_3",BitmapFactory.decodeResource(getResources(),R.drawable.bureau_chercheur_miam_3),10));
        qrCodes.add(new QrCode("cafe",BitmapFactory.decodeResource(getResources(),R.drawable.cafe),11));
        qrCodes.add(new QrCode("dupuis",BitmapFactory.decodeResource(getResources(),R.drawable.dupuis),12));
        qrCodes.add(new QrCode("e30",BitmapFactory.decodeResource(getResources(),R.drawable.e30),13));
        qrCodes.add(new QrCode("e31",BitmapFactory.decodeResource(getResources(),R.drawable.e31),14));
        qrCodes.add(new QrCode("e32",BitmapFactory.decodeResource(getResources(),R.drawable.e32),15));
        qrCodes.add(new QrCode("e33",BitmapFactory.decodeResource(getResources(),R.drawable.e33),16));
        qrCodes.add(new QrCode("e34",BitmapFactory.decodeResource(getResources(),R.drawable.e34),17));
        qrCodes.add(new QrCode("e35",BitmapFactory.decodeResource(getResources(),R.drawable.e35),18));
        qrCodes.add(new QrCode("e36",BitmapFactory.decodeResource(getResources(),R.drawable.e36),19));
        qrCodes.add(new QrCode("e37",BitmapFactory.decodeResource(getResources(),R.drawable.e37),20));
        qrCodes.add(new QrCode("e37_bis",BitmapFactory.decodeResource(getResources(),R.drawable.e37_bis),21));
        qrCodes.add(new QrCode("e38",BitmapFactory.decodeResource(getResources(),R.drawable.e38),22));
        qrCodes.add(new QrCode("fondement",BitmapFactory.decodeResource(getResources(),R.drawable.fondement),23));
        qrCodes.add(new QrCode("forestier",BitmapFactory.decodeResource(getResources(),R.drawable.forestier),24));
        qrCodes.add(new QrCode("hassenforder",BitmapFactory.decodeResource(getResources(),R.drawable.hassenforder),25));
        qrCodes.add(new QrCode("iariss",BitmapFactory.decodeResource(getResources(),R.drawable.iariss),26));
        qrCodes.add(new QrCode("labo_lsi",BitmapFactory.decodeResource(getResources(),R.drawable.labo_lsi),27));
        qrCodes.add(new QrCode("lauffenburger",BitmapFactory.decodeResource(getResources(),R.drawable.lauffenburger),28));
        qrCodes.add(new QrCode("laurain",BitmapFactory.decodeResource(getResources(),R.drawable.laurain),29));
        qrCodes.add(new QrCode("ledy",BitmapFactory.decodeResource(getResources(),R.drawable.ledy),30));
        qrCodes.add(new QrCode("lsi",BitmapFactory.decodeResource(getResources(),R.drawable.lsi),31));
        qrCodes.add(new QrCode("muller",BitmapFactory.decodeResource(getResources(),R.drawable.muller),32));
        qrCodes.add(new QrCode("orjuela",BitmapFactory.decodeResource(getResources(),R.drawable.orjuela),33));
        qrCodes.add(new QrCode("pc_reseaux",BitmapFactory.decodeResource(getResources(),R.drawable.pc_reseaux),34));
        qrCodes.add(new QrCode("perronne",BitmapFactory.decodeResource(getResources(),R.drawable.perronne),35));
        qrCodes.add(new QrCode("pinot",BitmapFactory.decodeResource(getResources(),R.drawable.pinot),36));
        qrCodes.add(new QrCode("prof_invite",BitmapFactory.decodeResource(getResources(),R.drawable.prof_invite),37));
        qrCodes.add(new QrCode("salle_reunion",BitmapFactory.decodeResource(getResources(),R.drawable.salle_reunion),38));
        qrCodes.add(new QrCode("secretariat_miage",BitmapFactory.decodeResource(getResources(),R.drawable.secretariat_miage),39));
        qrCodes.add(new QrCode("studer",BitmapFactory.decodeResource(getResources(),R.drawable.studer),40));
        qrCodes.add(new QrCode("tableau_sectoriel",BitmapFactory.decodeResource(getResources(),R.drawable.tableau_sectoriel),41));
        qrCodes.add(new QrCode("tableau_sectoriel_ts8",BitmapFactory.decodeResource(getResources(),R.drawable.tableau_sectoriel_ts8),42));
        qrCodes.add(new QrCode("thiry",BitmapFactory.decodeResource(getResources(),R.drawable.thiry),43));
        qrCodes.add(new QrCode("toilettes_femmes",BitmapFactory.decodeResource(getResources(),R.drawable.toilettes_femmes),44));
        qrCodes.add(new QrCode("toilettes_handicapes",BitmapFactory.decodeResource(getResources(),R.drawable.toilettes_handicapes),45));
        qrCodes.add(new QrCode("toilettes_hommes",BitmapFactory.decodeResource(getResources(),R.drawable.toilettes_hommes),46));
        qrCodes.add(new QrCode("toilette_ts7",BitmapFactory.decodeResource(getResources(),R.drawable.toilettes_ts7),47));
        qrCodes.add(new QrCode("vestiaire",BitmapFactory.decodeResource(getResources(),R.drawable.vestiaire),48));
        qrCodes.add(new QrCode("weber", BitmapFactory.decodeResource(getResources(),R.drawable.weber),49));
        qrCodes.add(new QrCode("weisser",BitmapFactory.decodeResource(getResources(),R.drawable.weisser),50));
        config.setAugmentedImageDatabase(qrCodes.getQrCodes());

    }


    @Override
    public void onUpdate(FrameTime frameTime) {


        Frame frame = arFragment.getArSceneView().getArFrame();
        for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
            if (plane.getTrackingState() == TrackingState.TRACKING) {
                arFragment.getPlaneDiscoveryController().hide();

            }
        }
        Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : images) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                switch (augmentedImage.getName()){
                    case "ambs":
                        Anchor anchor = augmentedImage.createAnchor(augmentedImage.getCenterPose());
                        createModel(anchor,getString(R.string.model1));
                }



            }
        }

    }


    private void createModel(Anchor anchor, String model) {
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
