package code.IO;
 /*Class defining the output "gate" of the circuits. Output (1 or 0) of each 
  * output line in the circuit is held here.*/

import java.util.ArrayList;

import code.logicGates.Gate;

public class Output implements code.logicGates.In{
	private int value = -1;
	private Object inputSource = null;
	private ArrayList<Object> familyTree = new ArrayList<Object>();  // A collection of all parents, grandparents, etc. of this gate
	
	private String id;
	
	private int xCoord, yCoord;
	
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
	public int setInput(Object input) {
		if (inputSource == null){
			inputSource = input;
			addToFamilyTree(input);
			if(input instanceof Gate) {
				Gate g = (Gate) input;
				ArrayList<Object> tree = g.getFamilyTree();
				for(int i = 0; i < tree.size(); ++i) {
					addToFamilyTree(tree.get(i));
				}
			}
			else if(input.getClass() == Input.class) {
				Input in = (Input) input;
				ArrayList<Object> tree = in.getFamilyTree();
				for(int i = 0; i < tree.size(); ++i) {
					addToFamilyTree(tree.get(i));
				}	
			}
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
	
	public void removeInputSource() {
		inputSource = null;
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