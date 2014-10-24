package io;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;





public class ImportText {
	
	//public static ImportText imp = new ImportText();
	public static String language;

	public static void setLanguage(String s) {
		language = s;
	}
	
	public static String get(String document, int number, String langue) {
		
		InputStream ips1 = null;
		Reader ipsr1 = null;
		try {
			URL url = ImportText.class.getResource("ImportText.class");
			URL urlText = new URL(url, "../data/language/"+langue+"/"+document+".txt");
			ips1 = urlText.openStream();
			//ips1 = new FileInputStream(urlText.toString());
			ipsr1 = new InputStreamReader(ips1,"UTF8");
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportText.class.getResource("ImportText.class");
				URL urlText = new URL(url, "../../data/language/"+langue+"/"+document+".txt");
				ips1 = urlText.openStream();
				//ips1 = new FileInputStream(urlText.toString());
				ipsr1 = new InputStreamReader(ips1,"UTF8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader br1=new BufferedReader(ipsr1);
		String ligne;
		try {
			ligne=br1.readLine();
			return ligne.split("#")[number];
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return "Epic fail";
	}
	
	
	
	public static String get(String document, int number) {
		
		InputStream ips1 = null;
		Reader ipsr1 = null;
		try {
			URL url = ImportText.class.getResource("ImportText.class");
			URL urlText = new URL(url, "../data/language/"+language+"/"+document+".txt");
			ips1 = urlText.openStream();
			//ips1 = new FileInputStream(urlText.toString());
			ipsr1 = new InputStreamReader(ips1,"UTF8");
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportText.class.getResource("ImportText.class");
				URL urlText = new URL(url, "../../data/language/"+language+"/"+document+".txt");
				ips1 = urlText.openStream();
				//ips1 = new FileInputStream(urlText.toString());
				ipsr1 = new InputStreamReader(ips1,"UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br1=new BufferedReader(ipsr1);
		String ligne;
		try {
			ligne=br1.readLine();
			return ligne.split("#")[number];
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return "Epic fail";
	}
	
	/**
	public static void main(String[] args) {
		System.out.println(get("hover",1));
	}*/
}
