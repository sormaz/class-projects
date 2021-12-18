package edu.ohiou.ise.ise589;

import edu.ohiou.labimp.draw.ImpObject;

public class ImpObjectClass extends ImpObject {

	public ImpObjectClass() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImpObjectClass ioc = new ImpObjectClass();
			ioc.setApplet(new edu.ohiou.labimp.draw.AnimApplet (ioc));
		ioc.display();
	}

}
