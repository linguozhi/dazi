package com.dazi.core.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UUIDHexGenerator {
	private static final String IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = format(ipadd);
	}

	private static final String JVM = format((int) (System.currentTimeMillis() >>> 8));

	private static short counter = (short) 0;
	
	private static long ts = System.currentTimeMillis();
	
	private static String dt;
	static {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = Calendar.getInstance().getTime();        
		dt = df.format(now);
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	public static String generate() {
		synchronized (UUIDHexGenerator.class) {
			if (ts == System.currentTimeMillis()) {
				counter++;
			} else {
				ts = System.currentTimeMillis();
				
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				Date now = Calendar.getInstance().getTime();        
				dt = df.format(now);
				
				counter = 0;
			}
			return dt + format(counter) + IP + JVM;
		}
	}

	static String format(int intValue) {
		String formatted = Integer.toHexString(intValue);
		StringBuilder buf = new StringBuilder("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	static String format(short shortValue) {
		String formatted = Integer.toHexString(shortValue);
		StringBuilder buf = new StringBuilder("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
	public static String createOrderByUserId(Long userId)
	{	
		synchronized (UUIDHexGenerator.class)
		{
			String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
			String orderNo = str  + userId;
			
			int m = 30 - orderNo.length();
			orderNo +=  RandomStringUtils.randomNumeric(m);
			return orderNo;
		}
		
	}
}
