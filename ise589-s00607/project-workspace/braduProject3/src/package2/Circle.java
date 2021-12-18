package package2;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.awt.geom.*;


public class Circle extends DrawObject{
	private double x1, y1, r;
	int n;
	
	public Circle(double x1, double y1, double r){
		super (x1,y1);
		this.x1 = x1;
		this.y1 = y1;
		this.r = r;
		}
	
	public double getR(){
		return this.r;
	}
	
	public void printout(){
		System.out.println(super.toString());
	}
	
	public void init() {
		 panel = new CirclePanel(this);
	}
	
	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		for(n=1;n<=360;n++){
			Line2D.Double line=new Line2D.Double(x1+r*Math.cos(n*Math.PI/180),y1+r*Math.sin(n*Math.PI/180),x1+r*Math.cos((n+1)*Math.PI/180),y1+r*Math.sin((n+1)*Math.PI/180));
			shapes.add(line);
			}
		return shapes;
		}
	}

class CirclePanel extends JPanel {
	Circle circle; 
	public CirclePanel(Circle c) {
		setLayout(new BorderLayout());	
		
		JPanel westPanel=new JPanel(new GridLayout(4,1));
		westPanel.add(new JLabel("Time"));
		westPanel.add(new JLabel("xPosition"));
		westPanel.add(new JLabel("yPosition"));
		westPanel.add(new JLabel("Radius"));
		
		JPanel centerPanel=new JPanel(new GridLayout(4,1));
		JFormattedTextField timeText= new JFormattedTextField(DateFormat.getDateTimeInstance());
		timeText.setValue(c.getTime());
		centerPanel.add(timeText);
		JFormattedTextField x1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		x1Text.setValue(c.getX());
		centerPanel.add(x1Text);
		JFormattedTextField y1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		y1Text.setValue(c.getY());
		centerPanel.add(y1Text);
		JFormattedTextField rText= new JFormattedTextField(NumberFormat.getNumberInstance());
		rText.setValue(c.getR());
		centerPanel.add(rText);
		
		
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.add(westPanel,BorderLayout.WEST);
		northPanel.add(centerPanel,BorderLayout.CENTER);
		
		add(northPanel,BorderLayout.NORTH);
	}
}