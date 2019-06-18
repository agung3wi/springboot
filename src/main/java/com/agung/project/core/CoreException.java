package com.agung.project.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class CoreException extends Exception {

	public String errorMessage;
	public Map errorList;

	
	public CoreException(String message){
		this.errorMessage = message;
		this.errorList = new HashMap();
	}
	
	public CoreException(String message, Map errList){
		this.errorMessage = message;
		this.errorList = errList;
	}

	public String getErrorMessage(){
		return this.errorMessage;
	}

	public Map getErrorList(){
		return this.errorList;
	}
}