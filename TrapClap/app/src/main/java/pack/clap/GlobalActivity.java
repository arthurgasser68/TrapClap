package pack.clap;

import android.app.Application;

public class GlobalActivity extends Application {

    private String room;
    private int roomid;

    public GlobalActivity()
    {
        this.room=null;
        this.roomid=-1;
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

}
