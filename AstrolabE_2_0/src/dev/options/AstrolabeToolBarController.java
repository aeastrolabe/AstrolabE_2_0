package dev.options;

import javax.swing.JButton;

import dev.astrolabe.AstrolabeMainController;
import dev.struct.Controller;

/**
 */
public class AstrolabeToolBarController extends Controller {
	
	protected AstrolabeToolBarView view;
	AstrolabeMainController astrolabeMainController;
	JButton toggleButtons;
	
	LatitudeSliderController latitudeSliderController;

	public AstrolabeToolBarController(AstrolabeMainController astrolabeMainController) {
		this.astrolabeMainController = astrolabeMainController;
		view = new AstrolabeToolBarView(this);
//		latitudeSliderController = new LatitudeSliderController(this);
		createGUI();
	}
	
	public void createGUI() {	
		view.add(latitudeSliderController.view);
		view.repaint();
	}
	
	public AstrolabeToolBarView getView() {
		return view;
	}
	

}
