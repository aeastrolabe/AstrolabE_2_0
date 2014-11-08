package dev.sky;

import java.awt.Graphics2D;

public interface CelestialBodyHandler {
	
	public CelestialBodyModel getSelected();
	
	public void setSelected(CelestialBodyModel celestialBody); 
	
	public void paintCelestialBody(CelestialBodyController c);
	
	void drawPlanets(Graphics2D g);

	void drawAllStars();

	void drawAllStars(Graphics2D g);
	
//	private CelestialBodyController selected;
//
//	
//	public CelestialBodyController getSelected() {
//		return selected;
//	}
//	
//	public void setSelected(CelestialBodyController celestialBody) {
//		selected = celestialBody;
//	}
//	
//	public static void setAstrolabeController(AstrolabeController controller) {
//		CelestialBodyController.setAstrolabeController(controller);
////		CelestialBodyViewListener.setAstrolabeController(controller);
//	}
//	
	//TODO correct this
//	public void drawAll(Graphics2D g, AstrolabeController astrolabeController, LinkedList<CelestialBodyController> celestialBodyList) {
////		AstrolabeModel astrolabe = AstrolabeModel.getModel();
//		ListIterator<CelestialBodyController> iterc = celestialBodyList.listIterator();
//		while (iterc.hasNext()){
//			CelestialBodyController o = iterc.next();
//			
//			if (
////					!astrolabe.isWholeEclipticCrossingSky() &&
//					o.isVisibleFromLocation()) {
//				o.setDisplayed(true);
//			}
//			else {
////				if (!astrolabe.isSkyDisplayedWhole()) {
////					if (o.condition1()) {
////						o.setDisplayed(true);
////					}
////					else {
////						o.setDisplayed(false);
////					}
////				}
////				else {
//					if (o.condition2()) {
//						o.setDisplayed(true);
//					}
//					else {
//						o.setDisplayed(false);
//					}
////				}
//			}
//			
//			//TODO temporary
//			if (o.isDisplayed() && o.model.magnitude > 2) {
//				o.changeDisplayed();
//			}
//			
//			if (o.isDisplayed()) {
//				o.view.draw(g);
//			}
//			else {
//				astrolabeController.getView().remove(o.view); //TODO this is somehow unsafe !
//			}
//		}
//		astrolabeController.getView().repaint();
//	}

}
