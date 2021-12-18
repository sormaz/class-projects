package package2;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Star extends DrawObject{
	private double x1, y1, r;
	int n,d;
	int i;
	public Star(double x1, double y1, double r, int v, int d){
		super (x1,y1);
		this.x1 = x1;
		this.y1 = y1;
		this.r=r; //radius of enclosing circle;
		this.n=v; //number of vertices;
		this.d=d; //density of the star polygon;
		//n and d should be relatively prime and usually d<n/2.
	}
	
	public void printout(){
		System.out.println(super.toString());
	}
	
	public double getR(){
		return this.r;
	}
	
	public double getN(){
		return this.n;
	}
	
	public double getD(){
		return this.d;
	}
	
	public void init() {
		 panel = new StarPanel(this);
	}
	
	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		for(i=1;i<=n;i++){
			Line2D.Double line=new Line2D.Double(x1+r*Math.cos((i*2*Math.PI)/n),y1+r*Math.sin((i*2*Math.PI)/n),x1+r*Math.cos(((i+d)*2*Math.PI)/n),y1+r*Math.sin(((i+d)*2*Math.PI)/n));
			shapes.add(line);
		}
		return shapes;
	}
}


class StarPanel extends JPanel {
	Star star; 
	public StarPanel(Star st) {
		setLayout(new BorderLayout());		
		
		JPanel westPanel=new JPanel(new GridLayout(6,1));
		westPanel.add(new JLabel("Time"));
		westPanel.add(new JLabel("xPosition"));
		westPanel.add(new JLabel("yPosition"));
		westPanel.add(new JLabel("Radius of enclosing circle"));
		westPanel.add(new JLabel("Number of vertices"));
		westPanel.add(new JLabel("Density of the star polygon"));
		
		
		JPanel centerPanel=new JPanel(new GridLayout(6,1));
		JFormattedTextField timeText= new JFormattedTextField(DateFormat.getDateTimeInstance());
		timeText.setValue(st.getTime());
		centerPanel.add(timeText);
		JFormattedTextField x1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		x1Text.setValue(st.getX());
		centerPanel.add(x1Text);
		JFormattedTextField y1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		y1Text.setValue(st.getY());
		centerPanel.add(y1Text);
		JFormattedTextField rText= new JFormattedTextField(NumberFormat.getNumberInstance());
		rText.setValue(st.getR());
		centerPanel.add(rText);
		JFormattedTextField nText= new JFormattedTextField(NumberFormat.getNumberInstance());
		nText.setValue(st.getN());
		centerPanel.add(nText);
		JFormattedTextField dText=new JFormattedTextField(NumberFormat.getNumberInstance());
		dText.setValue(st.getD());
		centerPanel.add(dText);
		
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.add(westPanel,BorderLayout.WEST);
		northPanel.add(centerPanel,BorderLayout.CENTER);
		
		add(northPanel,BorderLayout.NORTH);
	}
}
