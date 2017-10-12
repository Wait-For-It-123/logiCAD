package code.GUI;

import java.awt.geom.Line2D; // import for java Line2D class

public class Wire {
	
	private Line2D line1, line2, line3;	//create 3 Line2D to compose one full wire
	
	public Wire(Line2D l1, Line2D l2, Line2D l3) {
		line1 = l1;
		line2 = l2;
		line3 = l3;
	}
	//getter methods for each line of the wire
	
	public Line2D getLine1() {return line1;}//the first wire, out from the source gate
	public Line2D getLine2() {return line2;}//the second wire, drawn vertically to the height of the destination gate
	public Line2D getLine3() {return line3;}//the last wire drawn horizontally to the input of the destination gate
		
}
