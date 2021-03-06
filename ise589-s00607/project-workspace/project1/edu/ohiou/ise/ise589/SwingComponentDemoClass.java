package edu.ohiou.ise.ise589;
import java.awt.BorderLayout;
import javax.swing.table.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class SwingComponentDemoClass extends Object {
	
	JTextArea jTextArea;
	
	public JPanel createGUIPanel () {
		String [] names = {"Dusan Sormaz", "Chintan Patel",
				"Jing Huang", "Suhail Mohamed", "Jeremy Cosner", "Adrian Bradu"};
		String [] headers = {"name", "country", "age"};
		String data [][] = {{"Dusan Sormaz", "Serbia", "54"}, 
							{"Sai", "India", "25"},
							{"Hui li", "China", "13"},
							{"dan", "India", "30"},
							{"Jia", "USA", "75"},
							{"yang", "China", "19" },
							{"Sai 2nd", "India", "25" },							
							{"Adrian Bradu", "Romania", "22"}};
		JPanel topPanel = new JPanel ();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createTitledBorder("Top Panel"));
		
		final JPanel compPanel = new JPanel();
		compPanel.add (new JLabel("JLabel"));
		final JButton jButton = new JButton("JButton");

		
		compPanel.add (jButton);
		compPanel.add (new JTextField("JTextField"));
		compPanel.add (new JCheckBox("JCheckBox"));
		compPanel.add (new JRadioButton("JRadioButton"));
		compPanel.add (new JComboBox(names));
		
		ImageIcon icon = new ImageIcon(this.getClass().
				getResource("processplan-3dim.jpg"));

		compPanel.add (new JButton(icon));

		JSplitPane splitPane = new JSplitPane();

		JTabbedPane tabbedPane = new JTabbedPane();
		final JList jList = new JList(names);
		
		jList.addListSelectionListener(new ListSelectionListener () {
			
			public void valueChanged (ListSelectionEvent e) {
				jButton.setText(jList.getSelectedValues()[0].toString());
			}
		});
		
		
		
		
		
		tabbedPane.addTab("JList", new JScrollPane(jList));
		DefaultTableModel tableModel =  new DefaultTableModel(data, headers); 
		tabbedPane.addTab("JTable", new JScrollPane(new JTable(tableModel)));
		tabbedPane.addTab("Components in scroll pane", 
							new JScrollPane(compPanel));
//		tabbedPane.addTab("Components", compPanel);
		jTextArea = new JTextArea("Text\nArea");
		tabbedPane.addTab("JTextarea", new JScrollPane(jTextArea));
		
		for (int i =0; i< data.length; i++) {
			for (int j =0; j<data[0].length; j++) {
				jTextArea.append("\n" + data[i][j]);				
			}
		}
		
		compPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				compPanel.setToolTipText("x:" + e.getX() + ",y:" +e.getY());
			}
		});
		
		jButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jTextArea.append("\nExecution action in annonimuos listener");
			}
			
		});

		jButton.addActionListener(new MyButtonAdapter());
		
		splitPane.setLeftComponent(tabbedPane);

		splitPane.setRightComponent(new JScrollPane(new JTree()));
		splitPane.setDividerLocation(300);

		topPanel.add(splitPane, BorderLayout.CENTER);
		return topPanel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingComponentDemoClass demo = new SwingComponentDemoClass();
		JFrame frame= new JFrame();
		frame.setSize(300, 300);
		JButton button = new JButton("click");
		JToolBar bar = new JToolBar();
		bar.add(button);
		frame.setTitle("Swing Component Demo");
//		frame.getContentPane().add(new JLabel("test"));
		frame.getContentPane().add(demo.createGUIPanel());
		frame.setVisible(true);

	}

}

class MyButtonAdapter implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("\nExecution action in MyButtonAdapter");
	}
	
}