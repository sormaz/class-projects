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

public class StreamServer extends ViewObject implements Runnable {
  private int portNumber;
  private ServerSocket serverSocket;
  Socket clientSocket = null;
  Connectable user;

  Thread runner;

  public StreamServer () {
  }

  public StreamServer (Connectable  user) {
    this.user = user;
  }
  public StreamServer(int port) {
    portNumber = port;
    try {
      serverSocket = new ServerSocket (portNumber);
      System.out.println ("TalkServer up and running ..." + serverSocket);
    }
    catch (IOException e) {
      System.err.println ("Could not create socket");
      System.exit(1);
    }
  }

  public boolean isRunning() {
    return serverSocket != null;
  }

  public void init() {

    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
  }

  public JPanel createPanel () {
    return panel = new ServerPanel();
  }

  public void updateGUI() {
    ServerPanel sPanel = (ServerPanel) panel;
    sPanel.clientStatusLabel.setForeground(Message.OK_COLOR);
    sPanel.clientStatusLabel.setText ("client" + clientSocket.toString());

  }

//
//Component initialization
  private void jbInit() throws Exception {
    panel = new ServerPanel();
  }
//
//Start the applet
  public void start() {
    if (runner == null) {
      runner = new Thread(this);
    runner.start();  }
  }

//Stop the applet
  public void stop() {
    runner = null;
  }
//
// method to run thread
  public void run () {
    int i=0, j=0, k=0;
    System.out.println ("server socket is" + serverSocket);
    System.out.println ("in run i " + i++ % 100);

    while (true ) {
      System.out.println ("first while j " + j++ % 200);

      // wait for a client
      if (serverSocket == null)
        return;
      try {
        if (runner == Thread.currentThread()) {
          clientSocket = serverSocket.accept();
          System.out.println ("Client socket accepted");
          if (panel != null)
            updateGUI();
        }
      }
      catch (IOException e ) {
        System.err.println ("Error exception: " + e);
        System.exit (1);
      }

      try {
        InputStreamReader isr;
        BufferedReader inBuffer;
        StringBuffer str = new StringBuffer (128);
        String inStr;
        while (true) {
          System.out.println ("sec while k " + k++ % 500);
          if (clientSocket != null) {
            ObjectInputStream inputStream = new ObjectInputStream (clientSocket.getInputStream () );
//            System.out.println("in server receiving" + inputStream.readObject());
            user.receive(inputStream.readObject());
//            isr = new InputStreamReader (clientSocket.getInputStream () ); (
//            inBuffer = new BufferedReader (isr);
//            if ( (inStr = inBuffer.readLine()) != null) {
//              System.out.println("from clilent" + inStr);
//            }
          }
        }
      }
      catch (IOException e) {
        System.out.println ("in run, could not get in" + e);
      }
      catch (ClassNotFoundException cex) {
        cex.printStackTrace();
      }

    } // while
  }

  public static void main(String[] args) {
    StreamServer streamServer1 = new StreamServer();
    streamServer1.display();
  }

  class ServerPanel extends JPanel {
    JLabel clientStatusLabel= new JLabel();
    JTextField portField;
    JButton startButton;
    ServerPanel () {
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
      setBorder (BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Server"));
      clientStatusLabel.setForeground(Color.blue);
      clientStatusLabel.setToolTipText("shows current client connected");
      clientStatusLabel.setBackground(Color.white);
      clientStatusLabel.setText("Server not running");
      startButton = new JButton ("Start");
      startButton.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e) {
          if (!isRunning() ) {
            if (portNumber != 0)
              try {
              serverSocket = new ServerSocket(portNumber);
              System.out.println ("TalkServer up and running ..." + serverSocket);
              startButton.setText("Stop");
              clientStatusLabel.setForeground(Message.OK_COLOR);
              clientStatusLabel.setText("Server up and running");

              start();
            }
            catch (IOException ex) {
              System.err.println ("Could not create socket");
              ex.printStackTrace();
              clientStatusLabel.setForeground(Color.red);
              clientStatusLabel.setText("Server could not be started");
//              System.exit(1);
            }
          }
          else {
            try {
              System.out.println("before close");
              stop();
              serverSocket.close();
              System.out.println("after close");

            }
            catch (Exception ex) {
              ex.printStackTrace();
            }

            serverSocket = null;

//            portNumber =0;
            startButton.setText("Start");
//            portField.setText("");
            clientStatusLabel.setForeground(Color.blue);
            clientStatusLabel.setText("Server stopped");

          }
        }
      });
      JLabel portLabel = new JLabel ("Port");
      portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      portField = new JTextField();
      portField.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent e) {
          try {
            portNumber = Integer.parseInt(portField.getText());
          }
          catch (NumberFormatException ex) {
            clientStatusLabel.setForeground(Color.red);
            clientStatusLabel.setText("Enter integer for port number");
          }
        }
      });
      JPanel leftPanel = new JPanel();
      leftPanel.setLayout (new GridLayout (2,1));
      leftPanel.add(portLabel);
      leftPanel.add(startButton);
      JPanel rightPanel = new JPanel();
      rightPanel.setLayout (new GridLayout (2,1));
      rightPanel.add(portField);
      rightPanel.add(clientStatusLabel);

      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.CENTER);
    }


  }
}