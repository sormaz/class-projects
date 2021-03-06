package edu.ohiou.imse.ise589;

/**
 * Title:        ise 589 project for message center, spring 02/03
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Ohio University
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileFilter;
import java.net.URL;


class ImageMessage extends Message {
  public ImageMessage ( String from, URL f) {
    super (from);
    filename = f;
  }

  public void init() {
    panel = new MessagePanel(this);
    JLabel imagePanel = new JLabel(new ImageIcon (filename));
    JScrollPane contentPanel = new JScrollPane (imagePanel);
    panel.add(contentPanel, BorderLayout.CENTER);
  }

  public void play ()
  {
    Runtime r = Runtime.getRuntime();
    Process p = null;
    //    String ex = "C:/Program Files/Internet Explorer/IEXPLORE.EXE " + filename;
    String ex = "start " + filename;
    try {
      p = r.exec(ex);
    } catch (Exception e) {
      System.out.println("Error executing command " + ex);
      System.out.println(e.toString());
    }
  }

  public Container makeEditPanel() {
    final JPanel imagePanel = new JPanel();
    JPanel choicePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton browseButton = new JButton ("Browse...");
    JButton previewButton = new JButton("Preview");
    JLabel urlLabel = new JLabel ("URL");
    final JLabel imageLabel = new JLabel ("Preview of the image will be shown here");
    final JLabel errorLabel = new JLabel();
    final JTextField urlField = new JTextField();
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    final JScrollPane contentPanel = new JScrollPane (imageLabel);
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
    choicePanel.setLayout(new BorderLayout());
    choicePanel.add (urlLabel, BorderLayout.WEST);
    buttonPanel.add (browseButton);
    buttonPanel.add (previewButton);
    choicePanel.add (urlField, BorderLayout.CENTER);
    choicePanel.add (buttonPanel, BorderLayout.EAST);
    imagePanel.setLayout(new BorderLayout());
    imagePanel.add (choicePanel, BorderLayout.NORTH);
    imagePanel.add (contentPanel, BorderLayout.CENTER);
    imagePanel.add (errorLabel, BorderLayout.SOUTH);
    imagePanel.validate();
    previewButton.addActionListener(new ActionListener (){
      public void actionPerformed (ActionEvent e) {
        imageLabel.setIcon (new ImageIcon(filename));
        imageLabel.setText("");
      }
    });
    urlField.addFocusListener(new FocusAdapter () {
      public void focusLost (FocusEvent e) {
        if ( !urlField.getText().equals(filename.toString())) {
          imageLabel.setIcon (null);
          try {
            filename = new URL (urlField.getText());
            errorLabel.setText ("");
          }
          catch (Exception ex) {
            errorLabel.setText ("Invalid URL specification");
          }
        }
      }
    });
    browseButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        int returnVal = chooser.showOpenDialog(imagePanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          imageLabel.setIcon (null);

          try {
            filename = new URL ("file", "localhost", chooser.getSelectedFile().getName());
            urlField.setText(filename.toString());
            errorLabel.setText ("");
          }
          catch (Exception ex) {
            errorLabel.setText ("Invalid URL specification");

          }

        }

      }
    });
    return imagePanel;
  }

  private URL filename;
}