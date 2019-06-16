package com.agung.project.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.agung.project.core.CoreException;


public class ValidationUtil {
		
	public static void required(Map input, String key, String alias)  throws CoreException {
		if(input.get(key)==null || input.get(key)==null) {
			
			Map errorKey = new HashMap();
			errorKey.put(key, alias+" harus diisi");
			throw new CoreException("", errorKey);
		}
	}
	
	public static void required(Map input, Map<String, String> fields)  throws CoreException {
		Map errorKey = new HashMap();
		for(Map.Entry<String, String> entry : fields.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();

		    if(input.get(key)==null || input.get(key)==null) {
		
				errorKey.put(key, value+" harus diisi");
			}
		}
		
		if(errorKey.size() > 0)
			throw new CoreException("", errorKey);

		
	}

}
