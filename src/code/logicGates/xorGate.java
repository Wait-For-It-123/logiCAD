package code.logicGates;

public class xorGate extends Gate{
	
	@Override
	public int evaluateInputSignals() {
		if(input1Value == INVALID || input2Value == INVALID) {
			outputValue = INVALID;
		}
		else if(input1Value == 0 && input2Value == 0) {
			outputValue = 0;
		}
		else if(input1Value == 0 && input2Value == 1) {
			outputValue = 1;
		}
		else if(input1Value == 1 && input2Value == 0) {
			outputValue = 1;
		}
		else if(input1Value == 1 && input2Value == 1) {
			outputValue = 0;
		}
		return outputValue;
	}


}
