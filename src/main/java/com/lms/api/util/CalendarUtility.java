package com.lms.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 */
public class CalendarUtility {

	/**
	 * 取得系统时间（精确到分）
	 * 
	 * @return 系统时间
	 */
	public static long getSystemDatetime() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		return year * 100000000l + (month + 1) * 1000000l + day * 10000l + hour * 100l + minute;
	}

	/**
	 * 取得系统时间（精确到秒）
	 * 
	 * @return 系统时间
	 */
	public static long getSystemAccurateTime() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		int second = ca.get(Calendar.SECOND);
		return year * 10000000000l + (month + 1) * 100000000l + day * 1000000l + hour * 10000l + minute * 100l + second;
	}

	/**
	 * 取得系统时间（精确到毫秒）
	 * 
	 * @return 系统时间
	 */
	public static long getSystemMillisecondTime() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		int second = ca.get(Calendar.SECOND);
		int millisecond = ca.get(Calendar.MILLISECOND);
		return year * 1000000000000l + (month + 1) * 10000000000l + day * 100000000l + hour * 1000000l + minute * 10000l
				+ second * 100l + millisecond;
	}

	/**
	 * 取得指定格式系统时间
	 * 
	 * @return 系统时间
	 */
	public static String getSystemFormatDatetime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(now);
	}

	/**
	 * 取得距现在指定天数的日期
	 * 
	 * @return 指定时间
	 */
	public static long getOffsetDateAccuratetime(int offset) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int nowday = ca.get(Calendar.DAY_OF_YEAR);
		ca.set(Calendar.DAY_OF_YEAR, nowday - offset);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		int second = ca.get(Calendar.SECOND);
		return year * 10000000000l + (month + 1) * 100000000l + day * 1000000l + hour * 10000l + minute * 100l + second;
	}

	/**
	 * 取得距现在指定天数的日期
	 * 
	 * @return 指定时间
	 */
	public static long getOffsetDatetime(int offset) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int nowday = ca.get(Calendar.DAY_OF_YEAR);
		ca.set(Calendar.DAY_OF_YEAR, nowday - offset);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int minute = ca.get(Calendar.MINUTE);
		return year * 100000000l + (month + 1) * 1000000l + day * 10000l + hour * 100l + minute;
	}

	/**
	 * 根据日期取得日期对象
	 * 
	 * @param date 日期
	 * @return 日期对象
	 * @throws Exception
	 * @throws IncorrectParameterException 参数异常
	 */
	public static Calendar getCalendar(long date) throws Exception {
		String template = "0101000000";
		String dateStr = String.valueOf(date);
		int length = dateStr.length();
		if (length < 4) {
			throw new Exception();
		}
		dateStr += template.substring(length - 4);
		int year = Integer.valueOf(dateStr.substring(0, 4));
		int month = Integer.valueOf(dateStr.substring(4, 6)) - 1;
		int day = Integer.valueOf(dateStr.substring(6, 8));
		int hour = Integer.valueOf(dateStr.substring(8, 10));
		int minute = Integer.valueOf(dateStr.substring(10, 12));
		int second = Integer.valueOf(dateStr.substring(12, 14));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar;
	}

	/**
	 * 取得两个日期的间隔秒数
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 间隔分钟
	 * @throws Exception
	 * @throws IncorrectParameterException 参数异常
	 */
	public static long getSecondInterval(long date1, long date2) throws Exception {
		Calendar c1 = CalendarUtility.getCalendar(date1);
		Calendar c2 = CalendarUtility.getCalendar(date2);
		return (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000;
	}

	/**
	 * 取得两个日期的间隔分钟
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 间隔分钟
	 * @throws Exception
	 * @throws IncorrectParameterException 参数异常
	 */
	public static long getMinuteInterval(long date1, long date2) throws Exception {
		Calendar c1 = CalendarUtility.getCalendar(date1);
		Calendar c2 = CalendarUtility.getCalendar(date2);
		return (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60);
	}

	/**
	 * Calendar类型对象转字符串
	 * 
	 * @param param   Calendar类型对象
	 * @param pattern 显示模式
	 * @return 日期字符串
	 * @throws ParseException Parse异常
	 */
	public static String convertCalendarToStr(Calendar param, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(param.getTime());
	}

	/**
	 * Date类型对象转字符串
	 * 
	 * @param param   Date类型对象
	 * @param pattern 显示模式
	 * @return 日期字符串
	 * @throws ParseException Parse异常
	 */
	public static String convertDateToStr(Date param, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(param);
	}
}
