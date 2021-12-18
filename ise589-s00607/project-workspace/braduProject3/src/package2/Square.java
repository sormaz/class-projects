package package2;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Square extends DrawObject{
	private double x1, y1, a;
	public Square(double x1, double y1, double a){
		super (x1,y1);
		this.x1 = x1;
		this.y1 = y1;
		this.a = a;
	}
	public void printout(){
		System.out.println(super.toString());
	}
	
	public double getA(){
		return this.a;
	}
	
	public void init() {
		 panel = new SquarePanel(this);
	}

	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		Line2D.Double line1=new Line2D.Double(this.x1,this.y1,this.x1 + this.a,this.y1);
		Line2D.Double line2=new Line2D.Double(this.x1 + this.a,this.y1,this.x1 + this.a,this.y1 + this.a);
		Line2D.Double line3=new Line2D.Double(this.x1 + this.a,this.y1 + this.a,this.x1,this.y1+this.a);
		Line2D.Double line4=new Line2D.Double(this.x1,this.y1 + this.a,this.x1,this.y1);
		shapes.add(line1);
		shapes.add(line2);
		shapes.add(line3);
		shapes.add(line4);
		return shapes;
	}
}

class SquarePanel extends JPanel {
	Square square; 
	public SquarePanel(Square s) {
		setLayout(new BorderLayout());		
		
		JPanel westPanel=new JPanel(new GridLayout(4,1));
		westPanel.add(new JLabel("Time"));
		westPanel.add(new JLabel("xPosition"));
		westPanel.add(new JLabel("yPosition"));
		westPanel.add(new JLabel("Side"));
		
		
		JPanel centerPanel=new JPanel(new GridLayout(4,1));
		JFormattedTextField timeText= new JFormattedTextField(DateFormat.getDateTimeInstance());
		timeText.setValue(s.getTime());
		centerPanel.add(timeText);
		JFormattedTextField x1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		x1Text.setValue(s.getX());
		centerPanel.add(x1Text);
		JFormattedTextField y1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		y1Text.setValue(s.getY());
		centerPanel.add(y1Text);
		JFormattedTextField aText= new JFormattedTextField(NumberFormat.getNumberInstance());
		aText.setValue(s.getA());
		centerPanel.add(aText);
		
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.add(westPanel,BorderLayout.WEST);
		northPanel.add(centerPanel,BorderLayout.CENTER);
		
		add(northPanel,BorderLayout.NORTH);
	}
}
