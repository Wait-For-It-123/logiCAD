package code.driver;


import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;

import code.GUI.GUI;

import code.GUI.tools.GuiObserver;
import code.model.*;
import code.IO.*;
import code.logicGates.*;

public class driver {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		Model m = new Model();
		GUI gui = new GUI(m);
		new GuiObserver(gui);
		m.setGUI(gui);
		gui.run();

	}
}
