package p1;
import java.util.Scanner;

public class UserInputClass {
	
	public static void main (String [] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter something, please");
		int i = scanner.nextInt();
		System.out.println ("You entered: " + i);
	}

}
