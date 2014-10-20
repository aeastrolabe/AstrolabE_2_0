package dev.sky.dynamik.heliocentric;

import io.ImportData;
import io.ImportImage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import dev.astrolabe.AstrolabeHomeplanetModel;
import dev.sky.CelestialBodyController;
import dev.utils.JulianDate;

public class Planet extends HeliocentricDynamicCelestialBody {
	
	public static LinkedList<CelestialBodyController> planetList = new LinkedList<CelestialBodyController>();

	public static Planet mercury;
	public static Planet venus;
	public static Planet mars;
	public static Planet jupiter;
	public static Planet saturn;
	public static Planet uranus;
	public static Planet neptune;

	public BufferedImage planetImage;
	
	
	public Planet(String name, double[] planet_data) {
		super(name);
		Nb = planet_data[0];
		Na = planet_data[1];
		ib = planet_data[2];
		ia = planet_data[3];
		wb = planet_data[4];
		wa = planet_data[5];
		ab = planet_data[6];
		aa = planet_data[7];
		eb = planet_data[8];
		ea = planet_data[9];
		Mb = planet_data[10];
		Ma = planet_data[11];
		
		view.image = ImportImage.read("planets/"+name.toLowerCase()+".png");
	}	
	
	public static String[][] parsePlanetFile() throws IOException {
		String[][] result = new String[8-1][13];
		
		InputStream ips1 = null;
		try {
			URL url = ImportData.class.getResource("ImportData.class");
			URL urlText = new URL(url, "../data/planets.txt");
			ips1 = urlText.openStream();
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportData.class.getResource("ImportData.class");
				URL urlText = new URL(url, "../../data/planets.txt");
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
		while ((ligne=br1.readLine())!=null) {
			if (!ligne.equals("")) {
				result[incr/13][incr%13] = ligne;
				incr++;
			}

		}
		
		return result;
	}
	
	public static void setSphericalCoordinates(LinkedList<CelestialBodyController> planetList) {
		ListIterator<CelestialBodyController> iter = planetList.listIterator();
		
		Planet p;
		int[] ymd = new int[] {AstrolabeHomeplanetModel.getYear(), AstrolabeHomeplanetModel.getMonth(), AstrolabeHomeplanetModel.getDay()};
		while (iter.hasNext()) {
			p = (Planet) iter.next();
			double[] tmp = p.geocentricRectangularToSphericalCoordinates(JulianDate.toJulian(ymd));
			p.model.setAlpha(tmp[1]);
			p.model.setDelta(tmp[2]);
		}
	}
	
	public static void importPlanets(){
		String[][] data;
		try {
			data = parsePlanetFile();
			double[] values = null;
			
			for(int i = 0; i<data.length; i++) {
				values = parseDoubleTab(Arrays.copyOfRange(data[i], 1, data[i].length));
				planetList.add(new Planet(data[i][0],values));
			}
			
			mercury = (Planet) planetList.get(0);
			venus = (Planet) planetList.get(1);
			mars = (Planet) planetList.get(2);
			jupiter = (Planet) planetList.get(3);
			saturn = (Planet) planetList.get(4);
			uranus = (Planet) planetList.get(5);
			neptune = (Planet) planetList.get(6);
			
			planetList.removeLast();
			
			setSphericalCoordinates(planetList);
			
			System.out.println("**Importation des planètes terminée**");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
