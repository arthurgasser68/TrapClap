package mainModel.modelRooms;

import java.util.ArrayList;
import java.util.List;



public class Rooms {

    private String name;
    private Planning planning;
    private Informations info;

    private String type;

    public Rooms()
    {
        this(null,null,null,null);
    }

    public Rooms(String name, Planning plan, Informations info, String type)
    {
        this.name=name;
        this.planning=plan;
        this.info=info;
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

    public List<Rooms> buildRoom() //51 ID
    {
        List<Rooms> listRoom=new ArrayList<Rooms>();
        listRoom.add(new Rooms("Ambs",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau de Mr Ambs"),"Prof"));
        listRoom.add(new Rooms("Annexe_sanitaire",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Sanitaire"),"Sanitary"));
        listRoom.add(new Rooms("Aubry",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau de Mme Aubry"),"Prof"));
        listRoom.add(new Rooms("Basset",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau de Mr Basset"),"Prof"));
        listRoom.add(new Rooms("Ben_Souissi",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau de Mme Ben Souissi"),"Prof"));
        listRoom.add(new Rooms("Binder",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Binder"),"Prof"));
        listRoom.add(new Rooms("Birouche-Mourllion",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Birouche & Mr Mourllion"),"Prof"));
        listRoom.add(new Rooms("Bureau chercheur_LSI",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Fermé","Ouvert")),new Informations("LSI : "),"Research"));
        listRoom.add(new Rooms("Bureau chercheur_MIAM_1",new Planning(new Lessons().buildLesson("Fermé","Fermé","Ouvert","Ouvert")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),"Research"));
        listRoom.add(new Rooms("Bureau chercheur_MIAM_2",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),"Research"));
        listRoom.add(new Rooms("Bureau chercheur_MIAM_3",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Ouvert","Fermé")),new Informations("MIAM : Modélisation et Identification en Automatique et Mécanique"),"Research"));
        listRoom.add(new Rooms("Cafe",new Planning(new Lessons().buildLesson("Utilisable","Utilisable","Utilisable","Utilisable")),new Informations("Prix : "),"Equipment"));
        listRoom.add(new Rooms("Dupuis",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Dupuis"),"Prof"));

        listRoom.add(new Rooms("E30",new Planning(new Lessons().buildLesson("CMAGS","Anglais","Libre","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E31",new Planning(new Lessons().buildLesson("Web","Compatabilité","Java","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E32",new Planning(new Lessons().buildLesson("Physique","Réseaux","Web","CMAGS")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E33",new Planning(new Lessons().buildLesson("Anglais","Projet","ICG","Java")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants. Cette application a été majoritairement créé dans cette salle"),"Lessons"));
        listRoom.add(new Rooms("E34",new Planning(new Lessons().buildLesson("ICG","Libre","Réseaux","Libre")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E35",new Planning(new Lessons().buildLesson("Java","Libre","Libre","Allemand")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E36",new Planning(new Lessons().buildLesson("BI","Anglais","Libre","CMAGS")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E37",new Planning(new Lessons().buildLesson("Libre","SGBD","Libre","BI")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E37-bis",new Planning(new Lessons().buildLesson("SGBD","BI","ICG","Physique")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));
        listRoom.add(new Rooms("E38",new Planning(new Lessons().buildLesson("Economie","CMAGS","Libre","SGBD")),new Informations("Salle de TD pouvant accueillir environ 20 étudiants"),"Lessons"));

        listRoom.add(new Rooms("Fondement",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Fondement"),"Prof"));
        listRoom.add(new Rooms("Forestier",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Forestier"),"Prof"));
        listRoom.add(new Rooms("Hassenforder",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Hassenforder"),"Prof"));
        listRoom.add(new Rooms("IARISS",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Fermé")),new Informations("Il s’agit d’une Junior, elle a la forme d’une association à vocation économique et pédagogique, et permet aux élèves-ingénieurs de l’ENSISA de mettre en pratique l’enseignement dont ils bénéficient en réalisant des études correspondant à leurs domaines de compétences."),"Lessons"));
        listRoom.add(new Rooms("Labo_Isi",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Ouvert","Ouvert")),new Informations("LSI : "),"Research"));
        listRoom.add(new Rooms("Lauffenburger",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Lauffenburger"),"Prof"));
        listRoom.add(new Rooms("Laurain",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Bureau  de Mr Laurain"),"Prof"));
        listRoom.add(new Rooms("Ledy",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau  de Mr Ledy"),"Prof"));
        listRoom.add(new Rooms("LSI",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Fermé","Fermé")),new Informations("LSI : "),"Research"));
        listRoom.add(new Rooms("Muller",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Muller"),"Prof"));
        listRoom.add(new Rooms("Orjuela",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Orjuela"),"Prof"));
        listRoom.add(new Rooms("PC_reseaux",new Planning(new Lessons().buildLesson("CMAGS","Anglais","Libre","Allemand")),new Informations("Salle de TP notamment utilisé par les élèves ingénieurs en informatique et réseaux"),"Lessons"));
        listRoom.add(new Rooms("Peronne",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Peronne"),"Prof"));
        listRoom.add(new Rooms("Pinot",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Pinot"),"Prof"));
        listRoom.add(new Rooms("Prof_invite",new Planning(new Lessons().buildLesson("Absent","Présent","Absent","Absent")),new Informations("Salle disponible pour les professeurs vacataires"),"Prof"));
        listRoom.add(new Rooms("Salle_Réunion",new Planning(new Lessons().buildLesson("Libre","Libre","Fermé","Fermé")),new Informations("Salle de réunion pouvant accueiilir un grand nombre d'intervenants"),"Sanitary"));
        listRoom.add(new Rooms("Secrétariat_Miage",new Planning(new Lessons().buildLesson("Ouvert","Fermé","Fermé","Ouvert")),new Informations("Miage : Méthodes Informatiques Appliquées à la Gestion des Entreprises"),"Admin"));
        listRoom.add(new Rooms("Studer",new Planning(new Lessons().buildLesson("Absent","Absent","Présent","Absent")),new Informations("Bureau  de Mr Studer"),"Prof"));
        listRoom.add(new Rooms("Tableau_sectoriel",new Planning(new Lessons().buildLesson("A jour","A jour","A jour","A jour")),new Informations("Tableau : "),"Equipment"));
        listRoom.add(new Rooms("Tableau_sectoriel_ts8",new Planning(new Lessons().buildLesson("A jour","A jour","A jour","A jour")),new Informations("Tableau : "),"Equipment"));
        listRoom.add(new Rooms("Thiry",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Thiry"),"Prof"));
        listRoom.add(new Rooms("Toilettes_femmes",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),"Sanitary"));
        listRoom.add(new Rooms("Toilettes_handicapées",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),"Sanitary"));
        listRoom.add(new Rooms("Toilettes_hommes",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),"Sanitary"));
        listRoom.add(new Rooms("Toilettes_ts7",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Toilettes (Attention elles peuvent être fermées pendant la pause de midi)"),"Sanitary"));
        listRoom.add(new Rooms("Vestiaire",new Planning(new Lessons().buildLesson("Ouvert","Ouvert","Ouvert","Ouvert")),new Informations("Vestaire : "),"Sanitary"));
        listRoom.add(new Rooms("Weber",new Planning(new Lessons().buildLesson("Présent","Présent","Présent","Présent")),new Informations("Bureau  de Mr Weber"),"Prof"));
        listRoom.add(new Rooms("Weisser",new Planning(new Lessons().buildLesson("Absent","Présent","Présent","Absent")),new Informations("Bureau  de Mr Weisser"),"Prof"));
        return listRoom;
    }

    public String toString()
    {
        StringBuilder tmp = new StringBuilder();
        tmp.append(this.name+" : "+this.planning+"\n"+this.info);
        return tmp.toString();
    }

}
