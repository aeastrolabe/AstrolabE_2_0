package dev.mission;

import dev.sky.statik.Star;

public class StarSelectedStep extends Step {

	Star starToSelect;
	
	public StarSelectedStep(Star s) {
		starToSelect = s;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + starToSelect.getModel().getName();
	}
	
}
