package mainModel.modelRooms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Planning {

    private int currentHour;
    private int currentDay;
    private String currentDate;
    private List<Lessons> lessonsList;

    public Planning(List<Lessons> lessonsList)
    {
        this.lessonsList = lessonsList;
        DateFormat format = new SimpleDateFormat("H");
        this.currentHour= Integer.parseInt(format.format(new Date()));
        Calendar cal = Calendar.getInstance();
        this.currentDay = cal.get(Calendar.DAY_OF_WEEK);
        format=new SimpleDateFormat("dd/MM");
        this.currentDate=format.format(new Date());
    }

    public String getCurrentDate()
    {
        return this.currentDate;
    }

    public int getCurrentHour()
    {
        return this.currentHour;
    }

    public int getCurrentDay()
    {
        return this.currentDay;
    }

    public Lessons getLessons(int index)
    {
        return this.lessonsList.get(index);
    }

    public String convertDate()
    {
        String day=null;
        switch(this.currentDay)
        {
            case 1:
                day="Dimanche";
                break;
            case 2:
                day="Lundi";
                break;
            case 3:
                day="Mardi";
                break;
            case 4:
                day="Mercredi";
                break;
            case 5:
                day="Jeudi";
                break;
            case 6:
                day="Vendredi";
                break;
            case 7:
                day="Samedi";
                break;
        }
        return day;
    }

    public boolean isDispo(Planning plan, int hour)
    {
        boolean dispo=false;
        for(Iterator<Lessons> it = this.lessonsList.iterator(); it.hasNext();)
        {
            Lessons lesson=it.next();
<<<<<<< HEAD
            if((hour>=lesson.getHour1()&&hour<=lesson.getHour2())||(hour<8||hour>18))
            {
                if(lesson.getLesson()=="Libre"||lesson.getLesson()=="Pause"||lesson.getLesson()=="Fermé"||lesson.getLesson()=="Absent") dispo=false;
=======
            if(hour<8||hour>18) dispo=false;
            else
            {
                if((hour>=lesson.getHour1()&&hour<=lesson.getHour2()))
                {
                    if(lesson.getLesson()=="Libre"||lesson.getLesson()=="Pause"||lesson.getLesson()=="Ouvert"||lesson.getLesson()=="Présent") dispo=true;
                    else dispo=false;
                }
>>>>>>> 79091d0e0bc30066c84c6441de1e58d98f30a63b
            }
        }
        return dispo;
    }

    public String toString()
    {
        StringBuilder tmp = new StringBuilder();
        /*tmp.append("\nEmploi du temps\n\n");
        for(Iterator<Lessons> it = this.lessonsList.iterator(); it.hasNext();)
        {
            Lessons lessons =it.next();
            tmp.append(lessons.toString());
        }*/
        if(isDispo(this,this.currentHour)) tmp.append("\nActuellement disponible");
        else tmp.append("\nActuellement indisponible");
        return tmp.toString();
    }
}
