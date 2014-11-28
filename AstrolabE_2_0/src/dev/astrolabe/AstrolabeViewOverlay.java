package dev.astrolabe;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import dev.io.ImportImage;

@SuppressWarnings("serial")
public class AstrolabeViewOverlay extends JPanel {
	
	private AstrolabeView astrolabeView;
//	private BufferedImage overlayImage;
//	private BufferedImage cornersImage;
	private BufferedImage overlayCornersImage;
	
	public AstrolabeViewOverlay(AstrolabeView astrolabeView) {
		this.astrolabeView = astrolabeView;
		setEnabled(false);
		setOpaque(true);
//		overlayImage = ImportImage.read("../../../gui/overlay.png");
//		cornersImage = ImportImage.read("../../../gui/corners.png");
		overlayCornersImage = ImportImage.read("../../../gui/overlay+corners.png");
	}

	public void paintComponent(Graphics g) {
//		g.drawImage(overlayImage, 0, 0, astrolabeView.getWidth(), astrolabeView.getHeight(), null);
//		g.drawImage(cornersImage, 0, 0, astrolabeView.getWidth(), astrolabeView.getHeight(), null);
		g.drawImage(overlayCornersImage, 0, 0, astrolabeView.getWidth(), astrolabeView.getHeight(), null);
	}
}
