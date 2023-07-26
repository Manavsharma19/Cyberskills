package com.cyberskills.public_college.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private Integer id;
    private String studentName;
    private String studentEmail;
    private Boolean joinCourses;
    

    public Student() {}

    public Student(Integer id, String studentName, String studentEmail, boolean joinCourses) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.joinCourses = joinCourses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Boolean getJoinCourses() {
        return joinCourses;
    }

    public void setJoinCourses(Boolean joinCourses) {
        this.joinCourses = joinCourses;
    }
}