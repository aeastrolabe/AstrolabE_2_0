package dev.mission.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;

import dev.mission.Mission;
import dev.struct.Controller;

public class MissionController extends Controller {
	
	private Mission model;
	private MissionView view;
	
	@Deprecated
	public MissionController() {
		view = new MissionView();
		
		createGUI();
	}
	
	public MissionController(Mission m) {
		model = m;
		
		view = new MissionView();
		
		createGUI();
	}
	
	

	@Override
	public void createGUI() {
		int no, nu;
		if (model == null) {
			no = 0;
			nu = 0;
		}
		else{
			no = model.getOrderedStepsCount();
			nu = model.getUnorderedStepsCount();
		}
		view.setLayout(new GridLayout(no + nu + 2, 1));
		view.setPreferredSize(new Dimension(1*200, (no + nu)*20));
		
		initLabels(no, nu);
		addLabels();
	}
	
	public void initLabels(int no, int nu) {
		JLabel[] o = new JLabel[no+1];
		o[0] = new JLabel("Ordered missions");
		view.add(o[0]);
		System.out.println(no);
		for(int i = 0; i < no; i++) {
			o[i+1] = new JLabel(model.getOrderedSteps().get(i).getInstruction());
			view.add(o[i+1]);
		}
		view.setOrdered(o);
		
		JLabel[] u = new JLabel[nu+1];
		u[0] = new JLabel("Unordered missions");
		view.add(u[0]);
		for(int i = 0; i < nu; i++) {
			u[i+1] = new JLabel(model.getUnorderedSteps().get(i).getInstruction());
			view.add(u[i+1]);
		}
		view.setUnordered(u);
	}
	
	public void addLabels() {
		for(JLabel l : view.getOrdered()) {
			view.add(l);
		}
		for(JLabel l : view.getUnordered()) {
			view.add(l);
		}
	}

	public MissionView getView() {
		return view;
	}

}