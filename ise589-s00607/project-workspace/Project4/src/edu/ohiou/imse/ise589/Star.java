package edu.ohiou.imse.ise589;

import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;

public class Star extends DrawObject {
  private double x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
  public Star(double x1, double y1, double x2, double y2, double x3, double y3,
              double x4, double y4, double x5, double y5) {
    super( (x1+x2+x3+x4+x5)/5, (y1+y2+y3+y4+y5)/5);
    this.x1 = x1;
    this.x2 = x2;
    this.x3 = x3;
    this.x4 = x4;
    this.x5 = x5;
    this.y1 = y1;
    this.y2 = y2;
    this.y3 = y3;
    this.y4 = y4;
    this.y5 = y5;
  }
  public void printout() {
    System.out.println(super.toString());
  }
  private class StarPanel
      extends JPanel {
    public StarPanel() {
      add(new JLabel("Star:"));
      add(new JTextField(Star.this.toString()));
    }
  }
  private class EditPanel
      extends JPanel {
    JFormattedTextField x1text, y1text, x2text, y2text, x3text, y3text, x4text,
        y4text, x5text, y5text;
    public EditPanel() {
      JPanel westPanel = new JPanel();
      setLayout(new BorderLayout());
      westPanel.setLayout(new GridLayout(10, 1));
      westPanel.add(new JLabel("x1"));
      westPanel.add(new JLabel("y1"));
      westPanel.add(new JLabel("x2"));
      westPanel.add(new JLabel("y2"));
      westPanel.add(new JLabel("x3"));
      westPanel.add(new JLabel("y3"));
      westPanel.add(new JLabel("x4"));
      westPanel.add(new JLabel("y4"));
      westPanel.add(new JLabel("x5"));
      westPanel.add(new JLabel("y5"));
      add(westPanel, BorderLayout.WEST);
      JPanel centerPanel = new JPanel();
      x1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x1text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x1 = Double.parseDouble(x1text.getText());
        }
      });
      x1text.setBounds(10, 10, 10, 10);
      y1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y1text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y1 = Double.parseDouble(y1text.getText());
        }
      });
      y1text.setBounds(10, 10, 10, 10);
      x2text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x2text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x2 = Double.parseDouble(x2text.getText());
        }
      });
      x2text.setBounds(10, 10, 10, 10);
      y2text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y2text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y2 = Double.parseDouble(y2text.getText());
        }
      });
      y2text.setBounds(10, 10, 10, 10);
      x3text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x3text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x3 = Double.parseDouble(x3text.getText());
        }
      });
      x3text.setBounds(10, 10, 10, 10);
      y3text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y3text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y3 = Double.parseDouble(y3text.getText());
        }
      });
      y3text.setBounds(10, 10, 10, 10);
      x4text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x4text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x4 = Double.parseDouble(x4text.getText());
        }
      });
      x4text.setBounds(10, 10, 10, 10);
      y4text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y4text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y4 = Double.parseDouble(y4text.getText());
        }
      });
      y4text.setBounds(10, 10, 10, 10);
      x5text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x5text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x5 = Double.parseDouble(x5text.getText());
        }
      });
      x5text.setBounds(10, 10, 10, 10);
      y5text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y5text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y5 = Double.parseDouble(y5text.getText());
        }
      });
      y5text.setBounds(10, 10, 10, 10);
      centerPanel.setLayout(new GridLayout(10, 1));
      centerPanel.add(x1text);
      centerPanel.add(y1text);
      centerPanel.add(x2text);
      centerPanel.add(y2text);
      centerPanel.add(x3text);
      centerPanel.add(y3text);
      centerPanel.add(x4text);
      centerPanel.add(y4text);
      centerPanel.add(x5text);
      centerPanel.add(y5text);
      add(centerPanel, BorderLayout.CENTER);
    }
  }
  public void init() {
    panel = new StarPanel();
  }
  public LinkedList getDrawList() {
    LinkedList shapes = new LinkedList();
    Polygon pol = new Polygon();
    pol.addPoint( (int)this.x1, (int)this.y1);
    pol.addPoint( (int)this.x2, (int)this.y2);
    pol.addPoint( (int)this.x3, (int)this.y3);
    pol.addPoint( (int)this.x4, (int)this.y4);
    pol.addPoint( (int)this.x5, (int)this.y5);
    shapes.add(pol);
    return shapes;
  }
  public JPanel makeEditPanel() {
    panel = new EditPanel();
    return panel;
  }
  public void update(){
    xPosition = (x1+x2+x3+x4+x5)/3;
    yPosition = (y1+y2+y3+y4+y5)/3;
    Timestamp t = new Timestamp(System.currentTimeMillis());
    Time = t.getTime();
    time = t.toString();
  }
  public static void main(String args[]) {
    Star star = new Star(1,1,3,3,1,3,3,1,2,4);
    star.setNeedUpdate(true);
    star.display();
  }
}
