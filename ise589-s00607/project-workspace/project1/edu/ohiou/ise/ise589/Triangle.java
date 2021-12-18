package edu.ohiou.ise.ise589;

import java.awt.geom.Point2D;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Triangle extends DrawObject {
	
	private double base;
	private double height;

	public Triangle(Point2D.Double point, double b, double h)  
								throws DrawObjectException{
		this(point.x, point.y, b, h);
		// TODO Auto-generated constructor stub
	}

	public Triangle(double x, double y, double b, double h) 
								throws DrawObjectException{
		super(x, y);
		if (b <=0) {
			throw new DrawObjectException("The base should be a positive number.");
		}
		if (h <= 0) {
			throw new DrawObjectException("The height should be a positive number.");
		}
		base = b;
		height = h;
	}

	@Override
	public void printout() {
		// TODO Auto-generated method stub
		System.out.println(toString());
		System.out.println(getTime().toString());
		System.out.println(getTime().toLocaleString());
		System.out.println(getTime().toGMTString());
		System.out.println(new Date(0).toString());


	}
	
	public JPanel getPanel () {
		return new TrianglePanel();
	}

}

class TrianglePanel extends JPanel {
	public TrianglePanel () {
		setLayout(new BorderLayout());
		add(new JLabel("xpos"), BorderLayout.NORTH);
	}
}
