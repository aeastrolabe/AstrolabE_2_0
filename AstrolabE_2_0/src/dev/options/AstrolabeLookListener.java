package dev.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeStyleModel;

public class AstrolabeLookListener implements ActionListener {

	ButtonGroup astrolabeLookRadioGroup;
	
	AstrolabeController astrolabeController;
	
	public AstrolabeLookListener(ButtonGroup bg, AstrolabeController astrolabeController) {
		astrolabeLookRadioGroup = bg;
		this.astrolabeController = astrolabeController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		astrolabeController.setStyleModel(AstrolabeStyleModel.get(
						((JRadioButtonMenuItem) e.getSource()).getText()
						)
				);
		astrolabeController.updateBackgroundColor();
		astrolabeController.getView().repaint();
	}

}
