package dev.astrolabe.part.rule;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.part.AstrolabePartController;


public class RuleController extends AstrolabePartController {

	RuleView view;
	
	public RuleController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);
		setModels();
		view = new RuleView(this);
	}
	
	@Override
	public void createGUI() {
		
	}

	public void drawOstensor(Graphics2D g) {		
		double r = astrolabeController.getAstrolabeRadius();
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f));
		g.draw(new Line2D.Double(0, 0, r, 0));
	}

	public RuleView getView() {
		return view; 
	}
	
}
