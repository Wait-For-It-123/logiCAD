package code.logicGates;

public class notGate {
	
	private boolean input1;
	
	public boolean evaluateInputSignals() {
		return (!input1);
	}
	
	public void setInput1(boolean newInput) {
		input1 = newInput;
	}

}
