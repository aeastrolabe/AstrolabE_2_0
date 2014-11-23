package dev.sky;

import static dev.sky.CelestialBodyController.deg2rad;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dev.astrolabe.AstrolabeHomeplanetModel;

@SuppressWarnings("serial")
public class CelestialBodyView extends JPanel  {
	
	private CelestialBodyController controller;
	
	//abscisse et ordonnee seront calculees a partir de alpha et declination
	protected double abscissa;
	protected double ordinate;
	
	protected double x;
	protected double y;
	protected double zoom;
	
	private int size; //TODO : remplacer size par la taille réelle du panel
	
	private double sizeCoeff = 1;

	private BufferedImage image;
	
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
		double delta = controller.model.getDelta();
		double rho =  AstrolabeHomeplanetModel.DEFAULT_EQUATOR_RADIUS*Math.tan(deg2rad((90. - delta)/2.));
		abscissa = rho*Math.sin(deg2rad(controller.model.getAlpha()));
		ordinate = rho*Math.cos(deg2rad(controller.model.getAlpha()));
		double zoom = CelestialBodyController.getAstrolabeController().getAstrolabeScale();
		double x1 = abscissa*zoom;
		double y1 = ordinate*zoom;
		double thetaActuel = CelestialBodyController.getAstrolabeController().getReteRotation();
		x = x1*Math.cos(thetaActuel)-y1*Math.sin(thetaActuel);
		y = x1*Math.sin(thetaActuel)+y1*Math.cos(thetaActuel);

		x+=CelestialBodyController.getAstrolabeController().getAstrolabeCenter().getX();
		y+=CelestialBodyController.getAstrolabeController().getAstrolabeCenter().getY();
		size =  (int) ( getDiameter()*sizeCoeff*
				Math.sqrt
				(CelestialBodyController.getAstrolabeController().getAstrolabeScale()));
		setBounds((int) x-size/2, (int) y-size/2, size, size);
	}
	
	public void draw(Graphics2D g) {
		setCoordinates();
		repaint();
	}
	
	public void drawCelestialBody(Graphics2D g) {
		//TODO temporary to fix the image alpha problem
		
	}
	
	public void paint(Graphics g) {
		//This is necessary when hovering over the body
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setCoordinates();
			}
		});
		
		g.drawImage(image, 0, 0, size, size, null);
		g.drawImage(image, 0, 0, size, size, null);
	}

	public double getSizeCoeff() {
		return sizeCoeff;
	}
	
	public void setSizeCoeff(double sizeCoeff) {
		this.sizeCoeff = sizeCoeff;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
