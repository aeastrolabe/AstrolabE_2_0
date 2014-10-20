package dev.astrolabe.part.tympan;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeStyleModel;
import dev.astrolabe.part.AstrolabePartController;
import dev.utils.AstrolabeStroke;


public class TympanController extends AstrolabePartController {

	private TympanView view;
	private TympanModel model;
	
	
	
	public TympanController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);
		
		setModels();
		model = new TympanModel();
		model.set_h(localisationModel.getLatitude(),getEquatorRadius());
		model.set_pi_a(localisationModel.getLatitude(),getEquatorRadius());
		
		view = new TympanView(this);
	}
	
	//Unused
	public void createGUI() {
	}

	public TympanView getView() {
		return view;
	}
	
	public void updateModel() {
		model = new TympanModel();
		model.set_h(localisationModel.getLatitude(),getEquatorRadius());
		model.set_pi_a(localisationModel.getLatitude(),getEquatorRadius());
	}
	
	public void FillTympanBackground(Graphics2D g) {
		double r = getOuterTropicRadius() +  astrolabeController.getDrawingModel().TYMPAN_PADDING + astrolabeController.getDrawingModel().LIMBE_PADDING;
		g.setColor(AstrolabeStyleModel.getSelected().getTympanColor());
		g.fill(new Ellipse2D.Double(-r,-r,2*r,2*r));
	}

	public void drawEquator(Graphics2D g) {	
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		g.setColor(AstrolabeStyleModel.getSelected().getEquatorColor());
		drawCircle(g, 0, 0, getEquatorRadius());
	}
	
	public void drawTropics(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		g.setColor(AstrolabeStyleModel.getSelected().getTropicsColor());
		
		drawCircle(g, 0, 0, getCancerTropicRadius());
		drawCircle(g, 0, 0, getCapricornTropicRadius());
	}
	
	public void drawMire(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		g.setColor(Color.black);
		fillCircle(g, 0, 0, 2);
		drawLine(g, -3, 0, 3, 0);
		drawLine(g, 0, -3, 0, 3);
	}
	
	public void drawZenith(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.ULTRA_THIN, AstrolabeStroke.SOLID));
		g.setColor(Color.red);
		fillCircle(g,0, -model.getC_h()[model.N_ALTITUDE], 1);
		drawLine(g, -3, -model.getC_h()[model.N_ALTITUDE], 3, -model.getC_h()[model.N_ALTITUDE]);
		drawLine(g, 0, -model.getC_h()[model.N_ALTITUDE]-3, 0, -model.getC_h()[model.N_ALTITUDE]+3);
	}
	
	public void drawAlmucantarats(Graphics2D g) {
		for(int i=0;i<model.N_ALTITUDE;i++) {
			double ialtitude = (i*90)/model.N_ALTITUDE;
			if (ialtitude%10 == 0) {
				g.setColor(Color.black);
				g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN,AstrolabeStroke.SOLID));
			}
			else {
				g.setColor(Color.darkGray);
				g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.ULTRA_THIN,AstrolabeStroke.SOLID));
			}
			double r = getOuterTropicRadius() + astrolabeController.getDrawingModel().TYMPAN_PADDING;
			g.setClip(new Ellipse2D.Double(-r, -r, 2*r, 2*r));
			drawCircle(g, 0, -model.getC_h()[i], model.getR_h()[i]);
		}
		g.setClip(null);
	}
	
	public void drawLigneCrepusculaire(Graphics2D g) {
		double r = getOuterTropicRadius() + astrolabeController.getDrawingModel().TYMPAN_PADDING;
		Ellipse2D c = new Ellipse2D.Double(-r, -r, 2*r, 2*r);
		g.setClip(c);
		
		g.setColor(Color.black);
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.DASHED));

		drawCircle(g, 0, -model.getC_h()[model.N_ALTITUDE+1], model.getR_h()[model.N_ALTITUDE+1]);
		drawCircle(g, 0, -model.getC_h()[model.N_ALTITUDE+1], Math.abs(model.getR_h()[model.N_ALTITUDE+1]));

		g.setClip(null);
	}
	
	public void drawEqualAzimuth(Graphics2D g) {
		int i_lim = model.N_AZIMUTH - 1;
		
		double r = model.getR_h()[i_lim];
		Ellipse2D c = new Ellipse2D.Double(-r, -model.getC_h()[i_lim]-r, 2*r, 2*r);
		
		r = model.getR_h()[0];
		Ellipse2D c1 = new Ellipse2D.Double(-r, -model.getC_h()[0]-r, 2*r, 2*r);
		Path2D p1 = new Path2D.Double(Path2D.WIND_EVEN_ODD);
        p1.append(c1, false);
        p1.append(c, false);
        g.clip(p1);
		
		r = getOuterTropicRadius() + astrolabeController.getDrawingModel().TYMPAN_PADDING;
		Ellipse2D c2 = new Ellipse2D.Double(-r, -r, 2*r, 2*r);
		g.clip(c2);
		
		g.setColor(Color.black);
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN,AstrolabeStroke.SOLID));
		
		drawLine(g, 0, -model.getC_h()[0]+model.getR_h()[0], 0, -getOuterTropicRadius()-astrolabeController.getDrawingModel().TYMPAN_PADDING);
		
		for (int i=1;i<model.N_AZIMUTH;i++) {
			int iazimuth = (i*90)/model.N_AZIMUTH;

			if (iazimuth%10 == 0) {
				g.setColor(Color.black);
				g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN,AstrolabeStroke.SOLID));
			}
			else {
				g.setColor(Color.darkGray);
				g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.ULTRA_THIN,AstrolabeStroke.SOLID));
			}  

			drawCircle(g, model.getC_a()[i], -model.getPi(), model.getR_a()[i]);	
		}
		g.setClip(null);
	}
	
	public void drawLimbe(Graphics2D g) {
		g.setColor(Color.black);
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		drawCircle(g, 0, 0, getOuterTropicRadius() + astrolabeController.getDrawingModel().TYMPAN_PADDING);
	}
}
