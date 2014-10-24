package dev.sky;

import static dev.sky.CelestialBodyController.deg2rad;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import dev.astrolabe.AstrolabeHomeplanetModel;

@SuppressWarnings("serial")
public class CelestialBodyView extends JPanel  {
	
	protected CelestialBodyController controller;
	
	//abscisse et ordonnee seront calculees a partir de alpha et declination
	protected double abscissa;
	protected double ordinate;
	
	public int size; //TODO : remplacer size par la taille réelle du panel
	
	public double sizeCoeff = 1;

	public BufferedImage image;
	
	public CelestialBodyController getController() {
		return controller;
	}
	
	public CelestialBodyView(CelestialBodyController celestialBodyController) {
		controller = celestialBodyController;
	}

	public double getAbscissa() {
		return abscissa;
	}
	
	public double getOrdinate() {
		return ordinate;
	}
	
	public int getDiameter() {
		int d = 0;
		double m = controller.model.magnitude;
		d = (int) (-8 / 7.5 * m + 8)*2;
		return d;
	}
	
	public int getSizeOf() {
		return size;
	}
	
	public void setCoordinates() {
		double imageWidth = 2* CelestialBodyController.astrolabeController.getAstrolabeRadius();
		double delta2 = CelestialBodyController.astrolabeController.getLocalisationModel().getHemisphere() >= 0 ? controller.model.getDelta() : controller.model.getDelta() ;
		CelestialBodyController.astrolabeController.getHomeplanetModel();
		double rho =  AstrolabeHomeplanetModel.DEFAULT_EQUATOR_RADIUS*Math.tan(deg2rad((90. - delta2)/2.))
				/imageWidth*CelestialBodyController.astrolabeController.getAstrolabeRadius();
		abscissa = rho*Math.sin(deg2rad(controller.model.getAlpha()));
		ordinate = rho*Math.cos(deg2rad(controller.model.getAlpha()));
	}
	
	public void draw(Graphics2D g) {
		setCoordinates();
		double zoom = CelestialBodyController.astrolabeController.getAstrolabeScale();
		double x1 = abscissa*zoom;
		double y1 = ordinate*zoom;
		double thetaActuel = CelestialBodyController.astrolabeController.getReteRotation();
//		double thetaActuel = CelestialBodyController.astrolabeController.getThetaAraignee()+CelestialBodyController.astrolabeController.getThetaAraigneeTotal();
		double x = x1*Math.cos(thetaActuel)-y1*Math.sin(thetaActuel);
		double y = x1*Math.sin(thetaActuel)+y1*Math.cos(thetaActuel);

		x+=CelestialBodyController.astrolabeController.getAstrolabeCenter().getX();
		y+=CelestialBodyController.astrolabeController.getAstrolabeCenter().getY();
		size =  (int) ( getDiameter()*sizeCoeff*
				Math.sqrt
				(CelestialBodyController.astrolabeController.getAstrolabeScale()));
		setBounds((int) x-size/2, (int) y-size/2, size, size);
		repaint();
		CelestialBodyController.astrolabeController.getView().add(this,0);
		CelestialBodyController.astrolabeController.getView().repaint();
	}
	
	public void drawCelestialBody(Graphics2D g) {
//		if (this.controller.equals(CelestialBodyMasterController.getSelected())) {
//			g.setColor(Color.RED);
//			g.setStroke(new BasicStroke(size/4, BasicStroke.CAP_SQUARE,
//                BasicStroke.JOIN_MITER, 10.0f));
//			g.drawLine(0, 0, size, size);
//			g.drawLine(size, 0, 0, size);
//		}

		//TODO temporary to fix the image alpha problem
		g.drawImage(image, 0, 0, size, size, null);
		g.drawImage(image, 0, 0, size, size, null);
		

	}
	
	public void paint(Graphics g) {
		drawCelestialBody((Graphics2D) g);
	}

	
	
	
}
