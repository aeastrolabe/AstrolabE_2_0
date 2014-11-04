package dev.astrolabe.part.rete;

import java.awt.Graphics2D;
import java.util.ListIterator;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeStyleModel;
import dev.astrolabe.part.AstrolabePartController;
import dev.sky.Constellation;
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
		g.setColor(AstrolabeStyleModel.getSelected().getEclipticColor());
		drawCircle(g, getXecliptic(), getYecliptic(), getRecliptic());
	}
	
	public void drawStars(Graphics2D g) {
		//TODO I don't like this...
		ListIterator<Constellation> iterc = Constellation.getConstellationList().listIterator();
		while (iterc.hasNext()){
			Constellation c = iterc.next();
			astrolabeController.drawStars(g,c);
		}
	}
	
	public void drawPlanets(Graphics2D g) {
		astrolabeController.drawPlanets(g);
	}
	
}
