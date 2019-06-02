package com.agung.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agung.project.model.Divisi;

@Transactional
@Repository
public class DivisiDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    public Divisi findOne( long id ){
        return entityManager.find( Divisi.class, id );
    }
	    
	    public List< Divisi > findAll(){
	        return entityManager.createQuery( "from " + Divisi.class.getName() )
	        .getResultList();
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