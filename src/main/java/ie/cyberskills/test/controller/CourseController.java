package ie.cyberskills.test.controller;


import ie.cyberskills.test.entity.Course;
import ie.cyberskills.test.exception.ResourceNotFoundException;
import ie.cyberskills.test.repository.CourseRepository;
import ie.cyberskills.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<List<Course>> getAllCoursesByStudentId(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }

        List<Course> courses = courseRepository.findByStudentId(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCoursesByStudentId(@PathVariable(value = "id") Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Course> createCourse(@PathVariable(value = "studentId") Long studentId,
                                                 @RequestBody Course courseRequest) {
        Course course = studentRepository.findById(studentId).map(student -> {
            courseRequest.setStudent(student);
            return courseRepository.save(courseRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + studentId));

        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course courseRequest) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CourseId " + id + "not found"));

        course.setCourseName(courseRequest.getCourseName());
        course.setCourseDescription(courseRequest.getCourseDescription());

        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        courseRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/students/{studentId}/courses")
    public ResponseEntity<List<Course>> deleteAllCoursesOfStudent(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
        }

        courseRepository.deleteByStudentId(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
