
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

public class TalkServer extends JApplet implements Runnable {
  boolean isStandalone = false;
  Thread thread;
  Socket clientSocket = null;
   InputStreamReader isr = null;
  BufferedReader inBuffer = null;
  PrintWriter out  = null;

    private static final int PORTNUM = 1234;
  private ServerSocket serverSocket;

  JTextArea serverArea = new JTextArea();
  JTextArea clientArea = new JTextArea();
  JLabel serverLabel = new JLabel();
  JLabel clientLabel = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel clientStatusLabel = new JLabel();

  //Construct the applet
  public TalkServer() {
    //super ("Talk Server");
    try {
      serverSocket = new ServerSocket(PORTNUM);
      System.out.println ("TalkServer up and running ..." + serverSocket);
    }
    catch (IOException e) {
      System.err.println ("Could not create socket");
      System.exit(1);
    }
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
    this.getContentPane().setLayout(gridBagLayout1);
    serverArea.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        serverArea_keyTyped(e);
      }
    });
    serverLabel.setHorizontalAlignment(SwingConstants.CENTER);
    serverLabel.setText("Enter your text into upper area");
    clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
    clientLabel.setText("Text from other person will show below");
    clientStatusLabel.setForeground(Color.red);
    clientStatusLabel.setToolTipText("shows current client connected");
    clientStatusLabel.setText("no client");
    this.getContentPane().add(clientArea, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 23, 0, 31), 431, 93));
    this.getContentPane().add(serverArea, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 23, 0, 31), 432, 103));
    this.getContentPane().add(serverLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(13, 23, 0, 31), 271, 6));
    this.getContentPane().add(clientLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 23, 0, 31), 216, 9));
    this.getContentPane().add(clientStatusLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  //Start the applet
  public void start() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();  }
  }

  //Stop the applet
  public void stop() {
  }

  // method to run thread
  public void run () {
    System.out.println ("server socket is" + serverSocket);
    while (true) {
      // wait for a client
      if (serverSocket == null)
        return;
      try {
        clientSocket = serverSocket.accept();
        System.out.println ("Client socket accepted");
        clientStatusLabel.setForeground(Color.green);
        clientStatusLabel.setText ("client" + clientSocket.toString());
      }
      catch (IOException e ) {
        System.err.println ("Error exception: " + e);
        System.exit (1);
      }

      try {
        StringBuffer str = new StringBuffer (128);
        String inStr;
        while (true) {
          if (clientSocket != null) {
            isr = new InputStreamReader (clientSocket.getInputStream () );
            inBuffer = new BufferedReader (isr);
            if ( (inStr = inBuffer.readLine()) != null) {
              clientArea.append(inStr);
            }
          }
        }
      }
    catch (IOException e) {
      System.out.println ("in run, could not get in" + e);
      }

    } // while
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
    TalkServer applet = new TalkServer();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setTitle("TalkServer Frame");
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

  void serverArea_keyTyped(KeyEvent e) {

    // temporary copy it into client area
    //clientArea.append(new Character(e.getKeyChar()).toString());
    // send key to client
    try {
      System.out.println ("client socket is " + clientSocket);
      PrintWriter os = new PrintWriter ( new
      BufferedOutputStream (clientSocket.getOutputStream ()), false);
      String outLine = new Character(e.getKeyChar()).toString();
      os.println(outLine);
      os.flush();
    }
    catch (Exception ex)  {
      System.out.println ("error in key area" + ex);
      }
  }

}
