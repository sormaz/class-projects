package edu.ohiou.imse.ise589;
/**
 * <p>Title: ise 589 project for message center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Class for demonstration of animation procedure in applets and frames
 * The class extends JApplet to enable execution in both browser and stand alone
 * and implements Runnable to perform animation in a separate thread.
 * The class accepts two integer parameters from html page
 * INCREMENT- to specify number of pixels for increment for aniamtion
 * SLEEP_TIME -  to specify number of miliseconds for delay between 
 * 			two subsequent paintings
 */
public class AnimationApplet extends JApplet implements Runnable{


  private boolean isStandalone = false;
  Thread runner = new Thread(this);
  int increment, size = 100;
  int xcounter = 0, ycounter= 50;
  boolean increasing  = true;
  int sleepTime;
  boolean running = true;
  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public AnimationApplet() {
  }
  //Initialize the applet
  public void init() {
    try {
      increment = Integer.parseInt(this.getParameter("INCREMENT", "1"));
      sleepTime = Integer.parseInt(this.getParameter("SLEEP_TIME", "100"));
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception {
    this.setSize(new Dimension(600,600));
  }
  //Start the applet
  public void start() {
    System.out.println("in start");

    running = true;
    runner = new Thread(this);
    runner.start();

  }
  //Stop the applet
  public void stop() {
    running = false;
  }
  //Destroy the applet
  public void destroy() {
  }

  /**
   * Run method invoked from Thread for controling animation
   * repaints the applet and then delays for given time
   */
  public void run () {
	  while (running) {
//		  System.out.println("in run ");
		  repaint();
		  if (increasing) {
			  xcounter++;
			  if (xcounter * increment > this.getContentPane().getWidth()- size) {
				  increasing = false;
			  }
		  }
		  else {
			  xcounter--;
			  if (xcounter * increment < 0) {
				  increasing = true;
			  }
		  }
		  try {
			  runner.sleep (sleepTime);

		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
		  }
	  }
  }



  /**
   * Method for painting the content of the applet. Clears the applet for animation
   *  and then paints oval of a current size and author's name
   * @param g
   */
  public void paint(Graphics g) {
    g.clearRect(0,0,this.getSize().width, this.getSize().height);
    g.drawOval(xcounter*increment, 50, size, size);
    g.drawString("Dusan Sormaz", xcounter, ycounter);
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }
  //Get parameter info
  public String[][] getParameterInfo() {
    String[][] pinfo =
      {
      {"INCREMENT", "int", "Specifies pixel increment for two drawings"},
      {"SLEEP_TIME", "int", "Specifies number of miliseconds between two drawings"}
      };
    return pinfo;
  }

  //Main method
  public static void main(String[] args) {
    AnimationApplet applet = new AnimationApplet();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
//    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setTitle("Animation Applet");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(600,620);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }

}
