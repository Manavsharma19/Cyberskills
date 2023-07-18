package ie.cyberskills.application.repository;


import ie.cyberskills.application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByJoinCourses(boolean joinCourses);
    List<Student> findByStudentNameContaining(String studentName);
}
