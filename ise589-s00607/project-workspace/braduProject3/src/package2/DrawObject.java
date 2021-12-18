package package2;

import java.awt.Color;
import java.awt.geom.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import javax.swing.*;
import edu.ohiou.labimp.basis.Draw2DApplet;
import edu.ohiou.labimp.basis.Draw2DPanel;
import edu.ohiou.labimp.basis.DrawString;
import edu.ohiou.labimp.basis.Drawable2D;
import edu.ohiou.labimp.basis.GUIApplet;
import edu.ohiou.labimp.basis.Viewable;


public abstract class DrawObject implements Drawable2D, Viewable {
	
	private Dimension inSize=new Dimension(400,400);
	private String inTitle=new String("Default Title");
	private int appletClosing=JFrame.EXIT_ON_CLOSE;
	private double xPosition;
	private double yPosition;
	private long timestamp;	
	private Date d;
	private String filename;
	
	Draw2DPanel canvas;
	JPanel panel;
	Draw2DApplet applet;
	
	public DrawObject(double x, double y) {
		timestamp=System.currentTimeMillis();
		xPosition=x;
		yPosition=y;
		d=new Date(timestamp);
		}
	public double getX(){
		return xPosition;
	}
		
	public double getY(){
		return yPosition;
	}
	
	public long getTime(){
		return timestamp;
		}
	
	public String toString(){
		return "Type: "+this.getClass().getName()+" xPosition: "+this.xPosition+" yPosition "+this.yPosition+" time: "+this.d;
		}
	
	public void printout(){
		System.out.println(toString());
	}
	
	public void draw(){
		applet=new Draw2DApplet(this);
		applet.display();
	}

	@Override
	public void generateImageList() {
		java.awt.Image image=Toolkit.getDefaultToolkit().createImage(filename);
		if(canvas instanceof Draw2DPanel){
			Draw2DPanel panel2D=(Draw2DPanel) panel;
			AffineTransform transform=AffineTransform.getScaleInstance(1, -1);
			transform.translate(-4, -4);
			panel2D.addImage(image, transform);
			}
	}
	
//	public void  generateImageList () {
//	java.awt.Image  image = Toolkit.getDefaultToolkit().
//	createImage(Image. class .getResource( filename));
//	if (canvas instanceof Draw2DPanel) {
//		Draw2DPanel panel2D = (Draw2DPanel) canvas ;
//		AffineTransform transform =AffineTransform.getScaleInstance(1, -1);
//		transform.translate (-4,-4);
//		panel2D.addImage(image, transform);
//		
//		panel2D.addImage( new ImageIcon (
//				Image. class.getResource( filename )).getImage().
//				getScaledInstance(-1,10,0), transform);
//		}
//	}

	@Override
	public JPanel getCanvas() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public LinkedList<Shape> getDrawList() {
		LinkedList shapes=new LinkedList();
		return shapes;
	}

	@Override
	public LinkedList<Shape> getFillList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public LinkedList<DrawString> getStringList() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	@Override
	public Collection giveSelectables() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}

	@Override
	public void makeDrawSets() {
		Color color=Color.BLUE;
		Draw2DPanel drawPanel=(Draw2DPanel)canvas;
		drawPanel.addDrawShapes(color, getDrawList());
		drawPanel.addFillShapes(color, getDrawList());
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics2D g) { 
	}

	@Override
	public void setCanvas(Draw2DPanel canvas) {
		this.canvas=canvas;
		}

	@Override
	public void setNeedUpdate(boolean needUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(Point2D point) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPanel() {
		 if (applet instanceof Draw2DApplet) { //from ViewObject
		      Container appletPanel = applet.getContentPane();
		      if (appletPanel instanceof JSplitPane) {
		        JSplitPane splitPanel = (JSplitPane) appletPanel;
		        splitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		        splitPanel.setLeftComponent( ( (Draw2DApplet) applet).getCanvas());
		        splitPanel.setRightComponent(panel);}
		      }
		 else {
		      applet.getContentPane().add(panel, BorderLayout.CENTER);
		    }
	}
	
@Override
	public void display() {
		this.display(inTitle, inSize, appletClosing);
		}

	@Override
	public void display(String inTitle) {
	}

	@Override
	public void display(String inTitle, Dimension inSize) {
	}

	@Override
	public void display(String inTitle, int inWidth, int inHeight) {
	}

	@Override
	public void display(String inTitle, Dimension inSize, int appletClosing) {
		JFrame frame=new JFrame();
		frame.setTitle("Default Title");
		frame.setSize(250, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		}

	@Override
	public GUIApplet getApplet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getAppletSize() {
		// TODO Auto-generated method stub
		return new Dimension(800,400);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Viewable getGuiObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPanelLocation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPanelOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel makePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApplet(GUIApplet applet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGuiObject(Viewable guiObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPanel(JPanel inPanel) {
		this.panel=inPanel;
	}

	@Override
	public String toToolTipString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toggleVisible() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @param args
	 */
	
}
