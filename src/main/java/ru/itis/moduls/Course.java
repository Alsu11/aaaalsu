package ru.itis.moduls;

import java.util.List;

public class Course {
    private Integer id;
    private String nameCourse;
    private String start;
    private String ending;
    private Integer TeacherId;
    private List<Student> students;

    public Course(Integer id, String nameCourse, String start,
                  String ending, Integer TeacherId) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.start = start;
        this.ending = ending;
        this.TeacherId = TeacherId;
    }

    public Course(String nameCourse, String start,
                  String ending, Integer TeacherId) {
        this.nameCourse = nameCourse;
        this.start = start;
        this.ending = ending;
        this.TeacherId = TeacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Integer TeacherId) {
        this.TeacherId = TeacherId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", nameCourse='" + nameCourse + '\'' +
                ", start='" + start + '\'' +
                ", ending='" + ending + '\'' +
                ", TeacherId=" + TeacherId +
                ", students=" + students +
                '}';
    }
}
