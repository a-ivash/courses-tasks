package com.ivashchenko.practice7.db.dao.implementations;

import com.ivashchenko.practice7.db.dao.interfaces.TaskDao;
import com.ivashchenko.practice7.db.ConnectionWrapper;
import com.ivashchenko.practice7.db.exceptions.RecordNotFoundException;
import com.ivashchenko.practice7.entity.Department;
import com.ivashchenko.practice7.entity.Employee;
import com.ivashchenko.practice7.entity.Task;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDaoImpl extends CoreDaoImpl<Integer, Task> implements TaskDao {
    private static final String COLUMNS_TO_SELECT = "task_id, task_description, employees.employee_id, employees.first_name, " +
                                                    "employees.last_name, employees.position, departments.department_id," +
                                                    "departments.department_name, departments.phone";
    private static final String JOIN_EMPLOYEES = "JOIN employees ON tasks.employee_id = employees.employee_id";
    private static final String JOIN_DEPARTMENTS = "JOIN departments ON employees.department_id = departments.department_id";

    private static final String SQL_SELECT_ALL_TASKS = String.format("SELECT %s FROM tasks %s %s",
                                                                COLUMNS_TO_SELECT, JOIN_EMPLOYEES, JOIN_DEPARTMENTS);
    private static final String SQL_SELECT_EMPLOYEES_TASKS = String.format("SELECT %s FROM tasks %s %s WHERE tasks.employee_id = ?",
                                                                            COLUMNS_TO_SELECT, JOIN_EMPLOYEES, JOIN_DEPARTMENTS);
    private static final String SQL_SELECT_TASK = String.format("SELECT %s FROM tasks %s %s WHERE tasks.task_id = ?",
                                                                COLUMNS_TO_SELECT, JOIN_EMPLOYEES, JOIN_DEPARTMENTS);
    private static final String SQL_INSERT_TASK = "INSERT INTO tasks (task_description, employee_id) VALUES (?, ?)";

    public TaskDaoImpl(ConnectionWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected Task buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("departments.department_id"));
        department.setDepartmentName(resultSet.getString("departments.department_name"));
        department.setPhone(resultSet.getString("departments.phone"));

        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getInt("employees.employee_id"));
        employee.setFirstName(resultSet.getString("employees.first_name"));
        employee.setLastName(resultSet.getString("employees.last_name"));
        employee.setPosition(resultSet.getString("employees.position"));
        employee.setDepartment(department);

        Task task = new Task();
        task.setTaskId(resultSet.getInt("task_id"));
        task.setTaskDescription(resultSet.getString("task_description"));
        task.setEmployee(employee);
        return task;
    }

    @Override
    protected String getSelectAllQuery() {
        return SQL_SELECT_ALL_TASKS;
    }

    @Override
    protected String getFindByIdQuery() {
        return SQL_SELECT_TASK;
    }

    @Override
    protected void fillFindByIdStatement(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    public boolean delete(Task entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(Task entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Task createTask(String description, Employee employee) throws SQLException{
        PreparedStatement statement = super.createPreparedStatement(SQL_INSERT_TASK);
        statement.setString(1, description);
        statement.setInt(2, employee.getEmployeeId());

        /* If task was added, get it's primary key. */
        if(statement.execute()) {
            ResultSet rs = statement.getGeneratedKeys();
            int taskId = rs.getInt("task_id");
            return new Task(taskId, description, employee);
        } else {
            throw new SQLException("Task was not added. ");
        }
    }

    @Override
    public List<Task> getEmployeesTasks(Employee employee) throws SQLException, RecordNotFoundException {
        int employeeId = employee.getEmployeeId();
        PreparedStatement statement = super.createPreparedStatement(SQL_SELECT_EMPLOYEES_TASKS);
        statement.setInt(1, employeeId);
        ResultSet rs = statement.executeQuery();
        return buildListFromResultSet(rs);
    }
}
