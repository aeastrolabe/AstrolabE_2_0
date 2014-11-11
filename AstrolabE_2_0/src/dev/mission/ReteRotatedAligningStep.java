package dev.mission;

import java.util.Date;

import dev.astrolabe.AstrolabeStateModel;

public class ReteRotatedAligningStep extends Step {

	private double objectiveAngle = 0;
	
	public ReteRotatedAligningStep(Date date, double hour) {
		//TODO
		objectiveAngle = 0;
		setInstruction("Align "+date+" and "+hour);
	}
	
	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return (Math.toDegrees(stateModel.getRuleRotation() - stateModel.getReteRotation()) - objectiveAngle + 360)%360 < TOLERANCE_ANGLE;
	}

	@Override
	public String help() {
		return "Left-click on the rule and drag it";
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+" : " + objectiveAngle;
	}

	

}
