package edu.ohiou.ise.ise589;

import java.util.Scanner;

public class AdrianErrorClass {

	public static void main (String[] args){
		Scanner input=new Scanner(System.in);
		int intInput;
		double argument;
		String commandSelection="Select the function to execute:\n"+"\t1 sin(x)\n"+"\t2 cos(x)\n"+"\t3 tan(x)\n"+"\t4 asin(x)\n"+"\t5 acos(x)\n"+"\t6 atan(x)\n"+"\t7 sqrt(x)\n"+"\t8 pow(x,a)\n"+"Select:";

		while(true){
			System.out.print(commandSelection);
			String action=input.nextLine();
			if(action.equalsIgnoreCase("Quit")|| action.equalsIgnoreCase("Exit")){
				System.exit(0);
			}
			try {
				intInput=Integer.parseInt(action);
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("\tEnter only integer numbers between 1 and 8!!");
				continue;
			}
			if (intInput>8 ||intInput<1 ){
				System.out.println("\tChoice must be positive and between 1 and 8!!");
			}
			switch(intInput){
			case 1:
				System.out.println("\tEnter the argument value(angle in degrees):");
				String argumentString=input.nextLine();
				try {
					argument=Double.parseDouble(argumentString);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("\tThe argument is the value in degrees of the angle");
					continue;
				}
			}

		}

	}
}



