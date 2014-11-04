package dev.sky.datadisplay;

import java.awt.Graphics;

import javax.swing.JLabel;

import dev.struct.View;

@SuppressWarnings("serial")
public class CelestialBodyDataDisplayView extends View {

	private CelestialBodyDataDisplayController controller;
	
	private JLabel[] dataDisplays;
	
	public CelestialBodyDataDisplayView(CelestialBodyDataDisplayController controller) {
		super();
		this.controller = controller;
		setDataDisplays(new JLabel[controller.N_DATA]);
	}
	
	/**
	 * @return the dataDisplays
	 */
	public JLabel[] getDataDisplays() {
		return dataDisplays;
	}
	
	/**
	 * @param dataDisplays the dataDisplays to set
	 */
	public void setDataDisplays(JLabel[] dataDisplays) {
		this.dataDisplays = dataDisplays;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
		controller.updateDisplayedData();
	}
	
}
