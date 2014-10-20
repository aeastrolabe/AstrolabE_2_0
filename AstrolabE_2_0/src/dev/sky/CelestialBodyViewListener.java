package dev.sky;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.astrolabe.AstrolabeController;

public class CelestialBodyViewListener implements MouseListener {

	private static AstrolabeController astrolabeController;
	private CelestialBodyController celest;
	
	public CelestialBodyViewListener(CelestialBodyController o) {
		celest=o;
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO
//		astrolabeController.setInformation(celest);
//		CelestialBodyMasterController.setSelected(celest);
//		if (e.getClickCount()>1 & !astrolabeController.getButtonBar().isOstensorFixed()) {
//			astrolabeController.alignWithOstensor(celest.view.abscissa,celest.view.ordinate);
//		}
		System.out.println(((CelestialBodyView) e.getSource()).getController().getModel().getName());
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO
	}
	
	public static void setAstrolabeController(AstrolabeController controller) {
		astrolabeController = controller;
	}

}
