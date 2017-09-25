package com.ivashchenko.practice7.db.dao.interfaces;

import com.ivashchenko.practice7.db.exceptions.RecordNotFoundException;
import com.ivashchenko.practice7.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface CoreDao<K, T extends AbstractEntity> {
    List<T> findAll() throws SQLException;
    T findById(K key) throws RecordNotFoundException, SQLException;
    boolean delete(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
}
