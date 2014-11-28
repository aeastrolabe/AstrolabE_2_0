package dev.astrolabe;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import dev.io.ImportImage;

@SuppressWarnings("serial")
public class AstrolabeBackgroundView extends JPanel {
	
	private AstrolabeView astrolabeView;
	private BufferedImage backgroundImage;
	
	public AstrolabeBackgroundView(AstrolabeView astrolabeView) {
		this.astrolabeView = astrolabeView;
		backgroundImage = ImportImage.read("../../../gui/background.jpg");
	}

	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, astrolabeView.getWidth(), astrolabeView.getHeight(), null);
	}

}
