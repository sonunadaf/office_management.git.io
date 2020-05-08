package com.sodio.proj.office_management.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static Date getDate(Date date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String sdate = dateFormat.format(date);
			Date op = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sdate);
			return op;
		} catch (ParseException e) {
			return null;
		}
	}

}
