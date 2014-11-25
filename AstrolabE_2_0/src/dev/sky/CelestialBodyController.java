package dev.sky;

import dev.astrolabe.AstrolabeController;

public abstract class CelestialBodyController {
	
	protected CelestialBodyView view;
	protected CelestialBodyModel model;
	
	protected boolean surbrillance = false;
	protected boolean displayed;
	
	//does this need to be static ?... Yes !
	protected static AstrolabeController astrolabeController;
	
	private static StaticCelestialBodyViewListener listener = new StaticCelestialBodyViewListener();

	
	public CelestialBodyController(String name) {
		model = new CelestialBodyModel(name);
		view = new CelestialBodyView(this);
		
		view.addMouseListener(listener);
		
		//this is called only once in practice
		StaticCelestialBodyViewListener.setAstrolabeController(getAstrolabeController());
		
		displayed = true;
	}
	
	public CelestialBodyView getView() {
		return view;
	}
	
	public CelestialBodyModel getModel() {
		return model;
	}
	
	
	public void addToAstrolabeView() {
		CelestialBodyController.getAstrolabeController().getView().add(view,0);
	}
	
	public boolean isDisplayed() {
		return displayed;
	}
	
	public void setDisplayed(boolean b) {
		displayed = b;
	}
	
	public void changeDisplayed() {
		displayed=!displayed;
	}
	
	//TODO correct this method
	public void checkSurbrillance(double x, double y) {
//		double t = //panelAstrolab.getThetaOstensor()+panelAstrolab.getThetaOstensorTotal();
//					-astrolabeController.getThetaDifference();
//		double d = Math.abs(-Math.sin(t)*x+Math.cos(t)*y+0);
//		if (d<view.size/4) {
//			surbrillance=true;
//		}
//		else {
//			surbrillance=false;
//		}
		surbrillance = false;
	}
	
	
	
	public static double rev(double x) {
		return x-Math.floor(x/360.0)*360.0;
	}
	
	public static double deg2rad(double x) {
		return x*Math.PI/180.;
	}
	
	public static double rad2deg(double x) {
		return x*180./Math.PI;
	}

	
	public boolean isVisibleFromLocation() {
		return model.getDelta()*getAstrolabeController().getLocalisationModel().getHemisphere()>=-getAstrolabeController().getHomeplanetModel().getEpsilon();
	}
	
	public boolean condition1() {
//		return astrolabeController.getLocalisationModel().getHemisphere()*model.getDelta() >=
//				-(90-90/Math.PI*Math.atan(AstrolabeModel.getModel().useful_width/AstrolabeModel.getModel().getEquatorRadius()/2));
		return true;
	}
	
	public boolean condition2() {
		return getAstrolabeController().getLocalisationModel().getHemisphere()*model.getDelta() >=
				getAstrolabeController().getLocalisationModel().getHemisphere()*
				(getAstrolabeController().getLocalisationModel().getLatitude()-getAstrolabeController().getLocalisationModel().getHemisphere()*90);
	}

	/**
	 * @return the astrolabeController
	 */
	public static AstrolabeController getAstrolabeController() {
		return astrolabeController;
	}
	
	
	public static void setAstrolabeController(AstrolabeController controller) {
		astrolabeController = controller;
	}
}
