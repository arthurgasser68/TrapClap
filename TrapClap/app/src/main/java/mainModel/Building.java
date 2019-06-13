package mainModel;

import com.google.ar.core.Anchor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mainModel.modelMapping.AnchorLibraries;
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

    public TrapClap getTrapClap() {
        return tc;
    }


    public Object3D getObject3D() {
        return obj;
    }

    /** Instance unique non préinitialisée */
    private static Building INSTANCE = null;

    private TrapClap tc;
    private Object3D obj;
    private List<Rooms> roomsList;
    private QrCodes qrCodes;

    private Building()
    {
        this.tc=new TrapClap();
        this.obj=new Object3D();
        // creer la maps ici à l'aide d'une fonction dans la classe Maps
        Rooms rooms = new Rooms();
        this.roomsList=rooms.buildRoom();
        this.qrCodes=null;
    }

    public TrapClap getTc() {
        return tc;
    }

    public void setTc(TrapClap tc) {
        this.tc = tc;
    }

    public Object3D getObj() {
        return obj;
    }

    public void setObj(Object3D obj) {
        this.obj = obj;
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
