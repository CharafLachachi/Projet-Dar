package com.utils;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public abstract class PasrseJsonUtility {
	public static StringBuffer getRequestJson(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			return jb;
		} catch (Exception e) {
			/* report an error */ }
	return jb;
	}
}
