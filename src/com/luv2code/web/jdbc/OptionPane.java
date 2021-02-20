package com.luv2code.web.jdbc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OptionPane extends WindowAdapter{
	
	JFrame frame;
	
	public OptionPane() {
		/*
		frame = new JFrame();
		frame.addWindowListener(this);
		frame.setSize(300, 300);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		*/
	}
	
	public void windowClosing(WindowEvent e) {
		
		int a = JOptionPane.showConfirmDialog(frame, "Are you sure?");
		
		if(a == JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	public static void main(String[] args) {
		
		new OptionPane();
		
		//System.out.println("deneme");
		
		//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
	}
	
}








