/************************************************
 This program uses ServerRoutine.java
 ServerRoutine2.java to compare both in terms of
 max load, doubling N each iteration.
 
 Example:
 java-algs4 ServerBenchmark
************************************************/
import edu.princeton.cs.algs4.*;
public class ServerBenchmark{ 
	public static void main(String[] args){
		StdOut.println("Routine 1 : Routine 2");
		int n = 1;
		while(true){ //continues until your poor computer runs out of memory :(
			StdOut.println(n*2 + " " + ServerRoutine.run(n*2, false) + "  :   " + ServerRoutine2.run(n*2, false));
			n *= 2;
		}
	}
}