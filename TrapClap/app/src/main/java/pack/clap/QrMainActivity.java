package pack.clap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

import mainModel.QrCode;
import mainModel.QrCodes;
import mainModel.TrapClap;
import pack.clap.R;
import pack.clap.qr_reading.CustomArFragment;


public class QrMainActivity extends AppCompatActivity implements Scene.OnUpdateListener {
    QrCodes qrCodes;
    public CustomArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_main);

        this.arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this);



    }

    public void setupDatabase(Config config, Session session) {


        QrCodes qrCodes=new QrCodes(session);
        for(int i=0;i<6;i++){
            getDrawable();

        }







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
