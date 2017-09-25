package com.ivashchenko.practice7.db.dao.implementations;

import com.ivashchenko.practice7.db.dao.interfaces.CoreDao;
import com.ivashchenko.practice7.db.ConnectionWrapper;
import com.ivashchenko.practice7.db.exceptions.RecordNotFoundException;
import com.ivashchenko.practice7.entity.AbstractEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class CoreDaoImpl<K, T extends AbstractEntity> implements CoreDao<K, T> {
    private ConnectionWrapper wrapper;

    protected abstract T buildEntityFromResultSet(ResultSet resultSet) throws SQLException;
    protected abstract String getSelectAllQuery();
    protected abstract String getFindByIdQuery();
    protected abstract void fillFindByIdStatement(PreparedStatement statement, K key) throws SQLException;

    public CoreDaoImpl(ConnectionWrapper wrapper) {
        this.wrapper = wrapper;
    }

    protected PreparedStatement createPreparedStatement(String sqlQuery) throws SQLException {
        return wrapper.createPreparedStatement(sqlQuery);
    }

    @Override
    public List<T> findAll() throws SQLException {
        String sqlSelectAll = getSelectAllQuery();
        try (Statement statement = wrapper.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            return buildListFromResultSet(resultSet);
        }
    }

    protected List<T> buildListFromResultSet(ResultSet resultSet) throws SQLException{
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(buildEntityFromResultSet(resultSet));
        }
        return entities;
    }

    @Override
    public T findById(K key) throws RecordNotFoundException, SQLException {
        String sqlFindById = getFindByIdQuery();
        try (
                PreparedStatement statement =
                        wrapper.createPreparedStatement(sqlFindById);
        ){
            fillFindByIdStatement(statement, key);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildEntityFromResultSet(rs);
            } else {
                throw new RecordNotFoundException();
            }
        } catch (SQLException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public boolean delete(T entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(T entity) throws SQLException {
        return false;
    }
}
