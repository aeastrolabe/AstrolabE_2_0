package dev.astrolabe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dev.astrolabe.AstrolabeController;
import dev.struct.Controller;

public class FullControlModeController extends Controller {

	
	private AstrolabeController astrolabeController;
	private FullControlModeView view;
	
	private JButton toggleFullControlModeButton;
	
	public FullControlModeController(AstrolabeController astrolabeController) {
		this.astrolabeController = astrolabeController;
		view = new FullControlModeView();
		createGUI();
	}
	
	//TODO remove the TBAs
	@Override
	public void createGUI() {
		toggleFullControlModeButton = new JButton("TBA : Full Control : "+astrolabeController.getStateModel().isInFullControlMode());
		toggleFullControlModeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.getStateModel().toggleFullControlMode();
				toggleFullControlModeButton.setText("TBA : Full Control : "+astrolabeController.getStateModel().isInFullControlMode());
				astrolabeController.getView().repaint();
			}
		});
		view.add(toggleFullControlModeButton);
	}

	/**
	 * @return the view
	 */
	public FullControlModeView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(FullControlModeView view) {
		this.view = view;
	}
}
