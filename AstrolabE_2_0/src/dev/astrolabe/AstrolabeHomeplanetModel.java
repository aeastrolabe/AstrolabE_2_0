package dev.astrolabe;

import java.util.Date;
import java.util.Calendar;

public class AstrolabeHomeplanetModel {
	
	public final static double DEFAULT_EQUATOR_RADIUS = 200;
	
	
	/**
	 * Calendar in planet days with common origin the ***** TODO !!!
	 */
	
	private static int year = setYear();
	
	private static int month = Calendar.MONTH;
	
	private static int day = Calendar.DAY_OF_MONTH;
	
	/**
	 * @return the year
	 */
	public static int setYear() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public static int getMonth() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		month = cal.get(Calendar.MONTH)+1;
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public static void setMonth(int month) {
		AstrolabeHomeplanetModel.month = month;
	}

	/**
	 * @return the day
	 */
	public static int getDay() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public static void setDay(int day) {
		AstrolabeHomeplanetModel.day = day;
	}

	public double obliquite() {
		return 23.4378/180.*Math.PI;
	}
	
	public double getEpsilon() {
		return obliquite();
		// TODO change to obliquite today
	}

}
