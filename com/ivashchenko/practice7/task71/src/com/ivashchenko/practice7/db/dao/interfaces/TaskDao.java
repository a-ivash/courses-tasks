package com.ivashchenko.practice7.db.dao.interfaces;

import com.ivashchenko.practice7.db.exceptions.RecordNotFoundException;
import com.ivashchenko.practice7.entity.Employee;
import com.ivashchenko.practice7.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao extends CoreDao<Integer, Task> {
    public Task createTask(String description, Employee employee) throws SQLException;
    public List<Task> getEmployeesTasks(Employee employee) throws SQLException, RecordNotFoundException;
}
