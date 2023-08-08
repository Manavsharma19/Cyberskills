package ie.cyberskills.application.controller;

import ie.cyberskills.application.entity.Module;
import ie.cyberskills.application.entity.TemporaryTable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuestController {

    @GetMapping("/guest")
    public String showGuestPage(Model model) {
        // Retrieve data for guest users from the temporary table
        List<Module> guestModules = TemporaryTable.getInstance().getDataForGuestUsers();

        // Add the data to the model to pass it to the view (Thymeleaf template)
        model.addAttribute("modules", guestModules);

        // Return the name of the Thymeleaf template to render the guest page
        return "guest_page";
    }
}

