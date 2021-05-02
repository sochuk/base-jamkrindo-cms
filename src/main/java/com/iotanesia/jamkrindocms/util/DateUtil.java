package com.iotanesia.jamkrindocms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static String dateToString(Date date, String format, Locale local) {
		if (date == null) {
			return "-";
		}
		if (local != null) {
			return new SimpleDateFormat(format, local).format(date);
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static Date stringToDate(String dateString, String format) {
		try {
			return (new SimpleDateFormat(format)).parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date addMinutesToDate(int minutes, Date beforeTime) {
		final long ONE_MINUTE_IN_MILLIS = 60000;

		long curTimeInMs = beforeTime.getTime();
		Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
		return afterAddingMins;
	}
}
