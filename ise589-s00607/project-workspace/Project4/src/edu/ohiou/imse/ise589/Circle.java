package edu.ohiou.imse.ise589;

import java.lang.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;

public class Circle extends DrawObject {
  private double x1,y1,rad;
  double x,y;
  int i;
  public Circle (double x1, double y1, double rad) {
    super (x1,y1);
    this.x1 = x1;
    this.y1 = y1;
    this.rad = rad;
  }
  
  public void setRadius(double r) {
	  rad = r;
	  
  }
  
  public double getRadius() {
	  return rad;
  }
  public void printout() {
    System.out.println(super.toString());
  }
  private class CirclePanel extends JPanel {
    public CirclePanel() {
      add(new JLabel("Circle:"));
      add(new JTextField (Circle.this.toString()));
    }
  }
  private class EditPanel extends JPanel {
    JFormattedTextField x1text, y1text, radtext;
    public EditPanel() {
    	setLayout(new BorderLayout());
    	JPanel topPanel = new JPanel();
    	topPanel.setLayout(new BorderLayout());
      JPanel westPanel = new JPanel();
      setLayout(new BorderLayout());
      westPanel.setLayout (new GridLayout (3,1));
      westPanel.add(new JLabel("x1"));
      westPanel.add(new JLabel("y1"));
      westPanel.add(new JLabel("Radius:"));
      topPanel.add(westPanel, BorderLayout.WEST);
      JPanel centerPanel = new JPanel();
      x1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      
      x1text.setText("" + xPosition);
      x1text.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          x1 = Double.parseDouble(x1text.getText());
        }
      });
      x1text.setBounds(10,10,10,10);
      y1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y1text.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          y1 = Double.parseDouble(y1text.getText());
        }
      });
      y1text.setBounds(10,10,10,10);
      radtext = new JFormattedTextField(NumberFormat.getNumberInstance());
      radtext.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          rad = Double.parseDouble(radtext.getText());
        }
      });
      radtext.setBounds(10,10,10,10);
      centerPanel.setLayout (new GridLayout(3,1));
      centerPanel.add(x1text);
      centerPanel.add(y1text);
      centerPanel.add(radtext);
      topPanel.add(centerPanel,BorderLayout.CENTER);
      add(topPanel, BorderLayout.NORTH);
    }
  }
  public void init() {
//    panel = new CirclePanel();
    panel = new EditPanel();
  }
  public LinkedList getDrawList() {
    LinkedList shapes = new LinkedList();
    Polygon pol = new Polygon();
    for (i=1; i<=360; i++) {
      x=100*(x1+rad*Math.cos(i*2*Math.PI/360));
      y=100*(y1+rad*Math.sin(i*2*Math.PI/360));
      pol.addPoint((int)this.x,(int)this.y);
    }
    shapes.add(pol);
    return shapes;
  }
  public JPanel makeEditPanel() {
    panel = new EditPanel();
    return panel;
  }
  public void update(){
    xPosition = x1;
    yPosition = y1;
    Timestamp t = new Timestamp(System.currentTimeMillis());
    Time = t.getTime();
    time = t.toString();
  }
  public static void main (String args []) {
    Circle circle = new Circle(10,20,30);
    circle.setNeedUpdate(true);
    circle.display();
  }
}
