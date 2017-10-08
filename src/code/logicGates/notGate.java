package code.logicGates;

public class notGate extends Gate{

	@Override
	public int evaluateInputSignals() {
		// TODO Auto-generated method stub
		if(input1Value == INVALID) {
			outputValue = INVALID;
		}
		else if(input1Value == 0) {
			outputValue = 1;
		}
		else if(input1Value == 1) {
			outputValue = 0;
		}	
		return outputValue;
	}
}
