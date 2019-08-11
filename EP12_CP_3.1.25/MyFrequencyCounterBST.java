/*********************************************************
	This java program uses BST, 
	BSTCache to count the words on the StdIn.
	
	Usage:
	First argument: Minimum amount of letters on the 
	counted word.
		Example: 3
		
	Second argument: Selects whether the program uses 
	cache or not. Type 0 for without cache, 1 with cache.
	
	Compiation:
	javac-algs4 MyFrequencyCounter.java 
	
	Execution example:
	java-algs4 MyFrequencyCounter 1 1 < tale.txt
	
*********************************************************/

import edu.princeton.cs.algs4.*;
public class MyFrequencyCounterBST{
	public static void count (int iMinlen) {
        int distinct = 0, words = 0;
		BST<String, Integer> bst = new BST<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (bst.contains(key)) {
                bst.put(key, bst.get(key) + 1);
            }
            else {
                bst.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        bst.put(max, 0);
        for (String word : bst.keys()) {
            if (bst.get(word) > bst.get(max))
                max = word;
        }

        StdOut.println(max + " " + bst.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	
	public static void countCache (int iMinlen) {
        int distinct = 0, words = 0;
		BSTCache<String, Integer> bst = new BSTCache<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (bst.contains(key)) {
                bst.put(key, bst.get(key) + 1);
            }
            else {
                bst.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        bst.put(max, 0);
        for (String word : bst.keys()) {
            if (bst.get(word) > bst.get(max))
                max = word;
        }

        StdOut.println(max + " " + bst.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	
	public static void main (String[] args){
		StdOut.print ("Starting with BST algorithm,");
		if (args[1].equals("0")){
			StdOut.println (" without cache.");
			Stopwatch time = new Stopwatch();
			double start = time.elapsedTime();
			count(Integer.parseInt(args[0]));
			StdOut.println ("It took " + (time.elapsedTime() - start) + " seconds!");
		}
		else{
			StdOut.println (" with cache.");
			Stopwatch time = new Stopwatch();
			double start = time.elapsedTime();
			countCache(Integer.parseInt(args[0]));
			StdOut.println ("It took " + (time.elapsedTime() - start) + " seconds!");
		}
	}
}