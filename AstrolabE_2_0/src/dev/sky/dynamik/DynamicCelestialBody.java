package dev.sky.dynamik;

import dev.sky.CelestialBodyController;

public abstract class DynamicCelestialBody extends CelestialBodyController {

	public DynamicCelestialBody(String name) {
		super(name);
	}
	
	public abstract void updatePosition();

}
