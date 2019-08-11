/****************************************************************
 *  Compilation:  		javac-algs4 CEME_RPG.java
 *  Execution example:	java-algs4  CEME_RPG < Kingdom.txt
 *  Dependencies: 		EdgeWeightedDigraph.java StdIn.java 
 *	DijkstraSP.java Queue.java
 *
 *	This program receives a graph from the Standard Input, and 
 *	prints the bridges on the Standard Output.
 *
****************************************************************/
import edu.princeton.cs.algs4.*;
public class CEME_RPG{
	public static void main(String[] args){
		EdgeWeightedDigraph Kingdom = new EdgeWeightedDigraph(StdIn.readInt());
		int iRoutes = StdIn.readInt();
		int[] iHeroes = new int[StdIn.readInt()];
		for (int i = 0; i < iRoutes; i++){ 				//read the routes
			Kingdom.addEdge(new DirectedEdge(StdIn.readInt(), StdIn.readInt(), StdIn.readInt()));
		}
		for (int i = 0; i < iHeroes.length; i++){ 		//set the heroes position
			iHeroes[i] = StdIn.readInt();
		}
		DijkstraSP DoomTime = new DijkstraSP(Kingdom, StdIn.readInt()); 
		
		boolean[] NSFW = new boolean[Kingdom.V()]; 		//list of Not Safe For War cities
		
		for(int i : iHeroes){							//iterate per hero
			DijkstraSP AdventureTime = new DijkstraSP(Kingdom, i);
			for (int j = 0; j < Kingdom.V(); j++){		//iterate per city
				if(!NSFW[j] && DoomTime.distTo(j) <= AdventureTime.distTo(j)){
					NSFW[j] = true; 					//the city is no longer a rendez-vous candidate
				}
			}
		}
		Queue<Integer> SFW = new Queue<Integer>(); 		//queue for Safe for War cities
		for (int i = 0; i < Kingdom.V(); i++){
			if(!NSFW[i])
				SFW.enqueue(i);
		}
		if (SFW.isEmpty())
			StdOut.println("DEMISE OF THE KINGDOM");
		else{
			StdOut.println("VICTORY AND HAPPY EVER AFTER");
			StdOut.println(SFW.size());
			while (!SFW.isEmpty()){
				StdOut.print(SFW.dequeue() + " ");
			}
			StdOut.println();
		}
	}
}