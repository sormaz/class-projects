package edu.ohiou.imse.ise589;

/**
 * Title:        ise 589 project for messge center, spring 02/03
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Ohio University
 * @author Dusan N. Sormaz
 * @version 1.0
 */

import java.util.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import edu.ohiou.labimp.basis.ViewObject;

public class Mailbox extends ViewObject implements Connectable{

  private ArrayList messages = new ArrayList();
  StreamServer server = new StreamServer (this);

  public void init () {
    panel = new MailboxPanel(this);
  }

  public void update () {
    ((MailboxPanel)panel).update();
  }

  public void receive (Object o) {
    messages.add(o);
    update();
  }

  public void remove(int index)
  {
    try {
      messages.remove(index);
    }
    catch (Exception ex) {

    }

  }
  public void remove(Object message)
  {
    try {
      messages.remove(message);
    }
    catch (Exception ex) {

    }

  }

  public ArrayList getMessages () {
    return messages;
  }

  public Message getMessage (int index) {
    return (Message) messages.get(index);
  }

  public void addMessage (Message message){
    messages.add(message);
  }

  public void insert(Message m) {
    messages.add(m);
  }

  public void list () {
    int index = 1;
    for (Iterator itr = messages.iterator(); itr.hasNext();) {
      Message m = (Message) itr.next();
      System.out.println("" + index + " " + m.toString() );
      index++;
    }
  }

  public String status() {
    int nmsg = messages.size();
    if ( nmsg== 0)
      return "Mailbox empty";
    else if (nmsg == 1)
      return "1 message";
    else
      return nmsg + " messages";
  }

  public static void main (String args []) {
    Mailbox mb = new Mailbox ();
    mb.display(mb.toString(), mb.getAppletSize(), JFrame.EXIT_ON_CLOSE);
  }
}

class MailboxPanel extends JPanel {
  Mailbox mailbox;
  JList messageList;
      JButton sendButton;
  StreamClient client = new StreamClient ( new AbstractAction() {
  public void actionPerformed (ActionEvent e) {
    System.out.println("in action " + toString());
    sendButton.setEnabled (true);
  }
});



  public MailboxPanel (Mailbox mailbox) {
    this.mailbox = mailbox;
    init();
  }

  public void update () {
    messageList.setListData(mailbox.getMessages().toArray());
  }


  public void init () {
    this.setLayout(new BorderLayout());
    messageList = new JList (mailbox.getMessages().toArray());
    JScrollPane messagePanel = new JScrollPane (messageList);
    messagePanel.setBorder(BorderFactory.createTitledBorder("List of messages"));
    this.add(messagePanel, BorderLayout.CENTER);
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
         Message m  = (Message) messageList.getSelectedValue();
          m.display();
        }
      }
    };
    messageList.addMouseListener(mouseListener);

    JToolBar toolBar = new JToolBar ("Toolbar", SwingConstants.VERTICAL);
    JButton addButton = new JButton ("New");
    addButton.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        mailbox.addMessage (Message.createMessage(SwingUtilities.getWindowAncestor(MailboxPanel.this)) );
        update();
      }
    });
    JButton showButton = new JButton ("Show");
    showButton.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        Message m = (Message) messageList.getSelectedValue();
        if ( m != null)
          m.display();
      }
    });
    JButton deleteButton = new JButton ("Delete");
    deleteButton.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        mailbox.remove ( messageList.getSelectedValue() );
        update();
      }
    });
    sendButton = new JButton ("Send");
    sendButton.setEnabled(false);
    sendButton.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        client.send ( messageList.getSelectedValue() );
      }
    });

    toolBar.add(addButton);
    toolBar.add(showButton);
    toolBar.add(deleteButton);
    toolBar.add(sendButton);
    this.add(toolBar, BorderLayout.WEST);
    JSplitPane connectPanel = new JSplitPane();
    connectPanel.setContinuousLayout(true);
    connectPanel.setResizeWeight(0.5);
    connectPanel.setLeftComponent(client.makePanel());
    connectPanel.setRightComponent(mailbox.server.makePanel());
    this.add (connectPanel, BorderLayout.SOUTH);
  }
}
