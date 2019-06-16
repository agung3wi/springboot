package com.agung.project.service;

import com.agung.project.core.CoreException;
import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.util.DB;
import com.agung.project.model.Room;
import com.agung.project.util.ValidationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AddRoom extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;

	@PersistenceContext
    private EntityManager entityManager;
	public String task = "";
	
	public Map prepare(Map input) throws CoreException {
		Map validation = new HashMap();
		
		validation.put("n_row", "Kapasitas Baris");
		validation.put("n_column", "Kapasitas Kolom");
		validation.put("gh_id", "Green House");
		
		
		ValidationUtil.required(input, validation);
		return input;
	}
	
	public Map process(Map input, Map originalInput) {
		
//		System.out.print(input);
		Room room = new Room();
		room.ghId = (int) input.get("gh_id");
		room.roomName = (String) input.get("room_name");
		room.nRow = Integer.parseInt((String) input.get("n_row"));
		room.nColumn = Integer.parseInt((String) input.get("n_column"));
		room.createdAt = "20180201010";
		room.updatedAt = "20180201010";
		room.createdBy = 0;
		room.updatedBy = 0;
		room.version = 0;
		
		db.insert(room);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", room);
		return result;
	}
}
