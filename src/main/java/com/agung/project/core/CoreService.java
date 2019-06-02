package com.agung.project.core;

import java.util.Map;
import java.util.HashMap;
import java.io.*;
import com.agung.project.core.CoreException;
import com.agung.project.core.DefaultService;

public abstract class CoreService implements DefaultService {


	abstract protected Map prepare(Map input);
	abstract protected Map process(Map input, Map originalInput);

	public Map execute(Map input) throws CoreException {

		try {
			
//			Validator validator = new Validator();

//			validation = validator.make(Map input, this.validation();

//			validation.validate();

//			if (validation.fails()) {
//				throw new CoreException("", validation.errors());
//			}
			
			if (false) {
				throw new CoreException("", new HashMap());
			}

			Map inputNew =  this.prepare(input);
			Map result =  this.process(inputNew, input);
			return result;
		} catch(CoreException ex){
			throw new CoreException(ex.getMessage(), new HashMap());
		}
        
	}

	protected Map validation() {
		return new HashMap();
	}

}
