package dev.utils;

import dev.MainFrame;

public class LanguageManager {
	
	private static MainFrame mainFrame;
	
	final public static String FRANCAIS = "Francais";
	final public static String ENGLISH = "English";
	
	private static String language;
	
	public LanguageManager(String default_language) {
		language = default_language;
	}
	
	public static String getLanguage() {
		return language;
	}
	
	public static void setMainFrame(MainFrame frame) {
		mainFrame = frame;
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}

}
