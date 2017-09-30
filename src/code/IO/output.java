package code.IO;
 /*Class defining the output "gate" of the circuits. Output (1 or 0) of each 
  * output line in the circuit is held here.*/

public class output {
	private int value;
	
	/*setter for the output value.*/
	public void setOutputValue(int newValue) {
		value = newValue;
	}
	
	/*getter for the output value.*/
	public int getOutputValue() {
		return value;
	}
}
