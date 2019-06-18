package com.agung.project.service.room;

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
	
	public String getTask() {
		return "view-user";
	}
	
	public Map prepare(Map input) {
		int offset = (int) input.get("limit") * ((int) input.get("page") -1);
		input.put("offset", offset);
		return input;
	}
	
	public Map process(Map input, Map originalInput) {
		
		Map result = new HashMap();
		ObjectMapper oMapper = new ObjectMapper();
		
		Map param = new HashMap();
		String sql = "SELECT A.*, B.gh_name FROM m_room A  INNER JOIN m_green_house B"
				+ " ON B.id=A.gh_id WHERE true";
		
		String sqlCount = "SELECT COUNT(1) AS count FROM m_room A  INNER JOIN m_green_house B"
				+ " ON B.id=A.gh_id WHERE true";
		
		if(input.get("src") != null) {
			sql += " AND A.room_name ILIKE '%"+input.get("src")+"%'";
			sqlCount += " AND A.room_name ILIKE '%"+input.get("src")+"%'";
		}
		
		
		Map count = db.row(sqlCount);
		result.put("total", count.get("count"));
			
		sql += " LIMIT :limit OFFSET :offset";
		
		param.put("limit", input.get("limit"));
		param.put("offset", input.get("offset"));
		
		result.put("data", db.select(sql, param));
		
		Map map = oMapper.convertValue(result, Map.class);
		return (Map) map;
	}
}
