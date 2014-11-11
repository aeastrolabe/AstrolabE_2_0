package dev.mission;

import dev.astrolabe.AstrolabeStateModel;

public class RuleRotatedStep extends Step {
	
	public RuleRotatedStep() {
		setInstruction("Turn the rule");
	}

	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return stateModel.getRuleRotation() != 0;
	}

	@Override
	public String help() {
		return "Left-click on the rule";
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : " + "turn rule ";
	}

}
