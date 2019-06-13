
package mainModel.modelMapping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.Config.CloudAnchorMode;
import com.google.ar.core.HitResult;
import com.google.ar.core.Session;
import com.google.ar.core.codelab.cloudanchor.trapclaphelper.CloudAnchorManager;
import com.google.ar.core.codelab.cloudanchor.trapclaphelper.FirebaseManager;
import com.google.ar.core.codelab.cloudanchor.trapclaphelper.ResolveDialogFragment;
import com.google.ar.core.codelab.cloudanchor.trapclaphelper.SnackbarHelper;
import com.google.ar.core.codelab.cloudanchor.trapclaphelper.StorageManager;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.HashMap;

import java.util.Map;

import mainModel.Building;
import pack.clap.Map2DActivity;
import pack.clap.R;

import static java.lang.Integer.parseInt;

/**
 * Main Fragment for the Cloud Anchors Codelab.
 *
 * <p>This is where the AR Session and the Cloud Anchors are managed.
 */
public class AnchorLibraries extends ArFragment implements Scene.OnUpdateListener {

    private Scene arScene;
    private AnchorNode anchorNode;
    private ModelRenderable andyRenderable;
    private final CloudAnchorManager cloudAnchorManger = new CloudAnchorManager();
    private final SnackbarHelper snackbarHelper = new SnackbarHelper();
    private final StorageManager storageManager =new StorageManager();
    private FirebaseManager firebaseManager;
    private Button resolve_button;
    private Button get_position;
    private int  lastupdate=0;
    private Button create_path;
    private HashMap <Anchor,String> anchorMap = new HashMap<>();
    private Anchor currentAnchor=null;
    private Maps map =new Maps();
    private String destination;
    private int currentShortCode;

    public void setDestination(String destination) {
        this.destination = destination;
    }


    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    public void onAttach(Context context) {
        super.onAttach(context);
        ModelRenderable.builder()
                .setSource(context, R.raw.andy)
                .build()
                .thenAccept(renderable -> andyRenderable = renderable);
        firebaseManager =new FirebaseManager(context);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate from the Layout XML file.
        View rootView = inflater.inflate(R.layout.cloud_anchor_fragment, container, false);
        LinearLayout arContainer = rootView.findViewById(R.id.ar_container);


        // Call the ArFragment's implementation to get the AR View.
        View arView = super.onCreateView(inflater, arContainer, savedInstanceState);
        arContainer.addView(arView);
        Button clearButton = rootView.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> onClearButtonPressed());

        resolve_button = rootView.findViewById(R.id.resolve_button);
        resolve_button.setOnClickListener(v -> onResolveButtonPressed());

        get_position = rootView.findViewById(R.id.get_position);
        get_position.setOnClickListener(v -> onGetPositionButtonPressed());


        create_path=rootView.findViewById(R.id.create_lib);
        create_path.setOnClickListener(v -> onShowpathpressed());


        arScene = getArSceneView().getScene();
        arScene.addOnUpdateListener(frameTime -> cloudAnchorManger.onUpdate());
        setOnTapArPlaneListener((hitResult, plane, motionEvent) -> onArPlaneTap(hitResult));
        this.getArSceneView().getScene().addOnUpdateListener(this);



        return rootView;
    }

    private synchronized  void onCreatelibrary()
    {
        int i;

        for (i=1;i<=6;i++)

        {
            onSearch(i);
        }
    }

    private synchronized void onShowpathpressed()
    {
        if(currentAnchor!=null)
        {
            Building.getINSTANCE().setIdDebut(currentShortCode);
            snackbarHelper.showMessage(getActivity(),map.getPathFromTo(currentShortCode,destination).toString());
        }else
        {
            snackbarHelper.showMessage(getActivity(),"No position foudn unable to create a path");
        }


    }




    private synchronized void onSearch(int shortCode)
    {
        firebaseManager.getCloudAnchorId(shortCode, cloudAnchorId ->
        { resolve_button.setEnabled(false);
            cloudAnchorManger.resolveCloudAnchor(getArSceneView().getSession()
                    , cloudAnchorId, anchor -> onResolvedAnchorFound(anchor, shortCode));
        });

    }

    private void onResolvedAnchorFound(Anchor anchor, int shortCode) {

        anchorMap.put(anchor,Integer.toString(shortCode));
        resolve_button.setEnabled(true);

    }


    private void onGetPositionButtonPressed() {

        if (anchorMap.isEmpty())
        {
            onCreatelibrary();
        }

         for (Map.Entry<Anchor,String> entry : anchorMap.entrySet()) {

            if(currentAnchor == null)
            {
                onShortCodeEntered(parseInt(entry.getValue()));
            }
        }
            snackbarHelper.showMessage(getActivity(), "your Position was not found ");




    }

    private synchronized void onArPlaneTap(HitResult hitResult) {
        if (anchorNode != null) {
            // Do nothing if there was already an anchor in the Scene.
            return;
        }
        Anchor anchor = hitResult.createAnchor();
        setNewAnchor(anchor);
        resolve_button.setEnabled(false);
        snackbarHelper.showMessage(getActivity(),"Now Hosting ...");
        cloudAnchorManger.hostCloudAnchor(getArSceneView().getSession(),anchor,this::onHostedAcnhorAvailable);
    }

    private synchronized void onClearButtonPressed() {
        // Clear the anchor from the scene.
        cloudAnchorManger.clearListeners();
        resolve_button.setEnabled(true);

        currentAnchor=null;

        setNewAnchor(null);
    }

    private synchronized  void onResolveButtonPressed()
    {
        ResolveDialogFragment dialog = ResolveDialogFragment.createWithOkListener(this::onShortCodeEntered);
        dialog.show(getFragmentManager(),"Resolve");
        //this::onShortCodeEntered = tihs an okey listener used as a lambda expression
    }

    private synchronized void onHostedAcnhorAvailable(Anchor anchor)
    {

        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
        if(cloudState == Anchor.CloudAnchorState.SUCCESS) {
            //int shortCode = storageManager.nextShortCode(getActivity());
            String cloudAnchorId = anchor.getCloudAnchorId();
            firebaseManager.nextShortCode(shortCode -> {
                if (shortCode != null) {
                    firebaseManager.storeUsingShortCode(shortCode, cloudAnchorId);
                    AnchorNode an =new AnchorNode(anchor);
                    snackbarHelper.showMessage(getActivity(), "Cloud Anchor Hosted. ID: " + shortCode);
                    setNewAnchor(anchor);
                } else {
                    //firebase could not provide a short code
                    snackbarHelper.showMessage(getActivity(), "Cloud Anchor Hosted, but could get a short code from Firebase");
                }

            });
            setNewAnchor(anchor);
            // storageManager.storeUsingShortCode(getActivity(),shortCode,anchor.getCloudAnchorId());
        }else
        {
            snackbarHelper.showMessage(getActivity(), "Error while hosting: "+cloudState.toString());
        }
    }

    private synchronized  void onShortCodeEntered(int shortCode) {
        // String cloudAnchorId = storageManager.getCloudAnchorId(getActivity(),shortCode);
        firebaseManager.getCloudAnchorId(shortCode, cloudAnchorId -> {

            cloudAnchorManger.resolveCloudAnchor(getArSceneView().getSession(),
                    cloudAnchorId, anchor -> onResolvedAnchorAvailable(anchor, shortCode));


        });

    }

    private synchronized void onResolvedAnchorAvailable(Anchor anchor, int shortCode) {
        Anchor.CloudAnchorState cloudState = anchor.getCloudAnchorState();
        if(cloudState == Anchor.CloudAnchorState.SUCCESS) {
            snackbarHelper.showMessage(getActivity(), "You are in " + shortCode);
            setNewAnchor(anchor);
            currentAnchor=anchor;

            currentShortCode=shortCode;
            Building.getINSTANCE().setIdDebut(currentShortCode);

            return;

        }
        return;
    }

    // Modify the renderables when a new anchor is available.
    private synchronized void setNewAnchor(@Nullable Anchor anchor) {
        if (anchorNode != null) {
            // If an AnchorNode existed before, remove and nullify it.
            arScene.removeChild(anchorNode);
            anchorNode = null;
        }
        if (anchor != null) {
            if (andyRenderable == null) {
                // Display an error message if the renderable model was not available.
                Toast toast = Toast.makeText(getContext(), "Andy model was not loaded.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            // Create the Anchor.
            anchorNode = new AnchorNode(anchor);
            arScene.addChild(anchorNode);

            // Create the transformable andy and add it to the anchor.
            TransformableNode andy = new TransformableNode(getTransformationSystem());
            andy.setParent(anchorNode);
            andy.setRenderable(andyRenderable);
            andy.select();
        }
    }
    @Override
    protected Config getSessionConfiguration(Session session) {
        Config config = super.getSessionConfiguration(session);
        config.setCloudAnchorMode(CloudAnchorMode.ENABLED);
        return config;
    }

    @Override
    public void onUpdate(FrameTime frameTime)
    {

    if(lastupdate>500)
    {
        onGetPositionButtonPressed();
        Building.getINSTANCE().setIdDebut(currentShortCode);



        lastupdate=0;
    }else
    {
        lastupdate++;
    }


    }







}
