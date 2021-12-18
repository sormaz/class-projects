package edu.ohiou.ise.ise589;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


import edu.ohiou.labimp.basis.GUIApplet;
import edu.ohiou.labimp.basis.Viewable;


public class ViewableClass implements Viewable {
	
	private GUIApplet applet;
	private JPanel panel;

	public void addPanel() {
		// TODO Auto-generated method stub
	      applet.getContentPane().add(panel, BorderLayout.CENTER);

	}

	public void display() {
		applet = new GUIApplet(this);
		// TODO Auto-generated method stub
		applet.display();

	}

	public void display(String inTitle) {
		// TODO Auto-generated method stub

	}

	public void display(String inTitle, Dimension inSize) {
		// TODO Auto-generated method stub

	}

	public void display(String inTitle, int inWidth, int inHeight) {
		// TODO Auto-generated method stub

	}

	public void display(String inTitle, Dimension inSize, int appletClosing) {
		// TODO Auto-generated method stub

	}

	public GUIApplet getApplet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getAppletSize() {
		// TODO Auto-generated method stub
		return new Dimension (300,300);
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Viewable getGuiObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPanelLocation() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPanelOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void init() {
		panel = new JPanel();
		panel.add(new JLabel(toString()));
	}

	public JPanel makePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setApplet(GUIApplet applet) {
		// TODO Auto-generated method stub

	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	public void setGuiObject(Viewable guiObject) {
		// TODO Auto-generated method stub

	}

	public void setPanel(JPanel inPanel) {
		// TODO Auto-generated method stub

	}

	public String toToolTipString() {
		// TODO Auto-generated method stub
		return null;
	}

	public void toggleVisible() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ViewableClass vc = new ViewableClass();
		vc.display();

	}

}
