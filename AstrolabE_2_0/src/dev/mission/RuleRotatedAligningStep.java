package dev.mission;

import java.util.Date;

import dev.astrolabe.AstrolabeStateModel;
import dev.sky.CelestialBodyController;
import dev.utils.Log;

public class RuleRotatedAligningStep extends Step {

	private double objectiveAngle = 0;

	private int alignTarget;

	//TODO consider using
	//private Date objectiveDate;

	public RuleRotatedAligningStep(Date date) {
		//TODO
		this.alignTarget = ALIGN_TO_DATE;
		setInstruction("Align rule and" + date);
		objectiveAngle = 0;
	}

	public RuleRotatedAligningStep(double hour) {
		//TODO
		this.alignTarget = ALIGN_TO_HOUR;
		setInstruction("Align rule and" + hour); //TODO
		objectiveAngle = 0;
	}

	public RuleRotatedAligningStep(CelestialBodyController body) {
		this.alignTarget = ALIGN_TO_BODY;
		setInstruction("Align rule and" + body.getModel().getName());
		objectiveAngle = body.getModel().getAlpha();
	}

	//TODO check correctness
	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		Log.log(objectiveAngle+"", this);
		
		switch (alignTarget) {
		case 0:
			return (Math.toDegrees(stateModel.getRuleRotation() - stateModel.getReteRotation()) - objectiveAngle + 360)%360 < TOLERANCE_ANGLE;
		case 1:
			return (Math.toDegrees(stateModel.getRuleRotation()) - objectiveAngle + 360)%360 < TOLERANCE_ANGLE;
		case 2:
			return (Math.toDegrees(stateModel.getRuleRotation() - stateModel.getReteRotation()) - objectiveAngle + 360)%360 < TOLERANCE_ANGLE;
		default:
			System.err.println(this.getClass().getSimpleName() + "This should never happen");
			return false;
		}
	}

	@Override
	public String help() {
		switch (alignTarget) {
		case 0:
			return "Left-click on the rule and drag it";
		case 1:
			return "Left-click on the rule and drag it";
		case 2:
			return "Left-click on the rule and drag it";
		default:
			System.err.println(this.getClass().getSimpleName() + "This should never happen");
			return DEFAULT_HELP_MESSAGE;
		}

	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+" : " + objectiveAngle;
	}

}
