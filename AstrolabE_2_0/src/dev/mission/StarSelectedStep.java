package dev.mission;

import dev.astrolabe.AstrolabeStateModel;
import dev.sky.statik.Star;

public class StarSelectedStep extends Step {

	public Star starToSelect;
	
	public StarSelectedStep(Star s) {
		if (s == null) {
			throw new Error("Null star declared in step");
		}
		starToSelect = s;
		setInstruction("Select star " + s.getModel().getName());
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + starToSelect.getModel().getName();
	}
	
	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return completed || 
				(stateModel.getSelectedCelestialBody() != null &&
				starToSelect.getModel().getName().equals(stateModel.getSelectedCelestialBody().getName()));
		
	}

	@Override
	public String help() {
		return "Highlight the stars to find " + starToSelect.getModel().getName() + " and click on it when found.";
	}
	
}
