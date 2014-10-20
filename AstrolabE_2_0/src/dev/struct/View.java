package dev.struct;

import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class View extends JPanel {

	public View(LayoutManager manager) {
		super(manager);
	}
	
	public View() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
	}

}
