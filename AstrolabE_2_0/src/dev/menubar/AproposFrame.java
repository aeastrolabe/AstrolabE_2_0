package dev.menubar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AproposFrame extends JFrame {
	
	public AproposFrame() {
		super();
		setTitle("A propos");
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setPreferredSize(new Dimension(200, 150));
		JTextArea text = new JTextArea(
				"Logiciel créé par Nikolas Stott " +
				"\n" +
				"\n" +
				"Basé sur le logiciel AstrolabE 1.0" +
				"\n" +
				"créé par\n" +
				"  Romain Maire,\n" +
				"  Julien Tanneau,\n" +
				"  Nikolas Stott"
				);
		text.setEditable(false);
		this.setContentPane(text);
		pack();
		setVisible(true);
	}
}
