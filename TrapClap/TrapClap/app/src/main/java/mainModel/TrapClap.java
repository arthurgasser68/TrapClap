package mainModel;
import pack.clap.MainActivity;

import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.math.Vector3Evaluator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.animation.ModelAnimator;


    public class TrapClap extends AppCompatActivity {
        private MainActivity activity;
        private ModelRenderable clapRenderable;
        private ModelAnimator animator;
        private Node endNode;
        private Node startNode;
        private Node andy;
        private ObjectAnimator objectAnimation;
        private ModelRenderable model;
        private Anchor anchor;
        private ArFragment arFragment;

        public TrapClap(MainActivity activity, ArFragment arFragment){
            this.activity=activity;
            this.arFragment=arFragment;
        }

        public void playAnim(View unused) {
            AnimationData data = clapRenderable.getAnimationData(7);
            animator = new ModelAnimator(data, clapRenderable);
            animator.start();
        }

        public void Model(String s){
            ModelRenderable.builder()
                    .setSource(this.activity, Uri.parse(s))
                    .build()
                    .thenApply(renderable -> this.setRenderable(renderable))
                    .exceptionally(throwable -> {
                        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
                        builder.setMessage(throwable.getMessage())
                                .show();
                        return null;
                    });
        }

        public ModelRenderable setRenderable(ModelRenderable modelRenderable) {
            if (this.activity != null) {
                this.clapRenderable=modelRenderable;
            }
            return modelRenderable;
        }

        public void Tap(HitResult hitResult, Plane unusedPlane, MotionEvent motionEvent) {
            if (this.clapRenderable == null) {
                return;
            }
            anchor= hitResult.createAnchor();
            if (startNode == null) {
                startNode = new AnchorNode(anchor);
                startNode.setParent(arFragment.getArSceneView().getScene());

                andy = new Node();
                andy.setParent(startNode);
                andy.setRenderable(this.clapRenderable);
            }
            else {

                endNode = new AnchorNode(anchor);
                endNode.setParent(arFragment.getArSceneView().getScene());

                View view=arFragment.getArSceneView();
                this.playAnim(view);
                if(animator.isRunning()){
                    startWalking();
                }
                new Handler().postDelayed(runnable,2000);
            }
        }
        private void startWalking() {

            objectAnimation = new ObjectAnimator();
            objectAnimation.setAutoCancel(true);
            objectAnimation.setTarget(andy);

            objectAnimation.setObjectValues(andy.getWorldPosition(), endNode.getWorldPosition());

            objectAnimation.setPropertyName("worldPosition");

            objectAnimation.setEvaluator(new Vector3Evaluator());

            objectAnimation.setInterpolator(new LinearInterpolator());

            objectAnimation.setDuration(2000);
            objectAnimation.start();
        }
        public void rotate(float angle){
            andy.setRenderable(null);
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());

            andy.setLocalPosition(endNode.getLocalPosition());
            andy.setLocalRotation(new Quaternion(new Vector3(0, angle, 0), 180));
            //andy.setWorldPosition(endNode.getWorldPosition());
            andy.setParent(anchorNode);
            andy.setRenderable(clapRenderable);

            Toast.makeText(getApplicationContext(),andy.getLocalRotation().toString(), Toast.LENGTH_SHORT).show();
        }
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                rotate(180);
            }
        };
}
