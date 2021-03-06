package edu.ohiou.imse.ise589;


/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import edu.ohiou.labimp.basis.ViewObject;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StreamClient extends ViewObject {
  Socket socket = null;
  InputStreamReader isr = null;
  BufferedReader inBuffer = null;
  PrintWriter out  = null;
  Action action;

  public StreamClient() {
    this (new AbstractAction () {
      public void actionPerformed (ActionEvent e) {
      }
    } );
  }

  public StreamClient(Action action) {
    this.action = action;
  }

  public void send (Object o) {
    try {
      ObjectOutputStream outStream = new ObjectOutputStream (socket.getOutputStream());
      outStream.writeObject(o);
      outStream.flush();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println("object " + o);
  }

  public void init () {
    panel = new ClientPanel();
  }

  public JPanel createPanel () {
    return panel = new ClientPanel();
  }

  public static void main(String[] args) {
    StreamClient streamClient1 = new StreamClient();
    streamClient1.display();
  }

  class ClientPanel extends JPanel {
    JLabel statusLabel= new JLabel();
    JTextField portField;
    JTextField hostField;
    JButton connectButton;
    ClientPanel () {
      init();
    }

    public void init() {
      try  {
        jbInit();
      }
      catch(Exception e)  {
        e.printStackTrace();
      }
    }

    public void jbInit () {
      setLayout(new BorderLayout());
      setBorder (BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Client"));
      statusLabel.setForeground(Color.blue);
      statusLabel.setToolTipText("shows current client connected");
      statusLabel.setBackground(Color.white);
      statusLabel.setText("Not connected to server");
      connectButton = new JButton ("Connect");
      connectButton.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e) {
          try {
            socket =  new Socket (hostField.getText(),
                                  Integer.parseInt(portField.getText()));

            out = new PrintWriter (socket.getOutputStream(), true);
            System.out.println ("Passed connection, should be connected" + socket);
            statusLabel.setForeground(Message.OK_COLOR);
            action.actionPerformed(e);
            statusLabel.setText("connected" + socket.toString());
          }
          catch (NumberFormatException nfe) {
            statusLabel.setForeground(Color.red);
            statusLabel.setText("Enter correct port number");
          }
          catch  (IOException ex) {
            System.out.println ("Could not get connection" + ex);
            statusLabel.setForeground(Color.red);
            statusLabel.setText("Could not get connection");
          }

        }
      });
      JLabel hostLabel = new JLabel ("Host");
      JLabel portLabel = new JLabel ("Port");
      portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      portField = new JTextField();
      hostField = new JTextField("localhost");
      portField.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent e) {
        }
      });
      JPanel leftPanel = new JPanel();
      leftPanel.setLayout (new GridLayout (3,1));
      leftPanel.add(hostLabel);
      leftPanel.add(portLabel);
      leftPanel.add(connectButton);
      JPanel rightPanel = new JPanel();
      rightPanel.setLayout (new GridLayout (3,1));
      rightPanel.add(hostField);
      rightPanel.add(portField);
      rightPanel.add(statusLabel);

      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.CENTER);
    }


  }

}