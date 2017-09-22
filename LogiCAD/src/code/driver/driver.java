package code.driver;

import code.GUI.GUI;
import code.logicGates.*;

public class driver {
	
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		gui.run();
		
		andGate andGate1 = new andGate();
		
		andGate1.setInput1(true);
		andGate1.setInput2(true);
		
		System.out.println(andGate1.evaluateInputSignals());
		
		andGate1.setInput1(false);
		andGate1.setInput2(true);
		
		System.out.println(andGate1.evaluateInputSignals());
		
		andGate1.setInput1(true);
		andGate1.setInput2(false);
		
		System.out.println(andGate1.evaluateInputSignals());
		
		andGate1.setInput1(false);
		andGate1.setInput2(false);
		
		System.out.println(andGate1.evaluateInputSignals());
		
		
	}

}
