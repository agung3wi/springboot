package com.agung.project.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public abstract class Dao< T extends Serializable > {
    private Class< T > clazz;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    @PersistenceContext
    EntityManager entityManager;
    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }
 
    public T findOne( long id ){
        return entityManager.find( clazz, id );
    }
    
    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
        .getResultList();
    }
 
    public void create( T entity ){
    	entityManager.persist(entity);
    }
 
    public T update( T entity ){
        return entityManager.merge( entity );
    }
 
    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( long entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }
}