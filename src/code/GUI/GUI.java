package code.GUI;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
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
		//Pack contents of JFrame nicely
		frame.pack();
		
		//Make JFrame visible
		frame.setVisible(true);
			
	}
}
