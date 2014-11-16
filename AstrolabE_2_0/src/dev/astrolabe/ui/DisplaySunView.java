package dev.astrolabe.ui;

import java.awt.FlowLayout;
import java.awt.Graphics;

import dev.struct.View;

@SuppressWarnings("serial")
public class DisplaySunView extends View {

	public DisplaySunView() {
		super();
		setLayout(new FlowLayout());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
	}

}
