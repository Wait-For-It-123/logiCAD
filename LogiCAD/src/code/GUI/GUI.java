package code.GUI;


import java.awt.Dimension;

import javax.swing.JFrame;


public class GUI {
	
	public void run() {
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set launch window size
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		//Pack contents of JFrame nicely
		frame.pack();
		
		//Make JFrame visible
		frame.setVisible(true);
			
	}
}
