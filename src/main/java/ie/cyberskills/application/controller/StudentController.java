package ie.cyberskills.application.controller;


import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.entity.Student;

import ie.cyberskills.application.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private static final Logger logger = LogManager.getLogger(StudentController.class);

    private final HttpServletRequest request;
    private final StudentRepository studentRepository;


    @Autowired
    public StudentController(HttpServletRequest request, StudentRepository studentRepository) {
        this.request = request;
        this.studentRepository = studentRepository;
    }


    @GetMapping(value = "/")
    public String getHome() {
       logger.info("home page is working");
       System.out.println("until Home page is working");
        return "index";
    }


    @GetMapping(value = "/manager")
    public String getManager(Model model) {
        configCommonAttributes(model);
        model.addAttribute("students", studentRepository.findAll());

        System.out.println("until manager page is working");
  //      logger.info("model" + model);

        return "manager";
    }


    @GetMapping(value = "/students")
    public String getStudentManager(Model model, @Param("keyword") String keyword) {
        configCommonAttributes(model);
        try {
            List<Student> students = new ArrayList<Student>();

            if (keyword == null) {
                studentRepository.findAll().forEach(students::add);
            } else {
                studentRepository.findByStudentNameContainingIgnoreCase(keyword).forEach(students::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("students", students);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        System.out.println("until student manager page is working");
        return "students";
    }

    @GetMapping(value = "/students/new")
    public String addStudent(Model model) {
        Student student = new Student();
        student.setJoinCourses(true);

        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Create new Student");

        System.out.println("until new student page is working");

        return "student_form";
    }

    @PostMapping(value = "/students/save")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes) {
        try {
            studentRepository.save(student);

            redirectAttributes.addFlashAttribute("message", "The student has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        System.out.println("until save student page is working");


        return "redirect:/students";
    }

    @GetMapping(value = "/students/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Student student = studentRepository.findById(id).get();

            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student (ID: " + id + ")");

            return "student_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/students";
        }
    }

    @GetMapping(value = "/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            studentRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The student with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/students";
    }

    @GetMapping(value = "/students/{id}/joinCourses/{status}")
    public String updateStudentJoinCourseStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean joinCourses,
                                              Model model, RedirectAttributes redirectAttributes) {
        try {
            studentRepository.updateJoinCourseStatus(id, joinCourses);

            String status = joinCourses ? "published" : "disabled";
            String message = "The student id=" + id + " has been " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/students";
    }


    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        request.logout();

        System.out.println("until logout page is working");
        return "redirect:/";
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
    }

    /**
     * The KeycloakSecurityContext provides access to several pieces of information
     * contained in the security token, such as user profile information.
     */
    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}