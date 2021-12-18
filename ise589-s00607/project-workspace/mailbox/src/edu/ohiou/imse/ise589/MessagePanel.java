package edu.ohiou.imse.ise589;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */


class MessagePanel extends JPanel {
  Message message;
  boolean isEditable  = false;
  BorderLayout borderLayout1 = new BorderLayout();
  JTextField senderField;

  public MessagePanel (Message message, boolean isEditable) {
    this.message = message;
    this.setLayout(new BorderLayout());
    this.isEditable = isEditable;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public MessagePanel (Message message) {
    this(message, false);

  }

  private void jbInit() throws Exception {
    JLabel timeLabel = new JLabel("time");
    JLabel fromLabel = new JLabel("From");
    JTextField timeField = new JTextField (message.getTimeString());
    senderField = new JTextField (message.getSender());
    if (isEditable) {
      senderField.addFocusListener(new FocusAdapter () {
        public void focusLost (FocusEvent e) {
          System.out.println("updating senderfield");
          message.setSender (senderField.getText());
        }
      }
      );
    }
    JPanel dataPanel = new JPanel();

    this.setLayout(borderLayout1);
    dataPanel.setLayout(new GridLayout(2,2));
    dataPanel.add(fromLabel);
    dataPanel.add(senderField);
    dataPanel.add(timeLabel);
    dataPanel.add(timeField);
    dataPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.blue), "Message data"));
    this.add(dataPanel, BorderLayout.NORTH);
  }
}
