package ie.cyberskills.application.entity;

public class Module {
    private int moduleId;
    private String moduleName;
    private String description;

    public Module(int moduleId, String moduleName, String description) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.description = description;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
