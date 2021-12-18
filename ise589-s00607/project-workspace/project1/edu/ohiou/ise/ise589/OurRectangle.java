package edu.ohiou.ise.ise589;

public class OurRectangle {
	private double width;
	private double height;
	
	public OurRectangle (double w, double h) {
		width = w;
		height = h;
	}
	
	public OurRectangle () {
		this (1.0, 1.0);
		
	}
	public void setWidth (double w) {
		width = w;
		
	}
	
	public double getHeight () {
		return height;
	}

	public double getWidth () {
		return width;
	}
	
	public double area () {
		return width * height;
		
	}

}
