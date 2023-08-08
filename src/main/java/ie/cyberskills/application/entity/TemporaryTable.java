package ie.cyberskills.application.entity;

import java.util.ArrayList;
import java.util.List;

public class TemporaryTable {
    // Private static field to hold the single instance of the class
    private static TemporaryTable instance;

    // Private constructor to prevent direct instantiation from other classes
    private TemporaryTable() {
        // Initialize the modules list
        modules = new ArrayList<>();
        // Populate the list with some initial data (example)
        modules.add(new Module(1, "Module 1", "Description 1"));
        modules.add(new Module(2, "Module 2", "Description 2"));
        modules.add(new Module(3, "Module 3", "Description 3"));
        // ... add more data if needed
    }

    // Public static method to get the instance of the class
    public static TemporaryTable getInstance() {
        if (instance == null) {
            // If the instance is null, create a new one
            instance = new TemporaryTable();
        }
        return instance;
    }

    // Private list to store the modules data
    private List<Module> modules;

    // Public method to retrieve data for guest users
    public List<Module> getDataForGuestUsers() {
        // Return a copy of the modules list to prevent modifications
        return new ArrayList<>(modules);
    }

//    // Public method to add a new module to the temporary table
//    public void addModule(Module module) {
//        modules.add(module);
//    }

    // Other methods to manipulate the data in the temporary table (if needed)
}
