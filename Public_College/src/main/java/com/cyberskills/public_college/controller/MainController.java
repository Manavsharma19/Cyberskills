package com.cyberskills.public_college.controller;

import com.cyberskills.public_college.model.Course;
import com.cyberskills.public_college.repository.CourseRepository;
import com.cyberskills.public_college.repository.StudentRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static final Logger log = LogManager.getLogger(MainController.class);

    private final HttpServletRequest request;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public MainController(HttpServletRequest request, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.request = request;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping(value = "/")
    public String getHome() {
        System.out.println("until Home page is working");
        return "index";
    }

    @GetMapping(value = "/students")
    public String getStudents(Model model) {
        configCommonAttributes(model);
        model.addAttribute("students", studentRepository.readAll());

        System.out.println("until student page is working");
        return "students";
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model) {
    configCommonAttributes(model);
    model.addAttribute("students", studentRepository.readAll());

    System.out.println("until manager page is working");
    log.info("model" + model);

        return "manager";
    }


    @GetMapping(value = "/courses")
    public String getCourseManager(Model model, @Param("keyword") String keyword) {
        configCommonAttributes(model);
        try {
            List<Course> courses = new ArrayList<Course>();

            if (keyword == null) {
                courseRepository.findAll().forEach(courses::add);
            } else {
                courseRepository.findByTitleContainingIgnoreCase(keyword).forEach(courses::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("courses", courses);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        System.out.println("until course manager page is working");
        return "courses";
    }

    @GetMapping(value = "/courses/new")
    public String addCourse(Model model) {
        Course course = new Course();
        course.setPublished(true);

        model.addAttribute("course", course);
        model.addAttribute("pageTitle", "Create new Course");

        System.out.println("until new course page is working");


        return "course_form";
    }

    @PostMapping(value = "/courses/save")
    public String saveCourse(Course course, RedirectAttributes redirectAttributes) {
        try {
            courseRepository.save(course);

            redirectAttributes.addFlashAttribute("message", "The Course has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        System.out.println("until save course page is working");


        return "redirect:/courses";
    }

    @GetMapping(value = "/courses/{id}")
    public String editCourse(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Course course = courseRepository.findById(id).get();

            model.addAttribute("course", course);
            model.addAttribute("pageTitle", "Edit Course (ID: " + id + ")");

            return "course_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/courses";
        }
    }

    @GetMapping(value = "/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            courseRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Course with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/courses";
    }

    @GetMapping(value = "/courses/{id}/published/{status}")
    public String updateCoursePublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
                                              Model model, RedirectAttributes redirectAttributes) {
        try {
            courseRepository.updatePublishedStatus(id, published);

            String status = published ? "published" : "disabled";
            String message = "The Course id=" + id + " has been " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/courses";
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