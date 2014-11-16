package dev.astrolabe;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class AstrolabeViewListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	AstrolabeController astrolabeController;
	
	/*
	 * Temporary variables
	 */
	
	Point dragMotion = new Point();
	Point savedPosition = new Point();
	Point currentPosition = new Point();
	Point savedMousePosition = new Point();
	Point currentMousePosition = new Point();
	
	int rotatingPart = 0;
	
	private final int RETE_ROTATING = 1;
	private final int RULE_ROTATING = 2;
	
	double savedReteRotation = 0;
	
	double savedRuleRotation = 0;
	
	private int pressedButton = 0;
	
	private final int ROTATION_BUTTON = 1;
	private final int DRAG_BUTTON = 3;
	
	public AstrolabeViewListener(AstrolabeController astrolabeController) {
		this.astrolabeController = astrolabeController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			astrolabeController.setSelected(null);
			astrolabeController.getAstrolabeMainController().getCBDDcontroller().updateDisplayedData();
			astrolabeController.drawAllStars();
		}
		if (e.getClickCount() == 2) {
			astrolabeController.setRuleRotation(Math.atan2(e.getY() - astrolabeController.getAstrolabeCenter().y, e.getX() - astrolabeController.getAstrolabeCenter().x ));
			astrolabeController.getView().repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedButton = e.getButton();
		savedMousePosition.setLocation(e.getPoint());
		if (e.getButton() == DRAG_BUTTON) {
			savedPosition = astrolabeController.getAstrolabeCenter();
			currentPosition = (Point) savedPosition.clone();
		}
		if (e.getButton() == ROTATION_BUTTON && !astrolabeController.closeToRule(savedMousePosition)) {
			rotatingPart = RETE_ROTATING;
			savedReteRotation = astrolabeController.getReteRotation();
			if (astrolabeController.getStateModel().isRuleLocked()) {
				savedRuleRotation = astrolabeController.getRuleRotation();
			}
		}
		if (e.getButton() == ROTATION_BUTTON && astrolabeController.closeToRule(savedMousePosition)) {
			rotatingPart = RULE_ROTATING;
			savedRuleRotation = astrolabeController.getRuleRotation();
			if (astrolabeController.getStateModel().isRuleLocked()) {
				savedReteRotation = astrolabeController.getReteRotation();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentMousePosition.setLocation(e.getPoint().getX(), e.getPoint().getY());
		
		if (pressedButton == DRAG_BUTTON) {
			dragMotion.setLocation(currentMousePosition.x-savedMousePosition.x, currentMousePosition.y-savedMousePosition.y);
			currentPosition.setLocation(currentPosition.x + dragMotion.x, currentPosition.y + dragMotion.y); //translate(dragMotion.x, dragMotion.y);
			moveConstrainedAstrolabeCenter();
		}
		
		if (pressedButton == ROTATION_BUTTON && rotatingPart == RETE_ROTATING) {
			double anglei = Math.atan2(savedMousePosition.y - astrolabeController.getAstrolabeCenter().y,
					savedMousePosition.x - astrolabeController.getAstrolabeCenter().x);
			double anglef = Math.atan2(currentMousePosition.y - astrolabeController.getAstrolabeCenter().y,
					currentMousePosition.x - astrolabeController.getAstrolabeCenter().x);
			astrolabeController.setReteRotation(anglef-anglei+savedReteRotation);
			if (astrolabeController.getStateModel().isRuleLocked()) {
				astrolabeController.setRuleRotation(anglef-anglei+savedRuleRotation);
			}
		}
		if (pressedButton == ROTATION_BUTTON && rotatingPart == RULE_ROTATING) {
			double anglei = Math.atan2(savedMousePosition.y - astrolabeController.getAstrolabeCenter().y,
					savedMousePosition.x - astrolabeController.getAstrolabeCenter().x);
			double anglef = Math.atan2(currentMousePosition.y - astrolabeController.getAstrolabeCenter().y,
					currentMousePosition.x - astrolabeController.getAstrolabeCenter().x);
			astrolabeController.setRuleRotation(anglef-anglei+savedRuleRotation);
			if (astrolabeController.getStateModel().isRuleLocked()) {
				astrolabeController.setReteRotation(anglef-anglei+savedReteRotation);
			}
		}
		astrolabeController.getView().repaint();
	}
	
	private void moveConstrainedAstrolabeCenter() {
		switch (astrolabeController.outOfBounds(currentPosition)) {
		case 0:
			astrolabeController.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
			break;
		case 1:
//			currentPosition.translate(-dragMotion.x, 0);
			astrolabeController.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		case 2:
//			currentPosition.translate(0, -dragMotion.y);
			astrolabeController.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		case 3:
			astrolabeController.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressedButton == DRAG_BUTTON) {
			astrolabeController.getView().repaint();
			savedMousePosition = new Point();
			savedPosition = new Point();
			currentMousePosition = new Point();
			currentPosition = new Point();
			dragMotion = new Point();
			pressedButton = 0;
		}
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			astrolabeController.scaleAstrolabeByRelativeTo(AstrolabeStateModel.SCALING_RATE, e.getPoint());
		}
		else {
			astrolabeController.scaleAstrolabeByRelativeTo(1/AstrolabeStateModel.SCALING_RATE, e.getPoint());
		}
		
		astrolabeController.getView().repaint();
	}

}
