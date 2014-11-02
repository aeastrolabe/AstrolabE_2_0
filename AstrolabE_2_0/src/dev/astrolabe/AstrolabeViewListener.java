package dev.astrolabe;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class AstrolabeViewListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	AstrolabeController controller;
	
	/*
	 * Temporary variables
	 */
	
	Point dragMotion = new Point();
	Point savedPosition = new Point();
	Point currentPosition = new Point();
	Point savedMousePosition = new Point();
	Point currentMousePosition = new Point();
	
	double savedReteRotation = 0;
	
	private int pressedButton = 0;
	
	private final int ROTATION_BUTTON = 1;
	private final int DRAG_BUTTON = 3;
	
	public AstrolabeViewListener(AstrolabeController astrolabeController) {
		controller = astrolabeController;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO
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
			savedPosition = (Point) controller.getAstrolabeCenter();
			currentPosition = (Point) savedPosition.clone();
		}
		if (e.getButton() == ROTATION_BUTTON) {
			savedReteRotation = controller.getReteRotation();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentMousePosition = e.getPoint();
		
		if (pressedButton == DRAG_BUTTON) {
			dragMotion.setLocation(currentMousePosition.x-savedMousePosition.x, currentMousePosition.y-savedMousePosition.y);
			currentPosition.translate(dragMotion.x, dragMotion.y);
			moveConstrainedAstrolabeCenter();
		}
		
		if (pressedButton == ROTATION_BUTTON) {
			double anglei = Math.atan2(savedMousePosition.y - controller.getAstrolabeCenter().y,
					savedMousePosition.x - controller.getAstrolabeCenter().x);
			double anglef = Math.atan2(currentMousePosition.y - controller.getAstrolabeCenter().y,
					currentMousePosition.x - controller.getAstrolabeCenter().x);
			controller.setReteRotation(anglef-anglei+savedReteRotation);
		}
		controller.getView().repaint();
	}
	
	private void moveConstrainedAstrolabeCenter() {
		switch (controller.outOfBounds(currentPosition)) {
		case 0:
			controller.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
			break;
		case 1:
//			currentPosition.translate(-dragMotion.x, 0);
			controller.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		case 2:
//			currentPosition.translate(0, -dragMotion.y);
			controller.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		case 3:
			controller.setAstrolabeCenter((Point) currentPosition.clone());
			currentPosition = (Point) savedPosition.clone();
//			currentPosition.translate(-dragMotion.x, -dragMotion.y);
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressedButton == DRAG_BUTTON) {
			controller.getView().repaint();
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
			controller.scaleAstrolabeBy(AstrolabeStateModel.SCALING_RATE);
		}
		else {
			controller.scaleAstrolabeBy(1/AstrolabeStateModel.SCALING_RATE);
		}
		controller.getView().repaint();
	}

}
