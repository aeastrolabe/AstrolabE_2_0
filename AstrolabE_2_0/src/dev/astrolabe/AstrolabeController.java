package dev.astrolabe;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import dev.astrolabe.part.rete.ReteController;
import dev.astrolabe.part.tympan.TympanController;
import dev.struct.Controller;

public class AstrolabeController extends Controller {
	
	AstrolabeMainController astrolabeMainController;
	
	private AstrolabeView view;
	private AstrolabeStateModel drawingModel;
	private AstrolabeHomeplanetModel homeplanetModel;
	private AstrolabeLocalisationModel localisationModel;
	
	private TympanController tympanController;
	ReteController reteController;
	
	JPanel background;
	
	public AstrolabeController(AstrolabeMainController astrolabeMainController) {
		this.astrolabeMainController = astrolabeMainController;
		
		drawingModel = new AstrolabeStateModel();
		homeplanetModel = new AstrolabeHomeplanetModel();
		localisationModel = new AstrolabeLocalisationModel();
		
		view = new AstrolabeView(this);
		view.setPreferredSize(new Dimension(600, 400));
		view.setLayout(null);
		
		setupModel();
				
		tympanController = new TympanController(this);
		reteController = new ReteController(this);
		
		background = new JPanel();
		
		createBackground();
		createGUI();
		
		AstrolabeViewListener listener = new AstrolabeViewListener(this);
		
		view.addMouseListener(listener);
		view.addMouseMotionListener(listener);
		view.addMouseWheelListener(listener);
	}

	private void setupModel() {
		drawingModel.setAstrolabeCenter(new Point(view.getWidth()/2, view.getHeight()/2));
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
		view.add(reteController.getView(),new Integer(2),0);
		view.revalidate();
	}

	public void resetInitCenter() {
		drawingModel.setAstrolabeCenterInit(false);
	}
	
	public void resetScale() {
		drawingModel.resetAstrolabeScale();
	}
	
	public void initCenter() {
		if (!drawingModel.getAstrolabeCenterInit()) {
			drawingModel.setAstrolabeCenter(new Point(view.getWidth()/2,view.getHeight()/2));
			drawingModel.setAstrolabeCenterInit(true);
		}	
	}

	public void updateBounds() {
		background.setBounds(view.getVisibleRect());
		tympanController.getView().setBounds(view.getVisibleRect());
		reteController.getView().setBounds(view.getVisibleRect());
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
		return (Point) drawingModel.getAstrolabeCenter().clone();
	}

	public AstrolabeStateModel getStateModel() {
		return drawingModel;
	}
	
	public AstrolabeLocalisationModel getLocalisationModel() {
		return localisationModel;
	}
	
	public AstrolabeHomeplanetModel getHomeplanetModel() {
		return homeplanetModel;
	}

	public void setAstrolabeCenter(Point p) {
		drawingModel.setAstrolabeCenter((Point) p.clone());
	}

	public void scaleAstrolabeBy(double r) {
		drawingModel.scaleAstrolabeBy(r);
	}
	
	@SuppressWarnings("unused")
	public void scaleAstrolabeByRelativeTo(double r, Point p) {
		drawingModel.scaleAstrolabeBy(r);
	}
	
	public double getAstrolabeScale() {
		return drawingModel.getAstrolabeScale();
	}
	
	public void setReteRotation(double r) {
		drawingModel.setReteRotation(r);
	}
	
	public double getReteRotation() {
		return drawingModel.getReteRotation();
	}
	
	public void resetReteRotation() {
		drawingModel.setReteRotation(0);
	}
	
	public void setLatitude(double l) {
		localisationModel.setLatitude(Math.abs(l));
	}
	
	public double getAstrolabeRadius() {
		return tympanController.getOuterTropicRadius() + drawingModel.TYMPAN_PADDING + drawingModel.LIMBE_PADDING;
	}

}
