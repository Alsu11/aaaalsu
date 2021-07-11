package ru.itis.moduls;

import ru.itis.moduls.Course;

import java.util.ArrayList;

public class Student {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer groupNamber;
    private ArrayList<Course> courses;

    public Student(Integer id, String firsrName, String lastName,
                   Integer groupNamber, ArrayList<Course> courses) {
        this.studentId = id;
        this.firstName = firsrName;
        this.lastName = lastName;
        this.groupNamber = groupNamber;
        this.courses = courses;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGroupNamber() {
        return groupNamber;
    }

    public void setGroupNamber(Integer groupNamber) {
        this.groupNamber = groupNamber;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNamber=" + groupNamber +
                '}';
    }
}
