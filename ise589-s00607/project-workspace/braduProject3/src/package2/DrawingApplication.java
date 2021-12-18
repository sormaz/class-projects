package package2;

import java.util.*;


public class DrawingApplication{
	public static void main (String[] args){
		DrawingViewer drawView=new DrawingViewer();
		Scanner input=new Scanner(System.in);
		String commandSelection="Select:\n"+"\tlist, printout, delete, triangle, circle, star, square, image, quit, display, draw:";
		
		while(true){
			System.out.print(commandSelection);
			String selection=input.nextLine();
			
			if(selection.equalsIgnoreCase("quit")){
				System.exit(0);
				}
			
			else if(selection.equalsIgnoreCase("list")){
				drawView.list();
				continue;
				}
			
			else if(selection.equalsIgnoreCase("triangle")){
				System.out.println("What is the x coordinate of the first corner?");
				double xTriangle=input.nextDouble();
				System.out.println("What is the y coordinate of the first corner?");
				double yTriangle=input.nextDouble();
				System.out.println("What is the length of base of the triangle?");
				double baseTriangle=input.nextDouble();
				System.out.println("What is the height of the triangle?");
				double heightTriangle=input.nextDouble();
				Triangle t=new Triangle (xTriangle,yTriangle,baseTriangle,heightTriangle);
				drawView.add(t);
				}
			
			else if(selection.equalsIgnoreCase("circle")){
				System.out.println("What is the x coordinate of the center?");
				double xCenter=input.nextDouble();
				System.out.println("What is the y cooredinate of the center?");
				double yCenter=input.nextDouble();
				System.out.println("What is the radius of the circle?");
				double radius=input.nextDouble();
				Circle c=new Circle(xCenter,yCenter,radius);
				drawView.add(c);
			}
			
			else if(selection.equalsIgnoreCase("star")){
				System.out.println("What is the x coordinate of the center of the enclosing circle?");
				double xStar=input.nextDouble();
				System.out.println("What is the y coordinate of the center of the enclosing circle?");
				double yStar=input.nextDouble();
				System.out.println("What is the radius of the enclosing circle?");
				double rStar=input.nextDouble();
				System.out.println("What is the number of vertices?");
				int vNumber=input.nextInt();
				System.out.println("What is the density of the star polygon?"+"\t!!!usually v and d must be relatively prime numbers and d < n/2!!!");
				int dNumber=input.nextInt();
				Star s=new Star(xStar,yStar, rStar, vNumber,dNumber);
				drawView.add(s);
			}
			
			else if(selection.equalsIgnoreCase("square")){
				System.out.println("What is the x coordinate of the first corner?");
				double xSquare=input.nextDouble();
				System.out.println("What is the y coordinate of the first corner?");
				double ySquare=input.nextDouble();
				System.out.println("What is the length of the sides?");
				double sides=input.nextDouble();
				Square sq=new Square(xSquare,ySquare,sides);
				drawView.add(sq);				
			}
			
			else if(selection.equals("image")){
				System.out.println("What is the x coordinate of the center of image?");
				double xImage=input.nextDouble();
				System.out.println("What is the y coordinate of the center of image?");
				double yImage=input.nextDouble();
				System.out.println("What is the file name to be opened?");
				String fileName=input.next();
				System.out.println("What is the scale factor?");
				double sc=input.nextDouble();
				Image i=new Image(xImage, yImage, fileName, sc);
				drawView.add(i);
			}
			
			else if(selection.equalsIgnoreCase("printout")){
				System.out.println("Enter the index of the object to be printed:");
				int index=input.nextInt();
				drawView.get(index).printout();
			}
			
			else if(selection.equalsIgnoreCase("delete")){
				System.out.println("Enter the index of the object to be deleted:");
				int index=input.nextInt();
				drawView.delete(index);
			}
			
			else if(selection.equalsIgnoreCase("display")){
				System.out.println("Enter the index of the object to be displayed");
				int index=input.nextInt();
				drawView.get(index).display();			
				}
			else if(selection.equalsIgnoreCase("draw")){
				System.out.println("Enter the index of the object to be drawn");
				int index=input.nextInt();
				drawView.get(index).draw();
			}
			}
		}
	}