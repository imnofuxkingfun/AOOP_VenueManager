package Models;


public class StaffManager extends Staff {
    private Integer authorityLevel;

    public StaffManager(String name, String department, Double salary, Integer managerId, Integer authorityLevel) {
        super(name, department, salary, managerId);
        this.authorityLevel = authorityLevel;
    }

    public StaffManager(Integer id, String name, String department, Double salary, Integer managerId, Integer authorityLevel) {
        super(id, name, department, salary, managerId);
        this.authorityLevel = authorityLevel;
    }

    public Integer getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(Integer authorityLevel) {
        this.authorityLevel = authorityLevel;
    }
}
