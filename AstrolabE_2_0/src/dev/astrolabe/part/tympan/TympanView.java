package dev.astrolabe.part.tympan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import dev.astrolabe.part.AstrolabePartView;


@SuppressWarnings("serial")
public class TympanView extends AstrolabePartView {
	
	TympanController controller;
	
	public TympanView(TympanController controller) {
		this.controller = controller;
		setBackground(new Color(0,0,0,0));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		controller.initTympanView(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		controller.FillTympanBackground(g2);
		controller.drawMire(g2);
		controller.drawEqualAzimuth(g2);
		controller.drawAlmucantarats(g2);
		
		controller.drawLigneCrepusculaire(g2);
		controller.drawEquator(g2);
		controller.drawTropics(g2);
		controller.drawLimbe(g2);
		controller.drawZenith(g2);
		controller.drawCardinalPoints(g2);
	}


}
