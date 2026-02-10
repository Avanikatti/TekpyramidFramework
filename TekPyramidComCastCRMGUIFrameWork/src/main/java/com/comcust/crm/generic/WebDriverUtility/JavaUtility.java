package com.comcust.crm.generic.WebDriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	
		public int togetRandomNumber(int count)
		{
			Random random=new Random();
			int randoumCount = random.nextInt(count);
			return randoumCount;
		}
		
		public String togetCurrentDate()
		{
			Date dateObj=new Date();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sim.format(dateObj);
			return currentDate;
		}
		public String togetRequiredDate(String days)
		{
			Date dateObj=new Date();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sim.format(dateObj);
			Calendar cal=sim.getCalendar();
			int day = Integer.parseInt(days);
			cal.add(Calendar.DAY_OF_MONTH, day);
			String reqDate = sim.format(cal.getTime());
			return reqDate;
			
		}
}
