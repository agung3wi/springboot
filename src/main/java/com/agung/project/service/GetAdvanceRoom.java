package com.agung.project.service;

import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.util.DB;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetAdvanceRoom extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;
	public String task = "";
	
	public Map prepare(Map input) {
		return input;
	}
	
	public Map process(Map input, Map originalInput) {
		
		Map result = new HashMap();
		ObjectMapper oMapper = new ObjectMapper();
		
		Map param = new HashMap();
		param.put("param", true);
		
		result.put("data", db.select("SELECT A.*, B.gh_name FROM m_room A  INNER JOIN m_green_house B"
				+ " ON B.id=A.gh_id WHERE :param", param));
		
		
		result.put("total", 10);
		Map map = oMapper.convertValue(result, Map.class);
		return (Map) map;
	}
}
