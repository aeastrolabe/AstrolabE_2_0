package dev.astrolabe;

public class AstrolabeHomeplanetModel {
	
	public final static double DEFAULT_EQUATOR_RADIUS = 200;
	
	
	/**
	 * Calendar in planet days with common origin the ***** TODO !!!
	 */
	
	private static int year;
	
	private static int month;
	
	private static int day;
	
	/**
	 * @return the year
	 */
	public static int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public static void setYear(int year) {
		AstrolabeHomeplanetModel.year = year;
	}

	/**
	 * @return the month
	 */
	public static int getMonth() {
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
