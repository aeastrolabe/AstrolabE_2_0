package dev.astrolabe;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import dev.astrolabe.part.rete.ReteController;
import dev.astrolabe.part.tympan.TympanController;
import dev.sky.CelestialBodyController;
import dev.sky.CelestialBodyHandler;
import dev.sky.Constellation;
import dev.struct.Controller;

public class AstrolabeController extends Controller implements CelestialBodyHandler {
	
	private AstrolabeMainController astrolabeMainController;
	
	private AstrolabeView view;
	private AstrolabeStateModel stateModel;
	private AstrolabeHomeplanetModel homeplanetModel;
	private AstrolabeLocalisationModel localisationModel;
	
	private TympanController tympanController;
	private ReteController reteController;
	
	JPanel background;
		
	public AstrolabeController(AstrolabeMainController astrolabeMainController) {
		this.setAstrolabeMainController(astrolabeMainController);

		stateModel = new AstrolabeStateModel();
		homeplanetModel = new AstrolabeHomeplanetModel();
		localisationModel = new AstrolabeLocalisationModel();
		
		view = new AstrolabeView(this);
		view.setPreferredSize(new Dimension(600, 400));
		view.setLayout(null);
		
		setupModel();
		
		CelestialBodyController.setAstrolabeController(this);
				
		tympanController = new TympanController(this);
		setReteController(new ReteController(this));
		
		background = new JPanel();
		
		createBackground();
		createGUI();
		
		AstrolabeViewListener listener = new AstrolabeViewListener(this);
		
		view.addMouseListener(listener);
		view.addMouseMotionListener(listener);
		view.addMouseWheelListener(listener);
	}

	private void setupModel() {
		stateModel.setAstrolabeCenter(new Point(view.getWidth()/2, view.getHeight()/2));
	}
	
	public TympanController getTympanController() {
		return tympanController;
	}

	private void createBackground() {
		updateBackgroundColor();
		view.add(background,new Integer(0),0);
		view.revalidate();
	}
	
	@Override
	public void createGUI() {
		updateBounds();
		view.add(tympanController.getView(),new Integer(1),0);
		view.add(getReteController().getView(),new Integer(2),0);
		view.revalidate();
	}

	public void resetInitCenter() {
		stateModel.setAstrolabeCenterInit(false);
	}
	
	public void resetScale() {
		stateModel.resetAstrolabeScale();
	}
	
	public void initCenter() {
		if (!stateModel.getAstrolabeCenterInit()) {
			stateModel.setAstrolabeCenter(new Point(view.getWidth()/2,view.getHeight()/2));
			stateModel.setAstrolabeCenterInit(true);
		}	
	}

	public void updateBounds() {
		background.setBounds(view.getVisibleRect());
		tympanController.getView().setBounds(view.getVisibleRect());
		getReteController().getView().setBounds(view.getVisibleRect());
	}

	public AstrolabeView getView() {
		return view;
	}

	public void updateBackgroundColor() {
		background.setBackground(AstrolabeStyleModel.getSelected().getBackgroundColor());
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

	public void scaleAstrolabeBy(double r) {
		stateModel.scaleAstrolabeBy(r);
	}
	
	//TODO implement this !
	public void scaleAstrolabeByRelativeTo(double r, Point p) {
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
	public CelestialBodyController getSelected() {
		return stateModel.getSelectedCelestialBody();
	}

	@Override
	public void setSelected(CelestialBodyController celestialBody) {
		stateModel.setSelectedCelestialBody(celestialBody);
	}

	@Override
	public void drawStars(Graphics2D g, Constellation c) {
		CelestialBodyController s;
		for(Object o : c.getStarList().toArray()) {
			s = (CelestialBodyController) o;
			if (s.isDisplayed()) {
				s.getView().draw(g);
			}
		}
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
}
