/**
 * 
 */
package edu.ohiou.ise.ise589;

/**
 * @author Dusan Sormaz
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.table.*;
import edu.ohiou.labimp.basis.*;
import java.util.*;
public class ViewableDemoClass extends ViewObject {
  
  public void init () {
    panel = new JPanel();
    String [] names = {"Dusan Sormaz", "Chintan Patel",
        "Jing Huang", "Suhail Mohamed"};
    String [] headers = {"name", "country"};
    String data [][] = {{"Dusan Sormaz", "Serbia"}, 
              {"Chintan Patel", "India"},
              {"Jing Huang", "China"},
              {"Suhail Mohamed", "India"}};
    JPanel topPanel = new JPanel ();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Top Panel"));
    
    JPanel compPanel = new JPanel();
    compPanel.add (new JLabel("JLabel"));
    compPanel.add (new JButton("JButton"));
    compPanel.add (new JTextField("JTextField"));
    compPanel.add (new JCheckBox("JCheckBox"));
    compPanel.add (new JRadioButton("JRadioButton"));
    compPanel.add (new JComboBox(names));
    
    ImageIcon icon = new ImageIcon(this.getClass().
        getResource("processplan-3dim.jpg"));

    compPanel.add (new JButton(icon));

    JSplitPane splitPane = new JSplitPane();

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("JList", new JScrollPane(new JList(names)));
    DefaultTableModel tableModel =  new DefaultTableModel(data, headers); 
    tabbedPane.addTab("JTable", new JScrollPane(new JTable(tableModel)));
    tabbedPane.addTab("Components in scroll pane", 
              new JScrollPane(compPanel));
//    tabbedPane.addTab("Components", compPanel);
    JTextArea jtextArea = new JTextArea("Text\nArea");
    tabbedPane.addTab("JTextarea", new JScrollPane(jtextArea));
    
    for (int i =0; i< data.length; i++) {
      for (int j =0; j<data[0].length; j++) {
        jtextArea.append("\n" + data[i][j]);        
      }
    }
    
    splitPane.setLeftComponent(tabbedPane);

    splitPane.setRightComponent(new JScrollPane(new JTree()));
    splitPane.setDividerLocation(300);

    panel.add(splitPane, BorderLayout.CENTER);
  }
  
  public LinkedList getDrawList () {
	  LinkedList list = new LinkedList ();
	  list.add(new Ellipse2D.Double(0,0,200,100));
	  list.add(new Rectangle2D.Double(10,10,100,200));
	  return list;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ViewableDemoClass demo = new ViewableDemoClass();
    demo.setApplet(new Draw2DApplet (demo));
    demo.display();

  }

}
