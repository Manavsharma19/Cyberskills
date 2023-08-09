package ie.cyberskills.application.controller;
import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.repository.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import ie.cyberskills.application.repository.CourseService;
import java.util.List;

@Controller
public class GuestController {


    private final CourseService courseService;

    public GuestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/guest")
    public String showGuestPage(Model model) {
        // Retrieve data from the database using the service
        List<Course> courses = courseService.getAllCourses();

        // Add the data to the model to pass it to the view
        model.addAttribute("courses", courses);

        // Return the name of the Thymeleaf template to render the guest page
        return "guest_page";
    }
}