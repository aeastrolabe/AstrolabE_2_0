package dev.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ImportData {

	public static int readCharacteristics(int num) {
		InputStream ips1 = null;
		try {
			URL url = ImportData.class.getResource("ImportData.class");
			URL urlText = new URL(url, "../info.txt");
			ips1 = urlText.openStream();
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportData.class.getResource("ImportData.class");
				URL urlText = new URL(url, "../../info.txt");
				ips1=urlText.openStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reader ipsr1 = new InputStreamReader(ips1);
		BufferedReader br1=new BufferedReader(ipsr1);
		String ligne;
		Pattern pChar = Pattern.compile("([0-9]*)[,]([0-9]*)[,]([0-9]*)");
		try {
			ligne=br1.readLine();
			Matcher m = pChar.matcher(ligne);
			if (m.find()) {
				System.out.println(m.group(num));
				return Integer.parseInt(m.group(num));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
