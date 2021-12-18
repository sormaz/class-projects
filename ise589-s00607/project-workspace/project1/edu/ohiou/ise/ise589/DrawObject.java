package edu.ohiou.ise.ise589;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

abstract public class DrawObject {
	private double xPosition, yPosition;
	long timestamp;
	static int count = 0;
	
	public DrawObject (Point2D.Double point) {
//		xPosition = point.x;
//		yPosition = point.y;
//		timestamp = System.currentTimeMillis();
		this (point.x, point.y);	
	}

	public DrawObject (double x, double y) {
		xPosition = x;
		yPosition = y;
		timestamp = System.currentTimeMillis();
		count++;
	}
	
	public void setPosition (double x, double y) {
		xPosition = x;
		yPosition = y;
	}
	
	public double getX () {
		return xPosition;
	}
	
	public void output() {
		
	}
	
	public Date getTime () {
		return new Date (timestamp);
	}
	
	
	public String toString () {
		return this.getClass().getName() + "> xPos:" + xPosition + ", yPos: " + yPosition;
	}
	
	abstract public void printout();
	abstract public JPanel getPanel();
	
	public void show() {
		JFrame frame= new JFrame();
		frame.setSize(300, 300);
		frame.setTitle(toString());
//		frame.getContentPane().add(new JLabel("test"));
		frame.getContentPane().add(getPanel());
		frame.setVisible(true);

	}
}

class DrawDialog extends JDialog {
	
}
