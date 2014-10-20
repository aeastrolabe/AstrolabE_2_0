package dev.menubar;

import java.awt.Graphics;

import javax.swing.JMenuBar;

import dev.MainFrame;

@SuppressWarnings("serial")
public class AppMenuBar extends JMenuBar {
	
	private MainFrame mainFrame;
	
	private FileMenu file;
	private EditMenu edit;
	private OptionsMenu options;
	private HelpMenu help;
	
	public AppMenuBar(MainFrame mainFrame) {
		this.setMainFrame(mainFrame);
		
		initFileMenu();
		initEditMenu();
		initOptionsMenu();
		initAproposMenu();
	}
	
	private void initFileMenu() {
		file = new FileMenu();
		this.add(file);
	}
	
	private void initEditMenu() {
		edit = new EditMenu(mainFrame);
		this.add(edit);
	}
	
	private void initOptionsMenu() {
		options = new OptionsMenu(mainFrame);
		this.add(options);
	}
	
	private void initAproposMenu() {
		help = new HelpMenu();
		this.add(help);
	}
	
	

	/**
	 * @return the mainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintComponents(g);
		file.repaint();
		edit.repaint();
		options.repaint();
		help.repaint();
	}

	public EditMenu getEditMenu() {
		return edit;
	}
	
}
