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

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set launch window size
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		
		// Adds JToolbar and buttons to JFrame
		
		JToolBar gates_and_io = new JToolBar("Gates & I/O");
		gates_and_io.setRollover(true);
		
		JButton button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/or_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/not_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/xor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/nand_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/nor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
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
				abouttext.setPreferredSize(new Dimension(1300,768));
				abouttext.setVerticalAlignment(SwingConstants.NORTH);
				abouttext.setText("<html> <h3> Welcome to LogiCAD! </h3> <br>" +  
						"LogiCAD is a digital circuit Computer-Aided Design application. " + 
						"As you dive into our software you may notice several tools and menus at your disposal, so lets take a look at what we have to offer:<br>" +
						"<br>" + 
						"<br>" + 
						"Top Menu Bar:<br>" + 
						"<br>" + 
						"-File: Drop down menu of the following:<br>" + 
						"<br>" +								"&ensp;  &ensp;  Save:  User is able to save project files.<br>" + 
						"&ensp;  &ensp;  Load:  User is able to load previouse project files.<br>" + 
						"&ensp;  &ensp;  Import Module:  User is able to save circuit designs as modules that can be loaded and used in future projects.<br>" + 
						"&ensp;  &ensp;  Export Module:  A module is a completed circuit that is used after being saved as an element of another circuit.<br>" + 
						"<br>" + 
						"-Edit: Drop down menu of the following:<br>" + 
						"<br>" + 
						"-View: Drop down menu of the following:<br>" + 
						"<br>" + 
						"-Help: Drop down menu of the following:<br>" + 
						"<br>" + 
						"&ensp;  &ensp;  About:  Information about the software and button features.<br>" + 
						"<br>" + 
						"<br>" + 
						"<br>" + 
						"Gate Menu Bar:<br>" + 
						"<br>" + 
						"-AND Gate Button: An AND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-OR Gate Button: An OR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOT Gate Button: A NOT gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given one input and will provide an output.<br>" + 
						"\r\n" + 
						"-XOR Gate Button: An XOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NAND Gate Button: A NAND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOR Gate Button: A NOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-XNOR Gate Button: A XNOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-Input Gate Button: An Input wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
						"\r\n" + 
						"-Output Gate Button: An Output wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
						"<br>" + 
						"<br>" + 
						"<br>" +
						"Left Side Panel:\r\n" + 
						"<br>" + 
						"<br>" + 
						"-This space displays your current working directory after you specify the file path.<br>" + 
						"<br>" + 
						"<br>" + 
						"Grid Workspace:<br>" + 
						"<br>" + 
						"-This is the area a user will be given to work in.<br>" +
						"<br>" +
						"<br>");
								
					
						
						JScrollPane aboutpane = new JScrollPane(abouttext);
						JPanel about = new JPanel();
						about.add(aboutpane);
						aboutpane.getViewport().add(abouttext);
						aboutpane.setPreferredSize(d);
						JOptionPane.showMessageDialog(null, aboutpane);
						
						
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
