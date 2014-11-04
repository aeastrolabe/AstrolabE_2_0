package dev.mission;

import dev.astrolabe.AstrolabeStateModel;

public abstract class Step {
	
	protected boolean completed = false;
	
	protected String instruction = "DEFAULT INSTRUCTION";

	public static String getClassName(String s) {
		String[] t = s.split(".");
		return t[t.length];
	}
	
	public abstract boolean success(AstrolabeStateModel stateModel);

	/**
	 * @return completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param sets the stepas completed
	 */
	public void setCompleted() {
		this.completed = true;
	}
	
	public String getInstruction() {
		return instruction;
	}
	
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
