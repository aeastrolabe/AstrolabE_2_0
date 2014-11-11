package dev.mission;

import dev.astrolabe.AstrolabeStateModel;

public abstract class Step {
	
	protected boolean completed = false;
	
	private boolean helpEnabled = true;
	
	public final static String DEFAULT_HELP_MESSAGE = "No help available";
	
	private String instruction = "DEFAULT INSTRUCTION";
	
	protected final int ALIGN_TO_DATE = 0;
	protected final int ALIGN_TO_HOUR = 1;
	protected final int ALIGN_TO_BODY = 2;
	
	//in degrees
	protected final double TOLERANCE_ANGLE = 2;

	public static String getClassName(String s) {
		String[] t = s.split(".");
		return t[t.length];
	}
	
	public abstract boolean success(AstrolabeStateModel stateModel);
	
	public abstract String help();

	public boolean isHelpEnabled() {
		return helpEnabled;
	}
	
	@Override
	public abstract String toString();
	
	/**
	 * @return completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param sets the step as completed
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
