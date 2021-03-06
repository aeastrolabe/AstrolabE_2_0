package dev.mission.ui;

import java.awt.Graphics;

import javax.swing.JLabel;

import dev.struct.View;

@SuppressWarnings("serial")
public class MissionView extends View {
	
	private MissionController controller;
	
	private JLabel[] ordered;
	private JLabel[] unordered;
	
	public MissionView(MissionController controller) {
		super();
		this.controller = controller;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
		controller.updateLabels();
	}

	/**
	 * @return the ordered
	 */
	public JLabel[] getOrdered() {
		return ordered;
	}

	/**
	 * @param ordered the ordered to set
	 */
	public void setOrdered(JLabel[] ordered) {
		this.ordered = ordered;
	}

	/**
	 * @return the unordered
	 */
	public JLabel[] getUnordered() {
		return unordered;
	}

	/**
	 * @param unordered the unordered to set
	 */
	public void setUnordered(JLabel[] unordered) {
		this.unordered = unordered;
	}
	
}
