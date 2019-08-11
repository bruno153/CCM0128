/***********************************************
	This program performs the doubling test 
	for the NutsAndBolts java program.
	
	Compilation:
	javac-algs4 NutsBenchmark.java
	
	Execution:
	java-algs4 NutsBenchmark
************************************************/

import edu.princeton.cs.algs4.*;
public class NutsBenchmark{
	public static void main (String[] args){
		Stopwatch time = new Stopwatch();
		
		for (int i = 2; true; i*=2){ //runs until the computer runs out of memory, I hope that's ok
			int[][] aiPile = NutsAndBoltsGenerator.generate(i);
			int[] aiNuts = aiPile[0];
			int[] aiBolts = aiPile[1];
			//StdOut.print ("Size = " + i);
			StdOut.print (i);
			double start = time.elapsedTime();
			NutsAndBolts.sortPiles (aiNuts, aiBolts, 0, aiNuts.length - 1);
			//StdOut.println (" Time = " + (time.elapsedTime() - start));
			StdOut.println (" " + (time.elapsedTime() - start));
		}
	}
}