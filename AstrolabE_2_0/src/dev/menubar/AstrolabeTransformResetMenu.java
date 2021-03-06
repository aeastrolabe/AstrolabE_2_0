package dev.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dev.MainFrame;
import dev.astrolabe.AstrolabeController;

@SuppressWarnings("serial")
public class AstrolabeTransformResetMenu extends JMenu {
	
	private AstrolabeController astrolabeController;
	
	private JMenuItem resetAll;
	
	private JMenuItem resetPosition;
	
	private JMenuItem resetScale;
	
	private JMenuItem resetRotation;

	public AstrolabeTransformResetMenu(MainFrame mainFrame) {
		astrolabeController = mainFrame.getAstrolabeController();
		setText("Transform reset");
		initResetAll();
		addSeparator();
		initResetPosition();
		initResetScale();
		initResetRotation();
	}

	private void initResetAll() {
		resetAll = new JMenuItem();
		resetAll.setText("Reset all");
		resetAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});
		resetAll.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		this.add(resetAll);
	}

	public void resetAll() {
		astrolabeController.resetInitCenter();
		astrolabeController.initCenterAndBounds();
		astrolabeController.resetScale();
		
		//TODO maybe not rete and rule rotation....
//		astrolabeController.resetReteRotation();
		
		astrolabeController.getView().repaint();
	}

	private void initResetPosition() {
		resetPosition = new JMenuItem();
		resetPosition.setText("Reset position");
		resetPosition.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.resetInitCenter();
				astrolabeController.initCenterAndBounds();
				astrolabeController.getView().repaint();
			}
		});
		this.add(resetPosition);
	}
	
	private void initResetScale() {
		resetScale = new JMenuItem();
		resetScale.setText("Reset scale");
		resetScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.resetScale();
				astrolabeController.getView().repaint();
			}
		});
		this.add(resetScale);
	}
	
	private void initResetRotation() {
		resetRotation = new JMenuItem();
		resetRotation.setText("Reset rotation");
		resetRotation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.resetReteRotation();
				astrolabeController.getView().repaint();
			}
		});
		this.add(resetRotation);
	}
}
