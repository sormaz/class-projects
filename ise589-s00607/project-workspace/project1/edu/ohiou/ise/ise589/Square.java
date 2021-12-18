package edu.ohiou.ise.ise589;

import javax.swing.JPanel;

/** Square class represents a square in 2d space
 * 
 * @author Dusan Sormaz
 *
 */
public class Square extends DrawObject {
	
	/**
	 * side of the square
	 */
	private double side;

	public Square(double x, double y, double s) throws DrawObjectException{
		super(x, y);
		if (s <= 0) {
			throw new DrawObjectException("Side has to be positive number.");	
			}
		side = s;
	}
	
	/**
	 * this method modifies the side of the square to given parameter
	 * @param s - new side of the square
	 */
	public void setSide (double s) {
		side = s;
	}
	/**
	 * 
	 * @return
	 */
	public double diagonal () {
		return side * Math.sqrt(2);
	}

	@Override
	public void printout() {
		// TODO Auto-generated method stub
System.out.println(toString());
	}
	
	public JPanel getPanel () {
		return new JPanel();
	}


}
