package ie.cyberskills.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    private Long id;
    @Column(name = "studentName", nullable = false)
    private String studentName;

    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "joinCourses")
    private boolean joinCourses;

    public Student( String studentName, String address, String phoneNumber, boolean joinCourses) {
        this.studentName = studentName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.joinCourses = joinCourses;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isJoinCourses() {
        return joinCourses;
    }

    public void setJoinCourses(boolean joinCourses) {
        this.joinCourses = joinCourses;
    }


}
