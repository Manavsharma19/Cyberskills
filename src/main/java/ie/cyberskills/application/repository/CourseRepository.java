package ie.cyberskills.application.repository;

import ie.cyberskills.application.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStudentId(Long courseId);

    void deleteByStudentId(Long studentId);
}
