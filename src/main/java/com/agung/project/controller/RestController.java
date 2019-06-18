package com.agung.project.controller;

import com.agung.project.core.CoreException;

import com.agung.project.model.Divisi;
import com.agung.project.util.ObjectUtil;
import com.agung.project.core.CoreResponse;
import com.agung.project.core.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;


import java.util.HashMap;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;

@Transactional
@Controller
@RequestMapping("/api/v1")
public class RestController {

	@Autowired  
	ApplicationContext context;
	

    @CrossOrigin(origins = "*")
    @PostMapping("/service/guest")
    public ResponseEntity<Map> addRest(@RequestBody Map input) throws CoreException {
    	try {
    		
    		CoreService service = (CoreService) context.getBean((String) input.get("service_name") );
    		Object output = service.execute((Map) input.get("payload") );
    		
            return CoreResponse.ok(output);
    	} catch (CoreException ex) {
    	    return CoreResponse.fail(ex);
    	} catch (Exception e) {
    		CoreException ex =  new CoreException(e.getMessage());
    		return CoreResponse.fail(ex);
    	}
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/service")
    public ResponseEntity<Map> restApi(HttpServletRequest request, @RequestBody Map input) throws CoreException {
    	try {
    		String token = request.getHeader("Authorization");
        	if(token ==null) {
        		throw new CoreException("Token tidak ditemukan");
        	}
        	Map sessions = (Map) this.decodeJWT(token);
        	input.put("_session", sessions);
    		CoreService service = (CoreService) context.getBean((String) input.get("service_name") );
    		String[] tasks = ((String) sessions.get("tasks")).split(",");
    		if(!ObjectUtil.inArray(service.getTask(), tasks)) {
    			throw new CoreException("User tidak terauthorize");
    		}
    		Object output = service.execute((Map) input.get("payload") );
    		
            return CoreResponse.ok(output);
    	} catch (CoreException ex) {
    	    return CoreResponse.fail(ex);
    	} catch (Exception e) {
    		CoreException ex =  new CoreException(e.getMessage());
    		return CoreResponse.fail(ex);
    	}
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Map> login(HttpServletRequest request, @RequestBody Map input) throws CoreException {
    	try {
    		CoreService service = (CoreService) context.getBean("loginAuth");
    		Object output = service.execute(input);
            return CoreResponse.ok(output);
    	} catch (CoreException ex) {
    	    return CoreResponse.fail(ex);
    	}
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/logout")
    public ResponseEntity<Map> logout(HttpServletRequest request, @RequestBody Map input) throws CoreException {
    	try {
    		String token = request.getHeader("Authorization");
        	if(token ==null) {
        		throw new CoreException("Token tidak ditemukan");
        	}
            return CoreResponse.ok(input);
    	} catch (CoreException ex) {
    	    return CoreResponse.fail(ex);
    	}
    }
    
  
    @PostMapping("/add")
    public ResponseEntity<Divisi>  addDivisi(@RequestBody Divisi input) {
    	
    	Divisi divisi = new Divisi();
		divisi.div_name = "ssas";
		divisi.divCode = "ASS2";
		divisi.createdBy = -1;
		divisi.updatedBy = -1;
		divisi.createdAt = "yyy";
		divisi.updatedAt = "ssaas";
		divisi.version = 0;
		
//		divisiDao.create(divisi);
    	return new ResponseEntity<Divisi>(divisi, HttpStatus.OK);
    }
    
	
	public static Map decodeJWT(String jwt) {
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			
		    //We will sign our JWT with our ApiKey secret
		    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("d382482374278428424nnfruh989832483d382482374278428424nnfruh989832483");
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		    
		    Map claims = Jwts.parser()
		            .setSigningKey(signingKey)
		            .parseClaimsJws(jwt).getBody();
		    return claims;
		} catch (Exception ex) {
			return new HashMap();
		}
	}
		


}