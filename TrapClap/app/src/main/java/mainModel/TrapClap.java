package mainModel;

import mainModel.modelRessources.Animations;
import mainModel.modelRessources.Object3D;
import mainModel.modelRessources.Sounds;

public class TrapClap {

    private Object3D obj;
    private Sounds sound;
    private Animations anim;

    public TrapClap() {
        this.obj = new Object3D();
        this.sound = new Sounds();
        this.anim = new Animations();
    }

    public Object3D getObj() {
        return obj;
    }

    public void setObj(Object3D obj) {
        this.obj = obj;
    }

    public Sounds getSound() {
        return sound;
    }

    public void setSound(Sounds sound) {
        this.sound = sound;
    }

    public Animations getAnim() {
        return anim;
    }

    public void setAnim(Animations anim) {
        this.anim = anim;
    }


}
