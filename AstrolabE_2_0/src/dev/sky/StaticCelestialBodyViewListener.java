package dev.sky;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.astrolabe.AstrolabeController;
import dev.mission.StarSelectedStep;

public class StaticCelestialBodyViewListener implements MouseListener {

	
	private static AstrolabeController astrolabeController = null;
	private static CelestialBodyController celest = null;
	
	public StaticCelestialBodyViewListener() {
	
	}

	
	public void mouseClicked(MouseEvent e) {
		setupListener(e);
		astrolabeController.getStateModel().setSelectedCelestialBody(celest);
		System.out.println(astrolabeController.getLocalisationModel().getLatitude());
		System.out.println("#"+astrolabeController.getStateModel().getSelectedCelestialBody().getModel().getName()+"#");
		System.out.println("#"+((StarSelectedStep) (astrolabeController.getAstrolabeMainController().test_mission.getOrderedSteps().getFirst())).starToSelect.getModel().getName()+"#");
		System.out.println(astrolabeController.getAstrolabeMainController().test_mission.checkCurrentOrderedStepCompletion(astrolabeController.getStateModel()));

		// TODO
//		astrolabeController.setInformation(celest);
//		CelestialBodyMasterController.setSelected(celest);
//		if (e.getClickCount()>1 & !astrolabeController.getButtonBar().isOstensorFixed()) {
//			astrolabeController.alignWithOstensor(celest.view.abscissa,celest.view.ordinate);
//		}
		System.out.println(((CelestialBodyView) e.getSource()).getController().getModel().getName());
	}

	public void mouseEntered(MouseEvent e) {
		setupListener(e);
		// TODO for a later version : closer pointing for close stars
		//if (closeToCenter(e.getPoint())) {
			celest.getView().sizeCoeff = 1.5;
			celest.getView().repaint();
		//}
	}

	public void mouseExited(MouseEvent e) {
		celest.getView().sizeCoeff = 1.0;
		celest.getView().repaint();
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO
	}
	
	public static void setAstrolabeController(AstrolabeController controller) {
		if (astrolabeController == null) {
			astrolabeController = controller;
		}
	}
	
	@SuppressWarnings("unused")
	private boolean closeToCenter(Point p) {
		return (p.x - celest.getView().size/2)*(p.x - celest.getView().size/2) + (p.x - celest.getView().size/2)*(p.x - celest.getView().size/2)
				<= celest.getView().size*celest.getView().size/4;
	}

	private void setupListener(MouseEvent e) {
		celest = ((CelestialBodyView) e.getSource()).controller;
	}
	
}
