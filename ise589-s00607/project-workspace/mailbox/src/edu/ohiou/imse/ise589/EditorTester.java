package edu.ohiou.imse.ise589;

/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */

import edu.ohiou.labimp.draw.*;
import javax.swing.JFrame;
import javax.media.j3d.BranchGroup;

public class EditorTester {

  public EditorTester() {
  }
  public static void main(String[] args) {
    EditorTester editorTester1 = new EditorTester();
    try {
      JFrame frame = new JFrame("frame");
        frame.setVisible(true);
      LightEditorDialog dia = LightEditorDialog.createDialog(frame, "test 3f editor", true);
      dia.initializeDialog(new BranchGroup() );
      dia.show();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}