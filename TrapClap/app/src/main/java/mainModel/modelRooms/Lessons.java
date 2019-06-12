package mainModel.modelRooms;

import java.util.ArrayList;
import java.util.List;

public class Lessons {

        private String lesson;
        private int hour1;
        private int hour2;

        public Lessons()
        {
            this(null,0,0);
        }

        public Lessons(String lesson, int hour1, int hour2)
        {
            this.lesson=lesson;
            this.hour1=hour1;
            this.hour2=hour2;
        }

        public String getLesson() {
            return this.lesson;
        }

        public int getHour1()
        {
            return this.hour1;
        }

        public int getHour2()
        {
            return this.hour2;
        }

        public List<Lessons> buildLesson(String lesson1, String lesson2, String lesson3, String lesson4)
        {
            List<Lessons> listLessons =new ArrayList<Lessons>();
            listLessons.add(new Lessons(lesson1,8,10));
            listLessons.add(new Lessons(lesson2,10,12));
            listLessons.add(new Lessons("Pause",12,14));
            listLessons.add(new Lessons(lesson3,14,16));
            listLessons.add(new Lessons(lesson4,16,18));
            return listLessons;
        }

        public String toString()
        {
            StringBuilder tmp = new StringBuilder();
            tmp.append(this.hour1+"-"+this.hour2+"h : "+this.lesson+"\n");
            return tmp.toString();
        }
}

