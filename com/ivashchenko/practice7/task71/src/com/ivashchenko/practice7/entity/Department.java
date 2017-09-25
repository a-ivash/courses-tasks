package com.ivashchenko.practice7.entity;

public class Department extends AbstractEntity {
    private int departmentId;
    private String departmentName;
    private String phone;

    public Department() {

    }

    public Department(int departmentId, String departmentName, String phone) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Department name: %s, Phone: %s",
                departmentId, departmentName, phone == null ? "N/A" : phone);
    }
}
