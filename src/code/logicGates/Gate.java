package code.logicGates;

import java.util.ArrayList;
import code.IO.*;

public abstract class Gate implements In, Out {
	
	protected static final int INVALID = -1;
	protected static final int NOT = 3;
	protected Object input1Source = null;
	protected Object input2Source = null;
	protected int input1Value = INVALID;
	protected int input2Value = INVALID;
	protected int outputValue = INVALID;
	protected ArrayList<Object> outputsIntoInputs1 = new ArrayList<Object>();
	protected ArrayList<Object> outputsIntoInputs2 = new ArrayList<Object>();
	
	protected ArrayList<Object> familyTree = new ArrayList<Object>();  // A collection of all parents, grandparents, etc. of this gate
	
	protected String id;
	
	protected int xCoord;
	protected int yCoord;

	public int evaluateInputSignals() {return input1Value + input2Value;};

	public int setInput(Object input) {
		
//		if(input.getClass() == code.IO.Output) {
//			return 0; // An output cannot be set as an input
//					  // A return value of 0 means setting input is not possible.
//		}
		
		if(input1Source == null) {
			
			input1Source = input;
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
			if(this.getClass() == code.logicGates.notGate.class) {
				return NOT;
			}
			return 1;
		}
		else if((input2Source == null) && (this.getClass() != code.logicGates.notGate.class)) {
			
			input2Source = input;
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
			
			return 2;
		}
		
		return 0; // could not set an input source
	}
	
	public void setInput1Value(int newValue) {
		input1Value = newValue;
	}
	
	public void setInput2Value(int newValue) {
		input2Value = newValue;
	}
	
	public int getInput1Value() {return input1Value;}
	
	public int getInput2Value() {return input2Value;}
	
	public void setChild_Inputs1(Object newChild) {
		outputsIntoInputs1.add(newChild);
	}
	
	public void setChild_Inputs2(Object newChild) {
		outputsIntoInputs2.add(newChild);
	}
	
	public void setOutputValue(int newOutput) {
		outputValue = newOutput;
	}
	
	public int getOutputValue() {return outputValue;}
	
	public Object getInput1Source() { return input1Source;}
	
	public Object getInput2Source() { return input2Source;}
	
	public ArrayList<Object> getChildren_Inputs1(){ return outputsIntoInputs1; }
	
	public ArrayList<Object> getChildren_Inputs2(){ return outputsIntoInputs2; }
	
	public ArrayList<Object> getFamilyTree(){ return familyTree; }
	
	public void addToFamilyTree(Object newRelative) {
		if(!familyTree.contains(newRelative)) {
			familyTree.add(newRelative); 
		}
	}
	
	public void setID(String newID) {id = newID;}
	
	public String getID() {return id;}
	
	public void setXCoord(int newX) {
		xCoord = newX;
	}

	public void setYCoord(int newY) {
		yCoord = newY;
	}
	
	public void removeInput1Source() {
		input1Source = null;
	}
	
	public void removeInput2Source() {
		input2Source = null;
	}

}
