package com.hard.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2010-02-11 11:02:57
		Calendar calendar = new GregorianCalendar();
		String date = sdf.format(calendar.getTime());
		
		return date;
	}
}