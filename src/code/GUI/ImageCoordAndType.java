package code.GUI;

public class ImageCoordAndType {
	
	
	 /*
     * This class is used to store information on the location of any image and type in the workspace
     * this also takes into account the center coordinates and the upper left coordinates and upper left 
     * corner of buffer space
     */
    
   
    	private int elementType;
    	private int centerImageX;
    	private int centerImageY;
    	private int upperLeftImageX;
    	private int upperLeftImageY;
    	private int upperLeftBufferX;
    	private int upperLeftBufferY;
    	
    	private String id;
    	
    	public ImageCoordAndType(int type, int upperLeftX, int upperLeftY) {
    		elementType = type;
    		upperLeftImageX = upperLeftX;
    		upperLeftImageY = upperLeftY;
    		centerImageX = upperLeftX + 50;
    		centerImageY = upperLeftY + 25;
    		upperLeftBufferX = upperLeftX - 20;
    		upperLeftBufferY = upperLeftY - 10;
    			
    	}
    	
    	public String getID() {return id;}
    	public void setID(String s) {id = s;}
   				    	
    	public int getElementType() {return elementType;}
    	public int getUpperLeftImageX() {return upperLeftImageX;}
    	public int getUpperLeftImageY () {return upperLeftImageY;}
    	public int getCenterImageX() {return centerImageX;}
    	public int getCenterImageY() {return centerImageY;}
    	public int getUpperLeftBufferX() {return upperLeftBufferX;}
    	public int getUpperLeftBufferY() {return upperLeftBufferY;}
    
    

}
