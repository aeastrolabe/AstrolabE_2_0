package dev.astrolabe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AstrolabeView extends JPanel {

	AstrolabeController controller;
	
	public AstrolabeView(AstrolabeController controller) {
		this.controller = controller;
		
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.green));
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		super.paintComponents(g2);
		
		controller.initCenter();
		controller.updateBounds();
		
		controller.updateBackgroundColor();
		controller.background.repaint();
		controller.getTympanController().getView().repaint();
		controller.getReteController().getView().repaint();
	}
}
