package com.cts.taskmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TaskManagerUtil {
	
	public static Date convertToDate(String dateText) {
		
		String format  = "MM/dd/yyyy";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormatter.parse(dateText);
		} catch (ParseException e) {
			date = null;
		}
		
		return date; 
		
	}

}
