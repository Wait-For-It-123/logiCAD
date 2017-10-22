package code.model;

/**
 * Helper class for the GUI.  Connections between circuit elements are bundled into instances of this class.
 *
 */

public class Connection {
	
	
	/**
	 * unique ID of the parent gate
	 */
	private String parentID;
	
	
	/**
	 * unique ID of the child gate  
	 */
	private String childID;
	
	
	/**
	 * Identifies the input connection on the child gate into which this connection is made.
	 * For general Gates, this is 1 or 2 depending on if the connection is made into the upper or lower
	 * input.  For Not gates, this is equal to 3.  For Output objects, this equals 4.  Input objects cannot be a child.
	 */
	private int inputType;
	
	/**
	 * Constructor 
	 * 
	 * @param p the parent id
	 * @param c the child id
	 * @param t the input connection type
	 */
	public Connection(String p, String c, int t) {
		parentID = p;
		childID = c;
		inputType = t;
	}
	
	
	public String getParentID() {return parentID;}
	public String getChildID() {return childID;}
	public int getInputType() {return inputType;}
}
