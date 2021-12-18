package edu.ohiou.imse.ise589;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.*;
import java.util.LinkedList;
import java.io.Serializable;
import java.lang.reflect.*;
import edu.ohiou.labimp.basis.*;
import edu.ohiou.labimp.basis.GraphicsConfiguration;

public abstract class DrawObject implements Viewable, Drawable2D, Serializable {
  protected double xPosition, yPosition;
  protected long Time;
  protected String time;
  boolean needUpdate=false;

  transient JPanel panel;
  transient Draw2DApplet applet;
  transient Draw2DPanel canvas;
  transient Color color;

  public DrawObject(double x, double y) {
    applet = new Draw2DApplet(this);
    canvas = (Draw2DPanel)applet.getCanvas();
    Timestamp t = new Timestamp(System.currentTimeMillis());
    xPosition = x;
    yPosition = y;
    Time = t.getTime();
    time = t.toString();
  }
  public long getTime() {
    return Time;
  }
  public abstract void printout();

  public String toString() {
    return "Object type = " + this.getClass().getName() + ", xPosition = " + (int) this.xPosition +
	   ",  yPosition = " + (int) this.yPosition + ",  time = " + this.time;
  }
  public void setCanvas (Draw2DPanel param1) {
    canvas = param1;
  }
  public JPanel getCanvas () {
    return canvas;
  }
  public void paintComponent (Graphics param1) {
   throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void paintComponent (Graphics2D param1) {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public abstract LinkedList getDrawList();

  public LinkedList getStringList() {
    LinkedList strings = new LinkedList();
    return strings;
  }
  
  public void setPosition (Point2D point) {  
  }
  
  public void generateImageList() {
		// TODO Auto-generated method stub
		
	}
	public LinkedList getFillList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return new Point2D.Double();
	}
	public Collection giveSelectables() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}
	public void makeDrawSets() {
	    color = Color.black;;
 
	    if (canvas instanceof Draw2DPanel) {
	      Draw2DPanel drawPanel = (Draw2DPanel) canvas;
	      drawPanel.addDrawShapes(color, getDrawList());
	      drawPanel.addFillShapes(color, getFillList());
	    }
		
	}
	public String toToolTipString() {
		// TODO Auto-generated method stub
		return toString();
	}

  public void setNeedUpdate(boolean param1) {
    ((Drawable2D) canvas).setNeedUpdate(param1);
  }
  public void setPanel (JPanel param1) {
    this.panel = param1;
  }
  public JPanel getPanel() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public int getPanelLocation() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public int getPanelOrientation() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public JPanel makePanel() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void addPanel() {
    System.out.println("Panel" + panel + ", this" + this);
    if (applet instanceof Draw2DApplet) {
      Container appletPanel = applet.getContentPane();
      if (appletPanel instanceof JSplitPane) {
	JSplitPane splitPanel = (JSplitPane) appletPanel;
	splitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
	splitPanel.setLeftComponent(((Draw2DApplet)applet).getCanvas());
	splitPanel.setRightComponent(panel);
      }
    }
    else
      applet.getContentPane().add(panel,BorderLayout.CENTER);
  }
  public Color getColor() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void setColor(Color param1) {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public abstract void init();

  public void display() {
    if (applet == null) {
      applet = new Draw2DApplet(this);
    }
    applet.display();
  }
  public void display(String param1) {
    applet.display("Test");
  }
  public void display(String param1, Dimension param2) {
  }
  public void display(String param1, int param2, int param3) {
  }
  
  
  public void display(String param1, Dimension param2, int param3) {
    JFrame frame = new JFrame();
    frame.setTitle("Title");
    frame.setSize(500,500);
    frame.setDefaultCloseOperation(3);
    frame.setVisible (true);
  }
  public void setApplet (GUIApplet param1) {
    this.applet = (Draw2DApplet) param1;
  }
  public GUIApplet getApplet() {
    return applet;
  }
  public Dimension getAppletSize() {
    try {
      if (canvas == null) {
	return panel.getSize();
      }
      else {
	return new Dimension(500, 400);
      }
    }
      catch (NullPointerException POINTER) {
	return GUIApplet.defaultGUIAppletSize;
      }
    }
  public Viewable getGuiObject() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void setGuiObject(Viewable param1) {
  }
  public void toggleVisible() {
  }
  public void repaint() {
    try {
      ((JPanel)this.getCanvas()).repaint();
    }
    catch (NullPointerException e) {}
  }
  public abstract JPanel makeEditPanel();
  public abstract void update();
  
  public GraphicsConfiguration getGraphicsConfig() {
	  
	  return new GraphicsConfiguration();
	  
  }
}