package com.pinky83.photo.repository;

import java.util.List;
import java.util.Map;

public interface BaseRepository<T> {
    T find (Object id);
//    List<T> findAll ();
    T create (T entity);
    void delete (T entity);

    public List<T> findWithNamedQuery(String namedQueryName);

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters);

    public List<T> findWithNamedQuery(String queryName, int resultLimit);

    public List<T> findByNativeQuery(String sql);

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
}
