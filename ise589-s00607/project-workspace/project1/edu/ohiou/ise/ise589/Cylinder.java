package edu.ohiou.ise.ise589;

public class Cylinder {
	
	private Circle circle;
	private double height;

	public Cylinder (double x, double y, double d, double h) 
											throws DrawObjectException {
		circle = new Circle (x,y,d/2);
		height = h;
	}
	
	
	public void display() {
		Circle c;
		try {
			c = new Circle (circle.getX(), circle.getX(), circle.getRadius());
		} catch (DrawObjectException e) {
			// should never happen
		}
	}
}
