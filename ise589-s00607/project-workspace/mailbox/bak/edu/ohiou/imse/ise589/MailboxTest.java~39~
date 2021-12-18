package edu.ohiou.imse.ise589;
/**
 * @version 1.20 07 Apr 1998
 * @author Cay Horstmann
 */

//import java.awt.*;
//import javax.swing.*;
import  corejava.*;
import java.util.StringTokenizer;
import java.net.URL;

public class MailboxTest {

  public static void main(String[] args) {
    Mailbox mbox = new Mailbox();
    mbox.display();
    while (true) {
      System.out.println(mbox.status());
      String cmd = Console.readLine
      ("list, play, display, text, voice, image, draw, delete, quit> ");
      if (cmd.equals("play"))
      {
        mbox.list();
        int index = Console.readInt("Enter message number: ");
        Message m = (Message) mbox.getMessages().get(index-1);
        System.out.println("From: " + m.getSender());
        m.play();
      }
      else if (cmd.equals("text"))
          {  String from = Console.readLine("Your name: ");
      boolean more = true;
      String msg = "";
      System.out.println
      ("Enter message, 'exit' when done");

      while (more) {  String line = Console.readLine();
      if (line.equals("exit"))
        more = false;
      else msg = msg + line + "\n";
      }
      mbox.insert(new TextMessage(from, msg));
      mbox.update();
      }
      else if (cmd.equals("voice"))
          {  String from = Console.readLine("Your name: ");
      String msg
      = Console.readLine("Audio file name: ");
      try {
        mbox.insert(new VoiceMessage(from, new URL("file", "localhost", msg)));
        mbox.update();

      }
      catch (Exception ex) {

      }

      }
      else if (cmd.equals("image"))
          {  String from = Console.readLine("Your name: ");
      String msg
      = Console.readLine("Image file name (GIF or jpg): ");
      try {
        mbox.insert(new ImageMessage(from, new URL ("file", "localhost", msg)));
        mbox.update();
      }
      catch (Exception ex) {

      }

      }
      else if (cmd.equals("draw"))
          {  String from = Console.readLine("Your name: ");
      boolean dataCorrect = false;
      int x= 0, y=0, r=1;
      do  {
        String msg
        = Console.readLine("Enter circle data <X, Y, R>:");
        StringTokenizer tokenizer = new StringTokenizer(msg,",");
        try {
          x = Integer.parseInt(tokenizer.nextToken());
          y = Integer.parseInt(tokenizer.nextToken());
          r = Integer.parseInt(tokenizer.nextToken());
          dataCorrect = true;
        }
        catch (Exception ex) {
          System.out.println("Data incorrect, try again");
        }
      } while (!dataCorrect);

      mbox.insert(new DrawMessage(from, x, y, r));
      mbox.update();
      }
      else if (cmd.equalsIgnoreCase("list")) {
        mbox.list();
      }
      else if (cmd.equalsIgnoreCase("delete")) {
        mbox.list();
        int index = Console.readInt("Enter message number: ");
        mbox.remove(index-1);
        mbox.update();
      }
      else if (cmd.equalsIgnoreCase("display")) {
        mbox.list();
        int index = Console.readInt("Enter message number: ");
        mbox.getMessage(index-1).display();
      }
      else if (cmd.equals("quit"))
        System.exit(0);
    }
  }
}




