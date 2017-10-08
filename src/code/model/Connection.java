package code.model;

public class Connection {
	
	private String parentID;
	private String childID;
	private int inputType;
	
	public Connection(String p, String c, int t) {
		parentID = p;
		childID = c;
		inputType = t;
	}
	
	public String getParentID() {return parentID;}
	public String getChildID() {return childID;}
	public int getInputType() {return inputType;}
}
