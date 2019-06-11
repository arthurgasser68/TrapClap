package mainModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mainModel.modelMapping.Maps;
import mainModel.modelRessources.Object3D;
import mainModel.modelRooms.Rooms;

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

    public Map<QrCode, Rooms> getLinkQrRooms() {
        return rLink;
    }

    public Object3D getObject3D() {
        return obj;
    }

    public Maps getMaps() {
        return maps;
    }

    /** Instance unique non préinitialisée */
    private static Building INSTANCE = null;

    private TrapClap tc;
    private Map<QrCode, Rooms> rLink;
    private Object3D obj;
    private Maps maps;
    private List<Rooms> roomsList;

    private Building()
    {
        this.tc=new TrapClap();
        this.rLink=new HashMap<>();
        // fournir la map ici en salle a l'aide d'une fonction
        this.obj=new Object3D();
        this.maps=new Maps();
        // creer la maps ici à l'aide d'une fonction dans la classe Maps
        Rooms rooms = new Rooms();
        this.roomsList=rooms.buildRoom();

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
