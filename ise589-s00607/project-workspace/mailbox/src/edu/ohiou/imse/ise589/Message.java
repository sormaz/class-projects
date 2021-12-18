package edu.ohiou.imse.ise589;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author D N Sormaz
 * @version 1.0
 */
import edu.ohiou.labimp.basis.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import java.io.Serializable;


public abstract class Message implements Viewable, Serializable {

  // static constants
  public static final Dimension DEFAULT_SIZE = new Dimension (400,400);
  private String sender;
  private long timestamp;
  private Color color;
  protected JPanel panel;
  boolean visible;
  public static final Color OK_COLOR = new Color (0,153,0);

  public Message(String from) {
    sender = from;
    timestamp = System.currentTimeMillis();
  }

  public abstract void play();

  public abstract Container makeEditPanel();

  public String getSender() {
    return sender;
  }

  public void setSender (String sender) {
    this.sender = sender;
  }

  public String toString() {
    Date date = new Date (timestamp);
    String fullname = this.getClass().getName();
    String name = fullname.substring(fullname.lastIndexOf(".")+1, fullname.length() );
    return name + " from " + sender + " on " + date.toString();
  }
  
  public String toToolTipString () {
	  return toString();
  }

  public String getTimeString () {
    Date date = new Date (timestamp);
    return date.toString();
  }

  // Viewable interface

  public JPanel getPanel () {
    return panel;

  }
  public int getPanelLocation(){
    return SwingConstants.NORTH;
  }
  public int getPanelOrientation(){
    return SwingConstants.HORIZONTAL;
  }
//  public void setTarget (Viewable target);
//  public Viewable getTarget ();
  public void setApplet (GUIApplet applet){
  }
  public GUIApplet getApplet () {
    return new GUIApplet();
  }
  public void setPanel (JPanel inPanel){
    panel = inPanel;
  }
  public JPanel makePanel () {
    return panel = new JPanel();
  }
  public void addPanel () {}
  public Color getColor() {
    return color;
  }
  public void setColor (Color color) {
    this.color = color;
  }
  public void display (){
    display (toString(), DEFAULT_SIZE, JFrame.DISPOSE_ON_CLOSE);
  }
  public void display (String inTitle){
    display (inTitle, DEFAULT_SIZE, JFrame.DISPOSE_ON_CLOSE);
  }
  public void display (String inTitle, Dimension inSize){
    display (inTitle, inSize, JFrame.DISPOSE_ON_CLOSE);
  }

  public void display (String inTitle, int inWidth, int inHeight) {
    display (inTitle, new Dimension (inWidth, inHeight), JFrame.DISPOSE_ON_CLOSE);
  }
  public void display (String inTitle, Dimension inSize, int appletClosing) {
    JFrame frame = new JFrame();
    frame.setTitle(inTitle);
    this.init();
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setSize(inSize);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }

  public void toggleVisible () {
      visible = !visible;
  }
  public Dimension getAppletSize () {
    return DEFAULT_SIZE;
  }
  public Viewable getGuiObject () {
    return this;
  }
  public void setGuiObject (Viewable guiObject) {

  }

  public static Message createMessage (Window w) {
    MessageDialog dialog = new MessageDialog (w);
    return dialog.createMessage();
  }
}

class MessageDialog extends JDialog {
  Message message; // = new TextMessage ("", "");
  MessagePanel  panel; // = new MessagePanel(message);
  String sender = "";

  public MessageDialog (Window w) {
    super( (Frame) w);
    setTitle("Enter new message");
  }

  public void updateDialog (Message message) {
    if (panel != null)
      getContentPane().remove(panel);
    panel = new MessagePanel (message, true);
    panel.add(message.makeEditPanel(), BorderLayout.CENTER );
    getContentPane().add(panel, BorderLayout.CENTER);
    this.validate();
  }


  public Message createMessage () {
    final Container contentPane = this.getContentPane();
    this.setModal(true);
    contentPane.setLayout(new BorderLayout());
    JButton okButton = new JButton ("OK");
    okButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        MessageDialog.this.dispose();
         }
    });
    contentPane.add(okButton, BorderLayout.SOUTH);
    JRadioButton textButton = new JRadioButton("Text");
    textButton.setMnemonic(KeyEvent.VK_T);
//    textButton.setSelected(true);

    JRadioButton voiceButton = new JRadioButton("Voice");
    voiceButton.setMnemonic(KeyEvent.VK_V);

    JRadioButton imageButton = new JRadioButton("Image");
    imageButton.setMnemonic(KeyEvent.VK_I);

    JRadioButton drawButton = new JRadioButton("Draw");
    drawButton.setMnemonic(KeyEvent.VK_D);

//Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(textButton);
    group.add(voiceButton);
    group.add(imageButton);
    group.add(drawButton);

//Register a listener for the radio buttons.
    textButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        if (message != null)
          sender = message.getSender();
        message = new TextMessage(sender, "");
        updateDialog(message);
      }
    });
    voiceButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        if (message != null)
          sender = message.getSender();
        message = new VoiceMessage(sender, null);
        updateDialog(message);
     }
    });
    imageButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        if (message != null)
          sender = message.getSender();
        message = new ImageMessage(sender, null);
        MessageDialog.this.updateDialog(message);
 }
    });
    drawButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        if (message != null)
          sender = message.getSender();
        message = new DrawMessage(sender, 0,0,1);
        MessageDialog.this.updateDialog(message);
 }
    });
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new GridLayout(1,4));
    radioPanel.add(textButton);
    radioPanel.add(imageButton);
    radioPanel.add(voiceButton);
    radioPanel.add(drawButton);

    contentPane.add(radioPanel, BorderLayout.NORTH);
    this.setSize(new Dimension(500,500));
    this.show();
    return message;
  }

}