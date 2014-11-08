package dev.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class FileMenu extends JMenu {
	
	private JMenuItem close;
	
	public FileMenu() {
		setText("File");
		initCloseButton();
	}
	
	private void initCloseButton() {
		close = new JMenuItem();
		close.setText("Close");
		close.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		this.add(close);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	
}
