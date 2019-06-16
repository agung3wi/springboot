package com.agung.project.controller;

import com.agung.project.core.CoreException;

import com.agung.project.model.Divisi;
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
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;    

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
    	}
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public ResponseEntity<Map> test(@RequestParam Map input) {
    	Map<String, Object> jwt = new HashMap();
    	jwt.put("test", 1);
    	Map output = new HashMap();
    	String jwtGen = this.createJWT("zzzzz", "aa", "bb", jwt);
    	System.out.print(this.decodeJWT(jwtGen));
    	output.put("a", jwtGen);
    	return new ResponseEntity<Map>(output, HttpStatus.OK);
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
    
    

	public static String createJWT(String id, String issuer, String subject, Map<String, Object> claims) {
	  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("d382482374278428424nnfruh989832483d382482374278428424nnfruh989832483");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .setClaims(claims)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Map decodeJWT(String jwt) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("d382482374278428424nnfruh989832483d382482374278428424nnfruh989832483");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    Map claims = Jwts.parser()
	            .setSigningKey(signingKey)
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}


}