package edu.ohiou.ise.ise589;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
//import com.sun.java.swing.*; //Use this swing import
			       //line for release before Swing 1.1 and Java 2
import javax.swing.*;  

class MyButton extends JButton implements Printable {

    public MyButton(String label) {
	super(label);
    }

    public int print(Graphics g, PageFormat pf, int pi) 
					throws PrinterException {

	// There is only one page so this check is ok
	if (pi >= 1) {
           return Printable.NO_SUCH_PAGE;
	}

        Graphics2D g2 = (Graphics2D) g;
      	g2.translate(pf.getImageableX(), pf.getImageableY());
	Font f = new Font("Courier", Font.PLAIN, 12);
	g2.setFont (f);
	paint(g2);
        return Printable.PAGE_EXISTS;
    }
}

public class printbutton extends JPanel implements ActionListener {

    public printbutton() {
	setBackground(Color.white);
        MyButton b = new MyButton("MyButton");
        b.addActionListener(this);
        add(b);
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((MyButton) e.getSource());


//Page dialog
        PageFormat pf = printJob.pageDialog(printJob.defaultPage());



//Print dialog
	if(printJob.printDialog()){
          try { printJob.print(); } catch (Exception PrintException) { }
    	}


//No dialogs
          try { printJob.print(); } catch (Exception PrintException) { }

    }

    public static void main(String s[]) {
	WindowListener l = new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {System.exit(0);}
	};
	Frame f = new Frame("printbutton");
	f.addWindowListener(l);
	f.add("Center", new printbutton());
	f.pack();
	f.setSize(new Dimension(400,300));
	f.show();
    }

}
