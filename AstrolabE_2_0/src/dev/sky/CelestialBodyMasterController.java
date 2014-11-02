package dev.sky;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;

import dev.astrolabe.AstrolabeController;


public class CelestialBodyMasterController {
	
	//TODO remove static tag
	private static CelestialBodyController selected;

	
	public static CelestialBodyController getSelected() {
		return selected;
	}
	
	public static void setSelected(CelestialBodyController celestialBody) {
		selected = celestialBody;
	}
	
	public static void setAstrolabeController(AstrolabeController controller) {
		CelestialBodyController.setAstrolabeController(controller);
//		CelestialBodyViewListener.setAstrolabeController(controller);
	}
	
	//TODO correct this
	public static void drawAll(Graphics2D g, AstrolabeController astrolabeController, LinkedList<CelestialBodyController> celestialBodyList) {
//		AstrolabeModel astrolabe = AstrolabeModel.getModel();
		ListIterator<CelestialBodyController> iterc = celestialBodyList.listIterator();
		while (iterc.hasNext()){
			CelestialBodyController o = iterc.next();
			
			if (
//					!astrolabe.isWholeEclipticCrossingSky() &&
					o.isVisibleFromLocation()) {
				o.setDisplayed(true);
			}
			else {
//				if (!astrolabe.isSkyDisplayedWhole()) {
//					if (o.condition1()) {
//						o.setDisplayed(true);
//					}
//					else {
//						o.setDisplayed(false);
//					}
//				}
//				else {
					if (o.condition2()) {
						o.setDisplayed(true);
					}
					else {
						o.setDisplayed(false);
					}
//				}
			}
			
			//TODO temporary
			if (o.isDisplayed() && o.model.magnitude > 2) {
				o.changeDisplayed();
			}
			
			if (o.isDisplayed()) {
				o.view.draw(g);
			}
			else {
				astrolabeController.getView().remove(o.view); //TODO this is somehow unsafe !
			}
		}
		astrolabeController.getView().repaint();
	}
	
//	public static void checkForCelestialBodydrawingVariables(String name, CelestialBodyController o, AstrolabeModel astrolabe) {
//		if (o.model.getName().contains(name)) {
//			System.out.println("##");
//			System.out.println(o.isDisplayed());
//			System.out.println(astrolabe.isWholeEclipticCrossingSky());
//			System.out.println(o.isVisibleFromLocation());
//			System.out.println(astrolabe.isSkyDisplayedWhole());
//			System.out.println(o.condition1());
//			System.out.println(o.condition2());
//			System.out.println("##");
//		}
//	}

}
