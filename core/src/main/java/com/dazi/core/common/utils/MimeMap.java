package com.dazi.core.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MimeMap {
	
	private static final String MIME_MAP_CONFIG = "mime_type.list";
	
	private Map<String, String> MIME_MAP = new HashMap<String, String>();
	private static final MimeMap single = new MimeMap(); 
	
	private MimeMap() {
		InputStream inputStream = MimeMap.class.getClassLoader().getResourceAsStream(MIME_MAP_CONFIG);
		Properties prop = new Properties();
		try {
			if (inputStream == null)
				throw new Exception(MIME_MAP_CONFIG + " not found on classpath");     
			prop.load(inputStream);
			Enumeration e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				MIME_MAP.put(key, value);
			}
		} catch (Exception e) {
			System.out.println("Unable to load mime_map info: " + e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static MimeMap getInstatnce() { 
		return single;
	}

	public String getMime(String path) {
		String noMatch = "application/octet-stream";
		int t = path.lastIndexOf(".");
		if (t < 0) {
			return noMatch;
		}
		String suffix = path.substring(t + 1, path.length());
		if (suffix == null) {
			return noMatch;
		}
		if (!this.MIME_MAP.containsKey(suffix)) {
			return noMatch;
		}
		return this.MIME_MAP.get(suffix);
	}

/*
	public static void main(String args[]) {
		MimeMap mm = MimeMap.getInstatnce();
		Iterator<String> it = mm.MIME_MAP.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = mm.MIME_MAP.get(key);
			System.out.println(key + " -> " + value);
		}
		
		System.out.println(mm.getMime("fuck.png"));
	}
*/
}
