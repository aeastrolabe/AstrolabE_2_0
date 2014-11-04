package dev.sky.datadisplay;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import dev.astrolabe.AstrolabeMainController;
import dev.sky.CelestialBodyModel;
import dev.struct.Controller;

public class CelestialBodyDataDisplayController extends Controller {

	private CelestialBodyDataDisplayView view;
	
	private AstrolabeMainController astrolabeMainController;
	
	final int N_DATA = 6;
	/**
	 * dataDisplay contains :
	 * 0- name
	 * 1- class type
	 * 2- magnitude
	 * 3- constellation (if exists)
	 * 4- alpha
	 * 5- delta
	 */
	
	public CelestialBodyDataDisplayController(AstrolabeMainController astrolabeMainController) {
		this.astrolabeMainController = astrolabeMainController;
		view =  new CelestialBodyDataDisplayView(this);
		
		createGUI();
	}
	
	public CelestialBodyDataDisplayView getView() {
		return view;
	}
	
	@Override
	public void createGUI() {
		view.setLayout(new GridLayout(N_DATA,1));
		view.setPreferredSize(new Dimension(1*200, N_DATA*20));
		
		JLabel[] labels = new JLabel[N_DATA];
		for(int i = 0; i < N_DATA; i++) {
			labels[i] = new JLabel();
			view.add(labels[i]);
		}
		view.setDataDisplays(labels);
		updateDisplayedData();
	}


	public void updateDisplayedData() {
		CelestialBodyModel m = astrolabeMainController.getAstrolabeController().getSelected();
		if (updateSelectedDisplayedData(m)) {
			return;
		}
		else {
			m = astrolabeMainController.getAstrolabeController().getStateModel().getHighlightedCelestialBody();
			updateHighlightedDisplayedData(m);
		}
	}
	
	private boolean updateSelectedDisplayedData(CelestialBodyModel m) {
		JLabel[] labels = view.getDataDisplays();
		DecimalFormat df = new DecimalFormat("#.00"); 
		if (m == null) {
			return false;
		}
		else {
			labels[0].setText("Name : "+m.getName());
			labels[1].setText("Type : "+m.getType());
			labels[2].setText("Magnitude : "+m.getMagnitude());
			labels[3].setText("Contellation : "+m.getConstellation());
			labels[4].setText("Alpha : "+df.format(m.getAlpha()));
			labels[5].setText("Delta : "+df.format(m.getDelta()));
			return true;
		}
	}
	
	private boolean updateHighlightedDisplayedData(CelestialBodyModel m) {
		JLabel[] labels = view.getDataDisplays();
		DecimalFormat df = new DecimalFormat("#.00"); 
		if (m == null) {
			for(JLabel l : labels) {
				l.setText("No information available");
			}
			return false;
		}
		else {
			labels[0].setText("Name : "+m.getName());
			labels[1].setText("Type : "+m.getType());
			labels[2].setText("Magnitude : "+m.getMagnitude());
			labels[3].setText("Contellation : "+m.getConstellation());
			labels[4].setText("Alpha : "+df.format(m.getAlpha()));
			labels[5].setText("Delta : "+df.format(m.getDelta()));
			return true;
		}
	}

}
