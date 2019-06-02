package com.agung.project.service;

import com.agung.project.core.CoreException;

import com.agung.project.repository.DivisiRepository;
import com.agung.project.model.DB;
import com.agung.project.model.Divisi;
import com.agung.project.core.CoreResponse;
import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.dao.DivisiDao;
import com.agung.project.model.Rest;
import com.agung.project.model.RestResult;
import com.agung.project.service.GetRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agung.project.service.RestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
		divisi.divName = "ssas";
		divisi.divCode = "ASS12";
		divisi.createdBy = -1;
		divisi.updatedBy = -1;
		divisi.createdAt = "yyy";
		divisi.updatedAt = "ssaas";
		divisi.version = 0;
		
		divisiDao.create(divisi);

		return result;
	}
}
