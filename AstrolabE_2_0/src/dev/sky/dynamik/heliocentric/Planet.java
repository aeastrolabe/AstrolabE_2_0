package dev.sky.dynamik.heliocentric;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import dev.astrolabe.AstrolabeHomeplanetModel;
import dev.io.ImportImage;
import dev.sky.io.ImportCelestialData;
import dev.utils.JulianDate;

public class Planet extends HeliocentricDynamicCelestialBody {
	
	public static LinkedList<Planet> planetList = new LinkedList<Planet>();

	public static Planet mercury;
	public static Planet venus;
	public static Planet mars;
	public static Planet jupiter;
	public static Planet saturn;
	public static Planet uranus;
	public static Planet neptune;

	public BufferedImage planetImage;
	
	public Planet(String name) {
		super(name);
		model.setConstellation("Not available"); //TODO do we want to do something with this ?
		model.setType("Planet");
		Nb = 0;
		Na = 0;
		ib = 0;
		ia = 0;
		wb = 0;
		wa = 0;
		ab = 0;
		aa = 0;
		eb = 0;
		ea = 0;
		Mb = 0;
		Ma = 0;
		view.setImage(null);
	}
	
	public Planet(String name, double[] planet_data) {
		super(name);
		
		model.setConstellation("Not available"); //TODO do we want to do something with this ?
		model.setType("Planet");
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
		
//		setSphericalCoordinates();
			
		view.setImage(ImportImage.read("planets/"+name.toLowerCase()+".png"));
	}	
	
	public static void importPlanets(){
		String[][] data;
		try {
			data = ImportCelestialData.parsePlanetFile();
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
						
			System.out.println("**Importation des planètes terminée**");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setSphericalCoordinates() {
		//TODO why is this there ?
		AstrolabeHomeplanetModel hpm = astrolabeController.getHomeplanetModel();
		
		int[] ymd = new int[] {hpm.getYear(), hpm.getMonth(), hpm.getDay()};
		double[] tmp = this.geocentricRectangularToSphericalCoordinates(JulianDate.toJulian(ymd));
		model.setAlpha(tmp[1]);
		model.setDelta(tmp[2]);
	}

	@Override
	public void updatePosition() {
		setSphericalCoordinates();
	}
	
	public static void updatePositions() {
		for(Planet p : planetList) {
			p.updatePosition();
		}
	}
}
