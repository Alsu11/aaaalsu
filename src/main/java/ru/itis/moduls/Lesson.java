package ru.itis.moduls;

import ru.itis.moduls.Course;

public class Lesson {
    private Integer lessonId;
    private String lessonName;
    private String day;
    private String time;
    private Course course;

    public Lesson(Integer id, String lessonName,String day, String time, Course course) {
        this.lessonId = id;
        this.lessonName = lessonName;
        this.day = day;
        this.time = time;
        this.course = course;
    }

    public Lesson(String lessonName,String day, String time, Course course) {
        this.lessonName = lessonName;
        this.day = day;
        this.time = time;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", course=" + course.getNameCourse() +
                '}';
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
