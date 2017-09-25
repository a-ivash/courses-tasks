package com.ivashchenko.practice7.entity;

public class Task extends AbstractEntity{
    private int taskId;
    private String taskDescription;
    private Employee employee;

    public Task() {

    }

    public Task(int taskId, String taskDescription, Employee employee) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.employee = employee;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return String.format("ID: %d. Description: \"%s\" Assigned to: {%s %s}",
                                    taskId, taskDescription, employee.getFirstName(), employee.getLastName());
    }
}
