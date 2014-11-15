package dev.mission;

import dev.astrolabe.AstrolabeStateModel;

public class ReteRotatedStep extends Step {

	public ReteRotatedStep() {
		setInstruction("Turn the rete");
	}

	@Override
	public boolean success(AstrolabeStateModel stateModel) {
		return stateModel.getReteRotation() != 0;
	}

	@Override
	public String help() {
		return "Left-click on the rete and drag it";
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : " + "turn rete ";
	}

}
