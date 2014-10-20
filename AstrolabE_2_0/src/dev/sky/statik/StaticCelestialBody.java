package dev.sky.statik;

import dev.sky.CelestialBodyController;
import dev.sky.Constellation;

public abstract class StaticCelestialBody extends CelestialBodyController {
	
	protected String constellationName;

	public StaticCelestialBody(String name, String constellationName, double magnitude, double alpha, double delta) {
		super(name);
		this.constellationName = constellationName;
		model.setAlpha(alpha);
		model.setDelta(delta);
		model.setMagnitude(magnitude);
	}
	
	public String getConstellationName() {
		return constellationName;
	}
	
	
	public void setConstellation(Constellation c) {
		constellationName=c.getName();
	}
}
