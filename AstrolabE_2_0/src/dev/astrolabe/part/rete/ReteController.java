package dev.astrolabe.part.rete;

import java.awt.Graphics2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.part.AstrolabePartController;
import dev.utils.AstrolabeStroke;

public class ReteController extends AstrolabePartController {
	
	protected ReteView view;

	public ReteController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);
		setModels();
		view = new ReteView(this);
	}
	
	@Override
	public void createGUI() {
		
	}
	
	public ReteView getView() {
		return view;
	}

	
	public void drawEcliptic(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.LARGE, AstrolabeStroke.SOLID));
		g.setColor(astrolabeController.getStyleModel().getEclipticColor());
		drawCircle(g, getXecliptic(), getYecliptic(), getRecliptic());
	}
	
	public void drawAllStars(Graphics2D g) {
		astrolabeController.drawAllStars(g);
	}
	
	public void drawPlanets(Graphics2D g) {
		astrolabeController.drawPlanets(g);
	}
	
}
