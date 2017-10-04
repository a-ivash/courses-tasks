package project.database.dao.implementations.mysql;

import project.model.AbstractEntity;
import project.database.dao.interfaces.GenericDao;
import project.database.exceptions.RecordNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDaoImpl<K, T extends AbstractEntity> extends DaoConnection implements GenericDao<K, T> {
    GenericDaoImpl(Connection connection){
        super(connection);
    }

    protected abstract T buildEntityFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract String getSelectAllQuery();
    @Override
    public final List<T> findAll() throws SQLException {
        List<T> entityList = new ArrayList<>();
        String query = getSelectAllQuery();
        try(Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(query)){
                while(resultSet.next()) {
                    entityList.add(buildEntityFromResultSet(resultSet));
                }
            }
        }
        return entityList;
    }

    protected abstract String getSelectEntityByIdQuery();
    protected abstract void fillSelectByIdPreparedStatement(PreparedStatement statement, K id) throws SQLException;
    @Override
    public final T findById(K id) throws SQLException{
        String query = getSelectEntityByIdQuery();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            fillSelectByIdPreparedStatement(statement, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildEntityFromResultSet(resultSet);
                } else {
                    throw new RecordNotFoundException();
                }
            }
        }
    }

    protected abstract void fillInsertStatement(PreparedStatement statement, T entity) throws SQLException;
    protected abstract String getInsertQuery();
    @Override
    public T save(T entity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            fillInsertStatement(statement, entity);
            statement.execute();
            entity.setId(getGeneratedKey(statement));
            return entity;
        }
    }

    @Override
    public List<T> save(List<T> entities) throws SQLException {
        for (T entity: entities) {
            save(entity);
        }
        return entities;
    }

    protected abstract String getUpdateQuery();
    protected abstract void fillUpdateStatement(PreparedStatement statement, T entity) throws SQLException;
    public final boolean update(T entity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            fillUpdateStatement(statement, entity);
            return statement.execute();
        }
    }

    public List<T> update(List<T> entities) throws SQLException {
        for (T entity: entities) {
            update(entity);
        }
        return entities;
    }


    protected K getGeneratedKey(PreparedStatement statement) throws SQLException {
        try(ResultSet resultSet = statement.getGeneratedKeys()) {
            resultSet.next();
            return (K)resultSet.getObject(1);
        }
    }


    protected abstract String getDeleteQuery();
    protected abstract void fillDeleteStatement(PreparedStatement statement, T entity) throws SQLException;
    @Override
    public final boolean delete(T entity) throws SQLException {
        String deleteQuery = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            fillDeleteStatement(statement, entity);
            return statement.execute();
        }
    }


    protected final List<T> getItemsForQueryWildCardOnId(String query, AbstractEntity entity) throws SQLException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, (Long)entity.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(buildEntityFromResultSet(resultSet));
            }
        }
        return entities;
    }
}
