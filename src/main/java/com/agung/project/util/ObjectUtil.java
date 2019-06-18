package com.agung.project.util;

import java.util.Arrays;

public class ObjectUtil {

	public static boolean inArray(String str, String[] array) {
		return Arrays.stream(array).anyMatch(str::equals);
	}
}
