
//Title:       Your Product Name
//Version:
//Copyright:   Copyright (c) 1999
//Author:      DNS
//Company:     OU
//Description: Your description
package edu.ohiou.imse.ise589;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
//import com.borland.jbcl.layout.*;

public class TalkApplet extends JApplet implements Runnable {
  boolean isStandalone = false;
  private static final int PORTNUM = 1234;
  Socket socket = null;
  InputStreamReader isr = null;
  BufferedReader inBuffer = null;
  PrintWriter out  = null;
  Thread thread;


  JTextArea localArea = new JTextArea();
  JTextArea remoteArea = new JTextArea();
  JButton connectButton = new JButton();
  JTextField serverField = new JTextField();
  JLabel serverLabel = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel statusLabel = new JLabel();

  //Construct the applet
  public TalkApplet() {
  }

  //Initialize the applet
  public void init() {
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    localArea.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        localArea_keyPressed(e);
      }
    });
    this.setSize(new Dimension(555, 528));
    this.getContentPane().setLayout(gridBagLayout1);
    connectButton.setText("Connect");
    connectButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        connectButton_actionPerformed(e);
      }
    });
    serverField.setText("localhost");
    serverLabel.setHorizontalAlignment(SwingConstants.CENTER);
    serverLabel.setText("Server to connect to:");
    statusLabel.setForeground(Color.red);
    statusLabel.setToolTipText("shows the status of te connection");
    statusLabel.setText("not connected");
    this.getContentPane().add(localArea, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(40, 28, 0, 49), 478, 154));
    this.getContentPane().add(remoteArea, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(14, 28, 0, 49), 478, 161));
    this.getContentPane().add(connectButton, new GridBagConstraints(0, 2, 1, 3, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(37, 34, 25, 0), 115, 36));
    this.getContentPane().add(serverField, new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(7, 45, 25, 60), 166, 6));
    this.getContentPane().add(serverLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 91, 0, 104), 20, 10));
    this.getContentPane().add(statusLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 97, 0));
  }

  //Start the applet
  public void start() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();  }

  }


  public void run () {
    try {
      StringBuffer str = new StringBuffer (128);
      String inStr;
      while (true) {
        if (socket != null) {
          isr = new InputStreamReader (socket.getInputStream () );
          inBuffer = new BufferedReader (isr);
          if ( (inStr = inBuffer.readLine()) != null) {
            remoteArea.append(inStr);
          }
        }
      }
    }
    catch (IOException e) {
      System.out.println ("in run, could not get in" + e);
      }

  }
  //Stop the applet
  public void stop() {
  }

  //Destroy the applet
  public void destroy() {
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  //Main method
  public static void main(String[] args) {
    TalkApplet applet = new TalkApplet();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setTitle("Applet Frame");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    WindowListener wl = new WindowAdapter() {
      public void windowClosing (WindowEvent e) {
        System.exit(0);
        }
        };
    frame.addWindowListener (wl);
    applet.init();
    applet.start();
    frame.setSize(600,400);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
  // static initializer for setting look & feel
  static {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {}
  }

  void connectButton_actionPerformed(ActionEvent e) {
    try {
      socket =  new Socket (serverField.getText(), PORTNUM);

      out = new PrintWriter (socket.getOutputStream(), true);
      System.out.println ("Passed connection, should be connected" + socket);
      statusLabel.setForeground(Color.green);
      statusLabel.setText("connected" + socket.toString());
    }
    catch  (IOException ex) {
    System.out.println ("Could not get connection" + ex);
    statusLabel.setForeground(Color.red);
    statusLabel.setText("not connected");
    }

  }

  void localArea_keyPressed(KeyEvent e) {
        try {
      System.out.println ("server socket is " + socket);
    PrintWriter os = new PrintWriter ( new
          BufferedOutputStream (socket.getOutputStream ()), false);
          String outLine = new Character(e.getKeyChar()).toString();
          os.println(outLine);
          os.flush();
          }
    catch (Exception ex)  {
      System.out.println ("error in key area" + ex);
    }
  }

}