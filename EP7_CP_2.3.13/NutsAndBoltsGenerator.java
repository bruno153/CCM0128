/*********************************************
	This program is able to create an input
	for the Creative Problem 2.3.13 Nuts and 
	Bolts.
	
	Compilation:
	javac-algs4 NutsAndBoltsGenerator.javac-algs4
	
	Execution example:
	java-algs4 NutsAndBoltsGenerator 20
*********************************************/

import edu.princeton.cs.algs4.*;
public class NutsAndBoltsGenerator {
	public static int[][] generate (int iSize){
		int[] iNuts = new int[iSize];
		int[] iBolts = new int[iSize];
		for (int i = 0; i < iSize; i++){
			iNuts[i] = i;
			iBolts[i] = i;
		}
		StdRandom.shuffle (iNuts);
		StdRandom.shuffle (iBolts);
		
		int[][] aiReturn = new int[2][iSize];
		aiReturn[0] = iNuts;
		aiReturn[1] = iBolts;
		return aiReturn;
	}
	public static void main (String[] args){
		int iSize = Integer.parseInt (args[0]); //get size
		int[][] aiArray = generate (iSize);
		StdOut.println (iSize);
		for (int i : aiArray[0]){
			StdOut.print (i + " ");
		}
		StdOut.println ();
		StdOut.println (iSize);
		for (int i : aiArray[1]){
			StdOut.print (i + " ");
		}
	}
}