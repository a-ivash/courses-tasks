package com.ivashchenko.practice7.db.dao.implementations;

import com.ivashchenko.practice7.db.dao.interfaces.DepartmentDao;
import com.ivashchenko.practice7.db.ConnectionWrapper;
import com.ivashchenko.practice7.entity.Department;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDaoImpl extends CoreDaoImpl<Integer, Department> implements DepartmentDao {
    private static final String SQL_SELECT_ALL_DEPARTMENTS = "SELECT department_id, department_name," +
            " phone FROM departments";
    private static final String SQL_SELECT_DEPARTMENT = "SELECT department_id, department_name," +
            " phone FROM departments WHERE department_id = ?";
    private static final String SQL_INSERT_DEPARTMENT = "INSERT INTO departments (department_name, phone) VALUES (?, ?)";

    public DepartmentDaoImpl(ConnectionWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected Department buildEntityFromResultSet(ResultSet resultSet) throws SQLException{
        int departmentId = resultSet.getInt("department_id");
        String departmentName = resultSet.getString("department_name");
        String phone = resultSet.getString("phone");
        Department department = new Department(departmentId, departmentName, phone);
        return department;
    }

    @Override
    protected String getSelectAllQuery() {
        return SQL_SELECT_ALL_DEPARTMENTS;
    }

    @Override
    protected String getFindByIdQuery() {
        return SQL_SELECT_DEPARTMENT;
    }

    @Override
    protected void fillFindByIdStatement(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    public boolean delete(Department entity) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(Department entity) throws SQLException {
        throw new NotImplementedException();
    }
}
