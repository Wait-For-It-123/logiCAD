// This code from an Oracle tutorial handles putting elements into the
// workspace.

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 

package code.GUI;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import code.model.Connection;
import code.model.Model;

import javax.swing.event.MouseInputAdapter;
import java.io.IOException;



public class GUI {
	
	private static final int INVALID = -1;
	private static final int AND_BUTTON = 0;
	private static final int OR_BUTTON = 1;
	private static final int NOT_BUTTON = 2;
	private static final int XOR_BUTTON = 3;
	private static final int NAND_BUTTON = 4;
	private static final int NOR_BUTTON = 5;
	private static final int XNOR_BUTTON = 6;
	private static final int INPUT_BUTTON = 7;
	private static final int INPUT_LOGIC_0 = 7;
	private static final int INPUT_LOGIC_1 = 8;
	private static final int OUTPUT_BUTTON = 9;
	private static final int OUTPUT_LOGIC_0 = 10;
	private static final int OUTPUT_LOGIC_1 = 11;
	private static final int OUTPUT_LOGIC_X = 12;
	
	private int CEBTypetemp = -1;
	private int circuitElementButtonClicked = INVALID;
	
	private int optionButtons = INVALID;
	private static final int MOVE_BUTTON = 0;
	private static final int DELETE_BUTTON = 1;
	private static final int CONNECT_BUTTON = 2;
	private static final int CANCEL_BUTTON = 3;
	private static final int PARENT_SELECTED = 4;
	private static final int TOGGLE_INPUT_BUTTON = 5;
	
//	private JComponent contentPane;
	

	
	private HashMap<String, Integer> parentLineOffsets = new HashMap<String, Integer>();
	private ArrayList<Wire> wires = new ArrayList<Wire>();
	
	private Model model;
	
	public GUI(Model m) {
		this.model = m;
	}
	
	private JTree dirStructure;
	public String parentID;
	
	public void run() {
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");
		
		// Adds LogiCAD logo as icon to upper left JFrame Window and in task bar.
				try {
					Image img = ImageIO.read(getClass().getResource("images/logicad_logo_silver_40_40.png"));
					ImageIcon logo_icon = new ImageIcon(img);
					frame.setIconImage(logo_icon.getImage());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
//############################################################################################
// Some of the following code is borrowed from ScrollDemo2.java, a file from an Oracle Tutorial
// See Copyright at beginning the of GUI.java above.
// Tutorial is at the following URL: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ScrollDemo2Project/src/components/ScrollDemo2.java
				
				class ScrollDemo2 extends JPanel implements MouseListener {
				    
					private Dimension area; //indicates area taken up by graphics
				    private JPanel drawingPane;
				    private int x;
				    private int y;
				    private ArrayList<Image> circuitElementImages = new ArrayList<Image>();
				    private ArrayList<Image> elementImageTypes = new ArrayList<Image>();
				    private ArrayList<ImageCoordAndType> imageInfo = new ArrayList<ImageCoordAndType>();
				    public ArrayList<Image> getCircuitElementImages(){return circuitElementImages;}
				    public ArrayList<Image> getElementImageTypes(){return elementImageTypes;}
				    public ArrayList<ImageCoordAndType> getImageInfo(){return imageInfo;}
				    public JPanel getDrawingPane() {return drawingPane;}


				    public ScrollDemo2() {
				        super(new BorderLayout());
				        
				        initializeImageContainer();
				 
				        area = new Dimension(0,0);
				        //circles = new Vector<Rectangle>();
				 
				        
				 
				        //Set up the drawing area.
				        drawingPane = new DrawingPane();
				        drawingPane.setBackground(new Color(80,80,80));
				        drawingPane.addMouseListener(this);
				 
				        //Put the drawing area in a scroll pane.
				        JScrollPane scroller = new JScrollPane(drawingPane);
				        scroller.setPreferredSize(new Dimension(1366,768));
				 
				        //Lay out this demo.
				       
				        add(scroller, BorderLayout.CENTER);
				    }
				    
				    
				    class ImageCoordAndType{
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
				    	
				    	String getID() {return id;}
				    	void setID(String s) {id = s;}
				   				    	
				    	int getElementType() {return elementType;}
						int getUpperLeftImageX() {return upperLeftImageX;}
						int getUpperLeftImageY () {return upperLeftImageY;}
						int getCenterImageX() {return centerImageX;}
						int getCenterImageY() {return centerImageY;}
						int getUpperLeftBufferX() {return upperLeftBufferX;}
						int getUpperLeftBufferY() {return upperLeftBufferY;}
				    }
				    
				    
				    
				 
				    /** The component inside the scroll pane. */
				    class DrawingPane extends JPanel {
				        
				    	protected void paintComponent(Graphics g) {
				            
				    		
				    		Graphics2D g2 = (Graphics2D) g;
				    		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
				    		
				    		
				    		super.paintComponent(g);

				            for(int i = 0; i < circuitElementImages.size(); ++i) {
				            	g.drawImage(circuitElementImages.get(i), imageInfo.get(i).getUpperLeftImageX(), imageInfo.get(i).getUpperLeftImageY(), null);
				            }
				            
				            System.out.println(model.queryAndGetConnections());
				            
				            ArrayList<Connection> connections = model.queryAndGetConnections();
				    		
				            System.out.println(connections);
				            System.out.println(model.queryAndGetConnections());
				            
				            wires.clear();
				            parentLineOffsets.clear();
				            
				            for(Connection con: connections) {
				    			String parentID = con.getParentID();
				    			if(parentLineOffsets.get(parentID) == null) {
				    				parentLineOffsets.put(parentID, 0);
				    			}
				    			else {
				    				parentLineOffsets.put(parentID, parentLineOffsets.get(parentID) + 1);
				    			}
				    			String childID = con.getChildID();
				    			int inputType = con.getInputType();
				    			int parentType = -2;
				    			int xCoordParent = -1;
				    			int yCoordParent = -1;
				    			int xCoordChild = -1;
				    			int yCoordChild = -1;
				    			for(ImageCoordAndType imgData: imageInfo) {
				    				if(parentID.equals(imgData.getID())) {
				    					parentType = imgData.getElementType();
				    					xCoordParent = imgData.getCenterImageX();
				    					yCoordParent = imgData.getCenterImageY();
				    				}
				    			}
				    			
				    			for(ImageCoordAndType imgData: imageInfo) {
				    				if(childID.equals(imgData.getID())) {
				    					xCoordChild = imgData.getCenterImageX();
				    					yCoordChild = imgData.getCenterImageY();
				    				}
				    			}
				    			
				    			if(inputType == 1) {
				    				// parent is Gate
				    				if(parentType >= 0 && parentType <= 6) {
				    					yCoordChild-=10;//alligns the line to the correct place based on gate location
					    				xCoordChild-=45;
					    				xCoordParent+=45;
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}
				    				// parent is Input
				    				else if(parentType == 7) {
				    					yCoordChild-=10;//alligns the line to the correct place based on gate location
					    				xCoordChild-=45;
					    				xCoordParent+=30;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}	
				    				
				    			}//end if inputType == 1
				    			
				    			if(inputType == 2) {
				    				// parent is Gate
				    				if(parentType >= 0 && parentType <= 6) {
				    					yCoordChild+=10;//alligns the line to the correct place based on gate location
					    				xCoordChild-=45;
					    				xCoordParent+=45;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}
				    				// parent is Input
				    				else if(parentType == 7) {
				    					yCoordChild+=10;//alligns the line to the correct place based on gate location
					    				xCoordChild-=45;
					    				xCoordParent+=30;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}	
				    			}
				    			
				    			if(inputType == 3) {
				    				// parent is Gate
				    				if(parentType >= 0 && parentType <= 6) {
					    				xCoordChild-=45;
					    				xCoordParent+=45;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}
				    				// parent is Input
				    				else if(parentType == 7) {
					    				xCoordChild-=45;
					    				xCoordParent+=30;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}	
				    			}
				    			
				    			if(inputType == 4) {
				    				// parent is Gate
				    				if(parentType >= 0 && parentType <= 6) {
					    				xCoordChild-=30;
					    				xCoordParent+=45;
					    				
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}
				    				// parent is Input
				    				else if(parentType == 7) {
					    				xCoordChild-=30;
					    				xCoordParent+=30;
					    				int offset = parentLineOffsets.get(parentID);
					    				offset *= 5;
					    				Line2D line1 = new Line2D.Float(xCoordParent, yCoordParent, xCoordParent+offset, yCoordParent);
					    				Line2D line2 = new Line2D.Float(xCoordParent+offset, yCoordParent, xCoordParent+offset, yCoordChild);
					    				Line2D line3 = new Line2D.Float(xCoordParent+offset, yCoordChild, xCoordChild, yCoordChild);
					    				wires.add(new Wire(line1, line2, line3));
				    				}	
				    			}
	
				    		} // end for
				            
				            
				            g2.setStroke(new BasicStroke(2));
				            g2.setColor(Color.WHITE);
				            for(Wire w: wires) {
				            	g2.draw(w.getLine1());
				            	g2.draw(w.getLine2());
				            	g2.draw(w.getLine3());
				            }
		
				    	}// end paint component method

				    }// end class
				    		

				           
				    public void initializeImageContainer() {
				    	Image img = null;
				    	for(int i = 0; i < 13; ++i) {
				    		switch (i) {
				    			case 0:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/and_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				    				break;
				    			case 1:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/or_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 2:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/not_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 3:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/xor_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 4:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/nand_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 5:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/nor_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 6:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/xnor_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 7:
				    				try {
					    			img = ImageIO.read(getClass().getResource("images/input_0_color.png"));
						    		} catch (IOException exp) {
						    			exp.printStackTrace();
						    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 8:
				    				try {
					    			img = ImageIO.read(getClass().getResource("images/input_1_color.png"));
						    		} catch (IOException exp) {
						    			exp.printStackTrace();
						    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 9:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/output_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 10:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/output_0_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 11:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/output_1_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 12:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/output_x_color.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			
				    		}
				    			
				    	}
				    	
				    }
				 
				    //Handle mouse events.
				    public void mouseReleased(MouseEvent e) {
				        final int W = 300;
				        final int H = 300;
				        
				        boolean changed = false;
				        if (SwingUtilities.isRightMouseButton(e)) {
				            //This will clear the graphic objects.
//				            circles.removeAllElements();
//				            area.width=0;
//				            area.height=0;
				            changed = true;
				        } else {
				        	
				            //int x = e.getX() - W/2;
				            //int y = e.getY() - H/2;
				    
				      //no overlapping gates  
				        	
				        CEBTypetemp = circuitElementButtonClicked;
				        
//				        if(optionButtons == MOVE_BUTTON) {
//				        	
//				        }
				        
				        if(optionButtons == DELETE_BUTTON) {
				        	
				        	
				        	System.out.println("I am here 1");
				        	boolean b = false;
				        	String id = "";
				        	for (ImageCoordAndType temp : imageInfo) {
				        		
				        		int lowy = temp.getUpperLeftImageY()-20+25;
				        		int lowx = temp.getUpperLeftImageX()-33+50-12;
				        		int highy = temp.getUpperLeftImageY()+20+25;
				        		int highx = temp.getUpperLeftImageX()+33+50+12;
				        		

				        		if ((((e.getX()<=highx) && (e.getX()>=lowx)) && ((e.getY()<=highy) && (e.getY()>=lowy)))) {
				        			System.out.println("I am here");
				        			circuitElementButtonClicked = INVALID;
				        			id = temp.getID();
				        			b = true;
				        			
				        		}
				        	}
				        	
				        	if(b) {
				        		
				        		System.out.println("Deleted object: " + id);
				        		model.removeCircuitElementHelper(id);
				        		int indexToSave = 0;
				        		for(int i = 0; i < imageInfo.size(); ++i) {
				        			if(imageInfo.get(i).getID().equals(id)) {
				        				indexToSave = i;
				        				imageInfo.remove(i);
				        			}
				        		}
				        		circuitElementImages.remove(indexToSave);
				        		optionButtons = INVALID;
				        	}
			        		b = false;
				        	
				        	
				        }
				        
				        else if(optionButtons == CONNECT_BUTTON) {
				        	
				        	boolean b = false;
				        	String id = "";
				        	for (ImageCoordAndType temp : imageInfo) {
				        		
				        		int lowy = temp.getUpperLeftImageY()-10;
				        		int lowx = temp.getUpperLeftImageX()-20;
				        		int highy=lowy+70;
				        		int highx=lowx+140;
				        		

				        		if ((((e.getX()<=highx) && (e.getX()>=lowx)) && ((e.getY()<=highy) && (e.getY()>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			id = temp.getID();
				        			b = true;
				        			
				        		}
				        	}
				        	
				        	if(b) {
				        		
				        		System.out.println(id);
				        		parentID = id;
				        		optionButtons = PARENT_SELECTED;
				        	}
			        		b = false;
			        		
			        		
				       
				        	
				        }
				        
			        	else if(optionButtons == TOGGLE_INPUT_BUTTON) {
                        
                        String id = "";
                        for (int i = 0; i < imageInfo.size(); i++) {
                            
                        	ImageCoordAndType temp = imageInfo.get(i);
                            int lowy = temp.getUpperLeftImageY()-10;
                            int lowx = temp.getUpperLeftImageX()-20;
                            int highy=lowy+70;
                            int highx=lowx+140;
                            
                            if ((((e.getX()<=highx) && (e.getX()>=lowx)) && ((e.getY()<=highy) && (e.getY()>=lowy)))) {
                                circuitElementButtonClicked = INVALID;
                                id = temp.getID();
                                int newLogicValue = model.toggleInputFromID(id);
                                
                                if(newLogicValue == 1) {
                                	circuitElementImages.remove(i);
                                	circuitElementImages.add(i, elementImageTypes.get(INPUT_LOGIC_1));
                                }
                                
                                if(newLogicValue == 0) {
                                	circuitElementImages.remove(i);
                                	circuitElementImages.add(i, elementImageTypes.get(INPUT_LOGIC_0));
                                }
                                
                                if(newLogicValue == 1 || newLogicValue == 0) {
                                    System.out.println("Input " + id + " toggled");
                                }
                                else {System.out.println("Error, non input selected");}
                                
                            	}
                        	}
			        	}
				        
				        else if(optionButtons == PARENT_SELECTED) {
				        	
				        	boolean b = false;
				        	String id = "";
				        	for (ImageCoordAndType temp : imageInfo) {
				        		
				        		int lowy = temp.getUpperLeftImageY()-10;
				        		int lowx = temp.getUpperLeftImageX()-20;
				        		int highy=lowy+70;
				        		int highx=lowx+140;
				        		

				        		if ((((e.getX()<=highx) && (e.getX()>=lowx)) && ((e.getY()<=highy) && (e.getY()>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			id = temp.getID();
				        			b = true;
				        			
				        		}
				        	}
				        	
				        	if(b) {
				        		
				        		System.out.println(id);
				        		String childID = id;
				        		boolean connectionSuccessful = model.makeConnectionFromIDs(parentID, childID);
				        		if(connectionSuccessful) {
				        			System.out.println("connection made between " + parentID + " and " + childID);
				        			//model.printAllConnectionsForAllCircuitElements();
				        		}
				        		
				        		if(!connectionSuccessful) {
				        			JOptionPane.showMessageDialog(frame, "Not a valid connection!");
				        		}
				        		
				        		optionButtons = INVALID;
				        		
				        	}
			        		b = false;
			        		
			        		
				        	
				        }
				     	
				        else if (circuitElementButtonClicked != INVALID){ //General case
				        	boolean b = false;
				        	String id = "";
				        	for (ImageCoordAndType temp : imageInfo) {
				        		int clicklowx = e.getX()-50;
				        		int clickhighx = e.getX()+50;
				        		int clicklowy = e.getY()-25;
				        		int clickhighy = e.getY()+25;
				        		int lowy = temp.getUpperLeftImageY()-10;
				        		int lowx = temp.getUpperLeftImageX()-20;
				        		int highy=lowy+70;
				        		int highx=lowx+140;
				        		int bufferlowy = lowy-10;
				        		int bufferlowx = lowx-20;
				        		int bufferhighy= highy+10;
				        		int bufferhighx= highx+20;
				        		
				        		
				        		
				        		if ((((clicklowx<=highx) && (clicklowx>=lowx)) && ((clicklowy<=highy) && (clicklowy>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			JOptionPane.showMessageDialog(frame, "You have tried to place a new circuit element too close to an existing element!");
				        			id = temp.getID();
				        			b = true;

				  
				        		}
				        		else if ((((clicklowx<=highx) && (clicklowx>=lowx)) && ((clickhighy<=highy) && (clickhighy>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			JOptionPane.showMessageDialog(frame, "You have tried to place a new circuit element too close to an existing element!");
				        			id = temp.getID();
				        			b = true;

				        		}
				        		else if ((((clickhighx<=highx) && (clickhighx>=lowx)) && ((clicklowy<=highy) && (clicklowy>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			JOptionPane.showMessageDialog(frame, "You have tried to place a new circuit element too close to an existing element!");
				        			id = temp.getID();
				        			b = true;
				        			
				        			
				        		}
				        		else if ((((clickhighx<=highx) && (clickhighx>=lowx)) && ((clickhighy<=highy) && (clickhighy>=lowy)))) {
				        			circuitElementButtonClicked = INVALID;
				        			JOptionPane.showMessageDialog(frame, "You have tried to place a new circuit element too close to an existing element!");
				        			id = temp.getID();
				        			b = true;

				        		}
				        		
				        		if(b) {System.out.println(id);}
				        		b = false;
				        	}//end for
				        }//end else
				        	

				        	
				      //end no overlap  	
				            this.x = e.getX() - 50;
				            this.y = e.getY() - 25;
				            if (x < 0) x = 20;
				            if (y < 0) y = 10;
				            System.out.println(this.x);
				            System.out.println(this.y);
				            
				            if(circuitElementButtonClicked!=INVALID) {
				            	String newElementID = model.createandAddCircuitElement(circuitElementButtonClicked);
					            circuitElementImages.add(elementImageTypes.get(circuitElementButtonClicked));
					            ImageCoordAndType imageCoordType = new ImageCoordAndType(circuitElementButtonClicked, x, y);
					            imageCoordType.setID(newElementID);
					            imageInfo.add(imageCoordType);
					            circuitElementButtonClicked = INVALID;
				            }
				            
//				            Rectangle rect = new Rectangle(x, y, W, H);
//				            circles.addElement(rect);
//				            drawingPane.scrollRectToVisible(rect);
				// 
				            int this_width = (x + W + 2);
				            if (this_width > area.width) {
				                area.width = this_width; changed=true;
				            }
				 
				            int this_height = (y + H + 2);
				            if (this_height > area.height) {
				                area.height = this_height; changed=true;
				            }
				        }
				        if (changed) {
				            //Update client's preferred size because
				            //the area taken up by the graphics has
				            //gotten larger or smaller (if cleared).
				            drawingPane.setPreferredSize(area);
				 
				            //Let the scroll pane know to update itself
				            //and its scrollbars.
				            drawingPane.revalidate();
				        }
				        drawingPane.repaint();
				    }
				    public void mouseClicked(MouseEvent e){}
				    public void mouseEntered(MouseEvent e){}
				    public void mouseExited(MouseEvent e){}
				    public void mousePressed(MouseEvent e){}
				    
				    
				} // end class ScrollDemo2
				
				/*
				 * WE CHANGED:
				 *  JComponent newContentPane = new ScrollDemo2(); to
				 *  ScrollDemo2 newContentPane = new ScrollDemo2();
				 */
				ScrollDemo2 newContentPane = new ScrollDemo2();
				
				
		        newContentPane.setOpaque(true); //content panes must be opaque
		        frame.setContentPane(newContentPane);
		        
		     
				
				
// Some of the preceding code is borrowed from ScrollDemo2.java, a file from an Oracle Tutorial
// See Copyright at the beginning of GUI.java above
// See URL above for original, non-altered ScrollDemo.java code		        
//#############################################################################################

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set launch window size
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		
		// Adds JToolbar and buttons to JFrame
		
		JToolBar gates_and_io = new JToolBar("Gates & I/O");
		gates_and_io.setRollover(true);
		
		JButton button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/And_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/and_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = AND_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the AND Button " + circuitElementButtonClicked);
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Or_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/or_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = OR_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the OR Button " + circuitElementButtonClicked);
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Not_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/not_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NOT_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the NOT Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xor_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = XOR_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the XOR Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nand_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nand_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NAND_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the NAND Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nor_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NOR_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the NOR Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xnor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xnor_with_text_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = XNOR_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the XNOR Button");
			}
		});
		gates_and_io.add(button);
		
		
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/input_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.setToolTipText("<html> <font size=4>Click this button then cick in the workspace to place an Input circuit element<br> Inputs are the logic values flowing into the circuit(s)<br>Input values are initialized to 0</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = INPUT_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the INPUT Button");
			}
		});
		gates_and_io.add(button);
		
		
	
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/output_small.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.setToolTipText("<html> <font size=4> Click this button then click in the workspace to place an Output circuit element<br> Outputs display the logic values flowing out of a circuit<br>If circuit cannot be evaluated, Output will display \'X\'</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = OUTPUT_BUTTON;
				optionButtons = INVALID;
				System.out.println("I clicked the OUTPUT Button");
			}
		});
		gates_and_io.add(button);
		
		//Add hover to tell user what the following buttons do:
//		button = new JButton("MOVE");
//		button.setToolTipText("<html> <font size=4> This button allows you to move an existing circuit element in the workspace<br> 1. "
//				+ "Click Move <br> 2. Click desired circuit element <br> 3. Click new location for circuit element</font> </html>");
//		button.setSize(new Dimension(50, 50));
//		button.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				System.out.println("I clicked the MOVE Button");
//				optionButtons = MOVE_BUTTON;
//			}
//		});
		gates_and_io.add(button);
		
		button = new JButton("DELETE");
		button.setPreferredSize(new Dimension(50, 38));
		button.setToolTipText("<html> <font size=4> This button allows you to delete an existing circuit element in the workspace<br>All wires associated"
				+ "with the deleted circuit element will also be deleted<br> 1. "
				+ "Click Delete <br> 2. Click desired circuit element</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("I clicked the DELETE Button");
				
				optionButtons = DELETE_BUTTON;
				circuitElementButtonClicked = INVALID;
			}
		});
		gates_and_io.add(button);
		
		button = new JButton("CONNECT");
		button.setToolTipText("<html> <font size=4> This button allows you to connect two existing circuit elements with a wire in the workspace<br> 1. "
				+ "Click Connect <br> 2. Click desired parent circuit element <br> 3. Click desired child circuit element.</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("I clicked the CONNECT Button");
				
				optionButtons = CONNECT_BUTTON;
				circuitElementButtonClicked = INVALID;
				
				//test
				model.printAllFamilyTrees();
				//end test
				
			}
		});
		gates_and_io.add(button);
		
		
		 button = new JButton("TOGGLE INPUT");
			button.setToolTipText("<html> <font size=4> This button lets you change the input signal for an Input.<br> 1. Click TOGGLE INPUT Button<br>2. Click the Input circuit element whose value you would like to change<br> 3. The Input value has now changed (from 0 to 1 or 1 to 0)</font> </html>");
	        button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                System.out.println("I clicked the TOGGLE Button");
	                optionButtons = TOGGLE_INPUT_BUTTON;
	                circuitElementButtonClicked = INVALID;
	            }
	        });
	    gates_and_io.add(button);
		
		button = new JButton("CANCEL");
		button.setToolTipText("<html> <font size=4> This button allows you to cancel any operation.</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("I clicked the CANCEL Button");
				circuitElementButtonClicked = INVALID;
				optionButtons = INVALID;
			}
		});
		gates_and_io.add(button);
		
		button = new JButton("EVALUATE");
		button.setToolTipText("<html> <font size=4> This button will evaluate the circuit(s) and display output values.<br>ALL circuit elements must be fully connected <br> There can be no \"dangling\" wires</font> </html>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("I clicked the EVALUATE Button");
				circuitElementButtonClicked = INVALID;
				boolean canWeEvaluate = model.evaluateCircuitNetwork();
				
				if(canWeEvaluate) {
					
					for(int i = 0; i < newContentPane.getImageInfo().size(); ++i) {
						if(newContentPane.getImageInfo().get(i).getElementType() == 9) {
							String id = newContentPane.getImageInfo().get(i).getID();
							int outValue = model.getOutputValueFromID(id);
							
							if(outValue == 0) {
								newContentPane.getCircuitElementImages().remove(i);
								newContentPane.getCircuitElementImages().add(i, newContentPane.getElementImageTypes().get(10));
								newContentPane.getDrawingPane().revalidate();
								newContentPane.getDrawingPane().repaint();
					        
							}
							
							if(outValue == 1) {
								newContentPane.getCircuitElementImages().remove(i);
								newContentPane.getCircuitElementImages().add(i, newContentPane.getElementImageTypes().get(11));
								newContentPane.getDrawingPane().revalidate();
								newContentPane.getDrawingPane().repaint();
							}
						}
					}
				}
				
				if(!canWeEvaluate) {

					JLabel errorDialog = new JLabel();
					errorDialog.setHorizontalTextPosition(SwingConstants.CENTER);
					errorDialog.setVerticalTextPosition(SwingConstants.NORTH);
					errorDialog.setVerticalAlignment(SwingConstants.NORTH);
					errorDialog.setText("The circuit has missing connections and cannot be evaluated! Please complete the circuit.<br>All output values will now be set to X");
					JOptionPane.showMessageDialog(null,errorDialog, "Error!", 0, null);
					
					//elementImageTypes
					
					for(int i = 0; i < newContentPane.getImageInfo().size(); ++i) {
						if(newContentPane.getImageInfo().get(i).getElementType() == OUTPUT_BUTTON) {
							newContentPane.getCircuitElementImages().remove(i);
							newContentPane.getCircuitElementImages().add(i, newContentPane.getElementImageTypes().get(12));
							newContentPane.getDrawingPane().revalidate();
							newContentPane.getDrawingPane().repaint();
						}
					}
					
				}
				
				optionButtons = INVALID;
		
			}

		});
		gates_and_io.add(button);
		
	
		frame.getContentPane().add(gates_and_io, BorderLayout.NORTH);
		
		// End of JToolbar code
		
		
		// SIDE-BAR CODE COMMENTED OUT BELOW -- WILL ADD AGAIN IN FUTURE BY UNCOMMENTING
		// DO NOT REMOVE COMMENTED OUT CODE BELOW
		
//		// Add side-bar
//		// Expandable List     
//		DefaultMutableTreeNode falsetop = new DefaultMutableTreeNode("");
//		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Project1");
//		DefaultMutableTreeNode child = new DefaultMutableTreeNode("Example Circuit One");
//		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("32bit ALU");
//		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Carry Look Ahead Adder");
//		DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("32bit CPU");
//		falsetop.add(top);
//		falsetop.add(top2);
//		top.add(child);
//		top2.add(child2);
//		top2.add(child3);
//		dirStructure = new JTree(falsetop);
//		dirStructure.setRootVisible(false);
//		JScrollPane treeView = new JScrollPane(dirStructure);
//		treeView.setPreferredSize(new Dimension(200,768));
//		dirStructure.expandRow(1);
//		dirStructure.expandRow(2);
//				
//				         
//				
//		frame.getContentPane().add(treeView, BorderLayout.WEST);
		
		// DO NOT REMOVE COMMENTED OUT CODE ABOVE
		
		
		
		frame.getContentPane().setBackground(Color.GRAY);
		
		//Variables used for Menus
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the Main Menu Bar
		menuBar = new JMenuBar();
		
		// NON-WORKING MENU ITEMS -- WILL BE UNCOMMENTED FOR FUTURE USE
		// DO NOT REMOVE THIS COMMENTED OUT CODE BELOW

//		//Create File Menu
//		menu = new JMenu("File");
//		menu.setMnemonic(KeyEvent.VK_F);
//		menu.getAccessibleContext().setAccessibleDescription(
//		        "File Menu");
//		
//		//Add menu items to File Menu
//		menuItem = new JMenuItem("Save");
//		menu.add(menuItem);
//		menuItem = new JMenuItem("Load");
//		menu.add(menuItem);
//		menuItem = new JMenuItem("Import Module");
//		menu.add(menuItem);
//		menuItem = new JMenuItem("Export Module");
//		menu.add(menuItem);
//		menuItem = new JMenuItem("Set Project Directory");
//		menu.add(menuItem);
//		
//		//Add File Menu to Main Menu
//		menuBar.add(menu);
//		
//		//Create Edit Menu
//		menu = new JMenu("Edit");
//		menu.setMnemonic(KeyEvent.VK_E);
//		menu.getAccessibleContext().setAccessibleDescription(
//		        "Edit Menu");
//		
//		menuItem = new JMenuItem("Options");
//		menu.add(menuItem);
//		
//		menuBar.add(menu);
//		
//		//Create View Menu
//		menu = new JMenu("View");
//		menu.setMnemonic(KeyEvent.VK_V);
//		menu.getAccessibleContext().setAccessibleDescription(
//		        "View Menu");
//		
//		menuItem = new JMenuItem("Toggle Side-Bar");
//		menu.add(menuItem);
//		
//		menuBar.add(menu);
		
		// NON-WORKING MENU ITEMS -- WILL BE UNCOMMENTED FOR FUTURE USE
		// DO NOT REMOVE THIS COMMENTED OUT CODE ABOVE
		
		//Create Help Menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Help Menu");
		
		//Add About menu item
		menuItem = new JMenuItem("About");
		menuItem.setMnemonic(KeyEvent.VK_A);
		
		
		//Create an action listener for the about menu
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JLabel abouttext = new JLabel();
				abouttext.setHorizontalTextPosition(SwingConstants.CENTER);
				abouttext.setVerticalTextPosition(SwingConstants.NORTH);
				//abouttext.setPreferredSize(new Dimension(1300,768));
				abouttext.setVerticalAlignment(SwingConstants.NORTH);
				abouttext.setText("<html> <h3><font color = 'red'> Welcome to LogiCAD! </font></h3> <br>" +  
						"LogiCAD is a digital circuit Computer-Aided Design application. " + 
						"As you dive into our software you may notice several tools and menus at your disposal, so lets take a look at what we have to offer:<br>" +
						"<br>" + 
						"<br>" + 
						"Top Menu Bar:<br>" + 
						"<br>" + 
						"-File: Drop down menu of the following:<br>" + 
						"<br>" +								
						"&ensp;  &ensp;  Save:&ensp;  User is able to save project files.<br>" + 
						"&ensp;  &ensp;  Load:&ensp;  User is able to load previouse project files.<br>" + 
						"&ensp;  &ensp;  Import Module:&ensp;  User is able to save circuit designs as modules that can be loaded and used in future projects.<br>" + 
						"&ensp;  &ensp;  Export Module:&ensp;  A module is a completed circuit that is used after being saved as an element of another circuit.<br>" + 
						"&ensp;  &ensp;  Set Project Path:&ensp;  User is able to set the project path to be able to use the file explorer pannel.<br>" + 
						"<br>" + 
						"-Edit: Drop down menu of the following:<br>" + 
						"<br>" +
						"&ensp;  &ensp;  Options:&ensp;  User is able to set various preferences.<br>" + 
						"<br>" +
						"-View: Drop down menu of the following:<br>" + 
						"<br>" + 
						"&ensp;  &ensp;  Toggle Side-Bar:&ensp;  User is able to set whether they want to see the file explorer or not.<br>" + 
						"<br>" +
						"-Help: Drop down menu of the following:<br>" + 
						"<br>" + 
						"&ensp;  &ensp;  About:&ensp;  Information about the software and button features.<br>" + 
						"<br>" + 
						"<br>" + 
						"<br>" + 
						"Gate Menu Bar:<br>" + 
						"<br>" + 
						"-AND Gate Button:&ensp;  An AND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-OR Gate Button:&ensp;  An OR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOT Gate Button:&ensp;  A NOT gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given one input and will provide an output.<br>" + 
						"\r\n" + 
						"-XOR Gate Button:&ensp;  An XOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NAND Gate Button:&ensp;  A NAND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOR Gate Button:&ensp;  A NOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-XNOR Gate Button:&ensp;  A XNOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-Input Gate Button:&ensp;  An Input wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
						"\r\n" + 
						"-Output Gate Button:&ensp;  An Output wire will be presented to place on any logic gate or to attach to any other wires.<br>" +
						"<br>" + 
						"-This gate menu bar is able to be moved around. By clicking the very left of the bar, a user can drag the menu bar all around and place it wherever one wishes.<br>" +
						" If a user decides to even take the menu bar outside the window they may do so. When they click the exit button the bar will go back to where it last was.<br>" +
						"<br>" + 
						"<br>" + 
						"<br>" +
						"Left Side Panel:" + 
						"<br>" + 
						"<br>" + 
						"-This space displays your current working directory after you specify the file path.<br>" + 
						"<br>" + 
						"<br>" + 
						"Grid Workspace:<br>" + 
						"<br>" + 
						"-This is the area a user will be given to work in.<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" + 
						"Some of the code used in this program has been adapted from an Oracle tutorial and"+
						" file ScrollDemo2.java, for which the following Copyright and conditions apply: <br><br>"+
						" Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.<br>" + 
						" <br>" + 
						" Redistribution and use in source and binary forms, with or without<br>" + 
						" modification, are permitted provided that the following conditions<br>" + 
						" are met:<br>" + 
						" <br>" + 
						"   - Redistributions of source code must retain the above copyright<br>" + 
						"     notice, this list of conditions and the following disclaimer.<br>" + 
						" <br>" + 
						"   - Redistributions in binary form must reproduce the above copyright<br>" + 
						"     notice, this list of conditions and the following disclaimer in the<br>" + 
						"     documentation and/or other materials provided with the distribution.<br>" + 
						" <br>" + 
						"   - Neither the name of Oracle or the names of its<br>" + 
						"     contributors may be used to endorse or promote products derived<br>" + 
						"     from this software without specific prior written permission.<br>" + 
						" <br>" + 
						" THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS<br>" + 
						" IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,<br>" + 
						" THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR<br>" + 
						" PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR<br>" + 
						" CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,<br>" + 
						" EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,<br>" + 
						" PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR<br>" + 
						" PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF<br>" + 
						" LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING<br>" + 
						" NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS<br>" + 
						" SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.<br>" + 
						"<br>" );
								
					
						
						JScrollPane aboutpane = new JScrollPane(abouttext);
						JPanel about = new JPanel();
						about.add(aboutpane);
						aboutpane.getViewport().add(abouttext);
						aboutpane.setPreferredSize(new Dimension(1300,768));
						JOptionPane.showMessageDialog(null,aboutpane, "About", 1, null);
						
						
					}

				});
				
		menu.add(menuItem);
		
		//Add Help Menu to Main Menu
		menuBar.add(menu);
					
		//Set Main Menu as JMenuBar
		frame.setJMenuBar(menuBar);
		
		
		
		//Pack contents of JFrame nicely
		frame.pack();
		
		//Make JFrame visible
		frame.setVisible(true);
			
	} // end run method
	

} // end class GUI 
