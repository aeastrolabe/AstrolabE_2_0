package dev;


import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeMainController;
import dev.io.ImportImage;
import dev.menubar.AppMenuBar;
import dev.sky.dynamik.heliocentric.Planet;
import dev.sky.io.ImportCelestialData;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private AppMenuBar menuBar;
		
	private AstrolabeMainController astrolabeMainController;

	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
	
	public MainFrame() {
		setPreferredSize(new Dimension(1300,800));
		setLocation(20, 20);
		setResizable(false);
		setTitle("AstrolabE 2.0 - dev version");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImportImage.read("../../../gui/logo.png"));
		
		ImportCelestialData.getStarsShort();
		Planet.importPlanets();
		
		astrolabeMainController = new AstrolabeMainController();
		
		initMenuBar();
		
		initContentPane();
		
		pack();
		
		setVisible(true);
		repaint();
	}
	
	private void initMenuBar() {
		menuBar = new AppMenuBar(this);
		this.setJMenuBar(menuBar);
	}
	
	private void initContentPane() {
		setContentPane(astrolabeMainController.getView());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
//		super.paintComponents(g);
		astrolabeMainController.getView().repaint();
		menuBar.repaint();
	}
	
	public AstrolabeController getAstrolabeController() {
		return astrolabeMainController.getAstrolabeController();
	}
	
	public AppMenuBar getAppMenuBar() {
		return menuBar;
	}
	
}
