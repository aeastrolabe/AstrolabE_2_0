package dev.astrolabe;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import dev.astrolabe.part.rete.ReteController;
import dev.astrolabe.part.rule.RuleController;
import dev.astrolabe.part.tympan.TympanController;
import dev.sky.CelestialBodyController;
import dev.sky.CelestialBodyHandler;
import dev.sky.CelestialBodyModel;
import dev.sky.Constellation;
import dev.sky.dynamik.geocentric.Sun;
import dev.sky.dynamik.heliocentric.Planet;
import dev.struct.Controller;

public class AstrolabeController extends Controller implements CelestialBodyHandler {
	
	private AstrolabeMainController astrolabeMainController;
	
	private AstrolabeView view;
	private AstrolabeStateModel stateModel;
	private AstrolabeHomeplanetModel homeplanetModel;
	private AstrolabeLocalisationModel localisationModel;
	private AstrolabeStyleModel styleModel;
	
	private TympanController tympanController;
	private ReteController reteController;
	private RuleController ruleController;
	
	JPanel background;
		
	public AstrolabeController(AstrolabeMainController astrolabeMainController) {
		this.setAstrolabeMainController(astrolabeMainController);

		stateModel = new AstrolabeStateModel();
		homeplanetModel = new AstrolabeHomeplanetModel();
		localisationModel = new AstrolabeLocalisationModel();
		styleModel = AstrolabeStyleModel.get("Default");
		
		view = new AstrolabeView(this);
		view.setPreferredSize(new Dimension(600, 400));
		view.setLayout(null);
		
		setupStateModel();
		
		CelestialBodyController.setAstrolabeController(this);
				
		tympanController = new TympanController(this);
		reteController = new ReteController(this);
		ruleController = new RuleController(this);
		
		background = new JPanel();
		
		createGUI();
		
		addStarsToView();
		addPlanetsToView();
		
		AstrolabeViewListener listener = new AstrolabeViewListener(this);
		
		view.addMouseListener(listener);
		view.addMouseMotionListener(listener);
		view.addMouseWheelListener(listener);
	}

	private void setupStateModel() {
		stateModel.setAstrolabeCenter(new Point(view.getWidth()/2, view.getHeight()/2));
	}
	
	public TympanController getTympanController() {
		return tympanController;
	}
	
	public void updateBackgroundColor() {
		background.setBackground(styleModel.getBackgroundColor());
	}
	
	@Override
	public void createGUI() {
		updateBackgroundColor();
		view.add(background,new Integer(0),0);
		view.add(tympanController.getView(),new Integer(1),0);
		view.add(reteController.getView(),new Integer(2),0);
		view.add(ruleController.getView(), new Integer(3),0);
		view.revalidate();
	}

	public void resetInitCenter() {
		stateModel.setAstrolabeCenterInit(false);
	}
	
	public void resetScale() {
		stateModel.resetAstrolabeScale();
	}
	
	public void initCenterAndBounds() {
		if (!stateModel.getAstrolabeCenterInit()) {
			stateModel.setAstrolabeCenter(new Point(view.getWidth()/2,view.getHeight()/2));
			stateModel.setAstrolabeCenterInit(true);
			
			background.setBounds(view.getVisibleRect());
			tympanController.getView().setBounds(view.getVisibleRect());
			reteController.getView().setBounds(view.getVisibleRect());
			ruleController.getView().setBounds(view.getVisibleRect());
		}	
	}

	public AstrolabeView getView() {
		return view;
	}

	/**
	 * Returns
	 * 0 if no coordinate is locked
	 * 1 if x is locked
	 * 2 if y is locked
	 * 3 if both are locked
	 */
	public int outOfBounds(Point currentPosition) {
		return (currentPosition.x <= 0 || currentPosition.x >= view.getWidth() ? 1 : 0) +
				(currentPosition.y <= 0 || currentPosition.y >= view.getHeight() ? 2 : 0);
	}

	public Point getAstrolabeCenter() {
		return (Point) stateModel.getAstrolabeCenter().clone();
	}

	public AstrolabeStateModel getStateModel() {
		return stateModel;
	}
	
	public AstrolabeLocalisationModel getLocalisationModel() {
		return localisationModel;
	}
	
	public AstrolabeHomeplanetModel getHomeplanetModel() {
		return homeplanetModel;
	}

	public void setAstrolabeCenter(Point p) {
		stateModel.setAstrolabeCenter((Point) p.clone());
	}

	public void scaleAstrolabeByRelativeTo(double r, Point p) {
		Point c = stateModel.getAstrolabeCenter();
		stateModel.setAstrolabeCenter(new Point((int) ((1-r)*p.x+r*c.x),(int) ((1-r)*p.y+r*c.y))); //TODO maybe pass to double coordinates ? there is a drift...
		stateModel.scaleAstrolabeBy(r);
	}

	public double getAstrolabeScale() {
		return stateModel.getAstrolabeScale();
	}
	
	public void setReteRotation(double r) {
		stateModel.setReteRotation(r);
	}
	
	public double getReteRotation() {
		return stateModel.getReteRotation();
	}
	
	public void resetReteRotation() {
		stateModel.setReteRotation(0);
	}
	
	public void setLatitude(double l) {
		localisationModel.setLatitude(Math.abs(l));
	}
	
	public double getAstrolabeRadius() {
		return tympanController.getOuterTropicRadius() + stateModel.TYMPAN_PADDING + stateModel.LIMBE_PADDING;
	}
	
	public double getAstrolabeInternalRadius() {
		return tympanController.getOuterTropicRadius();
	}

	/**
	 * @return the reteController
	 */
	public ReteController getReteController() {
		return reteController;
	}

	/**
	 * @param reteController the reteController to set
	 */
	public void setReteController(ReteController reteController) {
		this.reteController = reteController;
	}

	@Override
	public CelestialBodyModel getSelected() {
		return stateModel.getSelectedCelestialBody();
	}

	@Override
	public void setSelected(CelestialBodyModel celestialBody) {
		stateModel.setSelectedCelestialBody(celestialBody);
	}

	/**
	 * @return the astrolabeMainController
	 */
	public AstrolabeMainController getAstrolabeMainController() {
		return astrolabeMainController;
	}

	/**
	 * @param astrolabeMainController the astrolabeMainController to set
	 */
	public void setAstrolabeMainController(AstrolabeMainController astrolabeMainController) {
		this.astrolabeMainController = astrolabeMainController;
	}

	public void addStarsToView() {
		CelestialBodyController s;
		for(Constellation c : Constellation.getConstellationList()) {
			for(Object o : c.getStarList()) {
				s = (CelestialBodyController) o;
				if (s.isDisplayed()) {
					s.addToAstrolabeView();
				}
			}
		}
	}
	

	private void addPlanetsToView() {
		for(CelestialBodyController b : Planet.planetList) {
			if (b.isDisplayed()) {
				b.addToAstrolabeView();
			}
		}
	}
	
	public void toggleSunAdded() {
		if (stateModel.isSunDisplayed()) {
			view.add(Sun.sun.getView(),0);
		}
		else {
			view.remove(Sun.sun.getView());	
		}
	}
	
	@Override
	public void drawAllStars(Graphics2D g) {
		CelestialBodyController s;
		for(Constellation c : Constellation.getConstellationList()) {
			for(Object o : c.getStarList()) {
				s = (CelestialBodyController) o;
				if (s.isDisplayed()) {
					s.getView().draw(g);
				}
			}
		}
	}
	
	@Override
	public void drawCelestialBody(CelestialBodyController c) {
		if (c.isDisplayed()) {
			c.getView().repaint();
		}
	}
	
	@Override
	public void drawPlanets(Graphics2D g) {
		CelestialBodyController s;
		for(Object o : Planet.planetList) {
			s = (CelestialBodyController) o;
			if (s.isDisplayed()) {
				s.getView().draw(g);
			}
		}
	}
	
	@Override
	public void drawSun(Graphics2D g) {
		if (stateModel.isSunDisplayed()) {
			Sun.sun.getView().draw(g);
		}
	}

	/**
	 * @return the styleModel
	 */
	public AstrolabeStyleModel getStyleModel() {
		return styleModel;
	}

	/**
	 * @param styleModel the styleModel to set
	 */
	public void setStyleModel(AstrolabeStyleModel styleModel) {
		this.styleModel = styleModel;
		tympanController.setModels();
		reteController.setModels();
		ruleController.setModels();
	}

	public double getRuleRotation() {
		return stateModel.getRuleRotation();
	}
	

	public void setRuleRotation(double ruleRotation) {
		stateModel.setRuleRotation(ruleRotation);
	}


	public boolean closeToRule(Point p) {
		return Math.abs(Math.atan2(p.y-getAstrolabeCenter().y, p.x-getAstrolabeCenter().x) - getRuleRotation()) < Math.toRadians(4); //TODO make this tolerance constant
	}

	

}