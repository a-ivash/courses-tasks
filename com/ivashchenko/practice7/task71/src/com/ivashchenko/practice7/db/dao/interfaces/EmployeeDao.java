package com.ivashchenko.practice7.db.dao.interfaces;

import com.ivashchenko.practice7.entity.Department;
import com.ivashchenko.practice7.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao extends CoreDao<Integer, Employee> {
    public boolean delete(Employee employee) throws SQLException;
    public List<Employee> getEmployeesFromDepartment(Department department) throws SQLException;
}
