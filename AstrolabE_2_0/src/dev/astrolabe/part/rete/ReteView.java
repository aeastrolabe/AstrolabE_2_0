package dev.astrolabe.part.rete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import dev.astrolabe.part.AstrolabePartView;


@SuppressWarnings("serial")
public class ReteView extends AstrolabePartView {

	ReteController controller;
	
	public ReteView(ReteController reteController) {
		controller = reteController;
		setBackground(new Color(0,0,0,0));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		controller.initReteView(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		controller.drawEcliptic(g2);
		controller.drawGraduationsOnEcliptic(g2);
		controller.drawMonthsOnEcliptic(g2);

		controller.drawAllStars(g2);
		controller.drawPlanets(g2);
		
		controller.drawSun(g2);
	}

}
