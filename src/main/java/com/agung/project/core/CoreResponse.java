package com.agung.project.core;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class CoreResponse {

    private String successMessage;
    private String errorMessage;
	private Map errorList;
	
	public static ResponseEntity<Map> ok(Object output){
        Map response = new HashMap();
        response.put("success", true);
        response.put("result", output);

        return new ResponseEntity<Map>(response, HttpStatus.OK);
	}

	public static ResponseEntity<Map> fail(CoreException ex){
		Map response = new HashMap();
        response.put("success", false);
        
        if(ex.getErrorMessage() != ""){
        	response.put("error_message", ex.getErrorMessage());
        }

        response.put("error_list", ex.getErrorList());
        return new ResponseEntity<Map>(response, HttpStatus.OK); 
	}
}