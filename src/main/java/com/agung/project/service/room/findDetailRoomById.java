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
public class findDetailRoomById extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;
	public String task = "";
	
	public String getTask() {
		return "view-user";
	}
	
	public Map prepare(Map input) {
		return input;
	}
	
	public Map process(Map input, Map originalInput) {

		String sql = "SELECT A.*, B.gh_name FROM m_room A  INNER JOIN m_green_house B"
				+ " ON B.id=A.gh_id WHERE A.id= :id";
		
		Map row = db.row(sql, input);
		
		return row;
	}
}
