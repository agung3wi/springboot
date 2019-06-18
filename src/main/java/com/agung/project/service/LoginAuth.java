package com.agung.project.service;

import com.agung.project.core.CoreException;
import com.agung.project.core.CoreService;
import com.agung.project.core.DefaultService;
import com.agung.project.util.DB;
import com.agung.project.util.ValidationUtil;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@Service
public class LoginAuth extends CoreService implements DefaultService {
	
	@Autowired
	private DB db;

	public String getTask() {
		return "";
	}
	
	public Map prepare(Map input) throws CoreException {
		Map validation = new HashMap();
		
		validation.put("username", "Username");
		validation.put("password", "Password");
				
		ValidationUtil.required(input, validation);
		return input;
	}
	
	public Map process(Map input, Map originalInput) throws CoreException {
		String sql = "SELECT id, password FROM users "
				+ "WHERE username='"+input.get("username")+"'";
		Map user = db.row(sql);
		if(user.size() == 0) {
			throw new CoreException("Username tidak ditemukan dalam sistem", new HashMap());
		}
		String passwd = (String) input.get("password");
		String passwdDb = (String) user.get("password");

		if(!BCrypt.checkpw(passwd,passwdDb)) {
			throw new CoreException("Username dan password tidak cocok", new HashMap());
		}
		
		String sqlOutput = "SELECT A.*, D.role_name, D.role_code, string_agg(C.task_name, ',') AS tasks "
				+ "FROM users A" + 
				"	INNER JOIN role_task B ON A.role_id=B.role_id" + 
				"	INNER JOIN tasks C ON B.task_id=C.id" + 
				"	INNER JOIN roles D ON A.role_id=D.id" + 
				"		WHERE A.id=:id" + 
				"	GROUP BY A.id, D.role_name, D.role_code";
		user.remove("password");
		Map outputQuery = db.row(sqlOutput, user);
		Map output = new HashMap();
		output.put("token", this.createJWT(outputQuery));
		return output;
	}
	
	public static String createJWT(Map<String, Object> claims) {
		 String id = "1"; 
		 String issuer = "JWT Auth Login";
		 String subject = "JWT Auth Login";
		 
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
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
}
