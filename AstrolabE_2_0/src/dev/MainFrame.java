package dev;

import io.ImportCelestialData;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeMainController;
import dev.menubar.AppMenuBar;
import dev.options.KeyboardListener;
import dev.sky.CelestialBodyMasterController;

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
		setPreferredSize(new Dimension(1200, 800));
		setLocation(20, 20);
		setResizable(false);
		setTitle("AstrolabE 2.0 - dev version");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		astrolabeMainController = new AstrolabeMainController();
		
		CelestialBodyMasterController.setAstrolabeController(astrolabeMainController.getAstrolabeController());
		ImportCelestialData.getStars();
		
		initMenuBar();
		
		initContentPane();
		
		pack();
		menuBar.repaint();
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyboardListener(this));
		
		setVisible(true);
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
		super.paintComponents(g);
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
