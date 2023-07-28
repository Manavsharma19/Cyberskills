//package ie.cyberskills.application.controller;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.keycloak.KeycloakSecurityContext;
//import org.slf4j.LoggerFactory;
//import ie.cyberskills.application.entity.Course;
//import ie.cyberskills.application.exception.ResourceNotFoundException;
//import ie.cyberskills.application.repository.CourseRepository;
//import ie.cyberskills.application.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
////@CrossOrigin(origins = "http://localhost:8081")
////@RestController
////@RequestMapping("/api")
//@Controller
//public class CourseController {
//
////    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
//    private static final Logger logger = LogManager.getLogger(CourseController.class);
//
//
//    private final HttpServletRequest request;
//    private final StudentRepository studentRepository;
//    private final CourseRepository courseRepository;
//    @Autowired
//    public CourseController(HttpServletRequest request, StudentRepository studentRepository, CourseRepository courseRepository) {
//        this.request = request;
//        this.studentRepository = studentRepository;
//        this.courseRepository = courseRepository;
//    }
//    @GetMapping("/students/{studentId}/courses")
//    public ResponseEntity<List<Course>> getAllCoursesByStudentId(@PathVariable(value = "studentId") Long studentId) {
//
//        if (!studentRepository.existsById(studentId)) {
//            logger.warn("Not found Student with id = {}", studentId);
//
//            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
//        }
//
//        List<Course> courses = courseRepository.findByStudentId(studentId);
//        return new ResponseEntity<>(courses, HttpStatus.OK);
//    }
//
//    @GetMapping("/courses/{id}")
//    public ResponseEntity<Course> getCoursesByStudentId(@PathVariable(value = "id") Long id) {
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));
//
//        return new ResponseEntity<>(course, HttpStatus.OK);
//    }
//
//    @PostMapping("/students/{studentId}/courses")
//    public ResponseEntity<Course> createCourse(Model model,@PathVariable(value = "studentId") Long studentId,
//                                               @RequestBody Course courseRequest) {
//        configCommonAttributes(model);
//        Course course = studentRepository.findById(studentId).map(student -> {
//            courseRequest.setStudent(student);
//            return courseRepository.save(courseRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + studentId));
//
//        return new ResponseEntity<>(course, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/courses/{id}")
//    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course courseRequest) {
//        logger.info("New course created for student with id = {}", id);
//
//
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("CourseId " + id + "not found"));
//
//        course.setCourseName(courseRequest.getCourseName());
//        course.setCourseDescription(courseRequest.getCourseDescription());
//
//        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/courses/{id}")
//    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
//        logger.info("Course deleted with id = {}", id);
//
//        courseRepository.deleteById(id);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/students/{studentId}/courses")
//    public ResponseEntity<List<Course>> deleteAllCoursesOfStudent(@PathVariable(value = "studentId") Long studentId) {
//
//        if (!studentRepository.existsById(studentId)) {
//            logger.warn("Not found Student with id = {}", studentId);
//            throw new ResourceNotFoundException("Not found Student with id = " + studentId);
//        }
//
//
//        courseRepository.deleteByStudentId(studentId);
//
//        logger.info("All courses deleted for student with id = {}", studentId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//
//    @GetMapping(value = "/logout")
//    public String logout() throws ServletException {
//        request.logout();
//
//        System.out.println("until logout page is working");
//        return "redirect:/";
//    }
//
//    private void configCommonAttributes(Model model) {
//        model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
//    }
//
//    /**
//     * The KeycloakSecurityContext provides access to several pieces of information
//     * contained in the security token, such as user profile information.
//     */
//    private KeycloakSecurityContext getKeycloakSecurityContext() {
//        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
//    }
//}
