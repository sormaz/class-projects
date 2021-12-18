package edu.ohiou.ise.ise589;

import java.io.*;

import javax.swing.JPanel;

public class Circle extends DrawObject {
	
	private double radius;
	
	public Circle (double x, double y, double r) throws DrawObjectException
	
	{
		super (x,y);
		if (r <= 0) {
		throw new DrawObjectException("Radius has to be positive number.");	
		}
		radius = r;
		
	}
	
	public double getRadius () {
		return radius;
	}
	
	public void setRadius( double r) {
		this.radius = r;
	}
	
	public static Circle readCircle (String fileName) 
							throws FileNotFoundException, DrawObjectException {
		File f = new File (fileName);
		FileInputStream fs = new FileInputStream (f);
		f.canRead();
		Circle c = new Circle(1,2,3);
		return c;
	}
	
//	public String toString

	public void printout () {
		System.out.println(toString());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Circle c = readCircle("c:/circle.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DrawObjectException ce) {
			ce.printStackTrace();
		}
	}
	public JPanel getPanel () {
		return new JPanel();
	}

}
