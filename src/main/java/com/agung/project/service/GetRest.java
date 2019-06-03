package com.agung.project.service;

import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.dao.DivisiDao;
import com.agung.project.model.Divisi;
import com.agung.project.service.GetRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class GetRest extends CoreService implements DefaultService {

	@Autowired
	private DivisiDao divisiDao;
	
	public Map prepare(Map input) {
		return input;
	}
	
	public Map process(Map input, Map originalInput) {
		Map result = new HashMap();
		Divisi divisi = new Divisi();
		divisi.div_name = "ssas";
		divisi.divCode = "ASS1cd870";
		divisi.createdBy = -1;
		divisi.updatedBy = -1;  
		divisi.createdAt = "yyy";
		divisi.updatedAt = "ssaas";
		divisi.version = 0;
		
		divisiDao.create(divisi);
//		result.put("data", divisiDao.findAll());
//		result.put("total", divisiDao.count());

		return result;
	}
}
