package dev.astrolabe.part;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeHomeplanetModel;
import dev.astrolabe.AstrolabeLocalisationModel;
import dev.astrolabe.AstrolabeStyleModel;
import dev.struct.Controller;


public abstract class AstrolabePartController extends Controller {

	protected AstrolabeController astrolabeController;

	protected AstrolabeHomeplanetModel homeplanetModel;
	protected AstrolabeLocalisationModel localisationModel;
	protected AstrolabeStyleModel styleModel;
	
	public void setAstrolabeController(AstrolabeController controller) {
		astrolabeController = controller;
	}
	
	public void setModels() {
		homeplanetModel = astrolabeController.getHomeplanetModel();
		localisationModel = astrolabeController.getLocalisationModel();
		styleModel = astrolabeController.getStyleModel();
	}
	
	public double getOuterTropicRadius() {
		return Math.max(getCancerTropicRadius(), getCapricornTropicRadius());
	}
	
	public double getCancerTropicRadius() {
		return getEquatorRadius()*cos(homeplanetModel.obliquite())
				/(1+localisationModel.getHemisphere()*sin(homeplanetModel.obliquite()));
	}
	
	public double getEquatorRadius() {
		return AstrolabeHomeplanetModel.DEFAULT_EQUATOR_RADIUS;
	}

	public double getCapricornTropicRadius() {
		return getEquatorRadius()*cos(homeplanetModel.obliquite())/(1-localisationModel.getHemisphere()*sin(homeplanetModel.obliquite()));
	}
	
	public double getRecliptic() {
		return (getCapricornTropicRadius() + getCancerTropicRadius())/2;
	}
	
	public double getXecliptic() {
		return -(getCapricornTropicRadius() - getCancerTropicRadius())/2;
	}
	
	public double getYecliptic() {
		return 0;
	}
	
	public void drawCircle(Graphics2D g, double x, double y, double r) {
		g.draw(new Ellipse2D.Double(x-r, y-r, 2*r, 2*r));
	}
	
	public void fillCircle(Graphics2D g, double x, double y, double r) {
		g.fill(new Ellipse2D.Double(x-r, y-r, 2*r, 2*r));
	}
	
	public void drawLine(Graphics2D g, double x1, double y1, double x2, double y2) {
		g.draw(new Line2D.Double(x1,y1,x2,y2));
	}
	
	public void initTympanView(Graphics2D g2) {
		g2.translate(astrolabeController.getAstrolabeCenter().x, astrolabeController.getAstrolabeCenter().y);
		g2.scale(astrolabeController.getAstrolabeScale(), astrolabeController.getAstrolabeScale());
//		g2.rotate(astrolabeController.getReteRotation());
	}
	
	public void initReteView(Graphics2D g2) {
		g2.translate(astrolabeController.getAstrolabeCenter().x, astrolabeController.getAstrolabeCenter().y);
		g2.scale(astrolabeController.getAstrolabeScale(), astrolabeController.getAstrolabeScale());
		g2.rotate(astrolabeController.getReteRotation());
	}
	
	public void initRuleView(Graphics2D g2) {
		g2.translate(astrolabeController.getAstrolabeCenter().x, astrolabeController.getAstrolabeCenter().y);
		g2.scale(astrolabeController.getAstrolabeScale(), astrolabeController.getAstrolabeScale());
		g2.rotate(astrolabeController.getRuleRotation());
	}
	
}
