package Services;

import DatabaseConnection.MenuDB;
import Models.*;
import com.sun.source.tree.Tree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StaffService extends MenuDB{

    private StaffService() {}

    private static final class SINGLETON_HOLDER {
        private static final StaffService instance = new StaffService();
    }

    static AuditService auditService = new AuditService();

    public static StaffService getInstance() {
        auditService.logAction("Singleton StaffService Instance Gotten");
        return SINGLETON_HOLDER.instance;
    }

    public List<Staff> getVenueStaffData() throws SQLException {
        //lista cu staff si manageri
        List<Staff> staffList = new ArrayList<>();
        ResultSet rs = getStaffStatement.executeQuery();
        while (rs.next()) {
            Integer managerCheck = rs.getInt("sm.id");
            if (!rs.wasNull()){ //e manager
                String name, department;
                Double salary;
                Integer authorityLevel, managerId;
                name = rs.getString("name");
                department = rs.getString("department");
                salary = rs.getDouble("salary");
                authorityLevel = rs.getInt("authorityLevel");
                managerId = rs.getInt("managerId");
                StaffManager staffManager = new StaffManager(managerCheck,name,department,salary,managerId,authorityLevel);
                staffList.add(staffManager);
            }
            else{ //nu e
                String name, department;
                Double salary;
                Integer managerId, id;
                id = rs.getInt("s.id");
                name = rs.getString("name");
                department = rs.getString("department");
                salary = rs.getDouble("salary");
                managerId = rs.getInt("managerId");
                Staff staff = new Staff(id,name,department,salary,managerId);
                staffList.add(staff);
            }
        }
        auditService.logAction("Staff List Loaded");
        return staffList;
    }

    public void displayStaffDetails(List<Staff> staffList) throws SQLException {
        Map<String, List<Staff>> staffByDepartment = new TreeMap<>();

        for (Staff staff : staffList) {
            staffByDepartment.putIfAbsent(staff.getDepartment(), new ArrayList<>());
            staffByDepartment.get(staff.getDepartment()).add(staff);
        }

        for (List<Staff> list : staffByDepartment.values()) {
            list.sort(Comparator.comparing(staff -> !(staff instanceof StaffManager)));
        }
        System.out.println();
        for(Map.Entry<String, List<Staff>> entry : staffByDepartment.entrySet()) {
            String department = entry.getKey();
            System.out.println("--"+department+"--");
            for(Staff staff : entry.getValue()) {
                MenuDB.findManagerNameStatement.setInt(1, staff.getManagerId());
                ResultSet rs = findManagerNameStatement.executeQuery();
                rs.next();
                String managerName = rs.getString("name");
                if(staff instanceof StaffManager) {
                    if(managerName.equals("is boss"))
                        System.out.println("[ID:" + staff.getId()+"] "+staff.getName()+" - "+staff.getSalary()+" - Boss");
                    else
                        System.out.println("[ID:" + staff.getId()+"] "+staff.getName()+" - "+staff.getSalary()+" - Manager: "+managerName + " - is Manager");
                }else{
                    System.out.println("[ID:" + staff.getId()+"] "+staff.getName()+" - "+staff.getSalary()+" - Manager: "+managerName);
                }
            }
        }
        auditService.logAction("Staff List Displayed");

    }

    public void addStaff() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert employee name:");
        String name = sc.nextLine();
        System.out.println("Insert employee department:");
        String department = sc.nextLine();
        double salary = 0;
        boolean salaryOk = false;
        while (!salaryOk){
            System.out.println("Insert employee salary:");
            String input = sc.nextLine();
            try{
                salary = Double.parseDouble(input);
                if (salary >= 0){
                    salaryOk = true;
                }
                else {
                    System.out.println("Please enter a valid positive number");
                }
            }catch (NumberFormatException e){
                System.out.println("Insert employee salary failed. Please enter a valid positive number");
            }
        }


        boolean managerExists = false;
        int managerId = 0;
        while (!managerExists) {

            System.out.println("Insert employee manager's id:");
            String input = sc.nextLine();
            if (!input.isBlank()){
                try {
                    managerId = Integer.parseInt(input);
                    MenuDB.checkManagerIdStatement.setInt(1, managerId);
                    ResultSet rs = checkManagerIdStatement.executeQuery();
                    rs.next();
                    if(rs.getInt(1) != 0)
                        managerExists = true;
                    else
                        System.out.println("Invalid Manager ID. Try again.");
                }catch(Exception e){
                    System.out.println("Insert employee manager's id failed. Please enter a valid number");
                }

            }

        }
        Staff staff = new Staff(name,department,salary,managerId);
        MenuDB.insertStaffStatement.setInt(1, staff.getId());
        MenuDB.insertStaffStatement.setString(2, name);
        MenuDB.insertStaffStatement.setString(3, department);
        MenuDB.insertStaffStatement.setDouble(4, salary);
        MenuDB.insertStaffStatement.setInt(5, managerId);
        MenuDB.insertStaffStatement.executeUpdate();
        auditService.logAction("Staff Added");
    }

    public void editStaff(int id) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert employee name: (leave empty for no change)");
        String name = sc.nextLine();
        if(!name.isBlank()){
            MenuDB.updateStaffNameStatement.setString(1,name);
            MenuDB.updateStaffNameStatement.setInt(2,id);
            MenuDB.updateStaffNameStatement.executeUpdate();
            auditService.logAction("Staff Name Edited");
        }
        System.out.println("Insert employee department: (leave empty for no change)");
        String department = sc.nextLine();
        if(!department.isBlank()){
            MenuDB.updateStaffDepartmentStatement.setString(1,department);
            MenuDB.updateStaffDepartmentStatement.setInt(2,id);
            MenuDB.updateStaffDepartmentStatement.executeUpdate();
            auditService.logAction("Staff Department Edited");
        }
        double salary = 0;
        boolean salaryOk = false;
        while (!salaryOk){
            System.out.println("Insert employee salary:");
            String input = sc.nextLine();
            if (input.isBlank()){
                salaryOk = true;
            }
            else{
                try{
                    salary = Double.parseDouble(input);
                    if (salary >= 0){
                        salaryOk = true;
                    }
                    else {
                        System.out.println("Please enter a valid positive number");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Insert employee salary failed. Please enter a valid positive number");
                }
            }
        }

        boolean managerExists = false;
        Integer managerId = 0;
        while (!managerExists) {
            System.out.println("Insert employee manager's id:");
            String input = sc.nextLine();
            if (!input.isBlank()){
                try {
                    managerId = Integer.parseInt(input);
                    MenuDB.checkManagerIdStatement.setInt(1, managerId);
                    ResultSet rs = checkManagerIdStatement.executeQuery();
                    rs.next();
                    if(rs.getInt(1) != 0)
                        managerExists = true;
                    else
                        System.out.println("Invalid Manager ID. Try again.");
                }catch(Exception e){
                    System.out.println("Insert employee manager's id failed. Please enter a valid number");
                }

            }
            else {
                managerExists = true;
            }

        }

        if(managerId!=null){
            MenuDB.updateStaffManagerIdStatement.setInt(1,managerId);
            MenuDB.updateStaffManagerIdStatement.setInt(2,id);
            MenuDB.updateStaffManagerIdStatement.executeUpdate();
            auditService.logAction("Staff Manager Id Edited");
        }


    }

    public void editManager(int id) throws SQLException {
        editStaff(id);
        Scanner sc = new Scanner(System.in);
        Integer authorityLevel = 0;
        boolean inputOk = false;
        while (!inputOk){
            System.out.println("Insert manager's authority level: (leave empty for no change)");
            String input = sc.nextLine();
            if (!input.isBlank()){
                try{
                    authorityLevel = Integer.parseInt(input);
                    if (authorityLevel <= 0){
                        System.out.println("Invalid authority level. Try again.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Insert manager authority level failed. Please enter a valid positive number");
                }
            }else{
                inputOk = true;
            }
        }
        if(authorityLevel != null){
            MenuDB.updateManagerAuthorityLevelStatement.setInt(1,authorityLevel);
            MenuDB.updateManagerAuthorityLevelStatement.setInt(2,id);
            MenuDB.updateManagerAuthorityLevelStatement.executeUpdate();
            auditService.logAction("Manager Authority Level Edited");
        }
    }

    public void promoteStaff(int id) throws SQLException {
        MenuDB.addManagerStatement.setInt(1,id);
        Scanner sc = new Scanner(System.in);
        Integer authorityLevel = 3;
        boolean inputOk = false;
        while (!inputOk){
            System.out.println("Insert manager's authority level: (leave empty for no change)");
            String input = sc.nextLine();
            if (!input.isBlank()){
                try{
                    authorityLevel = Integer.parseInt(input);
                    if (authorityLevel <= 0){
                        System.out.println("Invalid authority level. Try again.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Insert manager authority level failed. Please enter a valid positive number");
                }
            }else{
                inputOk = true;
            }
        }
        MenuDB.addManagerStatement.setInt(2,authorityLevel);
        MenuDB.addManagerStatement.executeUpdate();
        auditService.logAction("Employee with id "+id+" Promoted");
    }

    public void demoteManager(int id) throws SQLException {
        MenuDB.deleteManagerStatement.setInt(1,id);
        MenuDB.deleteManagerStatement.executeUpdate();
        auditService.logAction("Manager with id "+id+" Demoted");
    }

    public void deleteStaff(int id) throws SQLException {
        System.out.println("Are you sure you want to delete the employee with id: " + id  + " ? Type \"yes\" to continue...");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            MenuDB.deleteStaffStatement.setInt(1, id);
            deleteStaffStatement.execute();
            auditService.logAction("Deleted staff with id: " + id);
        }
    }
}
