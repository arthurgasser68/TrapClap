package pack.clap;

import android.app.Application;

public class GlobalActivity extends Application {

    private String room;

    public GlobalActivity()
    {
        this.room=null;
    }

    public String getRoom()
    {
        return this.room;
    }

    public void setRoom(String room)
    {
        this.room=room;
    }

}
