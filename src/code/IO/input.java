package code.IO;
/*Class defining the input "gate" of the circuits. Input (1 or 0) of each 
 * input line in the circuit is held here.*/

public class input {
	private int value;
	
	/*setter for the input value.*/
	public void setInputValue(int newValue) {
		value = newValue;
	}
	
	/*getter for the input value.*/
	public int getInputValue() {
		return value;
	}
}
