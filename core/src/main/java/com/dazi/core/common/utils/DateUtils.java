/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO.
 *             All rights reserved
 */
package com.dazi.core.common.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description:Date工具类
 * @author lingzuohi@52tt.com
 * @since 2016-06-06
 * @version 1.0.0
 */
public class DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/** 时间格式 **/
	public static final String DAY = "yyyy-MM-dd";
	public static final String DAY2 = "yyyy/M/dd";
	public static final String DAY3 = "yyyyMMdd";
	public static final String TIME = "HH:mm:ss";
	public static final String DATE = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH = "yyyy-MM";
	public static final String DEFAULT_DATE = "yyyy-MM-dd HH:mm:ss";

	public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式：yyyyMMddHHmmssSSS */
	public static String PATTERN_YYYY_MM_DD_HH_mm_ss_SSS = "yyyyMMddHHmmssSSS";

	/** 日期格式： yyyy-MM-dd HH:mm:ss */
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_NC = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式： yyyy/MM/dd HH:mm:ss */
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_NS = "yyyy/MM/dd HH:mm:ss";

	/** 日期格式：yyyy年MM月dd日 */
	public static final String PATTERN_YYYY_MM_DD_CN = "yyyy年MM月dd日";

	/** 日期格式： yyyy-MM-dd */
	public static final String PATTERN_YYYY_MM_DD_NC = "yyyy-MM-dd";

	/** 日期格式： yyyy/MM/dd */
	public static final String PATTERN_YYYY_MM_DD_NS = "yyyy/MM/dd";

	/** 日期格式： yyyyMMdd */
	public static final String PATTERN_YYYY_MM_DD = "yyyyMMdd";

	/** 日期格式：yyMMdd */
	public static String PATTERN_YY_MM_DD = "yyMMdd";

	/** 日期格式： yyyyMM */
	public static final String PATTERN_YY_MM = "yyyyMM";

	/** 日期格式： yyyy */
	public static final String PATTERN_YYYY = "yyyy";

	/** 日期格式： MM */
	public static final String PATTERN_MM = "MM";

	/**
	 * 获取前N个月数组
	 *
	 * @param n
	 * @return List<String>
	 */
	public static List<String> getPreMonths(int n) {
		List<String> result = new ArrayList<String>();
		if (n <= 0) {
			return result;
		}
		for (int i = 0; i < n; i++) {
			result.add(getPreMoth(i));
		}
		return result;
	}

	/**
	 * 获取前N月数据
	 *
	 * @param pre
	 * @return String
	 */
	public static String getPreMoth(int pre) {
		if (pre < 0) {
			return null;
		}
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - pre + 1);
		begin.set(Calendar.DAY_OF_MONTH, 0);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return format(begin.getTime(), MONTH);
	}

	/**
	 * 获取当前天
	 *
	 * @return String
	 */
	public static String getCurDayStr() {
		return new SimpleDateFormat(DAY).format(new Date());
	}

	/**
	 * 获取前一个月
	 *
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousMonth(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) - 1);
		return cd.getTime();
	}
	
	/**
	 * 获取下一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonth(Date date){
	    Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) + 1);
        return cd.getTime();
	}
    
    /**
     * 获取下一个月的指定一天的前一天
     * 
     * @param date
     * @return
     * @throws ParseException 
     */
    public static Date getTheDayInNextMonth(Date date, int pointDay) {
        Calendar cd = Calendar.getInstance();
        if(pointDay==1){
            try{
                cd.setTime(DateUtils.parse(getMonthEnd(date), DateTimeType.Day));
            } catch (ParseException pe){
				logger.error("getTheDayInNextMonth parse fail", pe.toString());
            }
        } else {
            cd.setTime(getNextMonth(date));
            cd.set(Calendar.DAY_OF_MONTH, pointDay-1);
        }
        return cd.getTime();
    }
	/**
	 * 获取前一天
	 *
	 * @return Date
	 */
	public static Date getPreviousDate() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, begin.get(Calendar.DAY_OF_MONTH) - 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return begin.getTime();
	}

	public static Date getPreviousDate(Date day) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(day);

		begin.set(Calendar.DAY_OF_MONTH, begin.get(Calendar.DAY_OF_MONTH) - 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return begin.getTime();
	}

	/**
	 *
	 * @param dayTime
	 * @param minute
     * @return
     */
	public static Date addMinute(Date dayTime, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dayTime);

		cal.add(Calendar.MINUTE, minute);

		return cal.getTime();
	}
	/**
	 * 获取下N天
	 *
	 * @return Date
	 */
	public static Date getNextDate(Date date, int n) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(date);
		begin.set(Calendar.DAY_OF_MONTH, begin.get(Calendar.DAY_OF_MONTH) + n);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		return begin.getTime();
	}

	/**
	 * 获取当月第一天
	 * @return
     */
	public static Date getFirstDateOfMonth() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		return begin.getTime();
	}

	/**
	 * 获取年度第一个月
	 *
	 * @param date
	 * @return String
	 */
	public static String getFirstMonth(Date date) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(date);
		begin.set(Calendar.MONTH, 0);
		return format(begin.getTime(), DateTimeType.Month);
	}

	/**
	 * 获取时间戳
	 *
	 * @return long
	 */
	public static long getTimeStamp(Date time) {
		return null == time ? 0 : time.getTime();
	}

	/**
	 * 检查格式
	 *
	 * @param str
	 * @param sdf
	 * @return boolean
	 */
	public static boolean isParse(String str, String sdf) {
		try {
			return null != parse(str, sdf);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 解析字符串(yyyy-MM-dd HH:mm:ss)
	 *
	 * @param str
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String str) throws ParseException {
		if (StringUtils.isEmpty(str))
			return null;
		return new SimpleDateFormat(DEFAULT_DATE).parse(str);
	}

	/**
	 * 解析字符串
	 *
	 * @param str
	 * @param sdf
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String str, String sdf) throws ParseException {
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(sdf))
			return null;
		return new SimpleDateFormat(sdf).parse(str);
	}

	/**
	 * 解析字符串
	 *
	 * @param str
	 * @param type
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String str, DateTimeType type) throws ParseException {
		if (StringUtils.isBlank(str))
			return null;
		switch (type) {
		case Date: {
			return new SimpleDateFormat(DAY).parse(str); // SimpleDateFormat有线程安全问题，需要独享
		}
		case Time: {
			return new SimpleDateFormat(TIME).parse(str);
		}
		case Day: {
			return new SimpleDateFormat(DAY).parse(str);
		}
		default: {
			return new SimpleDateFormat(DATE).parse(str);
		}
		}
	}

	/**
	 * 格式化日期, 根据用户传进来的格式
	 *
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String format(Date date, String sdf) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(sdf).format(date);
	}

	/**
	 * 格式化时间戳
	 *
	 * @param timestamp
	 * @return String
	 */
	public static String format(long timestamp) {
		if (timestamp < 0) {
			return "";
		}
		return format(new Date(timestamp), DateTimeType.Date);
	}

	/**
	 * 格式化时间戳
	 *
	 * @param timestamp
	 * @return String
	 */
	public static String format(long timestamp, String sdf) {
		if (timestamp < 0) {
			return "";
		}
		return new SimpleDateFormat(sdf).format(new Date(timestamp));
	}

	/**
	 * 格式化时间戳
	 *
	 * @param timestamp
	 * @param type
	 * @return String
	 */
	public static String format(long timestamp, DateTimeType type) {
		if (timestamp < 0) {
			return "";
		}
		return format(new Date(timestamp), type);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @return String
	 */
	public static String format(Date date) {
		return format(date, DateTimeType.DateTime.Date);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param type
	 * @return String
	 */
	public static String format(Date date, DateTimeType type) {
		if (null == date) {
			return "";
		}
		switch (type) {
		case Date: {
			return new SimpleDateFormat(DATE).format(date); // SimpleDateFormat有线程安全问题，需要独享
		}
		case Time: {
			return new SimpleDateFormat(TIME).format(date);
		}
		case Day: {
			return new SimpleDateFormat(DAY).format(date);
		}
		case Month: {
			return new SimpleDateFormat(MONTH).format(date);
		}
		default: {
			return new SimpleDateFormat(DATE).format(date);
		}
		}
	}

	/**
	 * 获取一天起始时间
	 *
	 * @param date
	 * @return Date
	 */
	public static Date getFisrtTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取一天结束时间
	 *
	 * @param date
	 * @return Date
	 */
	public static Date getEndTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取天
	 *
	 * @param date
	 * @return Date
	 */
	public static Date getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取年月
	 *
	 * @param date
	 * @return int
	 */
	public static int getYearMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 100 + (calendar.get(Calendar.MONTH) + 1); // 获取月份
	}

	/**
	 * @description 时间枚举
	 */
	public enum DateTimeType {
		DateTime(0), // 日期时间
		Date(1), // 日期
		Time(2), // 时间
		Month(3), // 年月
		Day(4);// 中文时间格式

		private int index;

		private DateTimeType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}
	


	/**
	 * 取得指定月份的第一天
	 *
	 * @param strdate
	 *            String
	 * @return String
	 */
	public String getMonthBegin(Date date) {
		return parseDate2Str(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得指定月份的最后一天
	 *
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//1.设为下个月的第一天，肯定是1号
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		//2.减一天就是上个月的最后一天
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return parseDate2Str(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String parseDate2Str(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public class PeriodDatePair {
		private Date startDate;
		private Date endDate;
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		@Override
		public String toString() {
			StringBuffer strbuf = new StringBuffer();
			strbuf.append(sdf.format(startDate))
				.append("~")
				.append(sdf.format(endDate));
			return strbuf.toString();
		}
	}

	public static Date getCurrentMonthPointDay(Date theDay, int pointDay){
	    Calendar theDayCalendar = Calendar.getInstance();
	    theDayCalendar.setTime(theDay);
	    theDayCalendar.set(Calendar.DAY_OF_MONTH, pointDay);
	    return theDayCalendar.getTime();
	}


	/**
	 * 取得日期字符串
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return String 日期字符串
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		return format.format(date);
	}

	/**
	 * 获取当前的时间戳字符串（精确到毫秒）
	 * @return
	 */
	public static String getCurrentDateStr() {
		return formatDate(new Date(), PATTERN_YYYY_MM_DD_HH_mm_ss_SSS);
	}

	/**
	 * 获取两个时间间隔天数
	 * @param startDate
	 * @param endDate
     * @return
     */
	public static int getDaysBetween(Date startDate, Date endDate) {
		long days = endDate.getTime() - startDate.getTime();
		return (int)(days/(24 * 60 * 60 * 1000));
	}

	/**
	 * 获取两个时间间隔分钟
	 * @param startTime
	 * @param endTime
     * @return
     */
	public static int getMinutesBetween(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
		return (int)(diff / (60 * 1000));
    }

	/**
	 * 计算日期加上N天后的日期
	 *
	 * @param dayTime
	 * @param days 可正可负
     * @return
     */
	public static Date addDays(Date dayTime, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dayTime);

		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/**
	 * 获取一天的开始，即yyyy-MM-dd 00:00:00
	 * @param dayTime
	 * @return
     */
	public static Date getDayStart(Date dayTime) {
		try {
			String dayStr = format(dayTime, "yyyy-MM-dd") + " 00:00:00";
			return parse(dayStr);
		} catch (ParseException ex) {
			logger.error("parse exception");
		}

		return null;

	}

	/**
	 * 获取一天的结束， 即yyyy-MM-dd 23:59:59
	 * @param dayTime
	 * @return
     */
	public static Date getDayEnd(Date dayTime) {
		try {
			String dayStr = format(dayTime, "yyyy-MM-dd") + " 23:59:59";
			return parse(dayStr);
		} catch (ParseException ex) {
			logger.error("parse exception");
		}

		return null;
	}

	/**
	 * 获取日期差值
	 * @param start
	 * @param end
	 * @return
	 */
	public static int daysDiff(DateTime start, DateTime end) {
		//LocalDate 不比较时间，只比较日期；DateTime 还比较时间，超过24小时才算一天
		LocalDate ld1 = start.toLocalDate();
		LocalDate ld2 = end.toLocalDate();
		return Days.daysBetween(ld1, ld2).getDays();
	}

	/**
	 * 判断当前时间是否在指定时间内
	 * @param startTime
	 * @param endTime
     * @return
     */
	public static boolean inRange(Date startTime, Date endTime) {
		DateTime start = new DateTime(startTime);//开始时间
		DateTime end = new DateTime(endTime);//结束时间
		Interval inteval = new Interval(start,end);

		boolean between = inteval.contains(new DateTime(new Date()));
		return between;
	}

	public static void main(String[] args) throws Exception {
		//Date endDate = DateUtils.parse("2016-12-07 23:59:59");
		//Date startDate = DateUtils.parse("2016-12-06 00:00:00");
        //
		//int days = getDaysBetween(startDate, endDate);
		//System.out.print(days);

		Date endDate = DateUtils.parse("2016-12-06 23:56:22");
		Date startDate = DateUtils.parse("2016-12-06 23:50:21");

		int mins = getMinutesBetween(startDate, endDate);
		System.out.print(mins);

		Date d1 = DateUtils.addMinute(startDate, -1);
		System.out.print(d1);
	}
}
