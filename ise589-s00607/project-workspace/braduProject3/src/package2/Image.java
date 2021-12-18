package package2;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import edu.ohiou.labimp.basis.Draw2DPanel;

import java.util.*;
import java.text.*;

public class Image extends DrawObject{
	private double x1,y1,s;
	String filename;
	
	public Image(double x1, double y1,String pathname,double scale){
		super (x1,y1);
		this.x1=x1;
		this.y1=y1;
		this.s=scale;
		this.filename=pathname;
	}
	
	public double getScale(){
		return this.s;
	}
	
	public String getPath(){
		return this.filename;
	}
	
	public void printout(){
		System.out.println(super.toString());
	}
	
	public void init() {
		 panel = new ImagePanel(this);
	}
	
	public LinkedList getDrawList() {
		LinkedList shapes = new LinkedList();
		return shapes;
	}
	
	public void generateImageList() {
		java.awt.Image image=Toolkit.getDefaultToolkit().createImage(filename);
		if(canvas instanceof Draw2DPanel){
//			Draw2DPanel panel2D=(Draw2DPanel) panel;
			Draw2DPanel panel2D = (Draw2DPanel) canvas ;
			AffineTransform transform=AffineTransform.getScaleInstance(1, -1);
			transform.translate(-4, -4);
			panel2D.addImage(image, transform);
		}
	}
}


class ImagePanel extends JPanel {
	Image image; 
	public ImagePanel(Image i) {
		setLayout(new BorderLayout());		

		JPanel westPanel=new JPanel(new GridLayout(5,1));
		westPanel.add(new JLabel("Time"));
		westPanel.add(new JLabel("xPosition"));
		westPanel.add(new JLabel("yPosition"));
		westPanel.add(new JLabel("Path"));
		westPanel.add(new JLabel("Scale"));
		
		
		JPanel centerPanel=new JPanel(new GridLayout(5,1));
		JFormattedTextField timeText= new JFormattedTextField(DateFormat.getDateTimeInstance());
		timeText.setValue(i.getTime());
		centerPanel.add(timeText);
		JFormattedTextField x1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		x1Text.setValue(i.getX());
		centerPanel.add(x1Text);
		JFormattedTextField y1Text= new JFormattedTextField(NumberFormat.getNumberInstance());
		y1Text.setValue(i.getY());
		centerPanel.add(y1Text);
		JFormattedTextField pathText=new JFormattedTextField();
		pathText.setValue(i.getPath());
		centerPanel.add(pathText);
		JFormattedTextField sText= new JFormattedTextField(NumberFormat.getNumberInstance());
		sText.setValue(i.getScale());
		centerPanel.add(sText);
				
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.add(westPanel,BorderLayout.WEST);
		northPanel.add(centerPanel,BorderLayout.CENTER);
		
		add(northPanel,BorderLayout.NORTH);
	}
}
