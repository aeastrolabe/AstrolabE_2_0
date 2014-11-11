package dev.mission;

import dev.astrolabe.AstrolabeStateModel;

public class SunDisplayedStep extends Step {

	private boolean objective;
	
	public SunDisplayedStep(boolean objective) {
		this.objective = objective;
		setInstruction("Display the sun");
	}
	
	public boolean toBeShown() {
		return objective;
	}
	
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + "show sun : " + objective;
	}

	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return completed || stateModel.isSunDisplayed();
	}

	@Override
	public String help() {
		return "Click on the Display Sun button";
	}
}
