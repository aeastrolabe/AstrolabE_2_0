package dev.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeStyleModel;
import dev.options.AstrolabeLookListener;

@SuppressWarnings("serial")
public class AstrolabeLookMenu extends JMenu {

	private JRadioButtonMenuItem[] astrolabeLookRadioButtonMenuItems;
	
	
	public AstrolabeLookMenu(AstrolabeController astrolabeController) {
		this.setText("Astrolabe style");
	
		
		ButtonGroup bg = new ButtonGroup();
		
		int N = AstrolabeStyleModel.values().length;
		astrolabeLookRadioButtonMenuItems = new JRadioButtonMenuItem[N];
		
		AstrolabeLookListener listener = new AstrolabeLookListener(bg,astrolabeController);
		
		for(int i = 0; i < N; i++) {
			astrolabeLookRadioButtonMenuItems[i] = new JRadioButtonMenuItem();
			astrolabeLookRadioButtonMenuItems[i].setText(AstrolabeStyleModel.get(i).getName());
			bg.add(astrolabeLookRadioButtonMenuItems[i]);
			astrolabeLookRadioButtonMenuItems[i].addActionListener(listener);
			this.add(astrolabeLookRadioButtonMenuItems[i]);
		}
		astrolabeLookRadioButtonMenuItems[0].setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		astrolabeLookRadioButtonMenuItems[1].setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		astrolabeLookRadioButtonMenuItems[2].setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		astrolabeLookRadioButtonMenuItems[3].setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_4, ActionEvent.CTRL_MASK));
			
		
		astrolabeLookRadioButtonMenuItems[N-1].setSelected(true);
	}
	
}
