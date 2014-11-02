package dev.mission;

public class SunDisplayedStep extends Step {

	private boolean objective;
	
	public SunDisplayedStep(boolean objective) {
		this.objective = objective;
	}
	
	public boolean toBeShown() {
		return objective;
	}
	
	
	public String toString() {
		return this.getClass().getSimpleName() + " : " + "show sun : " + objective;
	}
}
