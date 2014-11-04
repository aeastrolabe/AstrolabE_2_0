package dev.sky.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import dev.io.ImportData;
import dev.sky.Constellation;
import dev.sky.statik.Star;

public class ImportCelestialData {

	//Legacy methods to import stars.txt
	
//	private static String constellation="([0-9]{2})([ ]*)" +
//			"([A-Za-z]*)([ ]*)" +
//			"([A-Z])([A-Za-z ]*)" +
//			"([a-z])([ ]*)" +
//			"([0-9]{2})([h])" +
//			"([0-9]{2})([m])" +
//			"([ ]*)([+-])" +
//			"([0-9]{2})([d])" +
//			"([ ]*)(oui|non)";
//	
//	private static String etoile="([0-9]{2})([ ])([0-9a-z]*)" +
//			"([ ]*)([A-Za-z]*)" +
//			"([ ])([A-Za-z\\.]*)" +
//			"([ ]*)([0-9]{2})([h])" +
//			"([0-9]{2})([m])" +
//			"([ ]*)([+-])" +
//			"([0-9]{2})([d])([0-9]{2})" +
//			"(['])([ ]*)([-]{0,1})([ ]*)" +
//			"([0-9][\\.][0-9]{2})([ ]*)([0-9]{2})" +
//			"([ ])([0-9]{2})";
//	
//	private final static double maxMagnitudeAtImport = 2;
	
//	public static void getStars_() {
//		System.out.println("**Lecture des donnees en cours**");
//		InputStream ips1 = null;
//		Reader ipsr1 = null;
//		try {
//			try {
//				URL url = Constellation.class.getResource("Constellation.class");
//				URL urlText = new URL(url, "../etoiles.dat");
//				ips1 = urlText.openStream();
//				ipsr1 = new InputStreamReader(ips1);
//			} catch (FileNotFoundException e1) {
//				try {
//					URL url = Constellation.class.getResource("Constellation.class");
//					URL urlText = new URL(url, "../../etoiles.dat");
//					ips1=urlText.openStream();
//					ipsr1 = new InputStreamReader(ips1,"UTF-8");
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				}
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//
//			BufferedReader br1=new BufferedReader(ipsr1);
//			String ligne;
//			Pattern pEtoile = Pattern.compile(etoile);
//			Pattern pConstellations = Pattern.compile(constellation);
//			LinkedList<Star> listeEtoiles = new LinkedList<Star>();
//			LinkedList<String> listeConstellations = new LinkedList<String>();
//			while ((ligne=br1.readLine())!=null) {
//				Matcher m = pEtoile.matcher(ligne);
//				Matcher n = pConstellations.matcher(ligne);
//				if (n.find()) {
//					listeConstellations.add(n.group(6));
//					Constellation.getConstellationList().add(new Constellation(n.group(5)+n.group(6)+n.group(7), n.group(3)));
//				}
//				if (m.find()) {
//				    double delta = Integer.parseInt(m.group(15))+Integer.parseInt(m.group(17))/60.;
//				    double magnitude = Double.parseDouble(m.group(22));
//				    if (m.group(14).equals("-")) {
//				    	delta = -delta;
//				    }
//				    if (m.group(20).equals("-")) {
//				    	magnitude = - magnitude;
//				    }
//				    
//				    Star current = new Star(m.group(7/*ou3*/).replaceAll("\\s+$", ""),
//				    		m.group(5),
//				    		Integer.parseInt(m.group(1)),
//				    		magnitude,
//				    		(Integer.parseInt(m.group(9))+Integer.parseInt(m.group(11))/60.)*360/24,
//				    		delta,
//				    		"O", Integer.parseInt(m.group(26)),
//				    		m.group(3));
//				    Constellation.addStar(current);
//				    listeEtoiles.add(current);
//				}
//			}
//			System.out.println("**    Nombre de constellations : "+listeConstellations.size()+"**");
//			System.out.println("**    Nombre d'etoiles : "+listeEtoiles.size()+"**");
//		ips1.close();
//		System.out.println("**Lecture des donnees terminees**");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//	}
//	
//	
//	public static void getStars() {
//		System.out.println("**Lecture des donnees en cours**");
//		InputStream ips1 = null;
//		Reader ipsr1 = null;
//		try {
//			try {
//				URL url = Constellation.class.getResource("Constellation.class");
//				URL urlText = new URL(url, "../../data/stars.txt");
//				ips1 = urlText.openStream();
//				ipsr1 = new InputStreamReader(ips1);
//			} catch (FileNotFoundException e1) {
//				try {
//					URL url = Constellation.class.getResource("Constellation.class");
//					URL urlText = new URL(url, "../../../data/stars.txt");
//					ips1=urlText.openStream();
//					ipsr1 = new InputStreamReader(ips1,"UTF-8");
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				}
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//
//			BufferedReader br1=new BufferedReader(ipsr1);
//			String ligne;
//
//			LinkedList<Star> listeEtoiles = new LinkedList<Star>();
//			@SuppressWarnings("unused")
//			LinkedList<String> listeConstellations = new LinkedList<String>();
//			while ((ligne=br1.readLine())!=null) {
//				String[] info = ligne.split("@");
//				if (info.length>= 4 && Double.parseDouble(info[3].replaceAll(" ", "")) < maxMagnitudeAtImport) {
//					if (info.length>=8) {
//						try {
//							Star current = new Star(info[1].replaceAll("(&#160;;|&#160;)",""), info[2] ,
//									Integer.parseInt(info[0].replaceAll(" ", "")),
//									Double.parseDouble(info[3].replaceAll(" ", "")),
//									Double.parseDouble(info[4].replaceAll(" ", ""))*360/24., 
//									Double.parseDouble(info[5].replaceAll(" ", "")),
//									info[6].replaceAll(" *", ""), 0, info[7].replaceAll("(&#160;;|&#160;)",""));
//							Constellation.addNew(new Constellation(info[2],info[2]));
//							    Constellation.addStar(current);
//							    listeEtoiles.add(current);
//						}
//						catch (Exception e) {
//							e.printStackTrace();
//							for(int i=0;i<8;i++) {
//								System.out.println(info[i]);
//							}
//						}
//					}
//					else {
//						if (info.length>=7) {
//						Star current = new Star(info[1].replaceAll("(&#160;;|&#160;)",""), info[2] ,
//								Integer.parseInt(info[0].replaceAll(" ", "")),
//								Double.parseDouble(info[3].replaceAll(" ", "")),
//								Double.parseDouble(info[4].replaceAll(" ", ""))*360/24., 
//								Double.parseDouble(info[5].replaceAll(" ", "")),
//								info[6].replaceAll(" *", ""), 0, "");
//						Constellation.addNew(new Constellation(info[2],info[2]));
//						    Constellation.addStar(current);
//						    listeEtoiles.add(current);
//						}
//					}
//				}
//			}
//			System.out.println("**    Nombre de constellations : "+Constellation.getConstellationList().size()+"**");
//			System.out.println("**    Nombre d'etoiles : "+listeEtoiles.size()+"**");
//		ips1.close();
//		System.out.println("**Lecture des donnees terminees**");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//	}
	
	public static void getStarsShort() {
		System.out.println("**Lecture des donnees en cours**");
		InputStream ips1 = null;
		Reader ipsr1 = null;
		try {
			try {
				URL url = Constellation.class.getResource("Constellation.class");
				URL urlText = new URL(url, "../../data/starsAppleShort.txt");
				ips1 = urlText.openStream();
				ipsr1 = new InputStreamReader(ips1);
			} catch (FileNotFoundException e1) {
				try {
					URL url = Constellation.class.getResource("Constellation.class");
					URL urlText = new URL(url, "../../../data/starsAppleShort.txt");
					ips1=urlText.openStream();
					ipsr1 = new InputStreamReader(ips1,"UTF-8");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		
			BufferedReader br1=new BufferedReader(ipsr1);
			String ligne;
			
			LinkedList<Star> listeEtoiles = new LinkedList<Star>();
			@SuppressWarnings("unused")
			LinkedList<String> listeConstellations = new LinkedList<String>();
			
			while ((ligne=br1.readLine())!=null) {
				String[] info = ligne.split("@");
				
				Star current = new Star(info[1].replaceAll("(&#160;;|&#160;)",""),
						info[0],
						(int) (Math.random()*Integer.MAX_VALUE),
						Double.parseDouble(info[2].replaceAll(" ", "")),
						Double.parseDouble(info[3].replaceAll(" ", "")), 
						Double.parseDouble(info[4].replaceAll(" ", "")),
						info[5].replaceAll(" *", ""), 0, (info[6] == null ? "" : info[6]));
				
				Constellation.addNew(new Constellation(info[0],info[0]));
			    Constellation.addStar(current);
			    listeEtoiles.add(current);
			}
		System.out.println("**    Nombre de constellations : "+Constellation.getConstellationList().size()+"**");
		System.out.println("**    Nombre d'etoiles : "+listeEtoiles.size()+"**");
		ips1.close();
		System.out.println("**Lecture des donnees terminees**");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] parsePlanetFile() throws IOException {
		String[][] result = new String[8-1][13];
		
		InputStream ips1 = null;
		try {
			URL url = ImportData.class.getResource("ImportData.class");
			URL urlText = new URL(url, "../../data/planets.txt");
			ips1 = urlText.openStream();
		} catch (FileNotFoundException e1) {
			try {
				URL url = ImportData.class.getResource("ImportData.class");
				URL urlText = new URL(url, "../../../data/planets.txt");
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
	
}
