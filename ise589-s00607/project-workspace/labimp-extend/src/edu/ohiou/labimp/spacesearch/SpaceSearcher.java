package edu.ohiou.labimp.spacesearch;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import edu.ohiou.labimp.basis.ViewObject;
import java.util.Collection;
import java.util.HashSet;

import edu.ohiou.labimp.basis.ViewObject.ViewPanel;
import java.util.Comparator;
import edu.ohiou.labimp.spacesearch.Searchable;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import edu.ohiou.labimp.basis.*;

/**
 *
 *  Class SpeaceSearcher performs space search on a supplied Searchable objects.
 *  It provides implementation of generic psace search algorithms, like depth first,
 *  breadth first, bast first and A*.
 * 
 * 
 * 
 */

abstract public class SpaceSearcher extends ViewObject implements Searchable {
  Searchable initialState;
  Searchable goalState;
  Searchable currentState;
  
  // make collections into sets
  TreeSet<Searchable> open;
  HashSet<Searchable> closed;
//  Collection<Searchable> newStates; // remove may not be needed
  public static final int NOT_DEFINED = 0;
  public static final int DEPTH_FIRST = 1;
  public static final int BREADTH_FIRST = 2;
  public static final int BEST_FIRST = 3;
  public static final int ASTARALGORITHM = 4;
  public static final int USER_GUIDED = 5;
  public static int REACH_GOAL = Integer.MAX_VALUE;
  int searchOrder = NOT_DEFINED;
//LinkedList aallStates = new LinkedList();
  LinkedList children = new LinkedList();
  public static final Color CLOSED_COLOR = Color.red;
  public static final Color OPEN_COLOR = Color.blue;
  public static final Color CURRENT_COLOR = Color.magenta;
  public static final Color GOAL_COLOR = Color.green;

  //SpaceSearcherPanel sssPanel = new SpaceSearcherPanel();
//JPanel basePanel = new JPanel();
//boolean fflag1 = false;
  boolean hasReachedGoal = false;

  // needed for applet display in browser
  public SpaceSearcher() {
    this(null, null);
  }

  public SpaceSearcher(Searchable initial, Searchable goal) {
    initialState = initial;
//    currentState = initial;
    goalState = goal;

    if (searchOrder == NOT_DEFINED) {
      searchOrder = DEPTH_FIRST;
    }
    if (initial != null) {
      initializeSearch();
      //			initialiseStatePanel();
    }
  }

  public SpaceSearcher(Searchable initial, Searchable goal, int order) {
    this(initial, goal);
    searchOrder = order;
  }

  public SpaceSearcher(
      Searchable initial,
      Searchable currentState,
      Searchable goal,
      Comparator<Searchable> comparator) {
    searchOrder = BEST_FIRST;
    initialState = initial;
    goalState = goal;
    this.currentState = currentState;
    if (initialState != null ) {
      initializeSearch();
      //			initialiseStatePanel();
    }
  }

  public SpaceSearcher(
      Searchable initial,
      Searchable goal,
      Comparator comparator) {
    this(initial, initial, goal, comparator);
//    searchOrder = BEST_FIRST;
  }

  
  //move to blind searcher
  abstract public void initializeSearch();
  
  public DefaultMutableTreeNode getTree() {
    return null;
  }

//  public Searchable getClone() {
//    return new SpaceSearcher();
//  }

  public int[] setSearchTypes() {
    return new int[100];
  }

  public void init() {
    try {
      this.panel = new SpaceSearcherPanel(); //ssPanel.myPanel;
//    if (this.initialState != null) {
//    ((Viewable) this.initialState).init();

//    }
      //			ssPanel.jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public DefaultMutableTreeNode getNode() {
    return null;
  }

  public Dimension getAppletSize() {
    return new Dimension(900, 700);
  }

  public Searchable getParent() {
    return initialState.getParent();
  }
  
  public int getIndex() {
    return currentState.getIndex();
  }

  public Collection makeNewStates() {
    return currentState.makeNewStates();
  }
  
  public Comparator getComparator() {
    return currentState.getComparator();
  }


  public boolean isBetterThan(Searchable s) {
    return currentState.isBetterThan(s);
  }

  public boolean canBeGoal() {
    return currentState.canBeGoal();
  }

  public void setGoalState(Searchable goal) {
    goalState = goal;
  }

  public Searchable getGoalState() {
    return goalState;
  }
  
  public boolean isSearchComplete (SpaceSearcher s) {
    return initialState.isSearchComplete(this);
  }
  
  public double distFromStart() {
    return goalState.distFromStart();
  }
  
  public double distToGoal() {
    return goalState.distToGoal();
      }


  public Searchable runSpaceSearch(Searchable s) {
    goalState = s;
    return runSpaceSearch();
  }

  public Searchable runOneStep() {
    Searchable currentState = null;
    return currentState;
  }

  public Searchable runSpaceSearch() {
    return runSpaceSearch(REACH_GOAL);
  }
  
  abstract public  Searchable runSpaceSearch (int noSteps);

  public Searchable runSpaceSearchOld(int numberOfSteps) {

    if (currentState.equals(goalState) ) {
      hasReachedGoal = true;
      goalState = currentState;
      return currentState;
    }
    if ( open.size() == 0) {
      hasReachedGoal = false;
//    goalState = currentState;
      return null;
    } 

    System.out.println("remove in breadth search:"  + open.remove(currentState));
    closed.add(currentState);
    Collection newStates = (LinkedList) currentState.makeNewStates();
    removeOldStates(newStates, open);
    removeOldStates(newStates, closed);
    children = new LinkedList(newStates);
    if (searchOrder == BREADTH_FIRST)
      open.addAll(newStates);
    else {
      newStates.addAll(open);
      open = (TreeSet) newStates; // error 
    }
    // dsormaz modified to run space search without gui
    if (open.size() > 0) {
      currentState = (Searchable) open.iterator().next();
    }
    return runSpaceSearchOld (numberOfSteps-1);
//  return currentState;
  }
  
  public Searchable runOptSpaceSearch() {
    return runOptSpaceSearch(REACH_GOAL);
  }
  
  public Searchable runOptSpaceSearch (int numberOfSteps) {
    // move current to closed
//''''''''''JING HUANG''''''''''''''''''''''''''''  
    //((Viewable)currentState).setColor (CLOSED_COLOR);
//  ''''''''''JING HUANG'''''''''''''''''''''''''''' 
    closed.add(currentState);
    System.out.println("old current state:" + currentState);
    System.out.println("old open:" + open);
    // current = open.first
    currentState = (Searchable) ((TreeSet)open).first();
    open.remove(currentState);
    System.out.println("new current state:" + currentState);
    System.out.println(" open after remove:" + open);
    // check if current is goal
    if (currentState.canBeGoal()) { 
      if (goalState == null) {
        goalState = currentState; 
//      ''''''''''JING HUANG''''''''''''''''''''''''''''  
        //((Viewable)goalState).setColor (GOAL_COLOR);
//      ''''''''''JING HUANG''''''''''''''''''''''''''''          
       } else if (currentState.isBetterThan(goalState)) {
//    	 ''''''''''JING HUANG''''''''''''''''''''''''''''     	   
        //((Viewable)goalState).setColor (CLOSED_COLOR);
//      ''''''''''JING HUANG''''''''''''''''''''''''''''          
        goalState = currentState;
//      ''''''''''JING HUANG''''''''''''''''''''''''''''          
       // ((Viewable)goalState).setColor (GOAL_COLOR);
//      ''''''''''JING HUANG''''''''''''''''''''''''''''          
      }
      hasReachedGoal = true;
      return goalState;
    }

    // check if expansion is needed
//  ''''''''''JING HUANG''''''''''''''''''''''''''''      
   //((Viewable)currentState).setColor (CURRENT_COLOR);
//  ''''''''''JING HUANG''''''''''''''''''''''''''''      
    System.out.println("open after remove cs:" + open);

//    if (currentState.canBeGoal() && goalState != null &&
//        currentState.isBetterThan(goalState)) {
//      goalState = currentState;
//      ((Viewable)goalState).setColor (GOAL_COLOR);
//    }
    LinkedList newStates = (LinkedList) currentState.makeNewStates();
    System.out.println("new states: " + newStates);
    removeOldStates(newStates, open);
    removeOldStates(newStates, closed);
    children = new LinkedList(newStates);
    //      System.out.println("children: "+children);

    if (searchOrder == BEST_FIRST) {
      open.addAll(children);
      //        System.out.println("open :"+open);
      //        newStates.addAll(open);
      //        open = newStates;
    } else {
      open.addAll(newStates);
    }
    System.out.println("open befor next iteration:" + open);
    if (numberOfSteps <= 1) {
      return currentState;
    } 
    else {
      numberOfSteps--;
//      return null;
      return runOptSpaceSearch(numberOfSteps);
    }
  }
  

  public Searchable runOptSpaceSearchold(int numberOfSteps) {
    closed.add(currentState);
    ((Viewable)currentState).setColor (CLOSED_COLOR);
    if (open.size() == 0) {
      hasReachedGoal = true;
      return goalState;
    }
    if (currentState.canBeGoal()) { 
      if (goalState == null) {
        goalState = currentState;      
        ((Viewable)goalState).setColor (GOAL_COLOR);
       } else if (currentState.isBetterThan(goalState)) {
        ((Viewable)goalState).setColor (CLOSED_COLOR);
        goalState = currentState;
        ((Viewable)goalState).setColor (GOAL_COLOR);
      }
    }

      currentState = (Searchable) ((TreeSet)open).first();
    
//    System.out.println("new current?: " + currentState);
//    System.out.println("open before remove cs:" + open);
//    System.out.println("equals current?: " + 
//        currentState.equals((Searchable) ((TreeSet)open).first()));
//    System.out.println("equals first?: " + 
//        ((Searchable)((TreeSet)open).first()).equals(currentState));
//    Collection currCol = new ArrayList();
//    currCol.add(currentState);
//    removeOldStates(open, currCol);
//    System.out.println("remove all current?: " +open.removeAll(currCol));
//    System.out.println("remove current?: " + 
  
    ((Viewable)currentState).setColor (CURRENT_COLOR);
    System.out.println("open after remove cs:" + open);

//    if (currentState.canBeGoal() && goalState != null &&
//        currentState.isBetterThan(goalState)) {
//      goalState = currentState;
//      ((Viewable)goalState).setColor (GOAL_COLOR);
//    }
    LinkedList newStates = (LinkedList) currentState.makeNewStates();
    System.out.println("new states: " + newStates);
    removeOldStates(newStates, open);
    removeOldStates(newStates, closed);
    open.remove(currentState);
    children = new LinkedList(newStates);
    //			System.out.println("children: "+children);

    if (searchOrder == BEST_FIRST) {
      open.addAll(children);
      //				System.out.println("open :"+open);
      //				newStates.addAll(open);
      //				open = newStates;
    } else {
      open.addAll(newStates);
    }
    System.out.println("current new:" + currentState);
    if (numberOfSteps <= 1) {
      return currentState;
    } 
    else {
      numberOfSteps--;
//      return null;
      return runOptSpaceSearch(numberOfSteps);
    }
  }

  public void removeOldStates(Collection newStates, Collection oldStates) {
    LinkedList stateList = new LinkedList(newStates);
    for (ListIterator itr = stateList.listIterator(); itr.hasNext();) {
      Searchable state = (Searchable) itr.next();
      if (state.memberInList(oldStates))
        newStates.remove(state);
    }
  }

  public boolean memberInList(Collection stateList) {
    return true;
  }

  public String printPath() {
    return null;
  }

  public boolean equals(Searchable s) {
    return goalState.equals(s);
  }
  
  public String toString () {
    return this.getClassName() + " solving " + initialState.getClass().getName();
  }

  public static void main(String[] args) {
    SpaceSearcher searcher = new BlindSearcher(null, null);
    searcher.setApplet();
    searcher.display("General space searcher");
  }

  public class SpaceSearcherPanel extends ViewPanel {

    LinkedList parents = new LinkedList();

    int stepCount = 0;
    JPanel myyPanel = new JPanel();
    JPanel currentStatePanel = new JPanel();
    JLabel cStateLabel = new JLabel();
    JPanel initialStatePanel = new JPanel();
    JPanel goalStatePanel = new JPanel();
    JPanel selectedStatePanel = new JPanel();
    JLabel selectedStateLabel = new JLabel();
    final JButton runNSteps = new JButton();
    final JTextField nStepsTextField = new JTextField(10);
    int numberSteps = 0;
    JLabel gStateLabel = new JLabel();
    JPanel controllerPane = new JPanel();
    JButton runOneStep = new JButton();
    JButton resetButton = new JButton();
    JButton runAll = new JButton();
    JTree tree;
    JScrollPane treeView = new JScrollPane();
    JList solutionPath = new JList();
    JScrollPane solutionView = new JScrollPane(solutionPath);
    JRadioButton bestButton = new JRadioButton("best");
    JRadioButton depthButton = new JRadioButton("depth");
    JRadioButton breadthButton = new JRadioButton("breadth");
    JRadioButton aStarButton = new JRadioButton("A*");
    JRadioButton userGuidedButton = new JRadioButton("User-guided Search");
    ButtonGroup radioButtons = new ButtonGroup();
    JLabel iStateLabel = new JLabel();
    int[] types = new int[100];
    boolean setSearchType = false;
    boolean problemSelected = false;
    boolean blindSearch = false;
    boolean menuActivated = false;
    BorderLayout bl = new BorderLayout();
    JPanel searchTree = new JPanel(bl);
    JPanel solutionPane = new JPanel(new BorderLayout());
    JMenuBar menuBar = new JMenuBar();
    JScrollPane scrForCurrState = new JScrollPane();
    JScrollPane scrForInitState = new JScrollPane();
    JScrollPane scrForSelectedState = new JScrollPane();
    JMenuItem menuItemFarmer = new JMenuItem("Farmer");
    JMenuItem menuItemMC = new JMenuItem("MissionaryCannibal");
    JMenuItem menuItemTS = new JMenuItem("Traveling Salesman");
    JMenuItem menuItemGT = new JMenuItem("Group Technology");
    JMenuItem menuItemGTAP = new JMenuItem("Alternative Plans");
    JMenuItem menuItemClear = new JMenuItem("Clear");
    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
    JMenu menuItem = new JMenu("Select Problem");
    TreeModel treeModel;
    JTabbedPane statesPane = new JTabbedPane();
    Draw2DPanel canvas = null;

    public SpaceSearcherPanel() {
      if (initialState != null) {
        problemSelected = true;

        initialiseTree();
        menuItemMC.setEnabled(false);
        menuItemFarmer.setEnabled(false);
        menuItemGT.setEnabled(false);

      }
      init();
    }

    public void init() {
      initialiseStatePanel();
      try {
        //					    this.panel = SpaceSearcher.this.basePanel;
        jbInit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void initialiseStatePanel() {
      initialiseStateLabels();
      if (initialState != null) {
        initialiseTree();
        makeViewable();
        problemSelected = true;
        menuBar.setEnabled(false);
        menuItemFarmer.setEnabled(false);
        menuItemMC.setEnabled(false);
        menuItemTS.setEnabled(false);
        menuItemGT.setEnabled(false);
        menuItemGTAP.setEnabled(false);
      }
    }

    public void jbInit() {

      menuItem.add(menuItemFarmer);
      menuItem.add(menuItemMC);
      menuItem.add(menuItemTS);
      menuItem.add(menuItemGT);
      menuItem.add(menuItemGTAP);
      menuItem.add(menuItemClear);
      menuBar.add(menuItem);
      menuItemFarmer
      .addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          menuItemFarmer_actionPerformed(e);
        }
      });

      menuItemMC.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          menuItemMC_actionPerformed(e);
        }
      });

      menuItemTS.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          menuItemTS_actionPerformed(e);
        }
      });

      menuItemGT.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //				    				menuItemGT_actionPerformed(e);
        }
      });

      menuItemGTAP.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //				    				menuItemGTAP_actionPerformed(e);
        }
      });

      menuItemClear
      .addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          menuItemClear_actionPerformed(e);
        }
      });

      JTabbedPane pane = new JTabbedPane();
      searchTree.setBackground(Color.white);
      searchTree.setVisible(true);
      solutionPane.setBackground(Color.white);
      solutionPane.setVisible(true);
      solutionView.setBackground(Color.white);
      solutionPane.setBackground(Color.white);
      pane.addTab("Search Space", searchTree);
      pane.addTab("Solution", solutionPane);
      SpaceSearcher.this.getApplet().setJMenuBar(menuBar);
      GridLayout gridLayout1 = new GridLayout();
      JPanel jPanel4 = new JPanel();
      BorderLayout borderLayout12 = new BorderLayout();
      BorderLayout borderLayout3 = new BorderLayout();
      JMenuBar jMenuBar1 = new JMenuBar();
      JMenu menu = new JMenu();
      menu.setText("SelectProblem");
      jMenuBar1.add(menu);
      JPanel jPanel1 = new JPanel();
      JPanel searchTypePane = new JPanel();
      searchTypePane.add(bestButton);
      searchTypePane.add(depthButton);
      searchTypePane.add(breadthButton);
      searchTypePane.add(aStarButton);
      searchTypePane.add(userGuidedButton);
      bestButton.setEnabled(false);
      depthButton.setEnabled(false);
      breadthButton.setEnabled(false);
      aStarButton.setEnabled(false);
      userGuidedButton.setEnabled(false);

      if (initialState != null)
        types = SpaceSearcher.this.initialState.setSearchTypes();
      for (int i = 0; i < types.length; i++) {
        if (types[i] == SpaceSearcher.BEST_FIRST) {
          bestButton.setEnabled(true);
        }
        if (types[i] == SpaceSearcher.BREADTH_FIRST) {
          breadthButton.setEnabled(true);
        }
        if (types[i] == SpaceSearcher.DEPTH_FIRST) {
          depthButton.setEnabled(true);
        }
        if (types[i] == SpaceSearcher.ASTARALGORITHM) {
          aStarButton.setEnabled(true);
        }
        if (types[i] == SpaceSearcher.USER_GUIDED) {
          userGuidedButton.setEnabled(true);
        }
      }

      radioButtons.add(bestButton);
      radioButtons.add(depthButton);
      radioButtons.add(breadthButton);
      radioButtons.add(aStarButton);
      radioButtons.add(userGuidedButton);

      controllerPane.setLayout(gridLayout1);

      resetButton.setText("Reset");
      controllerPane.add(resetButton);
      runOneStep.setText("runOneStep");
      controllerPane.add(runOneStep);

      runAll.setText("runAll");
      controllerPane.add(runAll);

      runNSteps.setText("NSteps");
      controllerPane.add(runNSteps);


      nStepsTextField.addFocusListener(new FocusAdapter () {
        public void focusLost (FocusEvent e) {
          System.out.println("number steps" + numberSteps);
          try {
            numberSteps = Integer.parseInt(nStepsTextField.getText());
            System.out.println("number steps" + numberSteps);
            if (numberSteps >0) {
            runNSteps.setEnabled (true);
            }
          } catch (NumberFormatException ex) {
            runNSteps.setEnabled (false);
            // incorrect value for number of steps, be silent
//            e.printStackTrace();
          }
        }
      });

      controllerPane.add(nStepsTextField);
      searchTypePane.setBorder(BorderFactory.createEtchedBorder());
      jPanel1.setPreferredSize(new Dimension(150, 150));
      jPanel1.setLayout(borderLayout3);
      jPanel1.add(searchTypePane, BorderLayout.NORTH);
      jPanel1.add(controllerPane, BorderLayout.SOUTH);
      JPanel jPanel8 = new JPanel();
      BorderLayout borderLayout5 = new BorderLayout();
      jPanel8.setLayout(borderLayout5);
      JSplitPane jSplitPane3 = new JSplitPane();
      jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
      JPanel jPanel10 = new JPanel();
      BorderLayout borderLayout6 = new BorderLayout();
      jPanel10.setLayout(borderLayout6);
      scrForInitState.getViewport().add(initialStatePanel, null);
      jPanel10.add(scrForInitState, BorderLayout.CENTER);
      initialStatePanel.setBackground(Color.white);
      TitledBorder titledBorder1 = new TitledBorder("");
      initialStatePanel.setBorder(titledBorder1);
      initialStatePanel.setToolTipText("Initial State");
      currentStatePanel.setToolTipText("Current State");
      goalStatePanel.setToolTipText("Goal State");
      selectedStatePanel.setToolTipText("Selected State");
      BorderLayout borderLayout8 = new BorderLayout();
      initialStatePanel.setLayout(borderLayout8);
      iStateLabel.setBackground(Color.cyan);
      iStateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      iStateLabel.setOpaque(true);
      iStateLabel.setHorizontalAlignment(SwingConstants.LEFT);
      iStateLabel.setHorizontalTextPosition(SwingConstants.LEFT);

      if (initialState != null) {
        iStateLabel.setText("Initial State" + initialState.toString());
//      initialStatePanel.add(
//      ((Viewable)initialState).getPanel(),
//      BorderLayout.CENTER);
      } else {
        iStateLabel.setText("Initial State");

      }

      initialStatePanel.add(iStateLabel, BorderLayout.NORTH);
      JPanel jPanel11 = new JPanel();
      BorderLayout borderLayout9 = new BorderLayout();
      jPanel11.setLayout(borderLayout9);
      JScrollPane scrForGoalState = new JScrollPane();
      jPanel11.add(scrForGoalState, BorderLayout.CENTER);
      BorderLayout borderLayout10 = new BorderLayout();
      goalStatePanel.setLayout(borderLayout10);
      goalStatePanel.setBackground(Color.white);
      gStateLabel.setBackground(Color.yellow);
      gStateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      gStateLabel.setMaximumSize(new Dimension(62, 19));
      gStateLabel.setMinimumSize(new Dimension(62, 19));
      gStateLabel.setOpaque(true);
      gStateLabel.setPreferredSize(new Dimension(62, 19));
      if (goalState != null) {
        gStateLabel.setText("Goal State" + goalState.toString());
//      goalStatePanel.add(((Viewable)goalState).getPanel(), BorderLayout.CENTER);
      } else {
        gStateLabel.setText("Goal State");
      }

      goalStatePanel.add(gStateLabel, BorderLayout.NORTH);
      scrForGoalState.getViewport().add(goalStatePanel, null);
      jSplitPane3.add(jPanel10, JSplitPane.TOP);
      jSplitPane3.add(jPanel11, JSplitPane.BOTTOM);
      BorderLayout borderLayout11 = new BorderLayout();
      currentStatePanel.setLayout(borderLayout11);
      currentStatePanel.setBackground(Color.white);
      scrForCurrState.getViewport().add(currentStatePanel, null);
      cStateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      currentStatePanel.add(cStateLabel, BorderLayout.NORTH);
      cStateLabel.setBackground(Color.green);
      cStateLabel.setOpaque(true);
      cStateLabel.setText("Current State");
      BorderLayout blForSelectedState = new BorderLayout();
      selectedStatePanel.setLayout(blForSelectedState);
      selectedStatePanel.setBackground(Color.white);
      scrForSelectedState.getViewport().add(selectedStatePanel, null);
      selectedStateLabel.setBorder(BorderFactory
          .createLineBorder(Color.black));
      selectedStatePanel.add(selectedStateLabel, BorderLayout.NORTH);
      selectedStateLabel.setBackground(Color.green);
      selectedStateLabel.setOpaque(true);
      selectedStateLabel.setText("Selected State");
      selectedStatePanel.add(selectedStateLabel, BorderLayout.NORTH);
      statesPane.addTab("InitialState", this.scrForInitState);
      statesPane.addTab("GoalState", scrForGoalState);
      statesPane.addTab("CurrentState", this.scrForCurrState);
      statesPane.addTab("Selected State", this.scrForSelectedState);

      jPanel1.add(statesPane, BorderLayout.CENTER);
      runOneStep.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          runOneStep_actionPerformed(e);
        }
      });
      resetButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          resetButton_actionPerformed(e);
        }
      });
      runNSteps.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          runNSteps_actionPerformed(e);
        }
      });

      runAll.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          runAll_actionPerformed(e);
        }
      });

      depthButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          radioButtons_actionPerformed(e);
        }
      });

      breadthButton
      .addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          radioButtons_actionPerformed(e);
        }
      });

      bestButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          radioButtons_actionPerformed(e);
        }
      });

      userGuidedButton
      .addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          userGuidedButton_actionPerformed(e);
        }
      });

      aStarButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          radioButtons_actionPerformed(e);
        }
      });
      BorderLayout borderLayout1 = new BorderLayout();
      this.setLayout(borderLayout1);
      JSplitPane basePane = new JSplitPane();
      pane.setPreferredSize(new Dimension(300, 400));
      jPanel1.setPreferredSize(new Dimension(300, 400));
      basePane.setLeftComponent(pane);
      basePane.setRightComponent(jPanel1);
      basePane.setDividerLocation(-1);
      add(basePane, BorderLayout.CENTER);
    }

    void radioButtons_actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equalsIgnoreCase("depth")) {
        SpaceSearcher.this.searchOrder = SpaceSearcher.DEPTH_FIRST;
        blindSearch = true;
        initializeSearch();
      }
      if (e.getActionCommand().equalsIgnoreCase("breadth")) {
        SpaceSearcher.this.searchOrder = SpaceSearcher.BREADTH_FIRST;
        blindSearch = true;
        initializeSearch();
      }
      if (e.getActionCommand().equalsIgnoreCase("best")) {
        SpaceSearcher.this.searchOrder = SpaceSearcher.BEST_FIRST;
      }
      if (e.getActionCommand().equalsIgnoreCase("astar")) {
        SpaceSearcher.this.searchOrder = SpaceSearcher.ASTARALGORITHM;
      }
      setSearchType = true;
    }
    
    void userGuidedButton_actionPerformed(ActionEvent e) {
      System.out.println("UG button");
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
      .getLastSelectedPathComponent();
      if (node != null) {
        currentState = (Searchable) node.getUserObject();
      }
      runOptSpaceSearch(1);
      userGuidedButton.setEnabled(false);
      refreshVisuals();
    }

    void runAll_actionPerformed(ActionEvent e) {
      statesPane.setSelectedComponent(this.scrForCurrState);
      if (problemSelected && setSearchType) {
        while (!hasReachedGoal) {
          System.out.println("in callback, comp: " + open.comparator());
          System.out.println("in callback, current: " + currentState);
          System.out.println("in callback, size:, " + open.size());
          System.out.println("in callback, open: " + open);
          runOneStep_actionPerformed(e);
        }
      }
      refreshVisuals();
    }

    void runNSteps_actionPerformed(ActionEvent e) {
      if (problemSelected && setSearchType) {
          while (numberSteps > 0) {
            runOneStep_actionPerformed(e);
            numberSteps--;
            System.out.println("run step" + numberSteps);
          }
        } else {
          System.out.println("problem not selected or search type not set");
        }  
      refreshVisuals();

    }

    void runOneStep_actionPerformed(ActionEvent e) {
      System.out.println("-------NEW STEP---- \nOpen Size: "
          + open.size()
          + " Closed Size:"
          + +closed.size()
          + " step : "
          + stepCount++);
//      System.out.println("Current state: " + currentState.toString());
      statesPane.setSelectedComponent(scrForCurrState);
      if (problemSelected && setSearchType) {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
          SpaceSearcher.this.runSpaceSearch(1);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        refreshVisuals();
//        System.out.println("open in ros after step: " + open.size() + open);
//        System.out.println("current state in ros after step: " + currentState);
//        System.out.println("closed in ros after step: " + closed.size() + closed);
       
//        dsormaz commented out to separate visuals from search 6/17/07
//        if (open.size() != 0) {
//          if (open instanceof TreeSet)
//            currentState = (Searchable) ((TreeSet) open).first();
//          else
//            currentState = (Searchable) ((LinkedList) open).get(0);
//        }
        //						System.out.println("current state in ros: "+currentState);
      }
    }

    void refreshVisuals() {
      if (SpaceSearcher.this.hasReachedGoal == true) {
        gStateLabel.setText("Goal State: " + goalState);
        if (goalState instanceof Viewable) {
          ((Viewable) goalState).init();
          goalStatePanel.add(((Viewable)goalState).getPanel());
        }
        cStateLabel
        .setText("Current State: " + currentState.toString());
        if (currentState instanceof Viewable) {
          ((Viewable) currentState).init();
          currentStatePanel.add(((Viewable)currentState).getPanel());
        }
//        dsormaz commented out next line on 11.15.07
//        SpaceSearcher.this.closed.add(goalState);
        Searchable temp = goalState;
        parents.clear();
        while (temp != null) {
          parents.add(temp);
          temp = temp.getParent();
        }
        solutionPath.setListData(parents.toArray());
        solutionPane.add(solutionView, BorderLayout.CENTER);
        solutionPane.revalidate();
        menuItemClear.setEnabled(true);
        SpaceSearcher.this.getApplet().repaint();
      } else {
        if (SpaceSearcher.this.initialState
            .equals(SpaceSearcher.this.currentState)) {
          JLabel label1 = new JLabel("SAME AS INITIAL STATE");
          this.currentStatePanel.removeAll();
          cStateLabel.setText("CurrentState:"
              + SpaceSearcher.this.currentState.toString());
          currentStatePanel.setBackground(Color.white);
          this.currentStatePanel.add(cStateLabel, BorderLayout.NORTH);
          this.currentStatePanel.add(label1, BorderLayout.CENTER);
          this.currentStatePanel.revalidate();
          createNodes(SpaceSearcher.this.currentState);
          this.currentStatePanel.revalidate();
          SpaceSearcher.this.getApplet().repaint();
        } else {
          goalStatePanel.removeAll();
          gStateLabel.setText("GoalState: "
              + SpaceSearcher.this.goalState);
          goalStatePanel.add(gStateLabel, BorderLayout.NORTH);
          goalStatePanel.setBackground(Color.white);
          if (goalState != null) {
            if (goalState instanceof Viewable) {
            ((Viewable) goalState).init();
            goalStatePanel.add(
                ((Viewable)goalState).getPanel(),
                BorderLayout.CENTER);
          }
            else {
              goalStatePanel.add(new JLabel(goalState.toString()));
            }
          }
          goalStatePanel.revalidate();
          this.currentStatePanel.removeAll();
          cStateLabel.setText("CurrentState:"
              + currentState.toString());
          currentStatePanel.setBackground(Color.green);
          this.currentStatePanel.add(cStateLabel, BorderLayout.NORTH);
          if (currentState instanceof Viewable) {
            ((Viewable) SpaceSearcher.this.currentState).init();
            this.currentStatePanel.add(((Viewable)currentState)
                .getPanel(), BorderLayout.CENTER);
          }
          // dsormaz modified current state to init state to enanble tree update
          createNodes(SpaceSearcher.this.currentState);
//          tree.setModel(new DefaultTreeModel (createNodes(SpaceSearcher.this.initialState)));
          this.currentStatePanel.revalidate();
          Searchable temp = goalState;
          parents.clear();
          while (temp != null) {
            if (!parents.contains(temp))
              parents.add(temp);
            temp = temp.getParent();
          }
          solutionPath.setListData(parents.toArray());
          solutionPane.removeAll();
          solutionPane.add(solutionView, BorderLayout.CENTER);
          solutionPane.revalidate();
          SpaceSearcher.this.getApplet().repaint();
        }
      }
    }

    void resetButton_actionPerformed(ActionEvent e) {
      if (problemSelected) {
        open.clear();
        closed.clear();
//        newStates.clear();
        children.clear();
        parents.clear();
        currentState = initialState;
        currentStatePanel.removeAll();
        cStateLabel.setText("Current State");
        currentStatePanel.add(cStateLabel, BorderLayout.NORTH);
        selectedStatePanel.removeAll();
        selectedStateLabel.setText("Selected State: ");
        selectedStatePanel.add(selectedStateLabel, BorderLayout.NORTH);
        solutionPath.setListData(new Object[] {});
        tree.setModel(new DefaultTreeModel(createNodes(currentState)));
        problemSelected = true;
        hasReachedGoal = false;
      }
    }

    void menuItemFarmer_actionPerformed(ActionEvent e) {
      clearStatePanels();
      problemSelected = true;
      menuActivated = true;
      Farmer f = new Farmer(true, true, true, true, null);
      //						Farmer current = new Farmer(true,true,true,true,null);
      Farmer goal = new Farmer(false, false, false, false, null);
      SpaceSearcher.this.initialState = f;
      SpaceSearcher.this.goalState = goal;
      SpaceSearcher.this.currentState = f;
      SpaceSearcher.this.initializeSearch();
      initialiseStateLabels();
      types = f.setSearchTypes();
      initialiseTree();
      enableBlindSearch();
      makeViewable();
      disableAllProblems();
    }

    void menuItemMC_actionPerformed(ActionEvent e) {
      clearStatePanels();
      problemSelected = true;
      menuActivated = true;
      MissionaryCannibal mc = new MissionaryCannibal(3, 3, "Left", null);
      MissionaryCannibal goal = new MissionaryCannibal(
          0,
          0,
          "Right",
          null);
      SpaceSearcher.this.initialState = mc;
      SpaceSearcher.this.goalState = goal;
      SpaceSearcher.this.currentState = mc;
      SpaceSearcher.this.initializeSearch();
      initialiseStateLabels();
      types = mc.setSearchTypes();
      initialiseTree();
      enableBlindSearch();
      makeViewable();
      disableAllProblems();
    }

    void menuItemTS_actionPerformed(ActionEvent e) {
      clearStatePanels();
      problemSelected = true;
      menuActivated = true;
      TravelingSalesman ts1 = new TravelingSalesman();
      //						TravelingSalesman ts2 = new TravelingSalesman();
      TravelingSalesman goal = new TravelingSalesman();
      goal.visitedPath = new LinkedList(TravelingSalesman.cities);
      goal.unvisitedCities = new LinkedList();
      goal.totalDistance = 3000.0;
      SpaceSearcher.this.initialState = ts1;
      SpaceSearcher.this.goalState = goal;
      SpaceSearcher.this.currentState = ts1;
      SpaceSearcher.this.initializeSearch();
      initialiseStateLabels();
      initialiseTree();
      enableBestSearch();
      makeViewable();
      disableAllProblems();
    }

    void disableAllProblems() {
      menuItemFarmer.setEnabled(false);
      menuItemMC.setEnabled(false);
      menuItemTS.setEnabled(false);
      menuItemGT.setEnabled(false);
      menuItemGTAP.setEnabled(false);
      menuItemClear.setEnabled(false);
    }

    void menuItemClear_actionPerformed(ActionEvent e) {
      if (problemSelected) {
        try {
          menuItemFarmer.setEnabled(true);
          menuItemMC.setEnabled(true);
          menuItemTS.setEnabled(true);
          menuItemGT.setEnabled(true);
          menuItemGTAP.setEnabled(true);

          problemSelected = false;
          hasReachedGoal = false;
          setSearchType = false;

          this.runOneStep.setEnabled(true);
          this.runAll.setEnabled(true);
//          this.runNSteps.setEnabled(false);
          this.nStepsTextField.setEnabled(true);

          children = new LinkedList();
          parents = new LinkedList();
          searchTree.removeAll();
          solutionPath.setListData(new Object[] {});
          iStateLabel.setText("Initial State");
          cStateLabel.setText("Current State");
          gStateLabel.setText("Goal State");
          selectedStateLabel.setText("Selected State:");
          initialStatePanel.remove(((Viewable)initialState).getPanel());
          currentStatePanel.remove(((Viewable)currentState).getPanel());
          goalStatePanel.removeAll();
          goalStatePanel.add(gStateLabel, BorderLayout.NORTH);
          selectedStatePanel.remove(((Viewable)currentState).getPanel());
          initialState = null;
          currentState = null;
          goalState = null;

          breadthButton.setEnabled(false);
          depthButton.setEnabled(false);
          bestButton.setEnabled(false);
          aStarButton.setEnabled(false);
          radioButtons.remove(breadthButton);
          radioButtons.remove(bestButton);
          radioButtons.remove(depthButton);
          radioButtons.remove(aStarButton);
          radioButtons.add(breadthButton);
          radioButtons.add(bestButton);
          radioButtons.add(depthButton);
          radioButtons.add(aStarButton);
        } catch (Exception ex) {
        }
      }
    }

    void clearStatePanels() {
      initialStatePanel.removeAll();
      initialStatePanel.revalidate();
      currentStatePanel.removeAll();
      currentStatePanel.revalidate();
      goalStatePanel.removeAll();
      goalStatePanel.revalidate();
      selectedStatePanel.removeAll();
      selectedStatePanel.revalidate();
    }

    void clearSolutionTree() {
      tree.removeAll();
      searchTree.removeAll();
      searchTree.revalidate();
      solutionPath.removeAll();
      solutionView.removeAll();
      solutionPane.removeAll();
      solutionView.revalidate();
      solutionPane.revalidate();
    }

    void initialiseStateLabels() {
      this.initialStatePanel.remove(iStateLabel);
      this.iStateLabel.setText("Initial State " + initialState);
      this.iStateLabel.revalidate();
      this.gStateLabel.setText("Goal State:   " + goalState);
      this.cStateLabel.setText("Current State" + currentState);
      initialStatePanel.add(iStateLabel, BorderLayout.NORTH);
      currentStatePanel.add(cStateLabel, BorderLayout.NORTH);
      goalStatePanel.add(gStateLabel, BorderLayout.NORTH);
      selectedStatePanel.add(selectedStateLabel, BorderLayout.NORTH);
    }

    void initialiseTree() {
      /**
       * @todo
       * COMMENT THE GIF CODE WHEN COPYING TO labimp1
       */
      //						tree = new JTree(createNodes(currentState));
      treeModel = new DefaultTreeModel(createNodes(initialState));
      tree = new JTree(treeModel);
      tree.setCellRenderer(new ToolTipTreeRenderer());
      ToolTipManager.sharedInstance().registerComponent(tree);
      tree.putClientProperty("JTree.lineStyle", "Angled");
      treeView = new JScrollPane(tree);
      treeView.getViewport().add(tree, null);
      searchTree.add(treeView, BorderLayout.CENTER);
      tree.setEditable(true);
      tree.getSelectionModel().setSelectionMode(
          TreeSelectionModel.SINGLE_TREE_SELECTION);
      tree.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent e) {
          valueChanged_actionPerformed(e);
        }
      });
    }

    void valueChanged_actionPerformed(TreeSelectionEvent e) {
      System.out.println("in value changed"); 
      //user guided button is valid only if a state is selected in tree
      // and that state is in OPEN
      userGuidedButton.setEnabled(true);
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
      .getLastSelectedPathComponent();
      if (node != null) {
        currentState = (Searchable) node.getUserObject();
        //		    				if(currentState instanceof GTState)
        //										SpaceSearcher.this.initializeKnwSearch(((GTState)currentState).getComparator());
        Comparator comp = getComparator();
        if (comp != null) {
          SpaceSearcher.this
          .initializeSearch();         
        }     
//        if (currentState instanceof TravelingSalesman) {
//          SpaceSearcher.this
//          .initializeKnwSearch(((TravelingSalesman) currentState)
//              .getComparator());         
//        }
        hasReachedGoal = false;
        this.selectedStatePanel.removeAll();
        if (currentState instanceof Viewable) {
          ((Viewable) SpaceSearcher.this.currentState).init();
          JPanel localPanel =((Viewable)currentState).getPanel();
          this.selectedStatePanel.add(localPanel,BorderLayout.CENTER);  
//          if (currentState instanceof Drawable2D) {
//            if (canvas != null) {
//              localPanel.add(canvas, BorderLayout.CENTER);             
//            } else
//            
//          }
        } 
        else {
          this.selectedStatePanel.add(
              new JLabel(currentState.toString()),
              BorderLayout.CENTER);          
        }
        selectedStateLabel.setText("Selected State"
            + currentState.toString());
        this.selectedStatePanel.add(
            this.selectedStateLabel,
            BorderLayout.NORTH);
        this.selectedStatePanel.revalidate();
        SpaceSearcher.this.getApplet().repaint();
      }
    }

    void enableBestSearch() {
      this.bestButton.setEnabled(true);
    }

    void enableBlindSearch() {
      this.depthButton.setEnabled(true);
      this.breadthButton.setEnabled(true);
    }

    public DefaultMutableTreeNode createNodes(Searchable state) {
      DefaultMutableTreeNode node = state.getNode();
      for (Iterator itr = children.iterator(); itr.hasNext();) {
        node.add(((DefaultSpaceState) itr.next()).getNode());
      }
      if (node.getChildCount() != 0) {
        tree.scrollPathToVisible(new TreePath(
            ((DefaultMutableTreeNode) node.getChildAt(0)).getPath()));
      }
      return node;
      //					DefaultMutableTreeNode node = ((DefaultSpaceState)state).getNode();
    }

    void makeViewable() {
      if (initialState instanceof Viewable) {
        ((Viewable) initialState).init();
        this.initialStatePanel.add(
            ((Viewable)initialState).getPanel(),
            BorderLayout.CENTER);      
      }
      else {
        initialStatePanel.add(new JLabel(initialState.toString()), BorderLayout.CENTER);
      }
      if (goalState != null) {
      if (goalState instanceof Viewable) {
        ((Viewable) goalState).init();
        this.goalStatePanel.add(((Viewable)goalState).getPanel(), BorderLayout.CENTER);
      }
      else {
        goalStatePanel.add(new JLabel(goalState.toString()), BorderLayout.CENTER);

      }
      }
    }

    public Dimension getAppletSize() {
      return new Dimension(500, 500);
    }
  }
  
  class SearchSet extends TreeSet<Searchable> {
    
    public SearchSet (Comparator<Searchable> c)  {
      super (c); 
    }
    
    public boolean add (Searchable state) {
      HashSet<Searchable> hs = new HashSet<Searchable> (this);
      if (!hs.contains(state)) {
//        System.out.println("Contains not:\n"  + state + "\n" + hs);
        return super.add(state);
      }
//      System.out.println("Contains:\n"  + state + "\n" + hs);
      return false;
    }

  }

  }
