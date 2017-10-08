package code.IO;
/*Class defining the input "gate" of the circuits. Input (1 or 0) of each 
 * input line in the circuit is held here.*/
import java.util.ArrayList;

public class Input implements code.logicGates.In{
	private int value = 0;
	private ArrayList<Object> childrenInputs1 = new ArrayList<Object>();
	private ArrayList<Object> childrenInputs2 = new ArrayList<Object>();
	
	private ArrayList<Object> familyTree = new ArrayList<Object>();  // A collection of all parents, grandparents, etc. of this gate
																	 // For an input, this should always be an empty set (it can't have inputs).
	
	private String id;
	
	private int xCoord, yCoord;
	
	
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
	
	public ArrayList<Object> getFamilyTree(){ return familyTree; }
	
	public void addToFamilyTree(Object newRelative) { 
		if(!familyTree.contains(newRelative)) {
			familyTree.add(newRelative); 
		}
	}
	
	public void setID(String newID) {id = newID;}
	
	public void setXCoord(int newX) {
		setxCoord(newX);
	}

	public void setYCoord(int newY) {
		setyCoord(newY);
	}

	@Override
	public void setInput1Value(int newValue) {
		value = newValue;
		
	}

	@Override
	public void setInput2Value(int newValue) {
		value = newValue;
		
	}
	
	public String getID() {return id;}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int new_yCoord) {
		yCoord = new_yCoord;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int new_xCoord) {
		xCoord = new_xCoord;
	}
}