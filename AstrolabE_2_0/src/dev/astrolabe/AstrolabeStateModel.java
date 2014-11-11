package dev.astrolabe;

import java.awt.Point;

import dev.sky.CelestialBodyModel;

public class AstrolabeStateModel {
	
	/*
	 * Attributs de dessin
	 */
	
	private Point astrolabeCenter = new Point();
	private boolean astrolabeCenterInit = false;
	
	private double astrolabeScale = 1;
	private final static double DEFAULT_SCALING = 1;
	public final static double SCALING_RATE = 0.9;
	
	private double reteRotation = 0;
	
	private double ruleRotation = 0;
	
	public final double LIMBE_PADDING = 30;
	public final double TYMPAN_PADDING = 20;
	
	private boolean sunDisplayed = false;
	
	private CelestialBodyModel selectedCelestialBody;
	private CelestialBodyModel highlightedCelestialBody;
	
	public Point getAstrolabeCenter() {
		return (Point) astrolabeCenter.clone();
	}
	
	public void setAstrolabeCenter(Point p) {
		astrolabeCenter = (Point) p.clone();
	}
	
	public void setAstrolabeCenterInit(boolean b) {
		astrolabeCenterInit = b;
	}

	public boolean getAstrolabeCenterInit() {
		return astrolabeCenterInit;
	}
	
	public void scaleAstrolabeBy(double r) {
		astrolabeScale *= r;
	}
	
	public double getAstrolabeScale() {
		return astrolabeScale;
	}
	
	public void resetAstrolabeScale() {
		astrolabeScale = DEFAULT_SCALING;
	}

	public void setReteRotation(double r) {
		reteRotation = r;
	}
	
	public double getReteRotation() {
		return reteRotation;
	}

	/**
	 * @return the ruleRotation
	 */
	public double getRuleRotation() {
		return ruleRotation;
	}

	/**
	 * @param ruleRotation the ruleRotation to set
	 */
	public void setRuleRotation(double ruleRotation) {
		this.ruleRotation = ruleRotation;
	}

	/**
	 * @return the sunDisplayed
	 */
	public boolean isSunDisplayed() {
		return sunDisplayed;
	}

	/**
	 * @param sunDisplayed the sunDisplayed to set
	 */
	public void setSunDisplayed(boolean sunDisplayed) {
		this.sunDisplayed = sunDisplayed;
	}

	/**
	 * @return the selectedCelestialBody
	 */
	public CelestialBodyModel getSelectedCelestialBody() {
		return selectedCelestialBody;
	}

	/**
	 * @param selectedCelestialBody the selectedCelestialBody to set
	 */
	public void setSelectedCelestialBody(CelestialBodyModel selectedCelestialBody) {
		this.selectedCelestialBody = selectedCelestialBody;
	}
	
	public CelestialBodyModel getHighlightedCelestialBody() {
		return highlightedCelestialBody;
	}

	public void setHighlightedCelestialBody(CelestialBodyModel highlightedCelestialBody) {
		this.highlightedCelestialBody = highlightedCelestialBody;
	}

}
