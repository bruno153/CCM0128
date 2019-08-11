/***************************************************************
 *	Compilation: javac-algs4 PathCounter.java
 *	Execution: java-algs4 PathCounter graph.txt
 *
 **************************************************************/
import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
public class PathCounter{
	private Digraph digraph;
	public PathCounter(Digraph digraph){
		this.digraph = digraph;
	}
	public int paths(int s, int t){
		int[] topOrder = new int[digraph.V()];
		int[] graphOrder = new int[digraph.V()];
		int k = 0;
		Topological top = new Topological(digraph);
		if (topOrder == null){
			throw new RuntimeException("Given digraph is not a DAG.");
		}
		for (int i :top.order()){
			topOrder[k] = i;
			k++;
		}
		for (int i = 0; i < graphOrder.length; i++){ 
			graphOrder[topOrder[i]] = i;
		}
		
		int p1 = 0;
		int p2 = 0;
		for (int i = 0; i < topOrder.length; i++){
			if (s == topOrder[i])
				p1 = i;
			if (t == topOrder[i])
				p2 = i;
		}
		
		if (p1 >= p2){
			return 0;
		}
		int[] paths = new int[topOrder.length];
		paths[p1] = 1;
		for (int i = p1; i < p2; i++){ //starts iterating to all vertices on topological sort, counting how many paths are there
			for (int j : digraph.adj(topOrder[i])){
				paths[graphOrder[j]] = paths[graphOrder[j]] + paths[i];
			}
		}
		return paths[p2];
	}
	public static void main(String[] args){
		In in = new In(args[0]);
		Digraph digraph = new Digraph(in);
		StdOut.print("Insert the edges: ");
		StdOut.println("Number of paths: " + new PathCounter(digraph).paths(StdIn.readInt(), StdIn.readInt()));
	}
}