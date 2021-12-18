package edu.ohiou.labimp.spacesearch;

import java.io.IOException;
import java.util.*;

import edu.ohiou.labimp.basis.Viewable;

public class InformedSearcher extends SpaceSearcher {

  
	
  public InformedSearcher() {
    // TODO Auto-generated constructor stub
  }

  public InformedSearcher(Searchable initial, Searchable goal) {
    super(initial, goal);
    // TODO Auto-generated constructor stub
  }

  public InformedSearcher(Searchable initial, Searchable goal, int order) {
    super(initial, goal, order);
    // TODO Auto-generated constructor stub
  }

  public InformedSearcher(Searchable initial, Searchable currentState,
      Searchable goal, Comparator comparator) {
    super(initial, currentState, goal, comparator);
    // TODO Auto-generated constructor stub
  }

  public InformedSearcher(Searchable initial, Searchable goal,
      Comparator comparator) {
    super(initial, goal, comparator);
    // TODO Auto-generated constructor stub
  }
  
  public void initializeSearch() {
   initializeSearch(initialState.getComparator());
  }

  
  public void initializeSearch(Comparator<Searchable> comparator) {
    open = new SearchSet(comparator);
    closed = new HashSet<Searchable>();
    open.add(initialState);
  }
  
  /* (non-Javadoc)
   * @see edu.ohiou.labimp.spacesearch.SpaceSearcher#runOptSpaceSearch(int)
   */
  @Override
  public Searchable runOptSpaceSearch(int numberOfSteps) {
    // TODO Auto-generated method stub
    return super.runOptSpaceSearch(numberOfSteps);
  }

  /* (non-Javadoc)
   * @see edu.ohiou.labimp.spacesearch.SpaceSearcher#runSpaceSearch(int)
   */
  @Override
  public Searchable runSpaceSearch(int numberOfSteps) {
 
    System.out.println(open.comparator());
     try {
//    	 BY JING HUANG''''''''''''''''''''''''''''''''''''''''''''
//       if (currentState!= goalState)
//         ((Viewable)currentState).setColor (CLOSED_COLOR);
//       BY JING HUANG''''''''''''''''''''''''''''''''''''''''''''       
    } catch (NullPointerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    if (isSearchComplete(this)) {
      if (hasReachedGoal) 
         return goalState;
      else
         return null;
    }
    closed.add(currentState);

    Set newStates = (Set) currentState.makeNewStates();

    System.out.println("current states: " + currentState);
    System.out.println("new states: " + newStates);
    System.out.println("new states size: " + newStates.size());

    
    removeOldStates(newStates, open); // needed for jtree
    removeOldStates(newStates, closed);
    children = new LinkedList(newStates);
    open.addAll(children);
     
    System.out.println("open size:" + open.size());
    System.out.println("closed size:" + closed.size());
//     BY JING HUANG'''''''''''''''''''''''''''''''''''''''''''''
//    try {
//		(new TSPSampleGenerator()).appendFile(sampleInfo+ "1.csv",closed.size()+","+open.size());
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
// BY JING HUANG''''''''''''''''''''''''''''''''''''''''''''' 
    
    if (numberOfSteps <= 1) {
      return currentState;
    } 
    else {
      numberOfSteps--;
      try {
      return runSpaceSearch(numberOfSteps);
      }
      catch (RuntimeException rte) {
    	  try { 
    		TSPSampleGenerator.genFile("exception.txt");
			TSPSampleGenerator.appendFile(TSPSampleGenerator.getfileName(2, "exception.txt"), rte.toString()  );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rte.printStackTrace();
		return null;
      }
    }
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
