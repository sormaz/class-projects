package edu.ohiou.ise.ise589;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
 

public class SaveJPG {
	/*
	Here's a short example to demonstrate painting a component onto a BufferedImage, then writing that image to a file. All the action is in the ActionListener!

    */
	    public static void main(String[] args) {
	        JFrame f = new JFrame("SaveEx");
	        Container cp = f.getContentPane();
	        final Component comp = cp.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	            new JScrollPane(new JTextArea()), new JScrollPane(new JTextArea())));
	        JPanel south = new JPanel();
	        cp.add(south, BorderLayout.SOUTH);
	        JButton save = (JButton) south.add(new JButton("save"));
	        save.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt) {
	                try {
	                    int w = comp.getWidth(), h = comp.getHeight();
	                    BufferedImage image = new BufferedImage(w, h,
	                        BufferedImage.TYPE_INT_RGB);
	                    Graphics2D g2 = image.createGraphics();
	                    comp.paint(g2);
	                    g2.dispose();
	                    ImageIO.write(image, "jpeg", new File("example.jpeg"));
	                } catch (IOException e) {
	                    System.err.println(e);
	                }
	            }
	        });
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(400,400);
	        f.show();
	    }
	}

/*
	1. Never used ImageIO? It's part of J2SE 1.4:

	http://java.sun.com/j2se/1.4.2/docs/guide/imageio/index.html

	2. Still Using JPEGCodec with 1.4? Stop:

	http://java.sun.com/products/jdk/faq/faq-sun-packages.html

	3. Have fun!
	*/

