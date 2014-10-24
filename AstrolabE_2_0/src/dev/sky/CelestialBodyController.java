package dev.sky;

import dev.astrolabe.AstrolabeController;

public abstract class CelestialBodyController {
	
	protected static CelestialBodyMasterController celestialBodyMasterController = new CelestialBodyMasterController();
	
	protected CelestialBodyView view;
	protected CelestialBodyModel model;
	
	protected boolean surbrillance = false;
	protected boolean displayed;
	
	public static AstrolabeController astrolabeController;
	
	private static StaticCelestialBodyViewListener listener = new StaticCelestialBodyViewListener();

	
	public CelestialBodyController(String name) {
		model = new CelestialBodyModel(name);
		view = new CelestialBodyView(this);
		
		view.addMouseListener(listener);
		StaticCelestialBodyViewListener.setAstrolabeController(astrolabeController);
		
		displayed = true;
	}
	
	public CelestialBodyView getView() {
		return view;
	}
	
	public CelestialBodyModel getModel() {
		return model;
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
	
	public static void setAstrolabeController(AstrolabeController controller) {
		astrolabeController = controller;
	}
	
	public boolean isVisibleFromLocation() {
		return model.getDelta()*astrolabeController.getLocalisationModel().getHemisphere()>=-astrolabeController.getHomeplanetModel().getEpsilon();
	}
	
	public boolean condition1() {
//		return astrolabeController.getLocalisationModel().getHemisphere()*model.getDelta() >=
//				-(90-90/Math.PI*Math.atan(AstrolabeModel.getModel().useful_width/AstrolabeModel.getModel().getEquatorRadius()/2));
		return true;
	}
	
	public boolean condition2() {
		return astrolabeController.getLocalisationModel().getHemisphere()*model.getDelta() >=
				astrolabeController.getLocalisationModel().getHemisphere()*
				(astrolabeController.getLocalisationModel().getLatitude()-astrolabeController.getLocalisationModel().getHemisphere()*90);
	}
}
