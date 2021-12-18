package edu.ohiou.ise.ise589;

import java.util.Scanner;
import java.text.NumberFormat;
import static java.lang.Math.*;

public class CommandInterfaceClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String prompt = "Enter number \\of operation that \"you want to \\\\execute:\n" +
		"\t1 sin(x)\n" + "\t2 cos(x)\n" + "\t3 ta\\\n(x)\n" + "\t4 asin(x)\n" +
		"\t5 acos(x)\n" + "\t6 atan(x)\n" + "\t7 sqrt(x)\n" + "\t8 power(x, e)\n" +
		"Select->";
		String argPrompt = "Enter argument value (x):->";
		String expPrompt = "Enter expone value (e):->";
		Scanner scanner= null; // = new Scanner (System.in);
		int maxChoice = 8;
		
		while (true) {
			System.out.print(prompt);
			String response = scanner.nextLine();
			if (response.equalsIgnoreCase("Exit") || 
					response.equalsIgnoreCase("Quit")) {
				break;
			}
			
			int choice;
			try {
				choice = Integer.parseInt(response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.err.println("\tEnter only numbers!!");
				continue;
			}
			if (choice >maxChoice || choice < 1) {
				System.err.println("\tThis choice is not supported!!");
				continue;
			}
			
			double myArg;
			while(true) {
				try {
					System.out.print(argPrompt);
					myArg = Double.parseDouble(scanner.nextLine());
					break;
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.err.println("\tEnter only numbers for argument!!");
				}
				
			}
			double result = 0.0;
			switch (choice) {
				case 1:
					result = sin(myArg * PI/180);
					break;
				case 2:
					result = cos(myArg * PI/180);
					break;
				case 3:
					result = tan(myArg * PI/180);
					break;
				case 4:
					result = asin(myArg);
					break;
				case 5:
					result = acos(myArg);
					break;
				case 6:
					result = atan(myArg);
					break;
				case 7:
					result = sqrt(myArg);
					break;
				case 8:
					double exponent;
					System.out.print(expPrompt);
					while (true) {
						String expString = scanner.nextLine();
						try {
							exponent = Double.parseDouble(expString);
							break;
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							System.err.println("Enter numbers only for exponent!");
						}						
					}
					result = pow(myArg, exponent);
					break;
				default:
					System.err.println("\tThis choice is not supported!!");
					break;
			}
			NumberFormat format = NumberFormat.getInstance();
			System.out.println ("Result is: " + format.format(result));
		}
	}
}
