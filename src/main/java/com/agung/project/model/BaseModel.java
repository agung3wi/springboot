package com.agung.project.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseModel {
    @PersistenceContext
	EntityManager entityManager;
    
    public void save() {
    	entityManager.persist(this);
    }
	
}


