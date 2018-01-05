package ua.kh.kryvko.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T, PK extends Serializable> implements GenericDao<T, PK>{

    public PK create(T newInstance) {
        return null;
    }

    public T read(PK id) {
        return null;
    }

    public void update(T transientObject) {

    }

    public void delete(T persistentObject) {

    }

    public List<T> findAll() {
        return null;
    }

//    private Connection getConnetction() {
//
//
//    }
}
