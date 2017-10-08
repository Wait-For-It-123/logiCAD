package code.GUI;

import java.awt.geom.Line2D;

public class Wire {
	
	private Line2D line1, line2, line3;
	
	public Wire(Line2D l1, Line2D l2, Line2D l3) {
		line1 = l1;
		line2 = l2;
		line3 = l3;
	}
	
	public Line2D getLine1() {return line1;}
	public Line2D getLine2() {return line2;}
	public Line2D getLine3() {return line3;}
		
}
