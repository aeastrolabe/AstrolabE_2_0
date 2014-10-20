package dev.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import dev.MainFrame;

@SuppressWarnings("serial")
public class OptionsMenu extends JMenu {
	
	private AstrolabeLookMenu astrolabeLook;
	
	private JMenuItem showMap;
	
	public OptionsMenu(MainFrame f) {

		this.setText("Options");
		
		initAstrolabeLookMenu(f);
		this.addSeparator();
		initShowMap();
		
	}
	
	private void initAstrolabeLookMenu(MainFrame frame) {
		astrolabeLook = new AstrolabeLookMenu(frame.getAstrolabeController());
		this.add(astrolabeLook);
	}
	
	private void initShowMap() {
		showMap = new JMenuItem();
		showMap.setText("Show map");
		this.add(showMap);
	}
}
