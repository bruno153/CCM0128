/****************************************************************
  This program is able to draw a polygon based on
  the points supplied by the user. The input format
  is as follows:
  
  (number of vertix)
  (x coordinate of first point) (y coordinate of first point)
  (x coordinate of second point) (y coordinate of second point)
  ...
  
  Compile: 
  javac-algs4 Polygon.java
  
  Execution example:
  java-algs4 Polygon < heptagon.txt


****************************************************************/
import edu.princeton.cs.algs4.*;
public class Polygon{
	public static void main (String[] args){
		point[] polygon= new point[StdIn.readInt()]; 
		double smallX = Double.POSITIVE_INFINITY; //variables used to set the StdDraw scale
		double smallY = Double.POSITIVE_INFINITY;
		double bigX = Double.NEGATIVE_INFINITY;
		double bigY = Double.NEGATIVE_INFINITY;
		int smallYIndex = 0; //Index of the lowest point
		for (int i = 0; i < polygon.length; i++){ //creates the point vector
			polygon[i] = new point (StdIn.readDouble (), StdIn.readDouble ()); //stores the point from StdIn
			if (polygon[i].getX ()<smallX)
				smallX = polygon[i].getX ();
			if (polygon[i].getX ()>bigX)
				bigX = polygon[i].getX ();
			if (polygon[i].getY ()<smallY){
				smallY = polygon[i].getY ();
				smallYIndex = i;
			}
			if (polygon[i].getY ()>bigY)
				bigY = polygon[i].getY ();
		}
		if (smallYIndex!=0){ //puts the lowest point on the first index
			point tmp = polygon[0];
			polygon[0] = polygon[smallYIndex];
			polygon[smallYIndex] = tmp;
		}
		for (int i = 2; i < polygon.length; i++){
			for (int j = i; j >= 2 && polygon[0].distance (polygon[j]) > polygon[0].distance (polygon[j - 1]); j--){
				point tmp = polygon[j-1];
				polygon[j-1] = polygon[j];
				polygon[j] = tmp;
			}
		}
		for (int i = 2; i < polygon.length; i++){ //sorts the rest of the points by the cosine value by insertion
			for (int j = i; j >= 2 && polygon[0].cosine (polygon[j]) < polygon[0].cosine (polygon[j - 1]); j--){
				point tmp = polygon[j-1];
				polygon[j-1] = polygon[j];
				polygon[j] = tmp;
			}
		}
		StdDraw.setYscale (smallY - 0.1 * (bigY - smallY), bigY + 0.1 * (bigY - smallY)); //resizes the screen
		StdDraw.setXscale (smallX - 0.1 * (bigX - smallX), bigX + 0.1 * (bigY - smallY));
		for (int i = 0; i < polygon.length - 1; i++){ //draws the polygon
			polygon[i].line(polygon[i+1]);
		}
		polygon[polygon.length-1].line (polygon[0]);
	}
}