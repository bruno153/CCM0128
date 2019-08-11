/****************************************************************
 *  Compilation:  		javac-algs4 BridgeDetector.java
 *  Execution example:	java-algs4  BridgeDetector < graph.txt
 *  Dependencies: 		Graph.java Digraph.java StdIn.java 
 *	KosarajuSharirSCC.java
 *
 *	This program receives a graph from the Standard Input, and 
 *	prints the bridges on the Standard Output.
 *
****************************************************************/

import edu.princeton.cs.algs4.*;
public class BridgeDetector{
	private static Digraph direct(Graph graph){
		Digraph digraph = new Digraph(graph.V());
		boolean[] bMarked = new boolean[graph.V()];
		return direct(0, digraph, graph, bMarked);		//performs a DFS to transfer all vertices to digraph
	}
	private static Digraph direct(int i, Digraph digraph, Graph graph, boolean[] bMarked){ 	//recursive function
		bMarked[i] = true; 																	//mark the vertice
		for (int j : graph.adj(i)){ 														//iterate through all connected vertices
			boolean bFlag = true;
			for (int k : digraph.adj(j)){ 													//only makes a connection if it wasn't already made
				if (k == i)
					bFlag = false;
			}
			if (bFlag) 
				digraph.addEdge(i, j);
			if (!bMarked[j]) 																//recursive call
				direct(j, digraph, graph, bMarked);
		}
		return digraph;
	}
	public static void main(String[] args){
		Graph graph = new Graph(StdIn.readInt());
		for (int i = StdIn.readInt(); i > 0; i--){ 											//reads the input for edges
			graph.addEdge(StdIn.readInt(), StdIn.readInt());
		}
		Digraph digraph = direct(graph);
		StdOut.print(digraph);
		KosarajuSharirSCC kosajinho = new KosarajuSharirSCC (digraph); 						//searches for the strongly connected components
		for (int i = 0; i < digraph.V(); i++){
			for (int j : digraph.adj(i)){
				if (kosajinho.id(j) != kosajinho.id(i))
					StdOut.println(i + " " + j);
			}
		}
	}
}