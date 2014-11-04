package dev.sky.statik;

import dev.sky.CelestialBodyController;
import dev.sky.Constellation;

public abstract class StaticCelestialBody extends CelestialBodyController {


	public StaticCelestialBody(String name, String constellationName, double magnitude, double alpha, double delta) {
		super(name);
		model.setAlpha(alpha);
		model.setDelta(delta);
		model.setMagnitude(magnitude);
		model.setConstellation(constellationName);
		model.setType(this.getClass().getSimpleName());
	}
	
	
	public void setConstellation(Constellation c) {
		model.setConstellation(c.getName());
	}
}
