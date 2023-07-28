package ie.cyberskills.application.repository;
import ie.cyberskills.application.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//package ie.cyberskills.application.repository;
//
//
//import ie.cyberskills.application.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//@Transactional
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    List<Student> findByJoinCourses(boolean joinCourses);
//    List<Student> findByStudentNameContaining(String studentName);
//}




@Repository
public class StudentRepository {
    private static Map<Integer, Student> students = new ConcurrentHashMap<>();

    static {
        students.put(1, new Student(1,"Shamim","Cork","0850000001", "shamim@gmail.com", true));
        students.put(2, new Student(2,"Hasan","Cork","0850000002", "hasan@gmail.com", true));

        students.put(3, new Student(3,"Max","Cork","0850000003", "shamim.hasan@gmail.com", true));
        students.put(4, new Student(4,"Dev","Cork","0850000004", "shamim.hasan@gmail.com", true));

    }


    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<>(students.values());
        allStudents.sort(Comparator.comparing(Student::getId));
        return allStudents;
    }

    public void create(Student student) {
        students.put(student.getId(), student);
    }

    public Student read(Integer id) {
        return students.get(id);
    }

    public void update(Student student) {
        students.put(student.getId(), student);
    }

    public void delete(Integer id) {
        students.remove(id);
    }
}
