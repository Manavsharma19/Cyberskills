package ie.cyberskills.application.entity;
import javax.persistence.*;

//package ie.cyberskills.application.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "courses")
//public class Course {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
//    private Integer id;
//
//    @Lob
//    private String courseName;
//
//    @Lob
//    private String courseDescription;
//
//    @Lob
//    private int courseLevel;
//
//    @Lob
//    private boolean published;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "student_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Student student;
//
//    public Course(Integer id, String courseName, String courseDescription, int courseLevel Student student) {
//        this.id = id;
//        this.courseName = courseName;
//        this.courseDescription = courseDescription;
//        this.student = student;
//    }
//
//    public Course() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getCourseDescription() {
//        return courseDescription;
//    }
//
//    public void setCourseDescription(String courseDescription) {
//        this.courseDescription = courseDescription;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//}


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String courseName;

    @Column(length = 256)
    private String courseDescription;

    @Column(nullable = false)
    private int courseLevel;

    @Column
    private boolean published;

    public Course() {

    }

    public Course(String courseName, String courseDescription, int courseLevel, boolean published) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
        this.published = published;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(int courseLevel) {
        this.courseLevel = courseLevel;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + courseName + ", description=" + courseDescription + ", level=" + courseLevel
                + ", published=" + published + "]";
    }

}
