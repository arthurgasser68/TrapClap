package mainModel.modelMapping;

import com.google.ar.core.Anchor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {




    private Map<Anchor,String> anchorNames; // for path

    private Map<Anchor,String> anchorObject; // add object for Museum

    private Graph g;


    public Maps() {
        this.anchorNames = new HashMap<Anchor,String>();
        this.anchorObject = new HashMap<Anchor,String>();
        //this.g = new Graph(250);
        // creer ici la map finale
    }

    public Map<Anchor, String> getAnchorNames() {
        return anchorNames;
    }

    public void setAnchorNames(Map<Anchor, String> anchorNames) {
        this.anchorNames = anchorNames;
    }

    public Map<Anchor, String> getAnchorObject() {
        return anchorObject;
    }

    public void setAnchorObject(Map<Anchor, String> anchorObject) {
        this.anchorObject = anchorObject;
    }

    public void addAnchorNames(Anchor a,String s){
        this.anchorNames.put(a,s);
    }

    public void addAnchorObject(Anchor a,String s){
        this.anchorNames.put(a,s);
    }

}
