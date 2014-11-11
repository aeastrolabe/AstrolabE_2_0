package dev.astrolabe.part.rule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import dev.astrolabe.part.AstrolabePartView;

@SuppressWarnings("serial")
public class RuleView extends AstrolabePartView {

	RuleController controller;
	
	public RuleView(RuleController controller) {
		this.controller = controller;
		setBackground(new Color(0,0,0,0));
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		controller.initRuleView(g2);
		
		g2.setColor(Color.red);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		controller.drawOstensor(g2);
	}
}
