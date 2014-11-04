package dev.mission;

import java.util.LinkedList;
import java.util.ListIterator;

import dev.astrolabe.AstrolabeStateModel;
import dev.sky.dynamik.heliocentric.Planet;
import dev.sky.statik.Star;

public class Mission {

	private LinkedList<Step> orderedSteps;
	private LinkedList<Step> unorderedSteps;
	
	private int orderedStepsCount;
	private int unorderedStepsCount;
		
	public Mission() {
		orderedSteps = new LinkedList<>();
		unorderedSteps = new LinkedList<>();
	}
	
	/**
	 * @return the nOrderedSteps
	 */
	public int getOrderedStepsCount() {
		return orderedStepsCount;
	}

	/**
	 * @return the nUnorderedSteps
	 */
	public int getUnorderedStepsCount() {
		return unorderedStepsCount;
	}
	
	public LinkedList<Step> getOrderedSteps() {
		return orderedSteps;
	}
	
	public LinkedList<Step> getUnorderedSteps() {
		return unorderedSteps;
	}
	
	public void addOrderedStep(Step s) {
		orderedSteps.add(s);
		orderedStepsCount++;
	}
	
	public void addUnorderedStep(Step s) {
		unorderedSteps.add(s);
		unorderedStepsCount++;
	}
	
	public int getCurrentStep() {
		Step s;
		int i = 0;
		for(Object o : orderedSteps.toArray()) {
			s = (Step) o;
			if (s.isCompleted()) {
				i++;
			}
			else {
				break;
			}
		}

		return i;
	}
	
	
	public String toString() {
		String r = "";
		Step s;
		
		r += "Mission completed : " + checkCompletion() + "\n";
		
		r += "Ordered Steps : " + checkAllOrderedStepCompletion() + "\n";
		for(Object o : orderedSteps.toArray()) {
			s = (Step) o;
			r += "   ";
			r += s.isCompleted() + " -- " + s.toString() + "\n";
		}
		
		r += "Unordered Steps : "+checkAllUnorderedStepCompletion() + "\n";
		for(Object o : unorderedSteps.toArray()) {
			s = (Step) o;
			r += "   ";
			r += s.isCompleted() + " -- " + s.toString() + "\n";
		}
		return r;
	}
	
	public boolean checkCompletion() {
		return checkAllOrderedStepCompletion() && checkAllUnorderedStepCompletion();
	}
	
	public boolean checkCurrentOrderedStepCompletion(AstrolabeStateModel stateModel) {
		boolean test = orderedSteps.get(getCurrentStep()).success(stateModel);
		if (test) {
			orderedSteps.get(getCurrentStep()).setCompleted();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkAllOrderedStepCompletion() {
		Step s;
		for(Object o : orderedSteps.toArray()) {
			s = (Step) o;
			if (!s.isCompleted()) {
				return false;
			}
		}

		return true;
	}
	
	public boolean checkUnorderedStepCompletion(AstrolabeStateModel stateModel) {
		ListIterator<Step> iter = unorderedSteps.listIterator();
		Step test;
		boolean allCompleted = true;
		while(iter.hasNext()) {
			test = iter.next();
			if (test.success(stateModel)) {
				test.setCompleted();
			}
			else {
				allCompleted = false;
			}
		}
		return allCompleted;
	}
	
	public boolean checkAllUnorderedStepCompletion() {
		Step s;
		for(Object o : unorderedSteps.toArray()) {
			s = (Step) o;
			if (!s.isCompleted()) {
				return false;
			}
		}

		return true;
	}
	
	public static void main(String[] args) {
		Mission m = new Mission();
		
		m.addOrderedStep(new SunDisplayedStep(true));
		m.addOrderedStep(new PlanetSelectedStep(new Planet("Earth")));
		
		m.addUnorderedStep(new StarSelectedStep(new Star("Death Star")));
		
		System.out.println(m);
		
		m.orderedSteps.getFirst().setCompleted();
		
		System.out.println(m);
		System.out.println(m.getCurrentStep());
		
		m.orderedSteps.get(1).setCompleted();
		
		System.out.println(m);
		System.out.println(m.getCurrentStep());
		
		m.unorderedSteps.getFirst().setCompleted();
		
		System.out.println(m);
	}


}
