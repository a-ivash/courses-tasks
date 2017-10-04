package project.database.dao.interfaces;

import project.model.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<K, T extends AbstractEntity> {
    public List<T> findAll() throws SQLException;
    public T findById(K id) throws SQLException;
    public T save(T entity) throws SQLException;
    public List<T> save(List<T> entities) throws SQLException;
    public boolean update(T entity) throws SQLException;
    public List<T> update(List<T> entities) throws SQLException;
    public boolean delete(T entity) throws SQLException;
}
