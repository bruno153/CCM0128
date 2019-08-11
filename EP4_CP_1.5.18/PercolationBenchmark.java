/***************************************************
  This program receives an int (N) from the command
  line and generates a N x N sized grid. Using 
  RandomBag.java and WeightedQuickUnionUF.java
  this program adds a random connection and verifies
  if it can percolate. Then prints the number of 
  random connections needed to fully percolate.
  
  Compilation:
  javac-algs4 PercolationBenchmark.java
  
  Execution example:
  java-algs4 PercolationBenchmark 1000
  
***************************************************/


import edu.princeton.cs.algs4.*;
public class PercolationBenchmark{
	public static void main (String[] args){
		int N = Integer.parseInt (args[0]);
		String[] connections = RandomGrid.gimmeConnection (N);
		WeightedQuickUnionUF onion = new WeightedQuickUnionUF (N * N + 2);
		
		//generates the unions
		for (int i = 0; i < N; i++){
			onion.union (N*N, i);
			onion.union (N * N + 1, N * (N - 1) + i);
		}
		int i;
		for (i = 0; !onion.connected (N*N, N*N+1); i++){
			String[] integers = connections[i].split (" ");
			onion.union (Integer.parseInt (integers[0]), Integer.parseInt (integers[1]));
		}
		StdOut.println ("It's done: " + i + " connections were needed!");
	}
}