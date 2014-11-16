package dev.astrolabe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dev.astrolabe.AstrolabeController;
import dev.struct.Controller;

public class LockRuleController extends Controller {

	
	private AstrolabeController astrolabeController;
	private LockRuleView view;
	
	private JButton toggleRuleLockButton;
	
	public LockRuleController(AstrolabeController astrolabeController) {
		this.astrolabeController = astrolabeController;
		view = new LockRuleView();
		createGUI();
	}
	
	@Override
	public void createGUI() {
		toggleRuleLockButton = new JButton("Rule Lock : "+astrolabeController.getStateModel().isRuleLocked());
		toggleRuleLockButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				astrolabeController.getStateModel().toggleRuleLocked();
				toggleRuleLockButton.setText("Rule Lock : "+astrolabeController.getStateModel().isRuleLocked());
				view.repaint();
			}
		});
		view.add(toggleRuleLockButton);
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
