package com.agung.project.service.greenHouse;

import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.util.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetGreenHouse extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;
	
	public String getTask() {
		return "view-user";
	}
	
	public Map prepare(Map input) {
		return input;
	}
	
	public List process(Map input, Map originalInput) {
		
		return (List) db.select("SELECT * FROM m_green_house");
	}
}
