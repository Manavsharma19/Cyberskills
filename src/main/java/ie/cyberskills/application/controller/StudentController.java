//package ie.cyberskills.application.controller;
//
//
//import ie.cyberskills.application.entity.Student;
//import ie.cyberskills.application.repository.CourseRepository;
//import ie.cyberskills.application.repository.StudentRepository;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.keycloak.KeycloakSecurityContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import ie.cyberskills.application.exception.ResourceNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
////@CrossOrigin(origins = "http://localhost:8081")
////@RestController
////@RequestMapping("/api")
//@Controller
//public class StudentController {
//
//    private static final Logger logger = LogManager.getLogger(CourseController.class);
//
//
//    private final HttpServletRequest request;
//    private final StudentRepository studentRepository;
//    //private final CourseRepository courseRepository;
//    @Autowired
//    public StudentController(HttpServletRequest request, StudentRepository studentRepository) {
//        this.request = request;
//        this.studentRepository = studentRepository;
//        //this.courseRepository = courseRepository;
//    }
//
//    @GetMapping(value = "/manager")
//    public String getManager(Model model) {
//        configCommonAttributes(model);
//        model.addAttribute("students", studentRepository.readAll());
//
//        System.out.println("until manager page is working");
//        logger.info("model" + model);
//
//        return "manager";
//    }
//    @GetMapping("/students")
//    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String studentName) {
//        List<Student> students = new ArrayList<Student>();
//
//        if (studentName == null){
//            studentRepository.findAll().forEach(students::add);
//        }else{
//            studentRepository.findByStudentNameContaining(studentName).forEach(students::add);
//        }
//        if (students.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(students, HttpStatus.OK);
//    }
//
//    @GetMapping("/students/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
//        Student student = studentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Student with this id = " + id));
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//
//    @PostMapping("/students")
//    public ResponseEntity<Student>createStudent(@RequestBody Student student){
//
//        logger.info("first error");
//        Student newStudent = studentRepository.save(new Student(student.getStudentName(), student.getAddress(), student.getPhoneNumber(), true));
//
//        ResponseEntity newStu = null;
//
//        try {
//             newStu = new ResponseEntity<>(newStudent, HttpStatus.CREATED);
//        }
//        catch(Exception ex)
//        {
//            logger.error("Error Caught",ex);
//        }
//        return newStu;
//
//    }
//
//    @PutMapping("/students/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
//        Student editStudent = studentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(" Not Found this Student with Id = " + id));
//
//        editStudent.setStudentName(student.getStudentName());
//        editStudent.setAddress(student.getAddress());
//        editStudent.setPhoneNumber(student.getPhoneNumber());
//        editStudent.setJoinCourses(student.isJoinCourses());
//
//        return new ResponseEntity<>(studentRepository.save(editStudent), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/students")
//    public ResponseEntity<HttpStatus> deleteAllStudents() {
//        studentRepository.deleteAll();
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/students/published")
//    public ResponseEntity<List<Student>> findByJoinCourses() {
//        List<Student>students = studentRepository.findByJoinCourses(true);
//
//        if (students.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(students,HttpStatus.OK);
//    }
//
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
