package edu.ohiou.imse.ise589;

/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import java.awt.geom.CubicCurve2D;

import edu.ohiou.labimp.basis.Draw2DApplet;
import edu.ohiou.labimp.basis.ViewObject;
import java.util.LinkedList;


public class TestCubicCurve extends ViewObject{

  public TestCubicCurve() {
  }

  public LinkedList getDrawList () {
    LinkedList list = new LinkedList ();
    list.add (new CubicCurve2D.Double (0,0,50,50, 100,50, 200,0) );
    return list;
  }
  public static void main(String[] args) {
    TestCubicCurve tcc = new TestCubicCurve();
    tcc.setApplet(new Draw2DApplet(tcc));
    tcc.display();
  }
}
