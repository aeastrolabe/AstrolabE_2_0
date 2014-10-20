package dev.options;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JToolBar;

/**
 */
@SuppressWarnings("serial")
public class AstrolabeToolBarView extends JToolBar {
	
	/**
	 * Field controller.
	 */
	AstrolabeToolBarController controller;
	
	/**
	 * Constructor for AstrolabeToolBarView.
	 * @param controller AstrolabeToolBarController
	 */
	public AstrolabeToolBarView(AstrolabeToolBarController controller) {
		super();
		this.controller = controller;
		setPreferredSize(new Dimension(200, 50));
		//setOpaque(true);
		
		/* Pour empecher le déplacement */
		setEnabled(false);
		setFloatable(false);
		setVisible(false);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
	}
}
