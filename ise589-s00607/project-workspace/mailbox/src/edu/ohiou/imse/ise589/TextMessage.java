package edu.ohiou.imse.ise589;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextMessage extends Message {

  public TextMessage(String sender, String content) {
    super (sender);
    this.content=content;
  }

  public void init() {
    panel = new MessagePanel(this);
    JTextArea contentArea = new JTextArea();
    contentArea.setText(content);
    JScrollPane contentPanel = new JScrollPane (contentArea);
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
    panel.add(contentPanel, BorderLayout.CENTER);
  }

  public void play() { System.out.println(content); }

  public Container makeEditPanel() {
    final JTextArea contentArea = new JTextArea();
    contentArea.setText(content);
    contentArea.addFocusListener(new FocusAdapter () {
      public void focusLost ( FocusEvent e) {
        content=contentArea.getText();
      }
    });
    JScrollPane contentPanel = new JScrollPane (contentArea);
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
    return contentPanel;
  }

  public String getContent () {
    return content;
  }

  public void setContent (String inContent) {
    content = inContent;
  }

  String content;
}

