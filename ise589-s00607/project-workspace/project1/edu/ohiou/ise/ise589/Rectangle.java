package edu.ohiou.ise.ise589;

public class Rectangle {
	
	public static final int MAX_WIDTH = 4;

	private double width = 0.0;
	
	/**
	 * height of the rectangle
	 */
	private double height = 0.0;
	static double area;
	
	double diagonal;
		

// constructors

	public Rectangle (double inWidth, double height) {
	
		if (inWidth > MAX_WIDTH) 
			throw new IllegalArgumentException();
		else {
		width = inWidth;
		this.height = height;
		}
	}
	
	public Rectangle () {
		this (1,1);
	}
	
	
	public void setWidth (double width) {
		this.width = width;
	}
	
	
	public String toString() {
		return this.getClass().getName() + "<W: " + width + ",H: " + height + ">";
	}

}
