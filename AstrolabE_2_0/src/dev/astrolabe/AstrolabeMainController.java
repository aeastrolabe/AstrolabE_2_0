package dev.astrolabe;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import dev.mission.Mission;
import dev.mission.StarSelectedStep;
import dev.mission.SunDisplayedStep;
import dev.options.LatitudeSliderController;
import dev.sky.statik.Star;
import dev.struct.Controller;
/**
 */
public class AstrolabeMainController extends Controller {
	
	private AstrolabeMainView view;
	
	private AstrolabeController astrolabeController;
	
	public Mission test_mission;
	
	
	JPanel top = new JPanel();
	JPanel side = new JPanel();
	JPanel astrolabe = new JPanel();
	boolean buttonBarHidden = false;

	
	public AstrolabeMainController() {
		view = new AstrolabeMainView(this);
		astrolabeController = new AstrolabeController(this);
		
		test_mission = new Mission();
		test_mission.addOrderedStep(new StarSelectedStep(new Star("Sirius")));
		test_mission.addOrderedStep(new SunDisplayedStep(true));
		test_mission.addUnorderedStep(new StarSelectedStep(new Star("Death Star")));
		
		createGUI();
	}
	
	/**
	 * Method createGUI.
	 */
	public void createGUI() {
		view.setLayout(new BorderLayout());

		//astrolabe.setBackground(Color.black);
		astrolabe.setLayout(new BorderLayout());
		top.setBackground(Color.GREEN);
		side.setBackground(Color.CYAN);
		
		astrolabe.add(astrolabeController.getView());
		
		LatitudeSliderController ltc = new LatitudeSliderController(this);
		top.add(ltc.getView());
		
		view.add(astrolabe,BorderLayout.CENTER);
		view.add(top, BorderLayout.NORTH);
		view.add(side,BorderLayout.EAST);
	}

	public AstrolabeMainView getView() {
		return view;
	}
	
	public AstrolabeController getAstrolabeController() {
		return astrolabeController;
	}

}
