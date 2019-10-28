package com.ajbose.learning.com.ajbos.learning.upgrad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StringUtil {

	public static void main(String[] args) {
		String time = "2019-07-28T06:53:53z";
		Date utcDate = getUTCDate(time);
		System.out.println(utcDate);
	}

	
	/**
	 * 
	 * Convert the PPASS Standard date String to Date Object
	 * 
	 * Default value null
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date getUTCDate(String dateString) {
		Date date = null;
		String STANDARD_DATE_FORMAT_WITHOUT_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		String STANDARD_DATE_FORMAT_WITH_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'";

		if (dateString != null && dateString.length() > 0) {
			String input = dateString;
			boolean found = false;
			SimpleDateFormat formatter = null;
			// format1 : "2012-11-25t00:47:16.0380000z",
			String format1 = ("(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{7})Z)");
			// format2 : "1994-11-05T13:15:30Z"
			String format2 = ("((\\d{4}-\\d{2}-\\d{2}T\\d{2}(:\\d{2}){1,2})Z)");

			try {
				if (!found && input.matches(format1) && input.length() > 19) {
					found = true;
					formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_WITH_MILLISECONDS);
					formatter.setLenient(true);
//					formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
					date = formatter.parse(input);
				}

				if (!found && input.matches(format2) && input.length() > 19) {
					found = true;
					formatter = new SimpleDateFormat(STANDARD_DATE_FORMAT_WITHOUT_MILLISECONDS);
//					formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
					date = formatter.parse(input);
				}

			} catch (Exception exception) {
			}
		}
		return date;
	}
	
}