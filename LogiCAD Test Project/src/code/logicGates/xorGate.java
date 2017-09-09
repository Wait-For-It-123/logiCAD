package code.logicGates;

public class xorGate extends gate {

	@Override
	public boolean evaluateInputSignals() {
		// TODO Auto-generated method stub
		if (input1 != input2) {
			return true;
		}
		else {
			return false;
		}
	}

}
