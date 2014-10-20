package dev.menubar;

import javax.swing.JMenu;

import dev.MainFrame;

@SuppressWarnings("serial")
public class EditMenu extends JMenu {
	
	private AstrolabeTransformResetMenu transformResetMenu;
	
	public EditMenu(MainFrame mainFrame) {
		this.setText("Edit");
		initTransformResetMenu(mainFrame);
	}

	private void initTransformResetMenu(MainFrame mainFrame) {
		transformResetMenu = new AstrolabeTransformResetMenu(mainFrame);
		this.add(transformResetMenu);
	}
	
	public AstrolabeTransformResetMenu getTransformResetMenu() {
		return transformResetMenu;
	}
	
}
