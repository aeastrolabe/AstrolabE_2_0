package dev.astrolabe;

import java.util.Date;
import java.util.Calendar;

public class AstrolabeHomeplanetModel {
	
	public final static double DEFAULT_EQUATOR_RADIUS = 200; //TODO update with initial frame size
	
	
	/**
	 * Calendar in planet days with common origin the ***** TODO !!!
	 */
	
	private int year = setYear();
	
	private int month = Calendar.MONTH;
	
	private int day = Calendar.DAY_OF_MONTH;
	
	/**
	 * @return the year
	 */
	public int setYear() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		return year;
	}

	public int getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		month = cal.get(Calendar.MONTH)+1;
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	public double obliquite() {
		return 23.4378/180.*Math.PI;
	}
	
	public double getEpsilon() {
		return obliquite();
		// TODO change to obliquite today
	}

}
