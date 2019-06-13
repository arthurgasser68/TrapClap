package pack.clap;

import android.app.Application;

import java.util.Iterator;

import mainModel.Building;
import mainModel.modelRooms.Rooms;

public class GlobalActivity extends Application {

    private String room;
    private int roomid;
    private String [] rooms;

    public GlobalActivity()
    {
        this.room=null;
        this.roomid=-1;
        this.rooms=null;
    }

    public String getRoom()
    {
        return this.room;
    }

    public void setRoom(String room)
    {
        this.room=room;
    }

    public int getRoomId()
    {
        return this.roomid;
    }

    public void setRoomId(int id)
    {
        this.roomid=id;
    }

    public String [] createRooms()
    {
        this.rooms=new String[54];
        this.rooms[0]="Destination...";
        this.rooms[1]="Aucune";
        this.rooms[2]="Visite guidée";
        int i=3;
        for(Iterator<mainModel.modelRooms.Rooms> it = Building.getInstance().getRoomsList().iterator(); it.hasNext();)
        {
            Rooms r = it.next();
            this.rooms[i]=r.getName();
            i++;
        }
        return this.rooms;
    }

}
