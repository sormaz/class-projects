package edu.ohiou.labimp.spacesearch;

import edu.ohiou.labimp.basis.ImpXmlHandler;
import edu.ohiou.labimp.basis.ImpXmlReader;
import edu.ohiou.labimp.basis.ViewObject;
import edu.ohiou.labimp.basis.Viewable;
import edu.ohiou.labimp.draw.ImpObject;
import edu.ohiou.labimp.table.SquareTableModel;
import edu.ohiou.labimp.table.TableCellGenerator;
import java.util.LinkedList;
import edu.ohiou.labimp.table.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.io.*;
import java.net.URL;
import java.lang.reflect.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class TravelingSalesman extends ComparableSpaceState {
	static LinkedList cities = new LinkedList();
	LinkedList visitedPath = new LinkedList();
	LinkedList unvisitedCities = new LinkedList();
	double totalDistance;
    double minDistanceToGoal;
	TSTableModel tableModel;
	static String currentHeuristic="";
	static double distances[][] = { { 0, 5, 1, 2, 3 }, { 3, 0, 4, 5, 6 },
			{ 10, 6, 0, 8, 9 }, { 11, 6, 5, 0, 9 }, { 10, 12, 7, 8, 0 } };
//	  static double distances [][] = { {0, 5, 1},
//	 {5, 0, 4},
//	 {1, 4, 0}};
	 

	static {
    cities.add(new String("AT"));
		cities.add(new String("CL"));
		cities.add(new String("CO"));
		cities.add(new String("CI"));
		cities.add(new String("MA"));
	}
  
  public static void readFile (String fileName) throws IOException{
    readFile( new File(fileName));
  }
 
  public static void readFile (File file)throws IOException {
    readFile( new FileInputStream(file));

  }
  
  public static void readFile (URL file) throws IOException{
    readFile(file.openStream());
  }

  public static void readFile (InputStream stream) throws IOException{
    readFile(new BufferedReader(new InputStreamReader(stream)));
  }

  public static void readFile (BufferedReader reader) throws IOException {
    String citiNames = reader.readLine();
    StringTokenizer tokenizer = new StringTokenizer (citiNames,",");
    cities = new LinkedList();
    while (tokenizer.hasMoreTokens()) {
      cities.add(tokenizer.nextToken());
    }
    distances = new double [cities.size()][cities.size()];
    int i = 0;
    int size = cities.size();
    while (reader.ready()) {
      String distanceLine = reader.readLine();
      StringTokenizer distTokenizer = new StringTokenizer (distanceLine,",");
      if (distTokenizer.countTokens() < size) {
        throw new IOException("Not enough tokens in line: " + distanceLine);
      }
      for (int j = 0; j< size; j++) {
        try {
          distances[i][j] = Double.parseDouble(distTokenizer.nextToken());
        } catch (NumberFormatException e) {
          throw new IOException ("String does not parse into double:" + e.getMessage());
        }
      }
      i++; 
    }
    if (i< size) {
      throw new IOException("Not enough lines for all cities, premature EOF");
    }
      
  }


	public TravelingSalesman() {
		unvisitedCities = new LinkedList(cities);
		tableModel = new TSTableModel(cities, new TSCellGenerator());
		node = new DefaultMutableTreeNode(this);

	}

	public TravelingSalesman(LinkedList inCities, double[][] inDistances) {
		cities = inCities;
		distances = inDistances;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
 //''''''''BY JING HUANG'''''''''''''''''''''		
/*		return "{"
			+ visitedPath.toString()
			+ "--"
			+ unvisitedCities.toString()
			+ " Distance <"
			+ totalDistance
			+ ">, To go <" + minDistanceToGoal + " >}";*/
		return super.toString() +  "{"
		+ visitedPath.toString()
		+ " Distance <"
		+ totalDistance
		+ ">, To go <" + minDistanceToGoal + " >}";
	}

	private void computeTotalDistance() {

		int size = this.visitedPath.size();
		for (int i = 0; i < size-1; i++) {
			String icity = (String) this.visitedPath.get(i);
			String nextcity = (String) this.visitedPath.get((i + 1) % size);
			int j = ((AbstractTableModel) tableModel).findColumn(icity) - 1; // decrement for extra column
			int j1 = ((AbstractTableModel) tableModel).findColumn(nextcity);
			totalDistance += ((Double) ((List) tableModel.getDataObjects().get(
				j)).get(j1)).doubleValue();
			//      totalDistance += ((Double) tableModel.getDataObjects()[j][j1]).doubleValue();
		}
    if (this.unvisitedCities.size() == 0) {
      String icity = (String) this.visitedPath.get(0);
      String nextcity = (String) this.visitedPath.get(size-1);
      int j = ((AbstractTableModel) tableModel).findColumn(icity) - 1; // decrement for extra column
      int j1 = ((AbstractTableModel) tableModel).findColumn(nextcity);
      totalDistance += ((Double) ((List) tableModel.getDataObjects().get(
        j)).get(j1)).doubleValue();
      //      totalDistance += ((Double) tableModel.getDataObjects()[j][j1]).doubleValue();
    }
	}
  
  public double distFromStart() {
    return totalDistance;
  }
  
  public double distToGoal() {
    return minDistanceToGoal;
  }

  
  public void calculateMinDistToGoal() {
    HeuristicFunction heuristic;
    try {
      if (this.currentHeuristic.equalsIgnoreCase(""))
    	  heuristic = getHeuristic();
      else 
          heuristic = getHeuristic(this.currentHeuristic);
      minDistanceToGoal = heuristic.evaluate();
    } catch (HeuristicException e) {
      minDistanceToGoal = Double.NaN;
    }
      }
  
  public HeuristicFunction getHeuristic () 
    throws HeuristicException {
    String hName = ViewObject.properties.getProperty(
        this.getClass().getName() +".heuristic", "");
       HeuristicFunction heuristic = null;
      Class[] argTypes = {ComparableSpaceState.class };
      Object[] args = { this };
      if (hName != null && !hName.equalsIgnoreCase("")) {
          try {
            Class hClass = Class.forName(hName);
            Constructor c = hClass.getConstructor(argTypes);
            heuristic = (HeuristicFunction) c.newInstance(args);
          } catch (Exception e) {
            throw new HeuristicException("Heuristic unavailable", e);
       }
       }
      return heuristic;

  }

	public HeuristicFunction getHeuristic (String name) 
	 throws HeuristicException {
	    //String hName = this.getClass().getName() +"." + name;
	    String hName ="";
	    StringTokenizer tokenizer = new StringTokenizer (this.getClass().getName(),".");
	    while (tokenizer.countTokens()!=1) {
	    	hName=hName+tokenizer.nextToken()+".";
	    }
	    hName=hName+name;
	       HeuristicFunction heuristic = null;
	      Class[] argTypes = {ComparableSpaceState.class };
	      Object[] args = { this };
	      if (hName != null && !hName.equalsIgnoreCase("")) {
	          try {
	            Class hClass = Class.forName(hName);
	            Constructor c = hClass.getConstructor(argTypes);
	            heuristic = (HeuristicFunction) c.newInstance(args);
	          } catch (Exception e) {
	            throw new HeuristicException("Heuristic unavailable", e);
	       }
	       }
	      return heuristic;


  }
	public boolean canBeGoal() {
		return cities.size() == visitedPath.size();
	}

	public boolean isBetterThan(Searchable inState) {
		if (inState == null)
			return true;
		//    System.out.println("this distance " + this.totalDistance);
		//    System.out.println("in state distance " + ((TravelingSalesman) inState).totalDistance);
		return this.totalDistance <= ((TravelingSalesman) inState).totalDistance;
	}

	public boolean equals(Searchable s) {
//   System.out.println("in equals, this:" + this + " state s: " + s); 
		if (!(this.visitedPath.size() == ((TravelingSalesman) s).visitedPath
			.size()))
			return false;
		for (int i = 0; i < this.visitedPath.size(); i++) {
			String thisCity = (String) this.visitedPath.get(i);
			String sCity = (String) ((TravelingSalesman) s).visitedPath.get(i);
			if (!thisCity.equalsIgnoreCase(sCity))
				return false;
		}
		return true;
	}
  
  public int hashCode () {
    return 0;
  }
  
//  public boolean equals (Searchable s) {
//    return super.equals((Object)s);
//  }

	public int[] setSearchTypes() {
		int[] types = new int[2];
		types[0] = SpaceSearcher.BEST_FIRST;
    types[1] = SpaceSearcher.BREADTH_FIRST;
		return types;
	}

	public Collection makeNewStates() {
		Set<Searchable>  states=  new HashSet<Searchable>();

		for (Iterator itr = unvisitedCities.iterator(); itr.hasNext();) {
			String city = (String) itr.next();
			TravelingSalesman ts = new TravelingSalesman();
			ts.setColor (SpaceSearcher.OPEN_COLOR);
			ts.visitedPath = new LinkedList(visitedPath);
			ts.visitedPath.add(city);
			ts.unvisitedCities = new LinkedList(unvisitedCities);
			ts.unvisitedCities.remove(city);
			ts.computeTotalDistance();
      ts.calculateMinDistToGoal();
			states.add(ts);
		}
		return states;
	}

	public Searchable runSpaceSearch(Searchable goal) {
		SpaceSearcher searcher = new InformedSearcher(
			this,
			goal,
			new TSComparator());

		//	searcher.ssPanel.problemSelected = true;
		//
		//	searcher.ssPanel.initialiseTree();
		//	searcher.ssPanel.menuItemMC.setEnabled(false);
		//	searcher.ssPanel.menuItemFarmer.setEnabled(false);
		//	searcher.ssPanel.menuItemGT.setEnabled(false);

		//	searcher.ssPanel.makeViewable();
		//	searcher.setApplet();
    return searcher.runSpaceSearch(goal);
//		searcher.display("Traveling salesman");
//		return null;
		//    return searcher.runOptSpaceSearch();
	}

	public Comparator getComparator() {
		return new TSComparator();
	}

	public void init() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
//    TSTableModel visitedModel = new TSTableModel(visitedPath, 
//              new SquareSubModelCellGenerator(tableModel));
//    TSTableModel unvisitedModel = new TSTableModel(unvisitedCities, 
//              new SquareSubModelCellGenerator(tableModel));
//    ModelTable vTable = new ModelTable(visitedModel);
//    ModelTable uTable = new ModelTable(unvisitedModel);
   JPanel modelPanel = new JPanel();
//    modelPanel.setLayout(new GridLayout(3,1));
//    scrollPane.setPreferredSize(new Dimension(200,100));
//    modelPanel.add(scrollPane);
//    modelPanel.add(new JScrollPane(vTable));
//    panel.add(uTable.getPanel(),BorderLayout.CENTER );
		panel.add(scrollPane, BorderLayout.NORTH);
		panel.add(
			new JLabel("Total Distance: " + totalDistance),
			BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
    try {
      readFile(TravelingSalesman.class.
          //getResource("ts8x8-symmetric-s1.csv"));
    	  getResource("ts5x5-50-200.csv"));
      
      /*by jing*/
      //distances=TSPSampleGenerator.generatorSample(); 
      /*by jing*/
      
      
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		TravelingSalesman ts = new TravelingSalesman();
    try {
    	//ts.currentHeuristic="MinValuesHeuristic";
    	ts.currentHeuristic="MinColumnHeuristic";
    	//ts.currentHeuristic="BestFirstHeuristic";
    	//ts.currentHeuristic="MinRowHeuristic";
    	if (ts.currentHeuristic.equalsIgnoreCase(""))
    		System.out.println("H: " + ts.getHeuristic().evaluate());
    	else
            System.out.println("H: " + ts.getHeuristic(ts.currentHeuristic).evaluate());
    } catch (HeuristicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    ts.visitedPath =  new LinkedList();
    ts.visitedPath.add(cities.get(4));
    ts.unvisitedCities = new LinkedList(cities);
    ts.unvisitedCities.remove(cities.get(4));
    //    ts.display(ts.toString() );
		//    System.out.println(ts.makeNewStates() );
		TravelingSalesman goal = new TravelingSalesman();
		goal.visitedPath = new LinkedList(cities);
		goal.unvisitedCities = new LinkedList();
		goal.totalDistance = 3000.;

//    BlindSearcher searcher = new BlindSearcher(ts, goal);
    
//  Searchable g = searcher.runSpaceSearch();
// System.out.println("result " +  g.printPath());
// System.out.println("open: " +  searcher.open.size());
// System.out.println("closed: " +  searcher.closed.size());
    //''''''''JINH HUANG'''''''''''''''''''''''''''''''''''''''''''''
		//InformedSearcher searcher = new InformedSearcher(ts, null);
        //searcher.setApplet();
        //searcher.display();
		InformedSearcher searcher = new InformedSearcher(ts, goal);
		searcher.display();
//		searcher.runSpaceSearch();
		
		//BlindSearcher searcher = new BlindSearcher(ts, goal,SpaceSearcher.BREADTH_FIRST);
		//BlindSearcher searcher = new BlindSearcher(ts, goal,SpaceSearcher.BREADTH_FIRST);
		//searcher.initializeSearch();
		//searcher.runSpaceSearch();
    //''''''''JINH HUANG'''''''''''''''''''''''''''''''''''''''''''''''
//		TravelingSalesman newts = (TravelingSalesman) ts.runSpaceSearch(null);
//		    newts.display(newts.toString() );
//        System.out.println (newts);
	}

	class TSTableModel extends SquareTableModel {

		public TSTableModel(LinkedList inCities) {
			this(inCities, null);
		}

		public TSTableModel(
			LinkedList inCities,
			TableCellGenerator cellGenerator) {
			super(inCities.toArray(), cellGenerator);
			cities = inCities;
		}

	}

	class TSCellGenerator implements TableCellGenerator {

		public Object makeTableCell(Object o1, Object o2) {
			//  int o1Position = cities.
			int o1Position = cities.indexOf(o1);
			int o2Position = cities.indexOf(o2);
			return new Double(distances[o1Position][o2Position]);
		}

		public void updateRelation(Object o1, Object o2, Object value) {
		}

	} // EofClass TSCellGenerator

	class TSComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			if (((Searchable)o1).equals((Searchable)o2))
				return 0;
      TravelingSalesman t1 = (TravelingSalesman) o1;
      TravelingSalesman t2 = (TravelingSalesman) o2;
			if ((t1.totalDistance + t1.minDistanceToGoal) <= 
			        (t2.totalDistance + t2.minDistanceToGoal))
				return -1;
			else
				return 1;
		}
//		public boolean equals(Searchable s) {
//			if (!(visitedPath.size() == ((TravelingSalesman) s).visitedPath
//				.size()))
//				return false;
//			for (int i = 0; i < visitedPath.size(); i++) {
//				String thisCity = (String) visitedPath.get(i);
//				String sCity = (String) ((TravelingSalesman) s).visitedPath
//					.get(i);
//				if (!thisCity.equalsIgnoreCase(sCity))
//					return false;
//			}
//			return true;
//		}

	}

}

class MinValuesHeuristic implements HeuristicFunction {
  TravelingSalesman state;
  public MinValuesHeuristic (ComparableSpaceState state) {
    this.state = (TravelingSalesman)state; 
  }
  public double evaluate () {
    if (state.unvisitedCities.isEmpty()) {
      return 0;
    }
    ArrayList rows = new ArrayList (state.unvisitedCities);
    ArrayList cols = new ArrayList (state.unvisitedCities);
    if (state.visitedPath.size() > 0) {
      rows.add(state.visitedPath.getFirst());
      cols.add(state.visitedPath.getLast());
      
    }
    ArrayList<Double> values = new ArrayList<Double>();
    for (Iterator rowItr = rows.iterator(); rowItr.hasNext();) {
      int i = state.tableModel.findRow(rowItr.next());
      for (Iterator colItr = cols.iterator(); colItr.hasNext();) {
         int j = state.tableModel.findColumn(colItr.next());
//        String val = (String)state.tableModel.getValueAt(i,j);
         if (i != j) {
        Double val = (Double)state.tableModel.getValueAt(i,j+1);
        values.add(val);
         }
      }
    }
     Double [] arr = new Double [values.size()];
    values.toArray(arr);
   Arrays.sort(arr);
   double result = 0.0;
   for (int i = 0; i < rows.size(); i++) 
     result += arr[i];
    return result;
    
  }
}

class MinRowHeuristic implements HeuristicFunction {
  TravelingSalesman state;
  public MinRowHeuristic (ComparableSpaceState state) {
    this.state = (TravelingSalesman)state; 
  }
  public double evaluate () {
    double result = 0.0;
    if (state.unvisitedCities.isEmpty()) {
      return result;
    }
    ArrayList rows = new ArrayList (state.unvisitedCities);
    ArrayList cols = new ArrayList (state.unvisitedCities);
    if (state.visitedPath.size() > 0) {
      rows.add(state.visitedPath.getFirst());
      cols.add(state.visitedPath.getLast());
      
    }
//    ArrayList<Double> values = new ArrayList<Double>();
    for (Iterator rowItr = rows.iterator(); rowItr.hasNext();) {
      int i = state.tableModel.findRow(rowItr.next());
      TreeSet<Double> valueSet = new TreeSet<Double>();
      for (Iterator colItr = cols.iterator(); colItr.hasNext();) {
         int j = state.tableModel.findColumn(colItr.next());
//        String val = (String)state.tableModel.getValueAt(i,j);
         if (i != j) {
        Double val = (Double)state.tableModel.getValueAt(i,j+1);
        valueSet.add(val);
         }
      }
      result += valueSet.first();
   }
    return result;
    
  }
}





class BestFirstHeuristic implements HeuristicFunction {
	  TravelingSalesman state;
	  public BestFirstHeuristic (ComparableSpaceState state) {
	    this.state = (TravelingSalesman)state; 
	  }
	  public double evaluate () {
	    return 0;
	}
}

class MinRowColHeuristic implements HeuristicFunction {
	  TravelingSalesman state;
	  public MinRowColHeuristic  (ComparableSpaceState state) {
	    this.state = (TravelingSalesman)state; 
	  }
	  public double evaluate () {
	    if (state.unvisitedCities.isEmpty()) {
	      return 0;
	    }
	    ArrayList rows = new ArrayList (state.unvisitedCities);
	    ArrayList cols = new ArrayList (state.unvisitedCities);
	    if (state.visitedPath.size() > 0) {
	      rows.add(state.visitedPath.getFirst());
	      cols.add(state.visitedPath.getLast());
	      
	    }
	  ArrayList resultArray= new ArrayList();  
	    for (Iterator rowItr = rows.iterator(); rowItr.hasNext();) {
	        int i = state.tableModel.findRow(rowItr.next());
	        TreeSet<Double> valueSet = new TreeSet<Double>();
	        for (Iterator colItr = cols.iterator(); colItr.hasNext();) {
	           int j = state.tableModel.findColumn(colItr.next());
//	          String val = (String)state.tableModel.getValueAt(i,j);
	           if (i != j) {
	          Double val = (Double)state.tableModel.getValueAt(i,j+1);
	          valueSet.add(val);
	           }
	        }
	        resultArray.add(valueSet.first());
	     }
	    for (Iterator colsItr =cols.iterator(); colsItr.hasNext();) {
		      int j = state.tableModel.findColumn(colsItr.next());
		      TreeSet<Double> valueSet = new TreeSet<Double>();
		      for (Iterator rowsItr = rows.iterator(); rowsItr.hasNext();) {
		         int i = state.tableModel.findRow(rowsItr.next());
//		        String val = (String)state.tableModel.getValueAt(i,j);
		         if (i != j) {
		        Double val = (Double)state.tableModel.getValueAt(i,j+1);
		        valueSet.add(val);
		         }
		      }
		      resultArray.add(valueSet.first());
		   } 
	     Double [] arr = new Double [resultArray.size()];
	     resultArray.toArray(arr);
	     Arrays.sort(arr);
	     double result = 0.0;
	     for (int i = resultArray.size() -1; i > rows.size()-1; i--) 
	         result += arr[i];
	      return result;
	    
	  }
	}



class MinColumnHeuristic implements HeuristicFunction {
	  TravelingSalesman state;
	  public MinColumnHeuristic (ComparableSpaceState state) {
	    this.state = (TravelingSalesman)state; 
	  }
	  public double evaluate () {
	    double result = 0.0;
	    if (state.unvisitedCities.isEmpty()) {
	      return result;
	    }
	    ArrayList rows = new ArrayList (state.unvisitedCities);
	    ArrayList cols = new ArrayList (state.unvisitedCities);
	    if (state.visitedPath.size() > 0) {
	      rows.add(state.visitedPath.getFirst());
	      cols.add(state.visitedPath.getLast());
	      
	    }
//	    ArrayList<Double> values = new ArrayList<Double>();
	    for (Iterator colsItr =cols.iterator(); colsItr.hasNext();) {
	      int j = state.tableModel.findColumn(colsItr.next());
	      TreeSet<Double> valueSet = new TreeSet<Double>();
	      for (Iterator rowsItr = rows.iterator(); rowsItr.hasNext();) {
	         int i = state.tableModel.findRow(rowsItr.next());
//	        String val = (String)state.tableModel.getValueAt(i,j);
	         if (i != j) {
	        Double val = (Double)state.tableModel.getValueAt(i,j+1);
	        valueSet.add(val);
	         }
	      }
	      result += valueSet.first();
	   }
	    return result;
	    
	  }
	}
