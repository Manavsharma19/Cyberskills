package ie.cyberskills.application.repository;

import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByStudentNameContainingIgnoreCase(String keyword);



    @Query("UPDATE Student t SET t.joinCourses = :joinCourses WHERE t.id = :id")
    @Modifying
    public void updateJoinCourseStatus(Integer id, boolean joinCourses);
}
