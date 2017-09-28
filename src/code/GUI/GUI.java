package code.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.tree.DefaultMutableTreeNode;


public class GUI {
	private JTree dirStructure;
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
		
		// gridSpaceLabel is the workspace for the user
		JLabel gridSpaceLabel = new JLabel();
		
		// For the prototype we put an example circuit in the workspace
		// to give the user an example of what he can create in the
		// MVP.
		Image circuit_image;
		try {
			circuit_image = ImageIO.read(getClass().getResourceAsStream("images/sample_circuit2.png"));
			gridSpaceLabel.setIcon(new ImageIcon(circuit_image));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gridSpaceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gridSpaceLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		JScrollPane gridPane = new JScrollPane(gridSpaceLabel);

		
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
			Image img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Or_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/or_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Not_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/not_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nand_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nand_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xnor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xnor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/input.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		
	
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/output.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
	
		frame.getContentPane().add(gates_and_io, BorderLayout.NORTH);
		
		// End of JToolbar code
		
		
		// Add side-bar
		// Expandable List     
		DefaultMutableTreeNode falsetop = new DefaultMutableTreeNode("");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Project1");
		DefaultMutableTreeNode child = new DefaultMutableTreeNode("Example Circuit One");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("32bit ALU");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Carry Look Ahead Adder");
		DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("32bit CPU");
		falsetop.add(top);
		falsetop.add(top2);
		top.add(child);
		top2.add(child2);
		top2.add(child3);
		dirStructure = new JTree(falsetop);
		dirStructure.setRootVisible(false);
		JScrollPane treeView = new JScrollPane(dirStructure);
		treeView.setPreferredSize(new Dimension(200,768));
		dirStructure.expandRow(1);
		dirStructure.expandRow(2);
				
				         
				
		frame.getContentPane().add(treeView, BorderLayout.WEST);
		
		// Add gridPane to JFrame -- gridPane holds the JLabel gridSpaceLabel
		frame.getContentPane().add(gridPane, BorderLayout.CENTER);
		
		frame.getContentPane().setBackground(Color.GRAY);
		
		//Variables used for Menus
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the Main Menu Bar
		menuBar = new JMenuBar();

		//Create File Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
		        "File Menu");
		
		//Add menu items to File Menu
		menuItem = new JMenuItem("Save");
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menu.add(menuItem);
		menuItem = new JMenuItem("Import Module");
		menu.add(menuItem);
		menuItem = new JMenuItem("Export Module");
		menu.add(menuItem);
		menuItem = new JMenuItem("Set Project Directory");
		menu.add(menuItem);
		
		//Add File Menu to Main Menu
		menuBar.add(menu);
		
		//Create Edit Menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Edit Menu");
		
		menuItem = new JMenuItem("Options");
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		//Create View Menu
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
		        "View Menu");
		
		menuItem = new JMenuItem("Toggle Side-Bar");
		menu.add(menuItem);
		
		menuBar.add(menu);
		
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
			
	}
}
