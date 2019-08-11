/*******************************************************************************
 *  Compilation:  javac-algs4 MultipleWordSearcher.java
 *  Execution:    java-algs4 MultipleWordSearcher words words words < input.txt
 *
 *  This java program is able to search for multiple words from the 
 *	command line and search in a sequence of words in the standard input
 *
 *  % java FrequencyCounter It was silently acquiesced in and < dickens.txt
 *  It was silently acquiesced in and
 *
 *	% java FrequencyCounter momomo < dickens.txt
 *	No such words were found.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
public class MultipleWordSearcher{
	public static String[] search(String[] sText, String[] sWords){
		int iBesti = 0;
		int iBestj = sText.length - 1;
		int i = 0;
		int j = 1;
		boolean bFlag = false;
		RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		
		for (int k = 0; k < sWords.length; k++){ 					//initializes the counting symbol table
			st.put(sWords[k].replaceAll("[^A-Za-z]", ""), 0);
		}
		
		if (st.contains(sText[0])) 									//initializes the i and j pointers
			st.put(sText[0], 1);
		if (st.contains(sText[1]))
			st.put(sText[1], 1);
		
		while (j < sText.length - 1){ 								//starts sweeping the file
			while (!containsAll(st) && j < sText.length - 1){ 		//finds the j needed to get all the query
				j++;
				if (st.contains(sText[j])) 							//updates the frequences table
					st.put(sText[j], st.get(sText[j]) + 1);
			}
			if (((j - i) < (iBestj - iBesti)) && containsAll(st)){ 	//smallest the best sequence yet
				bFlag = true;
				iBesti = i;
				iBestj = j;
			}
			assert i <= j;
			if (st.contains(sText[i])) 								//updates the symbol table
					st.put(sText[i], st.get(sText[i]) - 1);
			i++;
		}
		
		if (!bFlag) 												//if the search fails, return null
			return null;
		
		String[] sAns = new String[(iBestj- iBesti + 1)]; 			//creates the output array
		
		for (int k = iBesti; k <= iBestj; k++){
			String sTmp = sText[k];
			sAns[k - iBesti] = sTmp;
		}
		return sAns;
	}
	
	public static boolean containsAll(RedBlackBST st){ 				//looks through symbol table to see if all words were present
		for (Object s: st.keys()){
			if (st.get((String)s) == 0)
				return false;
		}
		return true;
	}
	
	public static void main (String[] args){
		String[] sText = StdIn.readAllStrings();
		for (int k = 0; k < sText.length; k++){
			sText[k] = sText[k].replaceAll("[^A-Za-z]", "");
		}
		String[] ans = search(sText, args);
		if (ans == null){
			StdOut.println("No such words were found.");
		}
		else{
			for (String s : ans){
				StdOut.print(s + " ");
			}
			StdOut.println();
		}
	}
}