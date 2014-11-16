package dev.sky;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.astrolabe.AstrolabeController;
import dev.sky.dynamik.geocentric.Sun;
import dev.utils.Log;

public class StaticCelestialBodyViewListener implements MouseListener {

	
	private static AstrolabeController astrolabeController = null;
	private static CelestialBodyController celest = null;
	
	public StaticCelestialBodyViewListener() {
	
	}

	
	public void mouseClicked(MouseEvent e) {
		setupListener(e);
		if (celest == null) {
			return;
		}
		
		if (e.getClickCount() == 1) {
			astrolabeController.setSelected(celest.getModel());

			//update displayed data
			astrolabeController.getAstrolabeMainController().getCBDDcontroller().updateDisplayedData();
			
			//repainting
			astrolabeController.paintCelestialBody(celest);
		}
		if (e.getClickCount() == 2) {
			astrolabeController.setRuleRotation(astrolabeController.getReteRotation() + Math.atan2(celest.getView().getOrdinate(), celest.getView().getAbscissa()));
			astrolabeController.getView().repaint();
		}
		
		//TODO implement mission testing
		astrolabeController.getAstrolabeMainController().test_mission.checkCurrentOrderedStepCompletion(astrolabeController.getStateModel());
		astrolabeController.getAstrolabeMainController().test_mission.checkUnorderedStepCompletion(astrolabeController.getStateModel());
		astrolabeController.getAstrolabeMainController().getMissionController().getView().repaint();

		Log.log((((CelestialBodyView) e.getSource()).getController().getModel().getName()),this);;
	}

	public void mouseEntered(MouseEvent e) {
		setupListener(e);
		if (celest == null) {
			return;
		}
		
		//display information
		astrolabeController.getStateModel().setHighlightedCelestialBody(celest.getModel());
		astrolabeController.getAstrolabeMainController().getCBDDcontroller().updateDisplayedData();
		
		// TODO for a later version : closer pointing for close stars
		//if (closeToCenter(e.getPoint())) {
		celest.getView().setSizeCoeff(1.5);
		//}
			
		//repainting
		astrolabeController.paintCelestialBody(celest);
	}

	public void mouseExited(MouseEvent e) {
		if (celest == null) {
			return;
		}
		
		//discard information
		astrolabeController.getStateModel().setHighlightedCelestialBody(null);
		astrolabeController.getAstrolabeMainController().getCBDDcontroller().updateDisplayedData();
		
		//reducing celest size
		celest.getView().setSizeCoeff(1.0);
		
		//repainting
		astrolabeController.paintCelestialBody(celest);
		
		celest = null;
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
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
		if (((CelestialBodyView) e.getSource()).getController() != Sun.sun) {
			celest = ((CelestialBodyView) e.getSource()).getController();
		}
	}
	
}
