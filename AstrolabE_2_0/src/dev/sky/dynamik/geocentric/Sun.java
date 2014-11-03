package dev.sky.dynamik.geocentric;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;

import dev.io.ImportData;
import dev.sky.dynamik.heliocentric.HeliocentricDynamicCelestialBody;



public class Sun extends HeliocentricDynamicCelestialBody {
	
	public static Sun sun = new Sun();
	
	public Sun() {
		super("Sun");
		String[] sun_data_string = parseSunFile();
		double[] sun_data = parseDoubleTab(Arrays.copyOfRange(sun_data_string, 1, sun_data_string.length));
		Nb = sun_data[0];
		Na = sun_data[1];
		ib = sun_data[2];
		ia = sun_data[3];
		wb = sun_data[4];
		wa = sun_data[5];
		ab = sun_data[6];
		aa = sun_data[7];
		eb = sun_data[8];
		ea = sun_data[9];
		Mb = sun_data[10];
		Ma = sun_data[11];

		displayed = true;
	}
	
	public static double sunLongitude(double d) {
		return sun.v(d) + sun.w(d);
	}
	
	public static double[] sunRectangularCoordinates(double d) {
		double r = sun.r(d);
		double lon = deg2rad(sunLongitude(d));
		double[] s = new double[3];
		
		s[0] = r*Math.cos(lon);
		s[1] = r*Math.sin(lon);
		s[2] = 0;
		
		return s;
	}
	
	public static String[] parseSunFile() {
		String[] result = new String[13];
		
		InputStream ips1 = null;
		try {
			URL url = ImportData.class.getResource("ImportData.class");
			URL urlText = new URL(url, "../data/sun.txt");
			ips1 = urlText.openStream();
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportData.class.getResource("ImportData.class");
				URL urlText = new URL(url, "../../data/sun.txt");
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
		int incr = 0;
		try {
			while ((ligne=br1.readLine())!=null) {
				if (!ligne.equals("")) {
					result[incr] = ligne;
					incr++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
