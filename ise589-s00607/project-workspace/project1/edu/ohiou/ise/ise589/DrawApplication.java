package edu.ohiou.ise.ise589;

import java.util.*;
import java.io.*;

public class DrawApplication {
	
	DrawViewer viewer = new DrawViewer();
	
	public  void run (InputStream inputStream) {
		// TODO Auto-generated method stub
		String prompt = "list, printout, delete, triangle, circle, star, square, image, quit->";
		String indexPrompt = "Enter index number(n)->";
		Scanner scanner = new Scanner (inputStream);
			
		while (true) {
			System.out.print(prompt);
			String response = scanner.nextLine();
			if (response.equalsIgnoreCase("list")) {
				viewer.listObjects();
			} 
			else if (response.equalsIgnoreCase("printout")) {
				viewer.listObjects();
				System.out.print(indexPrompt);
				int index = scanner.nextInt();
				viewer.findObject(index-1).show();
			}
			else if (response.equalsIgnoreCase("delete")) {
				viewer.listObjects();
				System.out.print(indexPrompt);
				int index = scanner.nextInt();
				viewer.deleteObject(index-1);
			}
			else if (response.equalsIgnoreCase("triangle")) {
				while (true) {
					System.out.println ("Enter x, y, base and height (blank separated):");
					String data = scanner.nextLine();
					if (data.length()==0) {
						break;
					}
					StringTokenizer tokenizer = new StringTokenizer(data);
					try {

						double x = Double.parseDouble(tokenizer.nextToken());
						double y = Double.parseDouble(tokenizer.nextToken());
						double b = Double.parseDouble(tokenizer.nextToken());
						double h = Double.parseDouble(tokenizer.nextToken());

						viewer.addObject(new Triangle(x, y, b, h));
						break;
					} catch (NoSuchElementException e) {
						System.out.println("You have to enter four numbers.");
					} catch (NumberFormatException e) {
						System.out.println("You have to enter numbers only.");
					}
					catch (DrawObjectException e) {
						// TODO Auto-generated catch block

						System.out.println(e.getMessage() +" Try again." );
					}
				}
			}
			else if (response.equalsIgnoreCase("circle")) {
				while (true) {
					System.out.println ("Enter x, y, and radius (blank separated):");
					String data = scanner.nextLine();
					if (data.length()==0) {
						break;
					}
					StringTokenizer tokenizer = new StringTokenizer(data);
					try {

						double x = Double.parseDouble(tokenizer.nextToken());
						double y = Double.parseDouble(tokenizer.nextToken());
						double r = Double.parseDouble(tokenizer.nextToken());

						viewer.addObject(new Circle(x,y,r));
						break;
					} catch (NoSuchElementException e) {
						System.out.println("You have to enter three numbers.");
					} catch (NumberFormatException e) {
						System.out.println("You have to enter numbers only.");
					}
					catch (DrawObjectException e) {
						// TODO Auto-generated catch block

						System.out.println(e.getMessage() +" Try again." );
					}
				}
			}
			else if (response.equalsIgnoreCase("star")) {
				while (true) {
					System.out.println ("Enter x, y, radius and number of sides (blank separated):");
					String data = scanner.nextLine();
					if (data.length()==0) {
						break;
					}
					StringTokenizer tokenizer = new StringTokenizer(data);
					try {

						double x = Double.parseDouble(tokenizer.nextToken());
						double y = Double.parseDouble(tokenizer.nextToken());
						double r = Double.parseDouble(tokenizer.nextToken());
						int s = Integer.parseInt(tokenizer.nextToken());

						viewer.addObject(new Star(x, y, r, s));
						break;
					} catch (NoSuchElementException e) {
						System.out.println("You have to enter three numbers.");
					} catch (NumberFormatException e) {
						System.out.println("You have to enter numbers only.");
					}
					catch (DrawObjectException e) {
						// TODO Auto-generated catch block

						System.out.println(e.getMessage() +" Try again." );
					}
				}
			}
			else if (response.equalsIgnoreCase("square")) {
				while (true) {
					System.out.println ("Enter x, y, and side (blank separated):");
					String data = scanner.nextLine();
					if (data.length()==0) {
						break;
					}
					StringTokenizer tokenizer = new StringTokenizer(data);
					try {

						double x = Double.parseDouble(tokenizer.nextToken());
						double y = Double.parseDouble(tokenizer.nextToken());
						double s = Double.parseDouble(tokenizer.nextToken());

						viewer.addObject(new Square(x,y,s));
						break;
					} catch (NoSuchElementException e) {
						System.out.println("You have to enter three numbers.");
					} catch (NumberFormatException e) {
						System.out.println("You have to enter numbers only.");
					}
					catch (DrawObjectException e) {
						// TODO Auto-generated catch block

						System.out.println(e.getMessage() +" Try again." );
					}
				}
			}
			else if (response.equalsIgnoreCase("image")) {
				while (true) {
					System.out.println ("Enter x, y, scale, and file name (blank separated:");
					String data = scanner.nextLine();
					if (data.length()==0) {
						break;
					}
					StringTokenizer tokenizer = new StringTokenizer(data);
					try {

						double x = Double.parseDouble(tokenizer.nextToken());
						double y = Double.parseDouble(tokenizer.nextToken());
						double scale = Double.parseDouble(tokenizer.nextToken());
						String name = tokenizer.nextToken();

						viewer.addObject(new Image(x, y, scale, name));
						break;
					} catch (NoSuchElementException e) {
						System.out.println("You have to enter three values.");
					} catch (NumberFormatException e) {
						System.out.println("You have to enter numbers only for x and y.");
					}
					catch (DrawObjectException e) {
						// TODO Auto-generated catch block

						System.out.println(e.getMessage() +" Try again." );
					}
				}
			}
			else if (response.equalsIgnoreCase("Exit") || 
					response.equalsIgnoreCase("quit")) {
				System.exit(0);
			}
			else {
				System.out.println("Your command is not supported");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawApplication drawApplication = new DrawApplication();
			InputStream input;
		if (args.length > 0) {
			try {
				input = new FileInputStream (new File (args[0]));
			} catch (FileNotFoundException e) {
				input = System.in;
			}
		} else {
			input = System.in;
		}

		drawApplication.run(input);
//		drawApplication.viewer.display();
	}
}


