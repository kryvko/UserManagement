package ua.kh.kryvko.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    void create(T newInstance) throws SQLException;

    T read(PK id) throws SQLException;

    void update(T transientObject) throws SQLException;

    void delete(Long id) throws SQLException;

    List<T> findAll() throws SQLException;
}
