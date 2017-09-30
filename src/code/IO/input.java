package code.IO;
/*Class defining the input "gate" of the circuits. Input (1 or 0) of each 
 * input line in the circuit is held here.*/
import java.util.ArrayList;

public class input {
	private int value;
	private ArrayList<Object> childrenInputs1 = new ArrayList<Object>();
	private ArrayList<Object> childrenInputs2 = new ArrayList<Object>();
	
	/*setter for the input value.*/
	public void setInputValue(int newValue) {
		value = newValue;
	}
	
	/*getter for the input value.*/
	public int getInputValue() {
		return value;
	}
	/*Adds child gate object to array list signifying that
	 * the input is going to the 1st input of the gate*/
	public void addChild_Input1(Object newChild) {
		childrenInputs1.add(newChild);
	}
	/*Adds child gate object to array list signifying that
	 * the input is going to the 2nd input of the gate*/
	public void addChild_Input2(Object newChild) {
		childrenInputs2.add(newChild);
	}
	/*Gets the ArrayList of objects where the input
	 * is connected to the 1st input of the child gate*/
	public ArrayList<Object> getInput1Children() {
		return childrenInputs1;
	}
	/*Gets the ArrayList of objects where the input
	 * is connected to the 2nd input of the child gate*/
	public ArrayList<Object> getInput2Children() {
		return childrenInputs2;
	}
	
}
