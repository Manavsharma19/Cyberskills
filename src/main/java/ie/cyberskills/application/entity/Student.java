package ie.cyberskills.application.entity;
import org.springframework.stereotype.Component;


//package ie.cyberskills.application.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "student")
//public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
//    private Long id;
//    @Column(name = "studentName", nullable = false)
//    private String studentName;
//
//    @Column(name = "address")
//    private String address;
//    @Column(name = "phoneNumber")
//    private String phoneNumber;
//
//    @Column(name = "joinCourses")
//    private boolean joinCourses;
//
//    public Student( String studentName, String address, String phoneNumber, boolean joinCourses) {
//        this.studentName = studentName;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.joinCourses = joinCourses;
//    }
//
//    public Student() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public boolean isJoinCourses() {
//        return joinCourses;
//    }
//
//    public void setJoinCourses(boolean joinCourses) {
//        this.joinCourses = joinCourses;
//    }
//
//
//}



@Component
public class Student {
    private Integer id;
    private String studentName;
    private String address;
    private String phoneNumber;
    private String studentEmail;
    private Boolean joinCourses;


    public Student() {}

    public Student(Integer id, String studentName, String address, String phoneNumber, String studentEmail, boolean joinCourses) {
        this.id = id;
        this.studentName = studentName;
        this.address = address;
        this.phoneNumber = phoneNumber;
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