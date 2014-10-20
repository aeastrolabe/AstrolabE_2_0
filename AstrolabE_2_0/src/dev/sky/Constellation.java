package dev.sky;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.sky.statik.Star;


public class Constellation  {
	
	private static LinkedList<Constellation> constellationList = new LinkedList<Constellation>();
	private LinkedList<CelestialBodyController> starList = new LinkedList<CelestialBodyController>();
	private String name;
	private String abrege;
	public int interesting_stars = 0;
	public static int magnitudeMax = 2; //TODO passer en option
	private static String constellation="([0-9]{2})([ ]*)" +
			"([A-Za-z]*)([ ]*)" +
			"([A-Z])([A-Za-z ]*)" +
			"([a-z])([ ]*)" +
			"([0-9]{2})([h])" +
			"([0-9]{2})([m])" +
			"([ ]*)([+-])" +
			"([0-9]{2})([d])" +
			"([ ]*)(oui|non)";
	
	private static String etoile="([0-9]{2})([ ])([0-9a-z]*)" +
			"([ ]*)([A-Za-z]*)" +
			"([ ])([A-Za-z\\.]*)" +
			"([ ]*)([0-9]{2})([h])" +
			"([0-9]{2})([m])" +
			"([ ]*)([+-])" +
			"([0-9]{2})([d])([0-9]{2})" +
			"(['])([ ]*)([-]{0,1})([ ]*)" +
			"([0-9][\\.][0-9]{2})([ ]*)([0-9]{2})" +
			"([ ])([0-9]{2})";

	public Constellation(String name, String ab) {
		this.name=name;
		abrege=ab;
	}
	

	
	public static void getStars_() {
		System.out.println("**Lecture des donnees en cours**");
		InputStream ips1 = null;
		Reader ipsr1 = null;
		try {
			try {
				URL url = Constellation.class.getResource("Constellation.class");
				URL urlText = new URL(url, "../etoiles.dat");
				ips1 = urlText.openStream();
				ipsr1 = new InputStreamReader(ips1);
			} catch (FileNotFoundException e1) {
				try {
					URL url = Constellation.class.getResource("Constellation.class");
					URL urlText = new URL(url, "../../etoiles.dat");
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
			Pattern pEtoile = Pattern.compile(etoile);
			Pattern pConstellations = Pattern.compile(constellation);
			LinkedList<Star> listeEtoiles = new LinkedList<Star>();
			LinkedList<String> listeConstellations = new LinkedList<String>();
			while ((ligne=br1.readLine())!=null) {
				Matcher m = pEtoile.matcher(ligne);
				Matcher n = pConstellations.matcher(ligne);
				if (n.find()) {
					listeConstellations.add(n.group(6));
					Constellation.getConstellationList().add(new Constellation(n.group(5)+n.group(6)+n.group(7), n.group(3)));
				}
				if (m.find()) {
				    double delta = Integer.parseInt(m.group(15))+Integer.parseInt(m.group(17))/60.;
				    double magnitude = Double.parseDouble(m.group(22));
				    if (m.group(14).equals("-")) {
				    	delta = -delta;
				    }
				    if (m.group(20).equals("-")) {
				    	magnitude = - magnitude;
				    }
				    
				    Star current = new Star(m.group(7/*ou3*/),
				    		m.group(5),
				    		Integer.parseInt(m.group(1)),
				    		magnitude,
				    		(Integer.parseInt(m.group(9))+Integer.parseInt(m.group(11))/60.)*360/24,
				    		delta,
				    		"O", Integer.parseInt(m.group(26)),
				    		m.group(3));
				    Constellation.addStar(current);
				    listeEtoiles.add(current);
				}
			}
			System.out.println("**    Nombre de constellations : "+listeConstellations.size()+"**");
			System.out.println("**    Nombre d'etoiles : "+listeEtoiles.size()+"**");
		ips1.close();
		System.out.println("**Lecture des donnees terminees**");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void getStars() {
		System.out.println("**Lecture des donnees en cours**");
		InputStream ips1 = null;
		Reader ipsr1 = null;
		try {
			try {
				URL url = Constellation.class.getResource("Constellation.class");
				URL urlText = new URL(url, "../../data/stars.txt");
				ips1 = urlText.openStream();
				ipsr1 = new InputStreamReader(ips1);
			} catch (FileNotFoundException e1) {
				try {
					URL url = Constellation.class.getResource("Constellation.class");
					URL urlText = new URL(url, "../../../data/stars.txt");
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
				if (info.length>= 4 && Double.parseDouble(info[3].replaceAll(" ", ""))<magnitudeMax) {
					if (info.length>=8) {
						try {
							Star current = new Star(info[1].replaceAll("(&#160;;|&#160;)",""), info[2] ,
									Integer.parseInt(info[0].replaceAll(" ", "")),
									Double.parseDouble(info[3].replaceAll(" ", "")),
									Double.parseDouble(info[4].replaceAll(" ", ""))*360/24., 
									Double.parseDouble(info[5].replaceAll(" ", "")),
									info[6].replaceAll(" *", ""), 0, info[7].replaceAll("(&#160;;|&#160;)",""));
								addNew(new Constellation(info[2],info[2]));
							    Constellation.addStar(current);
							    listeEtoiles.add(current);
						}
						catch (Exception e) {
							e.printStackTrace();
							for(int i=0;i<8;i++) {
								System.out.println(info[i]);
							}
						}
					}
					else {
						if (info.length>=7) {
						Star current = new Star(info[1].replaceAll("(&#160;;|&#160;)",""), info[2] ,
								Integer.parseInt(info[0].replaceAll(" ", "")),
								Double.parseDouble(info[3].replaceAll(" ", "")),
								Double.parseDouble(info[4].replaceAll(" ", ""))*360/24., 
								Double.parseDouble(info[5].replaceAll(" ", "")),
								info[6].replaceAll(" *", ""), 0, "");
							addNew(new Constellation(info[2],info[2]));
						    Constellation.addStar(current);
						    listeEtoiles.add(current);
						}
					}
				}
			}
			System.out.println("**    Nombre de constellations : "+Constellation.constellationList.size()+"**");
			System.out.println("**    Nombre d'etoiles : "+listeEtoiles.size()+"**");
		ips1.close();
		System.out.println("**Lecture des donnees terminees**");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void addNew (Constellation c) {
		ListIterator<Constellation> iter = constellationList.listIterator();
		boolean needToAdd = true;
		while (iter.hasNext()) {
			needToAdd = needToAdd && (!iter.next().getName().equals (c.getName()));
		}
		if (needToAdd) {
			constellationList.add(c);
		}
	}

	public void sort() {
		int n=starList.size();
		LinkedList<CelestialBodyController> result = new LinkedList<CelestialBodyController>();
		for (int i=0;i<n;i++) {
			CelestialBodyController s = starList.getFirst();
			ListIterator<CelestialBodyController> iter = starList.listIterator();
			while (iter.hasNext()) {
				CelestialBodyController s2=iter.next();
				if (s2.getModel().getMagnitude()<s.getModel().getMagnitude()){
					s=s2;
				}
			}
			result.add(s);
			starList.remove(s);
		}
		starList=result;
	}
	
	public void addStarToThis(Star s) {
		starList.add(s);
		s.setConstellation(this);
	}
	
	
	public static void addStar(Star s) {
		String c = s.getConstellationName();
		ListIterator<Constellation> iter = constellationList.listIterator();
		while (iter.hasNext()) {
			Constellation cons = iter.next();
			//System.out.println(cons.name);
			if (c.equals(cons.abrege)) {
				cons.addStarToThis(s);
				if (!s.model.getName().contains("#no_name#")) {
					cons.interesting_stars++;
				}
				break;
			}
		}
	}
	
	public LinkedList<CelestialBodyController> getStarList(){
		return starList;
	}
	
	public static LinkedList<Constellation> getConstellationList() {
		return constellationList;
	}
	
	public String getName() {
		return name;
	}
	
	static Pattern ConstellationP = 
			Pattern.compile("(^<li><a href=\")(/wiki/Liste_d%.*)(\" title.*\">)(.*)(</a>.*)");
	static Pattern StarP =
			Pattern.compile("^(<td)( style=\"text-align: left;\")?(>)(.*)(</td>)$");
	static Pattern star_beg = Pattern.compile("<tr>");
	static Pattern star_end = Pattern.compile("</tr>");
	static Matcher ConstellationM;
	static Matcher star;
	static Matcher bounds;
	
	
	public static LinkedList<String[]> getCode(String url, boolean lookForStar) {
		LinkedList<String[]> code = new LinkedList<String[]>();
		boolean ret=false;
		int field = 0;
		String[] info = null;
		if(urlExists(url)) {
			Reader r = null;
			BufferedReader in = null;
			try {
				URL site = new URL(url);
				r = new InputStreamReader(site.openStream(),"UTF-8");
				in = new BufferedReader(r);
				String inputLine;
				if (!lookForStar) {
					while ((inputLine = in.readLine()) != null){
						ConstellationM = ConstellationP.matcher(inputLine);
						if (ConstellationM.find()) {
							code.add(new String[] {
									ConstellationM.group(2), 
									ConstellationM.group(4)
									});
						}
					}	
				}
				else {
					while ((inputLine = in.readLine()) != null){
						if (star_beg.matcher(inputLine).find()) {
							ret = true;
							info = new String[13];
						}
						if (star_end.matcher(inputLine).find()) {
							ret=false;
							field = 0;
							if (info[0]!=null)
							code.add(info);
						}
						if (ret) {
							star = StarP.matcher(inputLine);
							if(star.find()) {
								info[field] = star.group(4);
								field++;
							}
						}
						
					}
					
				}
				in.close();
			}
	        catch (IOException ex){ System.err.println("Erreur dans l'ouverture de l'URL : " + ex); }
	        finally {
	        	try { in.close(); }
	        	catch (IOException ex) { System.err.println("Erreur dans la fermeture du buffer : " + ex); }
	        }
		}
	    else { System.err.println("Le site n'existe pas !"); }
	    return code;
	}
	
	public static boolean urlExists(String url)
	   {
	        try {
	            URL site = new URL(url);
	            try {
	                site.openStream();
	                return true;
	            } catch (IOException ex) {
	                return false;
	            }
	        } catch (MalformedURLException ex) {
	            return false;
	        }
	   }
	
	
	
	
	
	
	
	
	
	 

}