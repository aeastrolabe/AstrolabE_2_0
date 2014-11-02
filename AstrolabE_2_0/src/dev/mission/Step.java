package dev.mission;

public abstract class Step {

	public static String getClassName(String s) {
		String[] t = s.split(".");
		return t[t.length];
	}
}
