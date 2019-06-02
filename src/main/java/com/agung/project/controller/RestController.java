package com.agung.project.controller;

import com.agung.project.core.CoreException;

import com.agung.project.repository.DivisiRepository;
import com.agung.project.model.DB;
import com.agung.project.model.Divisi;
import com.agung.project.core.CoreResponse;
import com.agung.project.dao.DivisiDao;
import com.agung.project.model.Rest;
import com.agung.project.model.RestResult;
import com.agung.project.service.GetRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashMap;

@Transactional
@Controller
@RequestMapping("")
public class RestController {
    @Autowired
    RestService restService;
    
    @Autowired
    DivisiRepository divisiRepository;
    
	@Autowired
	private DivisiDao divisiDao;
    
    @PersistenceContext
	EntityManager entityManager;

    @GetMapping("/test")
    public ResponseEntity<?> mapReturn() {
    	Map result = new HashMap();
    	result.put("id",1);
    	result.put("name","zzz");
        Map response = new HashMap();
        response.put("success", true);
        response.put("result", result);
        response.put("old", 5);
        return new ResponseEntity<Map>(response, HttpStatus.OK);
    }

    @PostMapping("/cek")
    public ResponseEntity<Map> addRest(@RequestBody Divisi divisi) throws CoreException {
    	
    	try {
    		GetRest service = new GetRest();
    		Map input = new HashMap();
    		service.execute(input);
    		
            return CoreResponse.ok(input);
    	} catch (CoreException ex) {
    	    return CoreResponse.fail(ex);
    	}
    }
    
    @PostMapping("/add")
    public ResponseEntity<Divisi>  addDivisi(@RequestBody Divisi input) {
    	
    	Divisi divisi = new Divisi();
		divisi.divName = "ssas";
		divisi.divCode = "ASS1";
		divisi.createdBy = -1;
		divisi.updatedBy = -1;
		divisi.createdAt = "yyy";
		divisi.updatedAt = "ssaas";
		divisi.version = 0;
		
		divisiDao.create(divisi);
    	return new ResponseEntity<Divisi>(divisi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResult> getById(@PathVariable("id") int id) {
        RestResult rest = restService.getRestById(id);
        return new ResponseEntity<RestResult>(rest, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RestResult>> getAll() {
        List<RestResult> listRest = restService.getAll();
        return new ResponseEntity<List<RestResult>>(listRest, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<RestResult> addRest(@RequestBody Rest rest) {
        restService.addRest(rest);
        RestResult rest2 = restService.getRestById(restService.lastestInput());
        return new ResponseEntity<RestResult>(rest2, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResult> updateRest(@PathVariable("id") int id, @RequestBody Rest rest) {
        restService.updateRest(rest, id);
        RestResult rest2 = restService.getRestById(id);
        return new ResponseEntity<RestResult>(rest2, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResult> deleteRest(@PathVariable("id") int id) {
        RestResult rest2 = restService.getRestById(id);
        restService.deleteRestById(id);
        return new ResponseEntity<RestResult>(rest2, HttpStatus.OK);
    }
}