package mainModel.modelRooms;

import java.util.ArrayList;
import java.util.List;

import mainModel.modelRessources.Object3D;

public class Rooms {

    private String name;
    private Planning planning;
    private Informations info;
    private Object3D obj;
    private String type;

    public Rooms()
    {
        this(null,null,null,null,null);
    }

    public Rooms(String name, Planning plan, Informations info, Object3D obj, String type)
    {
        this.name=name;
        this.planning=plan;
        this.info=info;
        this.obj=obj;
        this.type=type;
    }

    public String getName() {
        return this.name;
    }

    public Planning getPlanning()
    {
        return this.planning;
    }

    public void setPlanning(Planning planning)
    {
        this.planning = planning;
    }

    public Informations getInfo()
    {
        return this.info;
    }

    public void setInfo(Informations info)
    {
        this.info = info;
    }

    public Object3D getObj()
    {
        return this.obj;
    }

    public List<Rooms> buildRoom() //51 ID
    {
        List<Rooms> listRoom=new ArrayList<Rooms>();
        listRoom.add(new Rooms("Salle Prof : Ambs",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau de Mr Ambs"),null,"Prof"));
        listRoom.add(new Rooms("Sanitaire : Annexe_sanitaire",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Sanitaire"),null,"Sanitary"));
        listRoom.add(new Rooms("Salle Prof : Aubry",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau de Mme Aubry"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Basset",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau de Mr Basset"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Ben_Souissi",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau de Mme Ben Souissi"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Binder",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Binder"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Birouche-Mourllion",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Birouche & Mr Mourllion"),null,"Prof"));
        listRoom.add(new Rooms("Bureau : Bureau chercheur_LSI",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Fermé","Ouvert")),new Informations("LSI : "),null,"Research"));
        listRoom.add(new Rooms("Bureau : Bureau chercheur_MIAM_1",new Planning(new Lessons().buildLesson("Fermé","Fermé","Ouvert","Ouvert")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),null,"Research"));
        listRoom.add(new Rooms("Bureau : Bureau chercheur_MIAM_2",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),null,"Research"));
        listRoom.add(new Rooms("Bureau : Bureau chercheur_MIAM_3",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Ouvert","Fermé")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),null,"Research"));
        listRoom.add(new Rooms("Equipement : Cafe",new Planning(new Lessons().buildLesson("Utilisable","Utilisable","Utilisable","Utilisable")),new Informations("Prix : "),null,"Equipment"));
        listRoom.add(new Rooms("Salle Prof : Dupuis",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Dupuis"),null,"Prof"));

        listRoom.add(new Rooms("Salle : E30",new Planning(new Lessons().buildLesson("CMAGS","Anglais","Libre","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E31",new Planning(new Lessons().buildLesson("Web","Compatabilité","Java","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E32",new Planning(new Lessons().buildLesson("Physique","Réseaux","Web","CMAGS")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E33",new Planning(new Lessons().buildLesson("Anglais","Projet","ICG","Java")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E34",new Planning(new Lessons().buildLesson("ICG","Libre","Réseaux","Anglais")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E35",new Planning(new Lessons().buildLesson("Java","Libre","Libre","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E36",new Planning(new Lessons().buildLesson("BI","Anglais","Libre","CMAGS")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E37",new Planning(new Lessons().buildLesson("Libre","SGBD","Libre","BI")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E37-bis",new Planning(new Lessons().buildLesson("SGBD","BI","ICG","Physique")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));
        listRoom.add(new Rooms("Salle : E38",new Planning(new Lessons().buildLesson("Economie","CMAGS","Libre","SGBD")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),null,"Lessons"));

        listRoom.add(new Rooms("Salle Prof : Fondement",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Fondement"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Forestier",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Forestier"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Hassenforder",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Hassenforder"),null,"Prof"));
        listRoom.add(new Rooms("Bureau : IARISS",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Fermé")),new Informations("Il s’agit d’une Junior, elle a la forme d’une association à vocation économique et pédagogique, et permet aux élèves-ingénieurs de l’ENSISA de mettre en pratique l’enseignement dont ils bénéficient en réalisant des études correspondant à leurs domaines de compétences."),null,"Lessons"));
        listRoom.add(new Rooms("Labo : Labo_Isi",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Ouvert","Ouvert")),new Informations("LSI : "),null,"Research"));
        listRoom.add(new Rooms("Salle Prof : Lauffenburger",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Lauffenburger"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Laurain",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Laurain"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Ledy",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau  de Mr Ledy"),null,"Prof"));
        listRoom.add(new Rooms("Labo : LSI",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Fermé","Fermé")),new Informations("LSI : "),null,"Research"));
        listRoom.add(new Rooms("Salle Prof : Muller",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Muller"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Orjuela",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Orjuela"),null,"Prof"));
        listRoom.add(new Rooms("Salle : PC_reseaux",new Planning(new Lessons().buildLesson("CMAGS","Anglais","Libre","Allemand")),new Informations("Salle de TP notamment utilisé par les élèves ingénieurs en informatique et réseaux"),null,"Lessons"));
        listRoom.add(new Rooms("Salle Prof : Peronne",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Peronne"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Pinot",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Pinot"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Prof_invite",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Salle disponible pour les professeurs vacataires"),null,"Prof"));
        listRoom.add(new Rooms("Salle : Salle_Réunion",new Planning(new Lessons().buildLesson("Libre","Libre","Fermé","Fermé")),new Informations("Salle de réunion pouvant accueiilir un grand nombre d'intervenants"),null,"Sanitary"));
        listRoom.add(new Rooms("Administration : Secrétariat_Miage",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Fermé","Ouvert")),new Informations("Miage : Méthodes Informatiques Appliquées à la Gestion des Entreprises"),null,"Admin"));
        listRoom.add(new Rooms("Salle Prof : Studer",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau  de Mr Studer"),null,"Prof"));
        listRoom.add(new Rooms("Equipement : Tableau_sectoriel",new Planning(new Lessons().buildLesson("A jour","A jour","A jour","A jour")),new Informations("Tableau : "),null,"Equipment"));
        listRoom.add(new Rooms("Equipement : Tableau_sectoriel_ts8",new Planning(new Lessons().buildLesson("A jour","A jour","A jour","A jour")),new Informations("Tableau : "),null,"Equipment"));
        listRoom.add(new Rooms("Salle Prof : Thiry",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Thiry"),null,"Prof"));
        listRoom.add(new Rooms("Sanitaire : Toilettes_femmes",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),null,"Sanitary"));
        listRoom.add(new Rooms("Sanitaire : Toilettes_handicapées",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),null,"Sanitary"));
        listRoom.add(new Rooms("Sanitaire : Toilettes_hommes",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),null,"Sanitary"));
        listRoom.add(new Rooms("Sanitaire : Toilettes_ts7",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),null,"Sanitary"));
        listRoom.add(new Rooms("Sanitaire : Vestiaire",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Vestaire : "),null,"Sanitary"));
        listRoom.add(new Rooms("Salle Prof : Weber",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Weber"),null,"Prof"));
        listRoom.add(new Rooms("Salle Prof : Weisser",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Weisser"),null,"Prof"));
        return listRoom;
    }

    public String toString()
    {
        StringBuilder tmp = new StringBuilder();
        tmp.append(this.name+"\n"+this.planning+"\n"+this.info);
        return tmp.toString();
    }

}
