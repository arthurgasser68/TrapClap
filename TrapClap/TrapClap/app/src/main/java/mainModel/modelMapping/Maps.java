package mainModel.modelMapping;

import com.google.ar.core.Anchor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Maps {




    private Map<Anchor,String> anchorNames; // for path

    private Map<Anchor,String> anchorObject; // add object for Museum

    private Graph g;

    private Map<String,Integer> translate;


    public Maps() {
        this.anchorNames = new HashMap<Anchor,String>();
        this.anchorObject = new HashMap<Anchor,String>();
        this.g = new Graph();
        this.translate = new HashMap<String,Integer>();

        this.translate.put("Ambs",16);
        this.translate.put("Annexe_sanitaire",4);
        this.translate.put("Aubry",19);
        this.translate.put("Basset",18);
        this.translate.put("Ben_Souissi",34);
        this.translate.put("Binder",15);
        this.translate.put("Birouche-Mourllion",15);
        this.translate.put("Bureau chercheur_LSI",39);
        this.translate.put("Bureau chercheur_MIAM_1",19);
        this.translate.put("Bureau chercheur_MIAM_2",12);
        this.translate.put("Bureau chercheur_MIAM_3",12);
        this.translate.put("Cafe",8);
        this.translate.put("Dupuis",16);
        this.translate.put("E30",7);
        this.translate.put("E31",8);
        this.translate.put("E32",11);
        this.translate.put("E33",21);
        this.translate.put("E34",22);
        this.translate.put("E35",23);
        this.translate.put("E36",24);
        this.translate.put("E37",26);
        this.translate.put("E37-bis",28);
        this.translate.put("E38",29);
        this.translate.put("Fondement",36);
        this.translate.put("Forestier",38);
        this.translate.put("Hassenforder",34);
        this.translate.put("IARISS",27);
        this.translate.put("Labo_Isi",30);
        this.translate.put("Lauffenburger",14);
        this.translate.put("Ledy",18);
        this.translate.put("LSI",39);
        this.translate.put("Muller",36);
        this.translate.put("Orjuela",17);
        this.translate.put("Laurain",17);
        this.translate.put("PC_reseaux",31);
        this.translate.put("Peronne",33);
        this.translate.put("Pinot",37);
        this.translate.put("Prof_invite",38);
        this.translate.put("Salle_Réunion",1);
        this.translate.put("Secrétariat_Miage",23);
        this.translate.put("Studer",35);
        this.translate.put("Tableau_sectoriel",32);
        this.translate.put("Tableau_sectoriel_ts9",32);
        this.translate.put("Thiry",35);
        this.translate.put("Toilettes_femmes",10);
        this.translate.put("Toilettes_handicapées",5);
        this.translate.put("Toilettes_hommes",9);
        this.translate.put("Toilettes_ts7",3);
        this.translate.put("Vestiaire",2);
        this.translate.put("Weber",37);
        this.translate.put("Weisser",16);

    }

    public static void main (String[] args){
        Maps m=new Maps();
        System.out.println( m.getPathFromTo(11,"Weber")  );
    }

    public Map<String, Integer> getTranslate() {
        return translate;
    }

    public void setTranslate(Map<String, Integer> translate) {
        this.translate = translate;
    }

    public LinkedList<Graph.Vertex> getPathFromTo(int start, int target){
        this.g.execute(this.g.getNodes().get(start-1));
        return this.g.getPath(this.g.getNodes().get(target -1));

    }
    public LinkedList<Graph.Vertex> getPathFromTo(int start,String lieu){
        this.g.execute(this.g.getNodes().get(start-1));
        return this.g.addVertex( this.g.getPath(this.g.getNodes().get(this.translate.get(lieu) -1)),this.translate.get(lieu), lieu ) ;

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
