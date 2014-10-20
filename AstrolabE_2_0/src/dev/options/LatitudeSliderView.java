package dev.options;

import java.awt.Graphics;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class LatitudeSliderView extends JSlider {
	
	public LatitudeSliderView() {
		super(JSlider.HORIZONTAL,-90,90,48);
		setMajorTickSpacing(15);
		setMinorTickSpacing(5);
		setPaintTicks(true);
		setPaintLabels(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
	}

}
