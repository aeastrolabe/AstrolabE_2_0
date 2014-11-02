package dev.mission;

import dev.astrolabe.AstrolabeStateModel;
import dev.sky.dynamik.heliocentric.Planet;

public class PlanetSelectedStep extends Step {
	
	Planet planetToSelect;

	public PlanetSelectedStep(Planet p) {
		planetToSelect = p;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + planetToSelect.getModel().getName();
	}
	
	public boolean isDesiredPlanetSelected(AstrolabeStateModel astrolabeStateModel) {
		return planetToSelect.getModel().getName().equals(astrolabeStateModel.getSelectedCelestialBodyName());
	}
}
