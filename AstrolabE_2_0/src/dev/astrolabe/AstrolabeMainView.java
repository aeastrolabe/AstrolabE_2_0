package dev.astrolabe;

import java.awt.Color;
import java.awt.Graphics;

import dev.struct.View;


/**
 */
@SuppressWarnings("serial")
public class AstrolabeMainView extends View {

	/**
	 * Field controller.
	 */
	AstrolabeMainController controller;
	
	/**
	 * Field buttonBarHeight.
	 */
	double buttonBarHeight = 0.1;
	/**
	 * Field informationBarWidth.
	 */
	double informationBarWidth = 0.2;
	
	/**
	 * Constructor for AstrolabeMainView.
	 * @param controller AstrolabeMainController
	 */
	public AstrolabeMainView(AstrolabeMainController controller) {
		super();
		this.controller = controller;
		setBackground(Color.black);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
		controller.astrolabe.repaint();
		controller.top.repaint();
		controller.side.repaint();
	}
	
	
}
