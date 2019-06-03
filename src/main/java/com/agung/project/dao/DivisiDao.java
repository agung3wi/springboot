package com.agung.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agung.project.model.Divisi;

@Transactional
@Repository
public class DivisiDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Divisi findOne( long id ){
        return entityManager.find( Divisi.class, id );
    }
	    
	    public List< Divisi > findAll(){
	    	Query query = entityManager.createNativeQuery(
	    	        "SELECT * FROM m_divisi", Divisi.class);
	    	    return query.getResultList();
	    }
	    
	    public long count(){
	    	Query query = entityManager.createNativeQuery(
	    	        "SELECT COUNT(1) AS id FROM m_divisi", Divisi.class);
	    	Divisi row = (Divisi) query.getSingleResult();
	    	return row.id;
	    }
	 
	    public void create( Divisi entity ){
	    	entityManager.persist(entity);
	    }
	 
	    public Divisi update( Divisi entity ){
	        return entityManager.merge( entity );
	    }
	 
	    public void delete( Divisi entity ){
	        entityManager.remove( entity );
	    }
	    
	    public void deleteById( long entityId ){
	        Divisi entity = findOne( entityId );
	        delete( entity );
	    }
	    
}