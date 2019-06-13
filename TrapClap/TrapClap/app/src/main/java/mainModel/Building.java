package mainModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mainModel.modelMapping.Maps;
import mainModel.modelRessources.Object3D;
import mainModel.modelRooms.Rooms;
import pack.clap.QrMainActivity;

public class Building {


    public static Building getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(Building INSTANCE) {
        Building.INSTANCE = INSTANCE;
    }


    public Object3D getObject3D() {
        return obj;
    }

    public Maps getMaps() {
        return maps;
    }

    /** Instance unique non préinitialisée */
    private static Building INSTANCE = null;

    private Object3D obj;
    private Maps maps;
    private List<Rooms> roomsList;
    private QrCodes qrCodes;

    private Building()
    {
        this.obj=new Object3D();
        this.maps=new Maps();
        // creer la maps ici à l'aide d'une fonction dans la classe Maps
        Rooms rooms = new Rooms();
        this.roomsList=rooms.buildRoom();
        this.qrCodes=null;
    }

    public Object3D getObj() {
        return obj;
    }

    public void setObj(Object3D obj) {
        this.obj = obj;
    }

    public void setMaps(Maps maps) {
        this.maps = maps;
    }

    public List<Rooms> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(List<Rooms> roomsList) {
        this.roomsList = roomsList;
    }

    public QrCodes getQrCodes() {
        return qrCodes;
    }

    public void setQrCodes(QrCodes qrCodes)
    {
        this.qrCodes=qrCodes;
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Building getInstance()
    {
        if (INSTANCE == null)
        {   INSTANCE = new Building();
        }
        return INSTANCE;
    }

}
