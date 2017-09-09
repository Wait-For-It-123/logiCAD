package code.logicGates;

public abstract class gate {
	
	protected boolean input1;
	protected boolean input2;
	
	abstract boolean evaluateInputSignals();
	
	public void setInput1(boolean newInput) {
		input1 = newInput;
	}
	public void setInput2(boolean newInput) {
		input2 = newInput;
	}


}
