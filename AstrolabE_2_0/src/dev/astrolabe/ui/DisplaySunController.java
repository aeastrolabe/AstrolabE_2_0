package dev.astrolabe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dev.astrolabe.AstrolabeController;
import dev.sky.dynamik.geocentric.Sun;
import dev.struct.Controller;

public class DisplaySunController extends Controller {

	
	private AstrolabeController astrolabeController;
	private LockRuleView view;
	
	private JButton toggleDisplaySunButton;
	
	public DisplaySunController(AstrolabeController astrolabeController) {
		this.astrolabeController = astrolabeController;
		view = new LockRuleView();
		createGUI();
	}
	
	@Override
	public void createGUI() {
		toggleDisplaySunButton = new JButton("Sun displayed : "+astrolabeController.getStateModel().isSunDisplayed());
		toggleDisplaySunButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.getStateModel().toggleSunDisplayed();
				toggleDisplaySunButton.setText("Sun displayed : "+astrolabeController.getStateModel().isSunDisplayed());
				Sun.sun.getView().repaint();
				astrolabeController.getView().repaint();
				view.repaint();
			}
		});
		view.add(toggleDisplaySunButton);
	}

	/**
	 * @return the view
	 */
	public LockRuleView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(LockRuleView view) {
		this.view = view;
	}

}