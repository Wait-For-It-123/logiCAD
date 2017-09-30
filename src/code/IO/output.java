package code.IO;
 /*Class defining the output "gate" of the circuits. Output (1 or 0) of each 
  * output line in the circuit is held here.*/

public class output {
	private int value;
	private Object inputSource = null;
	
	/*setter for the output value.*/
	public void setOutputValue(int newValue) {
		value = newValue;
	}
	
	/*getter for the output value.*/
	public int getOutputValue() {
		return value;
	}
	/*sets the input Object gate that is setting the value of the output
	 * Returns 4 if successful and 0 otherwise*/
	public int setInput(Object newInputSource) {
		if (inputSource == null){
			inputSource = newInputSource;
			return 4;
		}
		else {
			return 0;
		}
	/*Returns the object that is setting the input*/	
	}
	public Object getInput() {
		return inputSource;
	}
}
