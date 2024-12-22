package Entities;

public class Employee {
    private int EmployeeId;
    private String Name;
    private int Age;
    private String Department;
    private Double Salary;

    // Getters and Setters

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;

    }

    public String getName(String Name) {
        return Name;

    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;

    }

    public void setAge(int Age) {
        this.Age = Age;

    }

    public String getDepartment() {
        return Department;

    }

    public void setDepartment(String Department) {
        this.Department = Department;

    }

    public Double getSalary() {
        return Salary;

    }

    public void setSalary(Double Salary) {
        this.Salary = Salary;
    }

    Employee employee = new Employee();

}
