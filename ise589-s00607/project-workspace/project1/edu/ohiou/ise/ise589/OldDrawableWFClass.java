package edu.ohiou.ise.ise589;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ohiou.labimp.basis.Draw2DPanel;
import edu.ohiou.labimp.basis.GUIApplet;
import edu.ohiou.labimp.basis.Viewable;
import edu.ohiou.labimp.draw.DrawWFPanel;
import edu.ohiou.labimp.draw.DrawableWF;
import edu.ohiou.labimp.gtk3d.WorldCS;

public class OldDrawableWFClass implements DrawableWF {
	
	DrawWFPanel canvas;

	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList getPointSet() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	public LinkedList getShapeList(DrawWFPanel canvas) {
		LinkedList shapes = new LinkedList();
		WorldCS cs = new WorldCS();
		shapes.addAll( cs.getShapeList(canvas));
		return shapes;
	}

	public void makeShapeSets(DrawWFPanel canvas) {

	      canvas.addDrawShapes(Color.black, getShapeList(canvas));
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics2D g, DrawWFPanel canvas) {
		// TODO Auto-generated method stub

	}

	public void repaint() {
		// TODO Auto-generated method stub

	}

	public void setCanvas(DrawWFPanel canvas) {
		this.canvas = canvas;
	}

	public void setNeedUpdate(boolean needUpdate) {
		// TODO Auto-generated method stub

	}

	public void addPanel() {
		// TODO Auto-generated method stub

	}

	public void display() {
		// TODO Auto-generated method stub

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
		return null;
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
		// TODO Auto-generated method stub

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
		OldDrawableWFClass dc = new OldDrawableWFClass();
		JFrame frame = new JFrame();
		DrawWFPanel panel = new DrawWFPanel();
		panel.setTarget(dc);
		frame.getContentPane().add(panel);
		frame.setSize(600,800);
		frame.setVisible(true);
	}

}
