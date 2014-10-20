package dev.menubar;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeStyleModel;
import dev.options.AstrolabeLookListener;

@SuppressWarnings("serial")
public class AstrolabeLookMenu extends JMenu {

	private JRadioButtonMenuItem[] astrolabeLookRadioButtonMenuItems;
	
	
	public AstrolabeLookMenu(AstrolabeController astrolabeController) {
		this.setText("Astrolabe style");
	
		
		ButtonGroup bg = new ButtonGroup();
		
//		AstrolabeLookModel.initAstrolabeLookOption();
		int n = AstrolabeStyleModel.values().length;
		astrolabeLookRadioButtonMenuItems = new JRadioButtonMenuItem[n];
		
		AstrolabeLookListener listener = new AstrolabeLookListener(bg,astrolabeController);
		
		for(int i=0;i<n;i++) {
			astrolabeLookRadioButtonMenuItems[i] = new JRadioButtonMenuItem();
			astrolabeLookRadioButtonMenuItems[i].setText(AstrolabeStyleModel.get(i).getName());
			bg.add(astrolabeLookRadioButtonMenuItems[i]);
			astrolabeLookRadioButtonMenuItems[i].addActionListener(listener);
			this.add(astrolabeLookRadioButtonMenuItems[i]);
		}
		
		astrolabeLookRadioButtonMenuItems[n-1].setSelected(true);
	}
	
}
