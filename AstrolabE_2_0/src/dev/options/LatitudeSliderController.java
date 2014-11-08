package dev.options;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dev.astrolabe.AstrolabeMainController;
import dev.struct.Controller;

public class LatitudeSliderController extends Controller {

	LatitudeSliderView view = new LatitudeSliderView();
	AstrolabeMainController astrolabeMainController;
	
	public LatitudeSliderController(AstrolabeMainController controller) {
		astrolabeMainController = controller;
		view = new LatitudeSliderView();
		view.addChangeListener(new LatitudeSliderListener());
	}
	
	@Override
	public void createGUI() {
		
	}
	
	public class LatitudeSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			astrolabeMainController.getAstrolabeController().setLatitude(((LatitudeSliderView) e.getSource()).getValue());

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					astrolabeMainController.getAstrolabeController().getTympanController().updateModel();
					astrolabeMainController.getView().repaint();
				}
			});
			
		}		
	}

	
	public LatitudeSliderView getView() {
		return view;
	}
}
