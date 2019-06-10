package mainModel.modelRooms;

public class Informations {

    private String info;

    public Informations(String info)
    {
        this.info=info;
    }

    public String getInfo()
    {
        return this.info;
    }

    public void setInfo(String info)
    {
        this.info=info;
    }

    public String toString()
    {
        StringBuilder tmp = new StringBuilder();
        tmp.append("\nInformations\n\n"+this.info);
        return tmp.toString();
    }

}
