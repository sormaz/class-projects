package edu.ohiou.ise.ise589;

import javax.swing.JPanel;

public class Star extends DrawObject {
	
	double radius;
	int numberSides;
	
	public Star (double x, double y, double r, int n) 
		throws DrawObjectException{
		super (x,y);
		if (r<= 0.0)
			throw new DrawObjectException(
						"Radius should be a positive number.");
		if (n<= 0)
			throw new DrawObjectException("" +
					"	Number of sides should be a positive number.");
		radius = r;
		numberSides = n;
	}
	
	public void printout() {
		
	}
	
	public String toString() {
		return super.toString() + ", radius: " + radius + ", number sides: " + numberSides;
		
	}
	
	public JPanel getPanel () {
		return new JPanel();
	}


}
