/****************************************************************
 *  Compilation:  		javac-algs4 CoreDetector.java
 *  Execution example:	java-algs4  CoreDetector core.txt
 *  Dependencies: 		Digraph.java KosarajuSharirSCC.java 
 *	Topological.java BreadthFirstDirectedPaths.java 
 *
 *	This program receives a directed graph through txt file on
 *	the command line, and prints the core vertices on the 
 *	digraph.
 *
****************************************************************/

import edu.princeton.cs.algs4.*;
public class CoreDetector{
	public static void main(String[] args){
		Digraph graph = new Digraph(new In(args[0])); 		//generates the digraph
		KosarajuSharirSCC strongComponents = new KosarajuSharirSCC(graph);
		Digraph kernel = new Digraph(strongComponents.count());
		for (int i = 0; i < graph.V(); i++){ 				//generates the kernel
			for (int j : graph.adj(i)){
				if (strongComponents.id(i) != strongComponents.id(j)){
					kernel.addEdge(strongComponents.id(i), strongComponents.id(j));
				}
			}
		}
		Topological top = new Topological(kernel); 			//sorts the kernel
		assert top.hasOrder();
		int coreCandidate = -1;
		for (int i : top.order()){ 							//gets the first vertice on the topological order
			coreCandidate = i;
			break;
		}
		assert coreCandidate >= 0;
		BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(kernel, coreCandidate);
		boolean isCore = true;
		for (int i = 0; i < kernel.V(); i++){ 				//verifies if the core candidate is indeed a core
			if (i != coreCandidate && !paths.hasPathTo(i))
				isCore = false;
		}
		if (isCore){
			StdOut.print("The core vertices are: ");
			for (int i = 0; i < graph.V(); i++){
				if (strongComponents.id(i) == coreCandidate)
					StdOut.print(i + " ");
			}
		}
		else{
			StdOut.println("There's no core vertices, better luck next time!");
		}
		StdOut.println();
	} 
}