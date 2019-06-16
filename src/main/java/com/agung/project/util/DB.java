package com.agung.project.util;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class DB {

	@Autowired
	JdbcTemplate template;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<Map<String, Object>> select(String sql, Object[] args){
    	return template.queryForList(sql, args);
	}
	
	public List<Map<String, Object>> select(String sql){
    	return template.queryForList(sql);
	}
	
	public Map<String, Object> row(String sql){
    	return template.queryForMap(sql);
	}
	
	public List<Map<String, Object>> select(String sql, Map<String, Object> param){
		int set = param.size();
		Object[] args = new Object[set];
		int i=0;
		
		for(Map.Entry<String, Object> entry : param.entrySet()) {
		    args[i] = entry.getValue();
		    sql = sql.replace(":"+entry.getKey(), "?");
		    i++;
		}
		
    	return template.queryForList(sql, args);
	}
	
	public Object insert(Object entity){
		entityManager.persist(entity);
		return entity;
	}
	
	public void insert(String table, Map<String, Object> field){
		int set = field.size();
		Object[] args = new Object[set];
		int i=0;
		String sql = "INSERT INTO "+table;
		String columnList = "(";
		String valueList = " VALUES(";
		
		for(Map.Entry<String, Object> entry : field.entrySet()) {
		    columnList += entry.getKey();
			columnList += (i<set-1)? ", " : ")";
			valueList += (i<set-1)? "?, " : "?)";
		    args[i] = entry.getValue();
		    sql = sql.replace(":"+entry.getKey(), "?");
		    i++;
		}
	}
		
	public void update(String table, Map<String, Object> field, Map<String, Object> condition){
		int set = field.size();
		int setCondition = condition.size();
		Object[] args = new Object[set+setCondition];
		int i=0;
		String sql = "UPDATE "+table+" SET ";
		
		for(Map.Entry<String, Object> entry : field.entrySet()) {
		    sql += entry.getKey()+"=?";
			sql += (i<set-1)? ", " : "";
			args[i] = entry.getValue();
		    i++;
		}
		
		
		int j =0;
		sql += " WHERE ";
		for(Map.Entry<String, Object> entry : condition.entrySet()) {
		    sql += entry.getKey()+"=?";
			sql += (j<setCondition-1)? " AND " : "";
			args[i] = entry.getValue();
			j++;
		    i++;
		}
	
		template.update(sql, args);
	}
	
	public void delete(String table, Map<String, Object> condition){
		int set = condition.size();
		Object[] args = new Object[set];
		int i=0;
		String sql = "DELETE FROM "+table;

		sql += " WHERE ";
		for(Map.Entry<String, Object> entry : condition.entrySet()) {
		    sql += entry.getKey()+"=?";
			sql += (i<set-1)? " AND " : "";
			args[i] = entry.getValue();
		    i++;
		}
	
		template.update(sql, args);
	}

}
