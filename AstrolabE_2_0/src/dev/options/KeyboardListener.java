package dev.options;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import dev.MainFrame;

public class KeyboardListener implements KeyEventDispatcher {
	
	MainFrame mainFrame;
	
	public KeyboardListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
//		switch (e.getKeyChar()) {
//			case 'r':
//				mainFrame.getAppMenuBar().getEditMenu().getTransformResetMenu().resetAll();
//				break;
//
//			default:
//				break;
//		}
		return false;
	}


}
