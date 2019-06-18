package com.agung.project.service.room;

import com.agung.project.core.CoreException;
import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.util.DB;
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
		
		int nRow = Integer.parseInt((String) input.get("n_row"));
		input.replace("n_row", nRow);
		
		int nCol = Integer.parseInt((String) input.get("n_column"));
		input.replace("n_column", nCol);
		input.put("created_at", "20180201010");
		input.put("updated_at", "20180201010");
		input.put("created_by", -1);
		input.put("updated_by", -1);
		input.put("version", 0);
		input.remove("_session");
		db.insert("m_room", input);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", input);
		return result;
	}
}
