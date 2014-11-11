package dev.utils;

public class Log {

	public static void log(String s, Object o) {
		System.out.println(o.getClass().getSimpleName()+ " : "+s);
	}
}
