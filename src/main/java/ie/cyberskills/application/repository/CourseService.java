package ie.cyberskills.application.repository;
import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


@Service
public class CourseService {

    private final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Define methods for CRUD operations
    public List<Course> getAllCourses() {
        logger.error("Fetching all courses from the database.");
        System.out.println("Fetching all courses from the database.");
        List<Course> courses = courseRepository.findAll();
        logger.error("Retrieved {} courses from the database.", courses.size());
        System.out.println("Retrieved {} courses from the database.");

        return courses;
    }

    // You can add methods for saving, updating, deleting courses, etc.
}

