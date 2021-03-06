package edu.ohiou.imse.ise589;

/**
 * Title:        ise 589 project for messge center, spring 02/03
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Ohio University
 * @author Dusan N. Sormaz
 * @version 1.0
 */

import java.net.*;
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VoiceMessage extends Message {

  public VoiceMessage(String from, URL f) {
    super(from);
    filename = f;
  }


  public void init() {
    panel = new MessagePanel(this);
    JLabel voiceLabel = new JLabel("File " + filename + " is being played");
    voiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(voiceLabel, BorderLayout.CENTER);
    play();
  }


  public void play() {
    try {
      AudioClip clip = Applet.newAudioClip(filename);
      clip.play();
    }
    catch(Exception e)
    {
      System.out.println("Can't open " + filename);
    }
  }

  public Container makeEditPanel() {
    final JPanel imagePanel = new JPanel();
    JPanel choicePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton browseButton = new JButton ("Browse...");
    JButton previewButton = new JButton("Play");
    JLabel urlLabel = new JLabel ("URL");
    final JLabel errorLabel = new JLabel();
    final JTextField urlField = new JTextField();
    choicePanel.setLayout(new BorderLayout());
    choicePanel.add (urlLabel, BorderLayout.WEST);
    buttonPanel.add (browseButton);
    buttonPanel.add (previewButton);
    choicePanel.add (urlField, BorderLayout.CENTER);
    choicePanel.add (buttonPanel, BorderLayout.EAST);
    imagePanel.setLayout(new BorderLayout());
    imagePanel.add (choicePanel, BorderLayout.NORTH);
    imagePanel.add (errorLabel, BorderLayout.SOUTH);
    imagePanel.validate();
    previewButton.addActionListener(new ActionListener (){
      public void actionPerformed (ActionEvent e) {
        play();
      }
    });
    urlField.addFocusListener(new FocusAdapter () {
      public void focusLost (FocusEvent e) {
        try {
          filename = new URL (urlField.getText());
          errorLabel.setText("");
        }
        catch (Exception ex) {
          errorLabel.setText("Invalid URL");
        }

      }
    });
    browseButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        int returnVal = chooser.showOpenDialog(imagePanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            filename =  new URL("file", "localhost", chooser.getSelectedFile().getName());
            urlField.setText(filename.toString());
            errorLabel.setText("");
          }
          catch (Exception ex) {
          }
          errorLabel.setText("Invalid URL");
        }

      }
    });
    return imagePanel;
  }

  private URL filename;
}