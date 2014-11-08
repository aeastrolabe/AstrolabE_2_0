package dev.sky;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.astrolabe.AstrolabeController;

@Deprecated
public class CelestialBodyViewListener implements MouseListener {

	private static AstrolabeController astrolabeController;
	private CelestialBodyController celest;
	
	public CelestialBodyViewListener(CelestialBodyController o) {
		celest=o;
	}

	
	public void mouseClicked(MouseEvent e) {
		System.out.println(astrolabeController.getLocalisationModel().getLatitude());
		// TODO
//		astrolabeController.setInformation(celest);
//		CelestialBodyMasterController.setSelected(celest);
//		if (e.getClickCount()>1 & !astrolabeController.getButtonBar().isOstensorFixed()) {
//			astrolabeController.alignWithOstensor(celest.view.abscissa,celest.view.ordinate);
//		}
		System.out.println(((CelestialBodyView) e.getSource()).getController().getModel().getName());
	}

	public void mouseEntered(MouseEvent e) {
		// TODO for a later version : closer pointing for close stars
		//if (closeToCenter(e.getPoint())) {
			celest.getView().setSizeCoeff(1.5);
			celest.getView().repaint();
		//}
	}

	public void mouseExited(MouseEvent e) {
		celest.getView().setSizeCoeff(1.0);
		celest.getView().repaint();
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
	
	@SuppressWarnings("unused")
	private boolean closeToCenter(Point p) {
		return (p.x - celest.getView().size/2)*(p.x - celest.getView().size/2) + (p.x - celest.getView().size/2)*(p.x - celest.getView().size/2)
				<= celest.getView().size*celest.getView().size/4;
	}

}
