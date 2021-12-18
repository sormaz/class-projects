package package2;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Triangle extends DrawObject {
	private double x1, y1, b, h;

	public Triangle(double x1, double y1, double b, double h) {
		super(x1, y1);
		this.x1 = x1;
		this.y1 = y1;
		this.b = b;
		this.h = h;
	}
	
	public double getB(){
		return this.b;
	}
	
	public double getH(){
		return this.h;
	}

	public void printout() {
		System.out.println(super.toString());
	}

	public void init() {
		 panel = new TrianglePanel(this);
	}

	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		Line2D.Double line1=new Line2D.Double(this.x1,this.y1,this.x1 + this.b,this.y1);
		Line2D.Double line2=new Line2D.Double(this.x1 + this.b,this.y1,(this.x1 + this.b) / 2,this.y1 + this.h);
		Line2D.Double line3=new Line2D.Double((this.x1 + this.b) / 2,this.y1 + this.h,this.x1,this.y1);
		// only for isosceles triangles
		shapes.add(line1);
		shapes.add(line2);
		shapes.add(line3);
		return shapes;
	}
}

class TrianglePanel extends JPanel {
	Triangle triangle; 
	public TrianglePanel(Triangle t) {
		setLayout(new BorderLayout());		
		
		JPanel westPanel=new JPanel(new GridLayout(5,1));
		westPanel.add(new JLabel("Time"));
		westPanel.add(new JLabel("xPosition"));
		westPanel.add(new JLabel("yPosition"));
		westPanel.add(new JLabel("Base"));
		westPanel.add(new JLabel("Height"));
		
		
		JPanel centerPanel=new JPanel(new GridLayout(5,1));
		JFormattedTextField timeText= new JFormattedTextField(DateFormat.getDateTimeInstance());
		timeText.setValue(t.getTime());
		centerPanel.add(timeText);
		JFormattedTextField x1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		x1Text.setValue(t.getX());
		centerPanel.add(x1Text);
		JFormattedTextField y1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		y1Text.setValue(t.getY());
		centerPanel.add(y1Text);
		JFormattedTextField bText= new JFormattedTextField(NumberFormat.getNumberInstance());
		bText.setValue(t.getB());
		centerPanel.add(bText);
		JFormattedTextField hText= new JFormattedTextField(NumberFormat.getNumberInstance());
		hText.setValue(t.getH());
		centerPanel.add(hText);
		
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.add(westPanel,BorderLayout.WEST);
		northPanel.add(centerPanel,BorderLayout.CENTER);
		
		add(northPanel,BorderLayout.NORTH);
	}
}
