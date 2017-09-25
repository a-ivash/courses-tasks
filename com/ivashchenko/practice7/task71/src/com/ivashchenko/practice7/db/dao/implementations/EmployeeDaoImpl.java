package com.ivashchenko.practice7.db.dao.implementations;

import com.ivashchenko.practice7.db.dao.interfaces.EmployeeDao;
import com.ivashchenko.practice7.db.ConnectionWrapper;
import com.ivashchenko.practice7.entity.Department;
import com.ivashchenko.practice7.entity.Employee;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl extends CoreDaoImpl<Integer, Employee> implements EmployeeDao {
    private static final String JOIN_ON_DEPARTMENT = "JOIN departments ON departments.department_id = employees.department_id";
    private static final String COLUMN_TO_SELECT = "employee_id, last_name, first_name, position," +
            " departments.department_id, departments.department_name, departments.phone";
    private static final String SQL_SELECT_ALL_EMPLOYEES = String.format("SELECT %s FROM employees %s",
                                                                         COLUMN_TO_SELECT, JOIN_ON_DEPARTMENT);

    private static final String SQL_SELECT_DEPARTMENTS_EMPLOYEES = String.format("SELECT %s FROM employees %s WHERE employees.department_id = ?",
                                                                            COLUMN_TO_SELECT, JOIN_ON_DEPARTMENT);

    private static final String SQL_SELECT_EMPLOYEE = String.format("SELECT %s FROM employees %s WHERE employee_id = ?",
                                                                    COLUMN_TO_SELECT, JOIN_ON_DEPARTMENT);;

    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id = ?";

    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employees (last_name, first_name, position, department_id) VALUES (?, ?, ?, ?)";

    public EmployeeDaoImpl(ConnectionWrapper connWrapper) {
        super(connWrapper);
    }

    @Override
    protected Employee buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = null;
        int employeeId = resultSet.getInt("employee_id");
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        String position = resultSet.getString("position");

        int departmentId = resultSet.getInt("departments.department_id");
        String departmentName = resultSet.getString("departments.department_name");
        String departmentPhone = resultSet.getString("departments.phone");

        Department department = new Department(departmentId, departmentName, departmentPhone);
        employee = new Employee(employeeId, firstName, lastName, position, department);
        return employee;
    }

    @Override
    protected String getSelectAllQuery() {
        return SQL_SELECT_ALL_EMPLOYEES;
    }

    @Override
    protected String getFindByIdQuery() {
        return SQL_SELECT_EMPLOYEE;
    }

    @Override
    protected void fillFindByIdStatement(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    public boolean delete(Employee employee) throws SQLException{
        PreparedStatement statement = super.createPreparedStatement(SQL_DELETE_EMPLOYEE);
        statement.setInt(1, employee.getEmployeeId());
        boolean status = statement.execute();
        return status;
    }

    @Override
    public boolean update(Employee entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> getEmployeesFromDepartment(Department department) throws SQLException{
        int departmentId = department.getDepartmentId();
        PreparedStatement statement = super.createPreparedStatement(SQL_SELECT_DEPARTMENTS_EMPLOYEES);
        statement.setInt(1, departmentId);
        ResultSet rs = statement.executeQuery();
        return  buildListFromResultSet(rs);
    }
}
