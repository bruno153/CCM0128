/****************************************
  This object class is used to represent
  points in a cartesian coordinates.
****************************************/

import edu.princeton.cs.algs4.*;
public class point{
	private double x;
	private double y;
	
	public point (double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double cosine (point other){
		return (other.x - x)/(this.distance (other));
	}
	
	public double distance (point other){
		return Math.sqrt (Math.pow (x - other.x, 2) + Math.pow (y - other.y, 2));
	}
	
	public double getX (){
		return x;
	}
	
	public double getY (){
		return y;
	}
	
	public void line (point other){
		StdDraw.line(x, y, other.x, other.y);
	}
	
	public void point (){
		StdDraw.setPenRadius(0.05);
		StdDraw.point(x, y);
		StdDraw.setPenRadius(0.025);
	}
	
	public String toString (){
		return "(" + x + ", " + y + ")";
	}
}