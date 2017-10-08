package code.model;

import java.util.ArrayList;
import code.logicGates.*;
import code.GUI.GUI;
import code.IO.*;

/**
 * @author Ian, Zack, Baqi
 *
 */
public class Model {
		
	private int andGateNum = 0;
	private int orGateNum = 0;
	private int xorGateNum = 0;
	private int xnorGateNum = 0;
	private int notGateNum = 0;
	private int norGateNum = 0;
	private int nandGateNum = 0;
	private int inputNum = 0;
	private int outputNum = 0;
	
	private ArrayList<Object> workspaceElements = new ArrayList<Object>();
	private GUI gui;
	
	
	public ArrayList<Object> getWorkspaceElements(){return workspaceElements;}
	
	
	
	
	
	public void removeCircuitElementHelper(String id) {
		
		for(int i = 0; i < workspaceElements.size(); ++i) {
			Object obj = workspaceElements.get(i);
			if(obj instanceof Gate) {
				if(((Gate) obj).getID().equals(id)) {
					this.removeCircuitElement(obj);
				}
			}
			else if(obj instanceof Input) {
				if(((Input) obj).getID().equals(id)) {
					this.removeCircuitElement(obj);
				}
			}
			else if(obj instanceof Output) {
				if(((Output) obj).getID().equals(id)) {
					this.removeCircuitElement(obj);
				}
			}
		}
	}
	
	/*
	 * int type: AND: 0
	 *		 	 OR:  1
	 *			 NOT: 2
	 *			 XOR: 3
	 *           NAND: 4
	 *           NOR: 5
	 *           XNOR: 6
	 *           INPUT: 7
	 *           OUTPUT: 8
	 */
	public String createandAddCircuitElement(int type) {
		
		String id = "";
		switch(type) {
		case 0:
			andGate and = new andGate();
			id = addObjectToWorkspace(and);
			System.out.println("Gate created: " + and.getID());
			return id;
		case 1:
			orGate or = new orGate();
			id = addObjectToWorkspace(or);
			System.out.println("Gate created: " + or.getID());
			return id;
		case 2:
			notGate not = new notGate();
			id = addObjectToWorkspace(not);
			System.out.println("Gate created: " + not.getID());
			return id;
		case 3:
			xorGate xor = new xorGate();
			id = addObjectToWorkspace(xor);
			System.out.println("Gate created: " + xor.getID());
			return id;
		case 4:
			nandGate nand = new nandGate();
			id = addObjectToWorkspace(nand);
			System.out.println("Gate created: " + nand.getID());
			return id;
		case 5:
			norGate nor = new norGate();
			id = addObjectToWorkspace(nor);
			System.out.println("Gate created: " + nor.getID());
			return id;
		case 6:
			xnorGate xnor = new xnorGate();
			id = addObjectToWorkspace(xnor);
			System.out.println("Gate created: " + xnor.getID());
			return id;
		case 7:
			Input in = new Input();
			id = addObjectToWorkspace(in);
			System.out.println("Gate created: " + in.getID());
			return id;
		case 9:
			Output out = new Output();
			id = addObjectToWorkspace(out);
			System.out.println("Gate created: " + out.getID());
			return id;
		}
		
		return id;
	}
	
	/**
	 * This method removes all connections from circuitElemeent element both existing in itself
	 * and by other elements connected to it.  It also visits every circuit element in workspaceElements
	 * and removes element from any family tree in which it exists. It tells the coordinate system that
	 * the element no longer is on the grid. Finally it removes it from the workspaceElements list.
	 * 
	 * @param element
	 * 
	 */
	public void removeCircuitElement(Object element) {
		
		// --Step 1:--
		// Look at the two or fewer circuit elements (parents) that feed into element
		// In THOSE circuit elements, remove element as a child (remove from its list of outputs)
		// After this is done, remove the circuit element as an input from element
		// --Step 2:--
		// Look at element's output connection.  For each circuit element that is connected, remove
		// element as an input connection in THOSE circuit elements.
		// After this is done, remove the circuit element from element's list of outputs.
		// --Step 3:--
		// Once all of element's inputs and outputs are removed, visit every circuit element and check
		// if element is in its family tree.  If it is, remove it.
		// --Step 4:--
		// Remove all objects from element's FamilyTree
		// --Step: 5:--
		// Remove element from workspaceElements list. -- Done --
		
		
		// For element of type Gate
		if(element instanceof Gate) {
			
			Gate g = (Gate) element;
			
			
			// Step 1 Gate
			if(g.getInput1Source() != null) {
				Object input1Source = g.getInput1Source();
				if(input1Source instanceof Input) {
					Input in = (Input) input1Source;
					ArrayList<Object> input1Children = in.getInput1Children();
					input1Children.remove(element);
				}
				else if(input1Source instanceof Gate){
					Gate gateSource = (Gate) input1Source;
					ArrayList<Object> input1Children = gateSource.getChildren_Inputs1();
					input1Children.remove(element);
				}
				g.removeInput1Source();
			}
			
			if(g.getInput2Source() != null) {
				Object input2Source = g.getInput2Source();
				if(input2Source instanceof Input) {
					Input in = (Input) input2Source;
					ArrayList<Object> input2Children = in.getInput2Children();
					input2Children.remove(element);
				}
				else if(input2Source instanceof Gate){
					Gate gateSource = (Gate) input2Source;
					ArrayList<Object> input2Children = gateSource.getChildren_Inputs2();
					input2Children.remove(element);
				}
				g.removeInput2Source();
			}
			
			// Step 2 Gate
			ArrayList<Object> input1Children = g.getChildren_Inputs1();
			System.out.println(g.getChildren_Inputs1());
			for(Object child: input1Children) {
				if(child instanceof Gate) {
					Gate childGate = (Gate) child;
					childGate.removeInput1Source();
					System.out.println("was here");
				}
				else if(child instanceof Output) {
					Output childOutput = (Output) child;
					childOutput.removeInputSource();
				}
			}
			input1Children.clear();
			
			ArrayList<Object> input2Children = g.getChildren_Inputs2();
			for(Object child: input2Children) {
				if(child instanceof Gate) {
					Gate childGate = (Gate) child;
					childGate.removeInput2Source();
				}
				else if(child instanceof Output) {
					Output childOutput = (Output) child;
					childOutput.removeInputSource();
				}
			}
			input2Children.clear();
			
			System.out.println(g.getChildren_Inputs1());
			
			// Step 3 Gate
			for(Object circuitElement: workspaceElements) {
				if(circuitElement instanceof Gate) {
					while(((Gate) circuitElement).getFamilyTree().remove(element)) {} // removes all instances of element in familyTree (this is considering the case where element was
																		  			  // an input to both of circuitElement's inputs and thus existed in its family tree twice.
				}
				else if(circuitElement instanceof Input) {
					while(((Input) circuitElement).getFamilyTree().remove(element)) {}
				}
				else if(circuitElement instanceof Output) {
					while(((Output) circuitElement).getFamilyTree().remove(element)){}
				}
			}
			
			// Step 4 Gate
			g.getFamilyTree().clear();
			
			// Step 5 Gate
			workspaceElements.remove(element);

		} // end if Gate
		
		// For element of type Input
		else if(element instanceof Input) {
			Input in = (Input) element;
			
			// Step 1 Input
			// Input has no input connections, so skip step 1
			
			// Step 2 Input
			ArrayList<Object> input1Children = in.getInput1Children();
			for(Object child: input1Children) {
				if(child instanceof Gate) {
					((Gate) child).removeInput1Source();
				}
				else if(child instanceof Output) {
					((Output) child).removeInputSource();
				}
			}
			input1Children.clear();
			
			ArrayList<Object> input2Children = in.getInput2Children();
			for(Object child: input2Children) {
				if(child instanceof Gate) {
					((Gate) child).removeInput2Source();
				}
				else if(child instanceof Output) {
					((Output) child).removeInputSource();
				}
			}
			input2Children.clear();
		
			// Step 3 Input
			for(Object circuitElement: workspaceElements) {
				if(circuitElement instanceof Gate) {
					while(((Gate) circuitElement).getFamilyTree().remove(element)) {} // removes all instances of element in familyTree (this is considering the case where element was
																		  			  // an input to both of circuitElement's inputs and thus existed in its family tree twice.
				}
				else if(circuitElement instanceof Input) {
					while(((Input) circuitElement).getFamilyTree().remove(element)) {}
				}
				else if(circuitElement instanceof Output) {
					while(((Output) circuitElement).getFamilyTree().remove(element)){}
				}
			}
			// Step 4 Input
			in.getFamilyTree().clear();
			// Step 5 Input
			workspaceElements.remove(element);
		}

		// For element of type Output
		else if(element instanceof Output) {
			// Step 1 Output
			Output out = (Output) element;
			if(out.getInput() != null) {
				Object inputSource = out.getInput();
				if(inputSource instanceof Input) {
					Input in = (Input) inputSource;
					in.getInput1Children().remove(element);
				}
				else if(inputSource instanceof Gate) {
					Gate gateSource = (Gate) inputSource;
					gateSource.getChildren_Inputs1().remove(element);
				}
				out.removeInputSource();
			}
			
			// Step 2 Output
			// An output has no output connections, so skip step 2
			
			// Step 3 Output
			for(Object circuitElement: workspaceElements) {
				if(circuitElement instanceof Gate) {
					while(((Gate) circuitElement).getFamilyTree().remove(element)) {} // removes all instances of element in familyTree (this is considering the case where element was
																		  			  // an input to both of circuitElement's inputs and thus existed in its family tree twice.
				}
				else if(circuitElement instanceof Input) {
					while(((Input) circuitElement).getFamilyTree().remove(element)) {}
				}
				else if(circuitElement instanceof Output) {
					while(((Output) circuitElement).getFamilyTree().remove(element)){}
				}
			}
			
			// Step 4 Output
			out.getFamilyTree().clear();
			// Step 5 Output
			workspaceElements.remove(element);
		}
		
		
	}
	
	/*
	 * This method allows for a connection in the circuit to be made.
	 * The convention is that the parent is the element whose output will feed into one of the inputs of the child.
	 */
	public int makeCircuitConnection(Object parent, Object child) {
		
		// We are not allowing a child's output to feed into one of its inputs (no feedback)
		if(parent == child) {System.out.println("Can't connect a circuit element to itself!"); 
			
			return 1;
		}
		
		if(parent instanceof Gate) {
			
			Gate g = (Gate) parent;
			ArrayList<Object> tree = g.getFamilyTree();
		
			if(tree.contains(child)) {System.out.println("Can't add a connection into predecessor!"); return 2;}
		}
		else if(parent.getClass() == Output.class) {
			Output o = (Output) parent;
			ArrayList<Object> tree = o.getFamilyTree();
			if(tree.contains(child)) {System.out.println("Can't add a connection into predecessor!"); return 2;}
		}
		
		//We cannot allow an output to function as a parent, because it has only a single input
		if(parent.getClass() == Output.class) { System.out.println("Can't add a connection in which Output is parent!") ;return 3; }
		
		// We cannot allow an input to function as a child, because it has only a single output
		if(child.getClass() == Input.class) { System.out.println("Can't add a connection in which Input is child!"); return 4; }
		
		// return value on attempt to make connection into the child
		int inputNum = 5;
		
		if(child.getClass() == Output.class) {
			Output out = (Output) child; // we know child is of type Output, so cast to Output to access its class members
			inputNum = out.setInput(parent);
			
			if(inputNum == 0) { return 5; } // The output must already contain an input, thus adding parent as input was unsuccessful
			else {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
					//gui.connectIO(g.getID(), out.getID(), 4);
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
					//gui.connectIO(i.getID(), out.getID(), 4);
				}
			}
			
		} // end if
		
		
		else if(child instanceof Gate) {
			Gate childGate = (Gate) child;
			inputNum = childGate.setInput(parent);
			propagateFamilyTreeToAllChildren(childGate);  // problem here?
			
			if(inputNum == 0) {return 6;}
			else if(inputNum == 1) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
					//gui.connectIO(g.getID(), childGate.getID(), 1);
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
					//gui.connectIO(i.getID(), childGate.getID(), 1);
				}
			}
			else if(inputNum == 2) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs2(child); // add child as an output
					//gui.connectIO(g.getID(), childGate.getID(), 2);
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input2(child);
					//gui.connectIO(i.getID(), childGate.getID(), 2);
				}
			}
			
			else if(inputNum == 3) {
				if(parent instanceof Gate) {
					Gate g = (Gate) parent;
					g.setChild_Inputs1(child); // add child as an output
					//gui.connectIO(g.getID(), childGate.getID(), 3);
				}
				else if(parent.getClass() == Input.class){
					Input i = (Input) parent;
					i.addChild_Input1(child);
					//gui.connectIO(i.getID(), childGate.getID(), 3);
				}
			}
		}
		
		return 7;
		
	} // end of MakeCircuitConnection
	
	// Adds members of childGate's familyTree to the familyTrees of all of its childrens' familyTrees.
	// This accounts for the case where a circuit is built in a different order than starting from inputs and
	// going to outputs, so that all children are aware of all their ancestors.  Otherwise, they would be missing some.
	private void propagateFamilyTreeToAllChildren(Gate childGate) {
		
		System.out.println("propagateFamilyTreeToAllChildren("+ childGate);
		
		// We will add all children of childGate to an ArrayList.  Once all children are added, we will visit every child
		// from index 0 up till there are no more children to visit, and update that child's familyTree to include all members 
		// in childGate's familyTree.  
		
		ArrayList<Object> childrenOfChildGate = new ArrayList<Object>();
		ArrayList<Object> outputsIntoInputs1ChildGate = childGate.getChildren_Inputs1();
		ArrayList<Object> outputsIntoInputs2ChildGate = childGate.getChildren_Inputs2();
		ArrayList<Object> familyTreeChildGate = childGate.getFamilyTree();
		
		// Add all of childGate's direct children to childrenOfChildGate
		for(Object obj: outputsIntoInputs1ChildGate) {
			if(!childrenOfChildGate.contains(obj)) {
				childrenOfChildGate.add(obj);
			}
		}
		for(Object obj: outputsIntoInputs2ChildGate) {
			if(!childrenOfChildGate.contains(obj)) {
				childrenOfChildGate.add(obj);
			}
		}
		
		while(!childrenOfChildGate.isEmpty()) {
			Object currentChild = (Object) childrenOfChildGate.get(0);
			
			if(currentChild instanceof Output) {
				// Outputs have no children, so don't have to worry about adding children here
				// Just update its family tree and remove it from childrenOfChildGate
				Output out = (Output) currentChild;
				for(Object newAncestor: familyTreeChildGate) {
					if(!out.getFamilyTree().contains(newAncestor)) {
						(out.getFamilyTree()).add(newAncestor);
					}
				}
				childrenOfChildGate.remove(0);
			}
			else if(currentChild instanceof Gate) {
				// Add all children of currentChild to childrenOfChildGate
				Gate currentChildGate = (Gate) currentChild;
				ArrayList<Object> outputsIntoInputs1CurrentChild = currentChildGate.getChildren_Inputs1();
				ArrayList<Object> outputsIntoInputs2CurrentChild = currentChildGate.getChildren_Inputs2();
				for(Object obj: outputsIntoInputs1CurrentChild) {
					if(!childrenOfChildGate.contains(obj)) {
						childrenOfChildGate.add(obj);
					}
				}
				for(Object obj: outputsIntoInputs2CurrentChild) {
					if(!childrenOfChildGate.contains(obj)) {
						childrenOfChildGate.add(obj);
					}
				}
				// Update family tree of currentChild
				for(Object newAncestor: familyTreeChildGate) {
					if(!currentChildGate.getFamilyTree().contains(newAncestor)) {
						currentChildGate.getFamilyTree().add(newAncestor);
					}
				}
				// remove currentChild from childrenOfChildGate
				childrenOfChildGate.remove(0);
			}
			// else if(currentChild instanceof Input) ***Note that this can never happen***
			
		}
	
	}



	// This method propagates the signals in the network from gate to gate over and over again
	// until no more change is happening.  This final state corresponds to the settled state when
	// the outputs have the expected outputs from the inputs through the network.  If any connections
	// in the network are missing (e.g., logic gate missing one of its inputs, output not having an
	// input, input not having an output, etc.) then the method returns false immediately, not having
	// done any signal propagation.
	public boolean evaluateCircuitNetwork() {
		
		// Check to see if any missing connections in the network are present
		// If so, return false immediately and thus not evaluating the network
		for(Object obj : workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(g instanceof notGate && g.getInput1Source() == null) {System.out.println("Missing Input Connections Exist!" + g); return false;}
				if((g.getInput1Source() == null || g.getInput2Source() == null) && !(g instanceof notGate)) {System.out.println("Missing Input Connections Exist!" + g); return false;}
				if(g.getChildren_Inputs1().isEmpty() && g.getChildren_Inputs2().isEmpty()) {System.out.println("Missing Output Connections Exist!"); return false;}
			}
			else if(obj.getClass() == Input.class) {
				Input in = (Input) obj;
				if(in.getInput1Children().isEmpty() && in.getInput2Children().isEmpty()) {System.out.println("An input does not have an output child!"); return false;}
			}
			else if(obj.getClass() == Output.class) {
				Output out = (Output) obj;
				if(out.getInput() == null) {System.out.println("An output does not have an input parent!"); return false;}
			}
		}
		
		// All connections exist, thus continue with evaluation of signals and propagation
		ArrayList<Integer> signalsLastPass = new ArrayList<Integer>();
		ArrayList<Integer> signalsCurrentPass = new ArrayList<Integer>();
		do {
			signalsLastPass.clear();
			for(Integer i: signalsCurrentPass) {
				signalsLastPass.add(i);
			}
			signalsCurrentPass.clear();
			for(Object obj : workspaceElements) {
				if(obj instanceof Gate) {
					Gate g = (Gate) obj;
					int signalToPropagate = g.evaluateInputSignals();
					g.setOutputValue(signalToPropagate);
					signalsCurrentPass.add(new Integer(signalToPropagate));
					ArrayList<Object> children1 = g.getChildren_Inputs1();
					for(Object child : children1) {
						In in = (In) child;
						in.setInput1Value(signalToPropagate);
					}
					ArrayList<Object> children2 = g.getChildren_Inputs2();
					for(Object child : children2) {
						In in = (In) child;
						in.setInput2Value(signalToPropagate);
					}
				}
				else if(obj.getClass() == Input.class) {
					Input in = (Input) obj;
					int signalToPropagate = in.getInputValue();
					signalsCurrentPass.add(new Integer(signalToPropagate));
					ArrayList<Object> children1 = in.getInput1Children();
					for(Object child : children1) {
						In childInput = (In) child;
						childInput.setInput1Value(signalToPropagate);
					}
					ArrayList<Object> children2 = in.getInput2Children();
					for(Object child : children2) {
						In childInput = (In) child;
						childInput.setInput2Value(signalToPropagate);
					}
				}
				
			}
			
			printAllInputAndOutputValuesForAllCircuitElements();
			System.out.println("");
			
			System.out.println(signalsLastPass + "\n" + signalsCurrentPass);
			
			
		} while(!areListsEqual(signalsLastPass, signalsCurrentPass));
		
		for(Integer i : signalsCurrentPass) {
			if(i.equals(new Integer(-1))){System.out.println("At least one signal is still equal to -1!") ;return false;}
		}
		
		return true;
	}
	
	public static boolean areListsEqual(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		
		if(list1.size() != list2.size()) {return false;}
		for(int i = 0; i < list1.size(); ++i) {
			if(!(list1.get(i).equals(list2.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	
	public String addObjectToWorkspace(Object obj) {
		workspaceElements.add(obj);
		String id = "";
		if(obj instanceof andGate) {
			andGate and = (andGate) obj;
			id = "and" + Integer.toString(andGateNum); 
			and.setID(id); 
			andGateNum++;
		}
		if(obj instanceof orGate) {
			orGate or = (orGate) obj;
			id = "or" + Integer.toString(orGateNum); 
			or.setID(id); 
			orGateNum++;
		}
		if(obj instanceof notGate) {
			notGate not = (notGate) obj;
			id = "not" + Integer.toString(notGateNum); 
			not.setID(id); 
			notGateNum++;
		}
		if(obj instanceof norGate) {
			norGate nor = (norGate) obj;
			id = "nor" + Integer.toString(norGateNum); 
			nor.setID(id); 
			norGateNum++;
		}
		if(obj instanceof nandGate) {
			nandGate nand = (nandGate) obj;
			id = "nand" + Integer.toString(nandGateNum); 
			nand.setID(id); 
			nandGateNum++;
		}
		if(obj instanceof xorGate) {
			xorGate xor = (xorGate) obj;
			id = "xor" + Integer.toString(xorGateNum); 
			xor.setID(id); 
			xorGateNum++;
		}
		if(obj instanceof xnorGate) {
			xnorGate xnor = (xnorGate) obj;
			id = "xnor" + Integer.toString(xnorGateNum); 
			xnor.setID(id); 
			xnorGateNum++;
		}
		if(obj instanceof Input) {
			Input in = (Input) obj;
			id = "in" + Integer.toString(inputNum); 
			in.setID(id); 
			inputNum++;
		}
		if(obj instanceof Output) {
			Output out = (Output) obj;
			id = "out" + Integer.toString(outputNum); 
			out.setID(id); 
			outputNum++;
		}
		return id;
	}

	
	public void printAllInputsAndValues() {
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Input.class) {
				Input i = (Input) obj;
				System.out.println("Input Identity:" + i + ", Name: " + i.getID() +  ", Value: " + i.getInputValue());
			}
		}
	}
	
	public String allInputsAndValuesToString() {
		String inputString = "";
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Input.class) {
				Input i = (Input) obj;
				inputString = inputString + "<br> Input: " + i.getID() +  ", Value: " + Integer.toString(i.getInputValue());
			}
		}
		return inputString;	
	}
	
	public void printAllOutputsAndValues() {
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Output.class) {
				Output o = (Output) obj;
				System.out.println("Output Identity:" + o + ", Name: " + o.getID() +  ", Value: " + o.getOutputValue());
			}
		}
	}
	
	public String allOutputsAndValuesToString() {
		String outputString = "";
		for(Object obj: workspaceElements) {
			if(obj.getClass() == Output.class) {
				Output o = (Output) obj;
				outputString = outputString + "<br> Output: " + o.getID() +  ", Value: " + Integer.toString(o.getOutputValue());
			}
		}
		return outputString;	
	}
	
	public void printAllInputAndOutputValuesForAllCircuitElements() {
		for(Object obj: workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(g instanceof notGate) {
					System.out.println("Identity: " + g + ", Name: " + g.getID() + ", Input_Value: " + g.getInput1Value() + ", Output_Value: " + g.getOutputValue() );
				}
				else {
					System.out.println("Identity: " + g + ", Name: " + g.getID() + ", Input1_Value: " + g.getInput1Value() + ", Input2_Value: " + g.getInput2Value() + ", Output_Value: " + g.getOutputValue() );
				}
			}
			else if(obj instanceof Input) {
				Input in = (Input) obj;
				System.out.println("Identity: " + in + " Name: " + in.getID() + ", Value: " + in.getInputValue());
			}
			else if(obj instanceof Output) {
				Output out = (Output) obj;
				System.out.println("Identity: " + out + " Name: " + out.getID() + ", Value: " + out.getOutputValue());	
			}
		}
	}
	
	public void printAllConnectionsForAllCircuitElements() {
		
		for(Object obj: workspaceElements) {
			if(obj instanceof Gate) {
				Gate g = (Gate) obj;
				if(obj instanceof notGate) {
					System.out.println("Identity: " + g + "Name: " + g.getID() + " InputSource: " + g.getInput1Source() + " Outputs: " + g.getChildren_Inputs1() + " " + g.getChildren_Inputs2());
				}
				else {
					System.out.println("Identity: " + g + "Name: " + g.getID() + " Input1Source: " + g.getInput1Source() + " Input2Source: " + g.getInput2Source() +  " Outputs: " + g.getChildren_Inputs1() + " " + g.getChildren_Inputs2());
				}
			}
			else if(obj instanceof Input) {
				Input in = (Input) obj;
				System.out.println("Identity: " + in + "Name: " + in.getID() + " Outputs: " + in.getInput1Children() + " " + in.getInput2Children());
			}
			else if(obj instanceof Output) {
				Output out = (Output) obj;
				System.out.println("Identity: " + out + "Name: " + out.getID() + "Input: " + out.getInput());
			
			}
		}
		
	}
	
public ArrayList<Connection> queryAndGetConnections() {
		
		ArrayList<Connection> connections = new ArrayList<Connection>();
		for(int i = 0; i < workspaceElements.size(); ++i) {
			Object parent = workspaceElements.get(i);
			
			// Parent is Input
			if(parent instanceof Input) {
				ArrayList<Object> children1Inputs = ((Input) parent).getInput1Children();
				for(int j = 0; j < children1Inputs.size(); ++j) {
					if(children1Inputs.get(j) instanceof Gate) {
						String parentID = ((Input) parent).getID();
						String childID = ((Gate) children1Inputs.get(j)).getID();
						int inputType = 1;
						if(children1Inputs.get(j) instanceof notGate) {
							inputType = 3;
						}
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
					else if(children1Inputs.get(j) instanceof Output) {
						String parentID = ((Input) parent).getID();
						String childID = ((Output) children1Inputs.get(j)).getID();
						int inputType = 4;
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
				}
				
				ArrayList<Object> children2Inputs = ((Input) parent).getInput2Children();
				for(int j = 0; j < children2Inputs.size(); ++j) {
					if(children2Inputs.get(j) instanceof Gate) {
						String parentID = ((Input) parent).getID();
						String childID = ((Gate) children2Inputs.get(j)).getID();
						int inputType = 2;
						if(children2Inputs.get(j) instanceof notGate) {
							inputType = 3;
						}
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
					else if(children2Inputs.get(j) instanceof Output) {
						String parentID = ((Input) parent).getID();
						String childID = ((Output) children2Inputs.get(j)).getID();
						int inputType = 4;
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
				}
				
				
			}
			
			// Parent is Gate
			if(parent instanceof Gate) {
				ArrayList<Object> children1Inputs = ((Gate) parent).getChildren_Inputs1();
				for(int j = 0; j < children1Inputs.size(); ++j) {
					if(children1Inputs.get(j) instanceof Gate) {
						String parentID = ((Gate) parent).getID();
						String childID = ((Gate) children1Inputs.get(j)).getID();
						int inputType = 1;
						if(children1Inputs.get(j) instanceof notGate) {
							inputType = 3;
						}
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
					else if(children1Inputs.get(j) instanceof Output) {
						String parentID = ((Gate) parent).getID();
						String childID = ((Output) children1Inputs.get(j)).getID();
						int inputType = 4;
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
				}
				
				ArrayList<Object> children2Inputs = ((Gate) parent).getChildren_Inputs2();
				for(int j = 0; j < children2Inputs.size(); ++j) {
					if(children2Inputs.get(j) instanceof Gate) {
						String parentID = ((Gate) parent).getID();
						String childID = ((Gate) children2Inputs.get(j)).getID();
						int inputType = 2;
						if(children2Inputs.get(j) instanceof notGate) {
							inputType = 3;
						}
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
					else if(children2Inputs.get(j) instanceof Output) {
						String parentID = ((Gate) parent).getID();
						String childID = ((Output) children2Inputs.get(j)).getID();
						int inputType = 4;
						Connection newCon = new Connection(parentID, childID, inputType);
						connections.add(newCon);
					}
				}

			}
		}//end outer for
		
		return connections;
	}
	
	
	public void printAllWorkspaceElements() {
		for(Object obj: workspaceElements) {
			System.out.println(obj);
		}
	}
	
	
	public void printAllFamilyTrees() {
		
		System.out.println("Family Trees All Circuit Elements: ");
		for(Object obj: workspaceElements) {
			if(obj instanceof Gate) {
				System.out.println(obj + " " + ((Gate) obj).getID() + " " + ((Gate) obj).getFamilyTree() );
			}
			else if(obj instanceof Input) {
				System.out.println(obj + " " + ((Input) obj).getID() + " " + ((Input) obj).getFamilyTree() );
			}
			else if(obj instanceof Output) {
				System.out.println(obj + " " + ((Output) obj).getID() + " " + ((Output) obj).getFamilyTree() );
			}
		}
	}



	public void setGUI(GUI gui) {
		this.gui = gui;
		
	}



	public void printElementMatching(String id) {
		for(int i = 0; i < workspaceElements.size(); i++) {
			Object obj = workspaceElements.get(0);
			if(obj instanceof Gate) {
				if(((Gate) obj).getID().equals(id)){
					System.out.println(((Gate) obj).getID());
				}
			}
			else if(obj instanceof Input) {
				if(((Input) obj).getID().equals(id)){
					System.out.println(((Input) obj).getID());
				}
				
			}
			else if(obj instanceof Output) {
				if(((Output) obj).getID().equals(id)){
					System.out.println(((Output) obj).getID());
				}
			}
		}
	}
	
	public int toggleInputFromID(String InputID) {
        Input Input1 = null;
        int found=-1;
        for(int i = 0; i < workspaceElements.size(); ++i) {
            Object currentElement = workspaceElements.get(i);
            if(currentElement instanceof Input) {
                if(((Input) currentElement).getID().equals(InputID)) {
                    Input1 =(Input) currentElement;
                    if(Input1.getInputValue()==0) {
                    Input1.setInputValue(1);
                    found = 1;
                    System.out.println("input set to 1");
                    }
                    else if(Input1.getInputValue()==1) {
                    Input1.setInputValue(0);
                    found = 0;
                    System.out.println("input set to 0");
                    
                    } 
                }
            }
        }
        return found;
    }
	

	public int getOutputValueFromID(String id) {
		int outputVal = -2;
		for(int i = 0; i < workspaceElements.size(); ++i) {
			Object obj = workspaceElements.get(i);
			if(obj instanceof Output) {
				Output out = (Output) obj;
				if(out.getID().equals(id)) {
					outputVal = out.getOutputValue();
				}
			}
		}
		return outputVal;
	}


	public int makeConnectionFromIDs(String parentID, String childID) {
		
		Object parent = null;
		Object child = null;
		for(int i = 0; i < workspaceElements.size(); ++i) {
			Object currentElement = workspaceElements.get(i);
			if(currentElement instanceof Gate) {
				if(((Gate) currentElement).getID().equals(parentID)) {
					parent = currentElement;
				}
				if(((Gate) currentElement).getID().equals(childID)) {
					child = currentElement;
				}
			}
			else if(currentElement instanceof Input) {
				if(((Input) currentElement).getID().equals(parentID)) {
					parent = currentElement;
				}
				if(((Input) currentElement).getID().equals(childID)) {
					child = currentElement;
				}
			}
			else if(currentElement instanceof Output) {
				if(((Output) currentElement).getID().equals(parentID)) {
					parent = currentElement;
				}
				if(((Output) currentElement).getID().equals(childID)) {
					child = currentElement;
				}
			}
		}
		return makeCircuitConnection(parent, child);
		
	}
	
	
	

	
}
