package com.agung.project.core;

import java.util.Map;
public class CoreException extends Exception {

	private String errorMessage;
	private Map errorList;

	public CoreException(String errorMessage, Map errorList){
		this.errorMessage = errorMessage;
		this.errorList = errorList;
	}

	public String getErrorMessage(){
		return this.errorMessage;
	}

	public Map getErrorList(){
		return this.errorList;
	}
}