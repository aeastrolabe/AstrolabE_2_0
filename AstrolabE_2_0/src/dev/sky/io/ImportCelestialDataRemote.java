package dev.sky.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportCelestialDataRemote {
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
						System.out.println(inputLine);
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
						System.out.println(inputLine);
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
	
	public static boolean urlExists(String url) {
		try {
	        URL site = new URL(url);
	        try {
	        	site.openStream();
	            return true;
	        } catch (IOException ex) {
	        	ex.printStackTrace();
	            return false;
	        }
	   } catch (MalformedURLException ex) {
		   ex.printStackTrace();
	        return false;
	   }
	}
}
