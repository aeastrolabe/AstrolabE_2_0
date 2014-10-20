package dev.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class HelpMenu extends JMenu {
	
	private JMenuItem apropos;
	
	public HelpMenu() {
		setText("Help");
		initCloseButton();
	}
	
	private void initCloseButton() {
		apropos = new JMenuItem();
		apropos.setText("A propos");
		this.add(apropos);
		
		apropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AproposFrame();
			}
		});
	}
	
	
}
