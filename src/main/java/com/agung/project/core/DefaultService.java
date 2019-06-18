package com.agung.project.core;

import java.util.Map;
import com.agung.project.core.CoreException;
/**
 * @author Agung
 */

public interface DefaultService {
	
	public String getTask(); 
	public Object execute(Map input)  throws CoreException ;

}