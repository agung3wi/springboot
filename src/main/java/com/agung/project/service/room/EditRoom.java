package com.agung.project.service.room;

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
public class EditRoom extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;

	@PersistenceContext
    private EntityManager entityManager;
	
	public String getTask() {
		return "view-user";
	}
	
	public Map prepare(Map input) throws CoreException {
		Map validation = new HashMap();
		
		validation.put("n_row", "Kapasitas Baris");
		validation.put("n_column", "Kapasitas Kolom");
		validation.put("gh_id", "Green House");
		
		
		ValidationUtil.required(input, validation);
		return input;
	}
	
	public Map process(Map input, Map originalInput) {
		
		input.put("updated_at", "20180201010");
		input.put("updated_by", -1);
		input.put("version", (int) input.get("version")+1);
		
		Map condition = new HashMap();
		condition.put("id",input.get("id"));
		input.remove("id");
		input.remove("gh_name");
		
		db.update("m_room",input, condition);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", input);
		return result;
	}
}
