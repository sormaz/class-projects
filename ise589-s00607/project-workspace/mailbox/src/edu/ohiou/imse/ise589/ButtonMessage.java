package edu.ohiou.imse.ise589;


/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import edu.ohiou.labimp.basis.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ButtonMessage extends Message {
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();

  public ButtonMessage() {
    super ("dusan");
  }

  public void init() {
    panel = new JPanel ();

    JButton yellowButton = new JButton ("Yellow");
    JButton blueButton = new JButton ("Blue");
    JButton redButton = new JButton ("Red");
    JButton greenButton = new JButton ("Green");
    JButton selectButton = new JButton ("Select");

    yellowButton.setBackground(Color.yellow);
    blueButton.setBackground(Color.blue);
    redButton.setBackground(Color.red);
    greenButton.setBackground(Color.green);

    yellowButton.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        panel.setBackground(Color.yellow);
      }
    });
    blueButton.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        panel.setBackground(Color.blue);
      }
    });
    redButton.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        panel.setBackground(Color.red);
      }
    });
    greenButton.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        panel.setBackground(Color.green);
      }
    });

    selectButton.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        JColorChooser colorChooser = new JColorChooser();
        Color color = JColorChooser.showDialog(panel, "Choose color", panel.getBackground());
        if (color != null)
          panel.setBackground(color);
      }
    });

//    panel.setLayout(new BorderLayout());
    panel.add(redButton);
    panel.add(greenButton);
    panel.add(yellowButton);
    panel.add(blueButton);
    panel.add(selectButton);
  }
  public void play() {
    /**@todo Implement this edu.ohiou.imse.ise589.Message abstract method*/
  }

  public Container makeEditPanel() {
    JScrollPane contentPanel = new JScrollPane (new JLabel("impleemnt edit panel"));
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
    return contentPanel;
  }

  public static void main(String[] args) {
    ButtonMessage bm = new ButtonMessage();
    bm.display();
  }

 }