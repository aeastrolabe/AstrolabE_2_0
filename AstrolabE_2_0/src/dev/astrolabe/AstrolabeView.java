package dev.astrolabe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AstrolabeView extends JLayeredPane {

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
		
		//this is only called once if the localisation doesn't change
		controller.initCenterAndBounds();
		
//		controller.updateBackgroundColor();
		
		//TODO this adds lag
//		controller.background.repaint();
//		controller.getTympanController().getView().repaint();
//		controller.getReteController().getView().repaint();
		
	}
}
