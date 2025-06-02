package Models;

import DatabaseConnection.StaffDB;

import java.sql.ResultSet;

public class Staff extends StaffDB {
    private static Integer nextId;
    protected Integer id;
    protected String name;
    protected String department;
    protected Double salary;
    protected Integer managerId; //fk catre manager bc even managers have managers doar seful nu are YEAAAH

    static{
        try {
            ResultSet rs = rowCountStatement.executeQuery();
            rs.next();
            nextId = rs.getInt(1);
            nextId = nextId + 1;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Staff(String name, String department, Double salary, Integer managerId) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Staff(Integer id, String name, String department, Double salary, Integer managerId) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.managerId = managerId;
    }

    public static Integer getNextId() {
        return nextId;
    }

    public static void setNextId(Integer nextId) {
        Staff.nextId = nextId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}

