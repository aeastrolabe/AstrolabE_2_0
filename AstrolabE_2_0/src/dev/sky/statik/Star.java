package dev.sky.statik;

import java.util.ListIterator;

import dev.io.ImportImage;
import dev.sky.CelestialBodyController;
import dev.sky.Constellation;

public class Star extends StaticCelestialBody {
	//ATTRIBUTS
	
	private String misc_info;
	private int id;
	private char colorCode;
	
	//CONSTRUCTEUR
	
	public Star(String name, String constellationName, int id, double magnitude, double alpha, double delta, String colorCode, int lien, String misc_info) {
		super(name,constellationName,magnitude,alpha,delta);
		this.id = id;
		this.misc_info = misc_info;
		this.setColorCode(colorCode.charAt(0));
		
		try {
			view.image = ImportImage.read("stars/star"+this.colorCode+".png");
		}
		catch (Exception e) {
			view.image = ImportImage.read("stars/starD.png");
		}
	}
	
	public Star(String name) {
		super(name,"",0,0,0);
		this.id = 0;
		this.misc_info = "";
		this.colorCode = 'a';
		view.image = null;
	}

	//METHODES
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  @return the color code
	 */
	public char getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode the colorCode to set
	 */
	public void setColorCode(char colorCode) {
		this.colorCode = colorCode;
	}
	
	/**
	 * @return the miscellanious information attached
	 */
	public String getMiscInfo() {
		return misc_info;
	}
		
	//methodes de dessin des Objets

	public static void setVisibleBelowMagnitude(double m) {
		ListIterator<Constellation> iterc = Constellation.getConstellationList().listIterator();
		while (iterc.hasNext()){
			Constellation c = iterc.next();
			ListIterator<CelestialBodyController> iters = c.getStarList().listIterator();
			while (iters.hasNext()){
				CelestialBodyController o = (Star) iters.next();
				if (!o.getModel().getName().contains("#no_name#")) {
					if (o.getModel().getMagnitude()<=m) {
						o.setDisplayed(true);
					}
					else {
						o.setDisplayed(false);
					}
				}
			}
		}
	}
	
	public static void removeAllStars() {
		ListIterator<Constellation> iterc = Constellation.getConstellationList().listIterator();
		while (iterc.hasNext()){
			Constellation c = iterc.next();
			ListIterator<CelestialBodyController> iters = c.getStarList().listIterator();
			while (iters.hasNext()){
				Star o = (Star) iters.next();
				getAstrolabeController().getView().remove(o.view);
			}
		}
	}
	

	
	public boolean getSurbrillance() {
		return surbrillance;
	}
	
	
//	public static double[] calculateur(Star o) {
//        double latitude = astrolabeController.getLocalisationModel().getLatitude()*Math.PI/180;
//		double longitude = astrolabeController.getLocalisationModel().getLongitude();
//		double azimuth = 0,rightascension = 0,declination = 0, altitude=0;
//		int second = 0;
//		int minute = 0;
//		int hour = 0;
//		int day = 1;
//		int month = 1;
//		int year = 2012;
//		
//		double t = (JulianDate.toJulian(new int[] {year,month,day})-JulianDate.toJulian(new int[] {2000,1,1}))/365.25;
//		double M = 6.240060119+6.283019551716*t;
//		M = (Math.PI*2+M)%(Math.PI*2);
//		//Equation du temps en minutes p460 GNOMONIQUE
//		double EoT = 7.362*Math.sin(M)-0.144*Math.cos(M)+8.944*Math.sin(2*M)+4.299*Math.cos(2*M)+0.288*Math.sin(3*M)+0.133*Math.cos(3*M)+0.139*Math.sin(4*M)
//				+0.171*Math.cos(4*M)+0.009*Math.sin(5*M)+0.011*Math.cos(5*M)+0.001*Math.sin(6*M)+0.006*Math.cos(6*M)-0.00268*t*Math.sin(2*M)+0.00538*t*Math.cos(2*M);
//		double TS = (hour+minute/60.+second/60./60)-EoT/60-longitude/360.*24;
//		
//		declination = o.model.getDelta()*Math.PI/180;
//		rightascension=o.model.getAlpha()*Math.PI/180;
//
//		double hourangle = TS/24.*2*Math.PI - rightascension;
//		
//		// **Recuperation de l'ascension droite et de la declinaison** //
//				
//				// *********************************************************** //
//				
//				// **Calcul de l'azimuth et de la hauteur** //
//				double sinh = Math.cos(latitude)*Math.cos(declination)*Math.cos(hourangle)+Math.sin(latitude)*Math.sin(declination);
//				double coshsinZ = Math.cos(declination)*Math.sin(hourangle);
//				double coshcosZ = Math.sin(latitude)*Math.cos(declination)*Math.cos(hourangle)-Math.cos(latitude)*Math.sin(declination);
//				
//				altitude = Math.PI/2-Math.acos(sinh);
//				
//				if (coshsinZ>=0) {
//					azimuth = Math.acos(coshcosZ/(Math.sqrt(coshcosZ*coshcosZ+coshsinZ*coshsinZ)));
//				}
//				else {
//					azimuth = Math.PI*2 -  Math.acos(coshcosZ/(Math.sqrt(coshcosZ*coshcosZ+coshsinZ*coshsinZ)));
//				}
//				
//				return new double[] {latitude,rightascension,declination,azimuth/Math.PI*180,altitude/Math.PI*180,hourangle/Math.PI*180};
//
//	}

	

}

