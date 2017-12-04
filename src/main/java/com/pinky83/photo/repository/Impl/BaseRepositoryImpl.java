package com.pinky83.photo.repository.Impl;

import com.pinky83.photo.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public  class BaseRepositoryImpl<T> implements BaseRepository<T> {

    private final   Class<T> clazz;

    public BaseRepositoryImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

//    public List<T> findAll() {
//        return em.;
//    }

    @PersistenceContext(unitName = "entityManager")
    protected EntityManager  em;

    public T create(Object t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return (T)t;
    }

    public  T find(Object id) {
        return  (T)this.em.find (clazz, id);
    }

    public void delete(Object id) {
        Object ref = this.em.getReference(clazz, id);
        this.em.remove(ref);
    }

    public  T update(T t) {
        return this.em.merge(t);
    }

    public List<T> findWithNamedQuery(String namedQueryName){
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters){
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    public List<T> findByNativeQuery(String sql) {
        return this.em.createNativeQuery(sql, clazz).getResultList();
    }

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit){
        Set<Map.Entry> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry entry : rawParameters) {
            query.setParameter((String)entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}
