package edu.ohiou.imse.ise589;

import edu.ohiou.labimp.basis.*;

/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawMessage extends Message {
  int x;
  int y;
  int radius;

  public DrawMessage (String from) {
    this(from, 0,0,1);
  }

  public DrawMessage(String from, int x, int y, int r) {
    super(from);
    this.x = x;
    this.y = y;
    this.radius = r;
  }


  public void init() {
    panel = new MessagePanel(this);
    JPanel drawPanel = new JPanel () {
      public void paintComponent (Graphics g) {
        g.drawOval (x-radius, y-radius, 2*radius, 2*radius);
      }
    };
    drawPanel.setBackground(Color.white);
    drawPanel.setForeground(Color.red);
    panel.add(drawPanel, BorderLayout.CENTER);
  }

  public void play() {
    System.out.println(toString());
  }

  public String toString() {
    return super.toString() + ", Circle: X=" + x + ", Y=" + y + ", R="+ radius;
  }

  public Container makeEditPanel() {
//  JScrollPane contentPanel = new JScrollPane (new JLabel("impleemnt edit panel"));
    JPanel contentPanel = new JPanel();
    JLabel xLabel = new JLabel ("   x");
    final JTextField xField = new JTextField();
    JLabel yLabel = new JLabel ("   y");
    final JTextField yField = new JTextField();
    JLabel rLabel = new JLabel ("   r");
    final JTextField rField = new JTextField();
    final JLabel errorLabel = new JLabel();
    JPanel labelPanel = new JPanel();
    JPanel fieldPanel = new JPanel();
    JPanel topPanel = new JPanel();

    labelPanel.setLayout (new GridLayout (3,1));
    labelPanel.add (xLabel);
    labelPanel.add (yLabel);
    labelPanel.add (rLabel);

    xField.addFocusListener(new FocusAdapter () {
      public void focusLost (FocusEvent e) {
        try {
          x = Integer.parseInt (xField.getText());
          errorLabel.setText ("");
        }
        catch (NumberFormatException ex) {
          errorLabel.setText ("Value for x is not an integer");
        }
      }
    });
    yField.addFocusListener(new FocusAdapter () {
      public void focusLost (FocusEvent e) {
        try {
          y = Integer.parseInt (yField.getText());
          errorLabel.setText ("");
        }
        catch (Exception ex) {
          errorLabel.setText ("Value for y is not an integer");
        }
      }
    });
    rField.addFocusListener(new FocusAdapter () {
      public void focusLost (FocusEvent e) {
        try {
          radius = Integer.parseInt (rField.getText());
          errorLabel.setText ("");
        }
        catch (Exception ex) {
          errorLabel.setText ("Value for radius is not an integer");
        }
      }
    });

    fieldPanel.setLayout (new GridLayout (3,1));
    fieldPanel.add (xField);
    fieldPanel.add (yField);
    fieldPanel.add (rField);
    topPanel.setLayout (new BorderLayout());
    topPanel.add (labelPanel, BorderLayout.WEST);
    topPanel.add (fieldPanel, BorderLayout.CENTER);
    contentPanel.setLayout (new BorderLayout());
    contentPanel.add (topPanel, BorderLayout.NORTH);
    contentPanel.add (errorLabel, BorderLayout.SOUTH);
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
    return contentPanel;
  }

}