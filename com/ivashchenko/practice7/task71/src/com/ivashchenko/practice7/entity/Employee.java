package com.ivashchenko.practice7.entity;

public class Employee extends AbstractEntity{
    private int employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private Department department;

    public Employee() {

    }

    public Employee(int employeeId, String firstName,
                    String lastName, String position,
                    Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Last name: %s," +
                            " Position: %s, Department: { %s }",
                            employeeId, firstName, lastName, position, department);
    }
}
