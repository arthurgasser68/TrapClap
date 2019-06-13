package mainModel;

import java.util.List;

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




    /** Instance unique non préinitialisée */
    private static Building INSTANCE = null;

    private TrapClap tc;

    private List<Rooms> roomsList;
    private QrCodes qrCodes;
    private int idDebut;
    private int idFin;

    public int getIdDebut() {
        return idDebut;
    }

    public void setIdDebut(int idDebut) {
        this.idDebut = idDebut;
    }

    public int getIdFin() {
        return idFin;
    }

    public void setIdFin(int idFin) {
        this.idFin = idFin;
    }

    private Building()
    {
        this.idDebut=0;
        //this.tc=new TrapClap();

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
