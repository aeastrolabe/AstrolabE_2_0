package dev.mission;

import dev.astrolabe.AstrolabeStateModel;
import dev.sky.dynamik.heliocentric.Planet;

public class PlanetSelectedStep extends Step {
	
	Planet planetToSelect;

	public PlanetSelectedStep(Planet p) {
		if (p == null) {
			throw new Error("Null planet declared in step");
		}
		planetToSelect = p;
		instruction = "Select planet " + p.getModel().getName();
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + planetToSelect.getModel().getName();
	}
	
	public boolean isDesiredPlanetSelected(AstrolabeStateModel astrolabeStateModel) {
		return planetToSelect.getModel().getName().equals(astrolabeStateModel.getSelectedCelestialBody().getName());
	}

	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return completed || planetToSelect.getModel().getName().equals(stateModel.getSelectedCelestialBody().getName());
	}
	
	@Override
	public String help() {
		return "Highlight the planets to find " + planetToSelect.getModel().getName() + " and click on it when found.";
	}
}
