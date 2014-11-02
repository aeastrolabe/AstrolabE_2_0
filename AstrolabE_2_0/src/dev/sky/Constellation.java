package dev.sky;

import java.util.LinkedList;
import java.util.ListIterator;

import dev.sky.statik.Star;


public class Constellation  {
	
	private static LinkedList<Constellation> constellationList = new LinkedList<Constellation>();
	private LinkedList<CelestialBodyController> starList = new LinkedList<CelestialBodyController>();
	private String name;
	private String abrege;


	public Constellation(String name, String ab) {
		this.name=name;
		abrege=ab;
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
					//TODO what to do here ?
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
	

	

}