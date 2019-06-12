package mainModel.modelRooms;

import mainModel.modelRessources.Object3D;

public abstract class Rooms {

    private Planning planning;
    private Informations info;
    private Object3D obj;

    public Rooms() {
        this.planning = new Planning();
        this.info = new Informations();
        this.obj=new Object3D();
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Informations getInfo() {
        return info;
    }

    public void setInfo(Informations info) {
        this.info = info;
    }

    public Object3D getObj() {
        return obj;
    }


}
