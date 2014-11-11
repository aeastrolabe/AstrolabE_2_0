package dev.astrolabe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import dev.mission.Mission;
import dev.mission.RuleRotatedAligningStep;
import dev.mission.StarSelectedStep;
import dev.mission.SunDisplayedStep;
import dev.mission.ui.MissionController;
import dev.options.LatitudeSliderController;
import dev.sky.datadisplay.CelestialBodyDataDisplayController;
import dev.sky.statik.Star;
import dev.struct.Controller;
/**
 */
public class AstrolabeMainController extends Controller {
	
	private AstrolabeMainView view;
	
	private AstrolabeController astrolabeController;
	
	private MissionController missionController;
	
	public Mission test_mission;
	
	private CelestialBodyDataDisplayController CBDDcontroller;
	
	
	JPanel top = new JPanel();
	JPanel side = new JPanel();
	JPanel astrolabe = new JPanel();
	
	public AstrolabeMainController() {
		view = new AstrolabeMainView(this);
		astrolabeController = new AstrolabeController(this);
		
		test_mission = new Mission();
		test_mission.addOrderedStep(new StarSelectedStep(new Star("Sirius")));
		test_mission.addOrderedStep(new SunDisplayedStep(true));
		test_mission.addUnorderedStep(new RuleRotatedAligningStep(new Star("Sirius")));
		test_mission.addUnorderedStep(new StarSelectedStep(new Star("Aldébaran")));
		
		setMissionController(new MissionController(test_mission));
		
		setCBDDcontroller(new CelestialBodyDataDisplayController(this));
		
		createGUI();
	}
	
	/**
	 * Method createGUI.
	 */
	public void createGUI() {
		view.setLayout(new BorderLayout());
		side.setLayout(new FlowLayout());
		
		side.setPreferredSize(new Dimension(220, 600));

		//astrolabe.setBackground(Color.black);
		astrolabe.setLayout(new BorderLayout());
		top.setBackground(Color.GREEN);
		side.setBackground(Color.CYAN);
		
		astrolabe.add(astrolabeController.getView());
		
		LatitudeSliderController ltc = new LatitudeSliderController(this);
		side.add(ltc.getView());
		side.add(missionController.getView());
		side.add(CBDDcontroller.getView());
		
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

	/**
	 * @return the missionController
	 */
	public MissionController getMissionController() {
		return missionController;
	}

	/**
	 * @param missionController the missionController to set
	 */
	public void setMissionController(MissionController missionController) {
		this.missionController = missionController;
	}

	/**
	 * @return the cBDDcontroller
	 */
	public CelestialBodyDataDisplayController getCBDDcontroller() {
		return CBDDcontroller;
	}

	/**
	 * @param cBDDcontroller the cBDDcontroller to set
	 */
	public void setCBDDcontroller(CelestialBodyDataDisplayController cBDDcontroller) {
		CBDDcontroller = cBDDcontroller;
	}

}
