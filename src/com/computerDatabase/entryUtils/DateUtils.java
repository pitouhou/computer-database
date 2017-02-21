package com.computerDatabase.entryUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

	public static Date convertDate(String dateIn){
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		java.sql.Date dateOut;
		try {
			date = sdf1.parse(dateIn);
			dateOut = new Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			dateOut = null;
		}
		
		
		return dateOut;
		
	}
	
	public static boolean compareDate(Date introduced, Date discontinued){
		
		if(introduced.before(discontinued)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
