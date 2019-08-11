/******************************************************
	This program solves the Creative Problem 2.3.13
	by using a modified quicksort algorithm.
	
	Compilation:
	javac-algs4 NutsAndBolts.javac-algs4
	
	Execution example:
	java-algs4 NutsAndBolts < BigPileOfNuts.txt
*******************************************************/



import edu.princeton.cs.algs4.*;
public class NutsAndBolts{
	public static int quickSort (int iSrtElm, int[] aiPile, int iLow, int iHigh){
		if(iHigh <= iLow){ //end if the the pointers are already crossed (better safe than sorry)
			return -1;
		}
		for (int k = iLow; k <= iHigh; k++){ //puts the only matching bolt or nut on the first index, to avoid three way partitioning
			if (aiPile[k] == iSrtElm){
				int iTmp = aiPile[iLow];
				aiPile[iLow] = aiPile[k];
				aiPile[k] = iTmp;
				break;
			}
		}
		int i = iLow; //low pointer
		int j = iHigh + 1; //high pointer
		
		while (true){ //classic quick sorting algorithm
			while (aiPile[++i] < iSrtElm){
				if (i == iHigh)
					break;
			}
			while (aiPile[--j] > iSrtElm){
				if (j == iLow)
					break;
			}
			if (i >= j) //checks if pointers are crossed
				break;
			
			int iTmp = aiPile[i]; //exchange elements
			aiPile[i] = aiPile[j];
			aiPile[j] = iTmp;
		}
		
		int iTmp = aiPile[iLow]; //exchange the sorting element to the correct place
		aiPile[iLow] = aiPile[j];
		aiPile[j] = iTmp;
		
		return j; //return the sorting index
		
	}
	
	public static void sortPiles (int[] aiNuts, int[] aiBolts, int iLow, int iHigh){
		if (iHigh <= iLow){ //if the pointers are crossed, end recursion
			return;
		}
		
		int iSrtIndx = quickSort (aiNuts[iLow], aiBolts, iLow, iHigh); //sorts the array and get the sorting element index
		quickSort (aiBolts[iSrtIndx], aiNuts, iLow, iHigh); //uses the previous sorting element on this function
		
		sortPiles (aiNuts, aiBolts, iLow, iSrtIndx - 1); //recursive call
		sortPiles (aiNuts, aiBolts, iSrtIndx + 1, iHigh);
	}
	
	public static void main (String[] args){
		int[] aiNuts = new int[StdIn.readInt ()]; //get input from the standard input
		for (int i = 0; i < aiNuts.length; i++){
			aiNuts[i] = StdIn.readInt ();
		}
		int[] aiBolts = new int[StdIn.readInt ()];
		for (int i = 0; i < aiBolts.length; i++){
			aiBolts[i] = StdIn.readInt ();
		}
		
		sortPiles (aiNuts, aiBolts, 0, aiNuts.length - 1); //call the sorting function
		
		for(int i : aiNuts){ //prints the outputs
			StdOut.print(i + " ");
		}
		StdOut.println();
		for(int i : aiBolts){
			StdOut.print(i + " ");
		}
	}
}