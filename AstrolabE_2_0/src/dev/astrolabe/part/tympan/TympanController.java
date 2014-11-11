package dev.astrolabe.part.tympan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.part.AstrolabePartController;
import dev.utils.AstrolabeStroke;


public class TympanController extends AstrolabePartController {

	private TympanView view;
	private TympanModel model;

	private RadialGradientPaint intern;

	public TympanController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);

		setModels();
		model = new TympanModel();
		model.set_h(localisationModel.getLatitude(),getEquatorRadius());
		model.set_pi_a(localisationModel.getLatitude(),getEquatorRadius());

		setupInternGradient();

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
	
	@Override
	public void setModels() {
		homeplanetModel = astrolabeController.getHomeplanetModel();
		localisationModel = astrolabeController.getLocalisationModel();
		styleModel = astrolabeController.getStyleModel();
		
		if(model != null) {
			setupInternGradient();
		}
	}

	public void FillTympanBackground(Graphics2D g) {
		double r = getOuterTropicRadius() +  astrolabeController.getStateModel().TYMPAN_PADDING + astrolabeController.getStateModel().LIMBE_PADDING;
		g.setPaint(intern);
		g.fill(new Ellipse2D.Double(-r,-r,2*r,2*r));

	}

	private void setupInternGradient() {
		Point2D center = new Point2D.Float(0,(float) -model.getC_h()[0]);
		float radius = (float) model.getR_h()[0];
		Point2D focus = new Point2D.Float(0,(float) -model.getC_h()[model.N_ALTITUDE]);

		try {
			intern = new RadialGradientPaint(
					center,
					radius,
					focus,
					styleModel.getTympanFractions(),
					styleModel.getTympanColors(),
					RadialGradientPaint.CycleMethod.NO_CYCLE);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public void drawEquator(Graphics2D g) {	
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		g.setColor(styleModel.getEquatorColor());
		drawCircle(g, 0, 0, getEquatorRadius());
	}

	public void drawTropics(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		g.setColor(styleModel.getTropicsColor());

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
			double r = getOuterTropicRadius() + astrolabeController.getStateModel().TYMPAN_PADDING;
			g.setClip(new Ellipse2D.Double(-r, -r, 2*r, 2*r));
			drawCircle(g, 0, -model.getC_h()[i], model.getR_h()[i]);
		}
		g.setClip(null);
	}

	public void drawLigneCrepusculaire(Graphics2D g) {
		double r = getOuterTropicRadius() + astrolabeController.getStateModel().TYMPAN_PADDING;
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

		r = getOuterTropicRadius() + astrolabeController.getStateModel().TYMPAN_PADDING;
		Ellipse2D c2 = new Ellipse2D.Double(-r, -r, 2*r, 2*r);
		g.clip(c2);

		g.setColor(Color.black);
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN,AstrolabeStroke.SOLID));

		drawLine(g, 0, -model.getC_h()[0]+model.getR_h()[0], 0, -getOuterTropicRadius()-astrolabeController.getStateModel().TYMPAN_PADDING);

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
	
	public void drawCardinalPoints(Graphics2D g) {
		int r1 = (int) (astrolabeController.getAstrolabeInternalRadius()*1.05);
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,(int) (2*7)));
		int dx = -4;
		
		if (localisationModel.getHemisphere() < 0) {
			g.rotate(Math.PI);
		}	
		g.drawString("N", dx, r1);
		g.rotate(Math.PI/2);
		
		g.drawString("E", dx, r1);
		g.rotate(Math.PI/2);
		
		g.drawString("S", dx, r1);
		g.rotate(Math.PI/2);
		
		g.drawString("O", dx, r1);
		g.rotate(Math.PI/2);
		if (localisationModel.getHemisphere() < 0) {
			g.rotate(Math.PI);
		}
	}
	
	public void drawLimbe(Graphics2D g) {
		g.setColor(Color.black);
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.THIN, AstrolabeStroke.SOLID));
		drawCircle(g, 0, 0, getOuterTropicRadius() + astrolabeController.getStateModel().TYMPAN_PADDING);

//		double t = localisationModel.getLongitude();
		
		double R_inf = astrolabeController.getAstrolabeInternalRadius() + astrolabeController.getStateModel().TYMPAN_PADDING;
		double R_sup = astrolabeController.getAstrolabeRadius();
		
		double epaisseurLimbeEpais = 1;
		double epaisseurLimbeFin = 1;
		
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f));
		g.drawOval(-(int)R_inf, -(int)R_inf, 2*(int)R_inf, 2*(int)R_inf);
		g.drawOval(-(int)R_sup, -(int)R_sup, 2*(int)R_sup, 2*(int)R_sup);
		
		g.rotate(Math.PI/2);
		
		double dr_limbe = astrolabeController.getStateModel().LIMBE_PADDING*0.5;

		double d_angle = 0.5;
		double angle = 0;
		double trait, tick;
		int n_angle = (int) (360/d_angle);
		for (int i=0;i<n_angle;i++){
			//angle+=d_angle;
			double angle_rad=-localisationModel.getHemisphere()*angle/180*Math.PI; //TODO
			double c = Math.cos(angle/180.*Math.PI);
			double s = Math.sin(angle/180.*Math.PI);
			if (angle%2.5==0) {
				if (angle%15==0) {
					tick = dr_limbe;
					trait = epaisseurLimbeEpais;
					g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,(int) (trait*6)));
					g.rotate(-angle_rad);
					g.translate(0, -R_inf-dr_limbe/2);
					//g2D.drawString(Double.toString((int) (angle/15)+6), (int) (R_mil1+dr_limbe/2), 3);
					g.drawString(Integer.toString((int) (angle/15+24-(localisationModel.getHemisphere()*6))%24), 5, 0);
					g.translate(0, R_inf+dr_limbe/2);
					g.rotate(angle_rad);
					g.setFont(new Font(Font.SANS_SERIF,Font.BOLD, (int) (trait*3)));
					g.rotate(-angle_rad);
					g.translate(0, -R_inf-dr_limbe/2);
					//g2D.drawString(Double.toString((int) (angle/15)+6), (int) (R_mil1+dr_limbe/2), 3);
					g.drawString("h", (int) (5+epaisseurLimbeEpais*8), -(int) (epaisseurLimbeEpais*2));
					g.translate(0, R_inf+dr_limbe/2);
					g.rotate(angle_rad);
				}
				else {
					tick = dr_limbe/2;
					trait = epaisseurLimbeFin;
					if (angle%7.5==0) {
						g.setFont(new Font(Font.SANS_SERIF,Font.BOLD, (int) (trait*4)));
						g.rotate(-angle_rad);
						g.translate(0, -R_inf-dr_limbe/2);
						//g2D.drawString("30", (int) (R_mil1+dr_limbe/2), 5);
						g.drawString("30", (int) (-trait*3), 0);
						g.translate(0, R_inf+dr_limbe/2);
						g.rotate(angle_rad);
					}
				}
			}
			else {
				tick = dr_limbe/4;
				trait = epaisseurLimbeFin;
			}
			
			g.setStroke(new BasicStroke((float) trait, BasicStroke.CAP_SQUARE,
	                BasicStroke.JOIN_MITER, 10.0f));
			Line2D.Double mark = new Line2D.Double(c*R_inf, s*R_inf, c*(R_inf+tick), s*(R_inf+tick));
			g.draw(mark);
			//g2D.drawLine((int) R_mil1, 0, (int)(R_mil1+tick), 0);
			angle-=d_angle;
			angle=(angle+360)%360;
			//g2D.rotate(-d_angle);
		}
		g.rotate(-Math.PI/2);
	}
	
}
