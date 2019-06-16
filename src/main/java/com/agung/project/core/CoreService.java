package com.agung.project.core;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import com.agung.project.core.CoreException;
import com.agung.project.core.DefaultService;

public abstract class CoreService implements DefaultService {


	abstract protected Map prepare(Map input) throws CoreException;
	abstract protected Object process(Map input, Map originalInput);

	public Object execute(Map input) throws CoreException {

		Map inputNew =  this.prepare(input);
		Object result =  (Object) this.process(inputNew, input);
		return result;
        
	}

	protected Map validation() {
		return new HashMap();
	}

}
