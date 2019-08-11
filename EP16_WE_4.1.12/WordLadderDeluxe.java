/***************************************************************************************
 *  Compilation:  		javac-algs4 WordLadderDeluxe.java
 *  Execution example:	java-algs4  WordLadderDeluxe physics happiness < words.utf-8.txt
 *  Dependencies: 		StdOut.java StdIn.java Queue.java QuickX.java Graph.java 
 *						BreadthFirstPaths.java
 *  
 *  Uses graph and many other stuff to see if it's possible to assemble a word ladder
 *	between two words given from the command line.
 *
 **************************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
public class WordLadderDeluxe{
	private static class CycleString implements Comparable <CycleString>{
		private int index;
		private String string;
		
		public CycleString (){ 									//constructors
			string = null;
			index = 0;
		}
		public CycleString (String string){
			this.string = string;
			index = 0;
		}
		public CycleString (String string, int i){
			this.string = string;
			if (i >= string.length())
				throw new NoSuchElementException("Index out of bounds");
			index = i;
		}
		public String toString(){ 								//converts to string
			return string;
		}
		public void index(int i){ 								//sets a index
			if (i >= string.length())
				throw new NoSuchElementException("Index out of bounds");
			this.index = i;
		}
		public void index(){ 									//increments a index by 1
			this.index++;
			if (index >= string.length())
				index = 0;
		}
		public int length(){ 									//returns length
			return string.length();
		}
		public String lastLetter(){ 							//returns the last letter
			return getIndex(string.length() - 1);
		}
		public String getIndex(int i){ 							//get a letter based on the index and cyclic rule
			assert i >=0;
			if (i >= string.length())
				throw new NoSuchElementException("Index out of bounds");
			if (i + index < string.length())
				return string.substring(i + index, i + index + 1);
			else
				return string.substring(i + index - string.length(), i + index - string.length() + 1);
		}
		public int compareTo(CycleString that){
			if (this.length() > that.length()) 					//by size
				return 1;
			if (this.length() < that.length())
				return -1;
			for (int i = 0; i < this.length(); i++){ 			//by letter
				if (this.getIndex(i).compareTo(that.getIndex(i)) >= 1)
					return 1;
				if (this.getIndex(i).compareTo(that.getIndex(i)) <= -1)
					return -1;
			}
			return 0;
		}
		public boolean difByOne(CycleString that){ 
			if (this.length() != that.length())
				return false;
			for (int i = 0; i < this.length() - 1; i++){		//by letter
				if (!this.getIndex(i).equals(that.getIndex(i)))
					return false;
			}
			if (!this.getIndex(this.length() - 1).equals(that.getIndex(that.length() - 1)))
				return true;
			return false;
		}
	}
	
	public static boolean difByOne(String s1, String s2){		//checks if the words with differents lengths can be connected
		if (s1.length() > s2.length()){ 						//makes s1 the smallest string
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		if (s2.length() - s1.length() != 1) 					//returns false if the difference isn't by one
			return false;
		for (int i = 0; i < s1.length(); i++){
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		}
		return true;
	}
	
	public static boolean hasChance(String s1, String s2){
		for (int i = 0; i < s1.length() && i < s2.length(); i++){
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		}
		return true;
	}
	
	public static void main (String[] args){
		StdOut.println("Reading the dictionary...");
		String[] sTmp = StdIn.readAllStrings();
		CycleString[] dictionary = new CycleString[sTmp.length];
		for (int i = 0; i < dictionary.length; i++){ 								//creates the CycleString dictionary
			dictionary[i] = new CycleString(sTmp[i]);
		}
		
		StdOut.println("Preparing data...");
		Graph graph = new Graph(dictionary.length);
		RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		QuickX.sort(dictionary); 													//sorts the dictionary by size
		QuickX.sort(sTmp);
		
		for (int i = 0; i < dictionary.length; i++){ 								//creates symbol table to link string to graph
			st.put((dictionary[i]).toString(), i);
		}
		
		if (st.get(args[0]) == null) 												//verifies if the given words does exist
			throw new NoSuchElementException(args[0] + " does not exist on the given dictionary.");
		if (st.get(args[1]) == null)
			throw new NoSuchElementException(args[1] + " does not exist on the given dictionary.");
		
		StdOut.println("Generating graph...");
		StdOut.println("Similar sized words:");
		int l = 0;
		for (int size = 2, i = 0; i < dictionary.length; size++){ 					//links similar sized words
			StdOut.printf("%.3f", 100.0 * i/sTmp.length);
			StdOut.println("% complete...");
			Queue<CycleString> queue = new Queue<CycleString>();
			for (;i < dictionary.length && dictionary[i].length() < size; i++){ 	//enqueue similiar sized words
				queue.enqueue(dictionary[i]);
			}
			CycleString[] tmp = new CycleString[queue.size()];
			for (int k = 0; !queue.isEmpty(); k++){ 								//puts them into a array
				tmp[k] = new CycleString((queue.dequeue()).toString());
			}
			for (int k = 0; k < size; k++){ 										//searches for words that differs by one letter
				QuickX.sort(tmp);
				for (int j = 0; j < tmp.length - 1; j++){
					for (int r = j + 1; r < tmp.length - 1 && tmp[j].difByOne(tmp[r]); r++){
						//StdOut.println(tmp[j] + " " + tmp[j+1]);					//add edge to graph														
						graph.addEdge(st.get(tmp[j].toString()), st.get(tmp[r].toString())); 			 
					}
					tmp[j].index(); 												//cycle the string
				}
				if (tmp.length > 1)
					tmp[tmp.length - 1].index(); 									//cycle the last word
			}
		}
		
		StdOut.println("Different sized words...");
		l = 0;
		for (int i = 0; i < sTmp.length - 1; i++){
			int j = i + 1;
			while (j < sTmp.length - 1 && hasChance(sTmp[i], sTmp[j])){
				if (((sTmp[j].length() - sTmp[i].length()) == 1)){
					graph.addEdge(st.get(sTmp[i]), st.get(sTmp[j]));
				}
				j++;
			}
			if (i / 50000 > l){
				StdOut.printf("%.3f", 100.0 * i/sTmp.length);
				StdOut.println("% complete...");
				l++;
			}
		}
		for (CycleString s: dictionary){ 											//resets the cycle on the CycleStrings
			s.index(0);
		}
		BreadthFirstPaths look = new BreadthFirstPaths(graph, st.get(args[0])); 	//starts the breadth first search
		if (look.hasPathTo(st.get(args[1]))){
			for (int i : look.pathTo(st.get(args[1]))){
				StdOut.println(dictionary[i]);
			}
		}
		else{
			StdOut.println("Such connection does not exist.");
		}
	}
}