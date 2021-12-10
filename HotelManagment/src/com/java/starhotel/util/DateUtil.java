package com.java.starhotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static Date convertToDate(String date) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return is the date before now
	 */
	public static boolean isPastDate (Date date) {
		Date now = new Date(System.currentTimeMillis());
		return date.before(now);
	}
}
