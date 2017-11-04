package code.driver;


import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;

import code.GUI.GUI;

import code.model.*;
import code.IO.*;
import code.fileio.FileInputAndOutput;
import code.logicGates.*;

public class driver {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {	
	
		//javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		Model m = new Model();
		GUI gui = new GUI(m);
		m.setGUI(gui);
		gui.run();

//		andGate and = new andGate();
//		m.addObjectToWorkspace(and);
//		orGate or = new orGate();
//		m.addObjectToWorkspace(or);
//		Input in = new Input();
//		m.addObjectToWorkspace(in);
//		Output out = new Output();
//		m.addObjectToWorkspace(out);
//		and = new andGate();
//		m.addObjectToWorkspace(and);
//		in = new Input();
//		m.addObjectToWorkspace(in);
//		
//		m.makeConnectionFromIDs("in0", "and0");
//		m.makeConnectionFromIDs("in0", "or0");
//		m.makeConnectionFromIDs("in1", "and0");
//		m.makeConnectionFromIDs("in1", "or0");
//		m.makeConnectionFromIDs("and0", "and1");
//		m.makeConnectionFromIDs("or0", "and1");
//		m.makeConnectionFromIDs("and1", "out0");
//		
//		m.imageCoordHelper(0, 40, 100);
//		m.imageCoordHelper(4, 150, 13);
//		
//		FileInputAndOutput fileio = new FileInputAndOutput();
//		String state = m.serializeModelData();
//		String path = "save1.txt";
//		System.out.println(fileio.saveStateToFile(state, path));
//		
//		
//		System.out.println(m.serializeModelData());
		
	}
}
