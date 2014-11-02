package dev.mission;

import java.util.LinkedList;

import dev.sky.dynamik.heliocentric.Planet;
import dev.sky.statik.Star;

public class Mission {

	private LinkedList<Step> orderedSteps;
	private LinkedList<Step> unorderedSteps;
	
	private int currentStep = 0;
	
	public Mission() {
		orderedSteps = new LinkedList<>();
		unorderedSteps = new LinkedList<>();
	}
	
	public LinkedList<Step> getOrderedSteps() {
		return orderedSteps;
	}
	
	public LinkedList<Step> getUnorderedSteps() {
		return unorderedSteps;
	}
	
	public void addOrderedStep(Step s) {
		orderedSteps.add(s);
	}
	
	public void addUnorderedStep(Step s) {
		unorderedSteps.add(s);
	}
	
	public String toString() {
		String r = "";
		Step s;
		
		r += "Ordered Steps :\n";
		for(Object o : orderedSteps.toArray()) {
			s = (Step) o;
			r += s.toString() + "\n";
		}
		
		r += "Unordered Steps :\n";
		for(Object o : unorderedSteps.toArray()) {
			s = (Step) o;
			r += s.toString() + "\n";
		}
		return r;
	}
	
	
	
	public static void main(String[] args) {
		Mission m = new Mission();
		
		m.addOrderedStep(new SunDisplayedStep(true));
		m.addOrderedStep(new PlanetSelectedStep(new Planet("Earth")));
		
		m.addUnorderedStep(new StarSelectedStep(new Star("Death Star")));
		
		System.out.println(m);
	}
}
