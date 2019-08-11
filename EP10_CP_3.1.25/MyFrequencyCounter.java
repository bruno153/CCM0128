/*********************************************************
	This java program uses SequencialSearchST, 
	SequencialSearchCacheST, BinarySearchST,
	BinarySearchCacheST to count the words on the StdIn.
	
	Usage:
	First argument: Minimum amount of letters on the 
	counted word.
		Example: 3
		
	Second argument: Selects whether the program uses 
	cache or not. Type 0 for without cache, 1 with cache.
	
	Third argument: Selects the used algorithm. Type 
	sequential for sequential algorithm or binary for
	binary search algorithm.
	
	Compiation:
	javac-algs4 MyFrequencyCounter.java 
	
	Execution example:
	java-algs4 MyFrequencyCounter 1 1 sequential < tale.txt
	
*********************************************************/

import edu.princeton.cs.algs4.*;
public class MyFrequencyCounter{
	public static void count (int iMinlen) {
        int distinct = 0, words = 0;
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	public static void count2 (int iMinlen) {
        int distinct = 0, words = 0;
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	
	public static void countCache (int iMinlen) {
        int distinct = 0, words = 0;
		SequentialSearchCacheST<String, Integer> st = new SequentialSearchCacheST<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	
	public static void countCache2 (int iMinlen) {
        int distinct = 0, words = 0;
		BinarySearchCacheST<String, Integer> st = new BinarySearchCacheST<String, Integer>();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < iMinlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
	
	public static void main (String[] args){
		if (args[2].equals("sequential")){
			StdOut.print ("Starting with sequencial algorithm,");
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
		else{
			StdOut.print ("Starting with binary search algorithm,");
			if (args[1].equals("0")){
				StdOut.println (" without cache.");
				Stopwatch time = new Stopwatch();
				double start = time.elapsedTime();
				count2(Integer.parseInt(args[0]));
				StdOut.println ("It took " + (time.elapsedTime() - start) + " seconds!");
			}
			else{
				StdOut.println (" with cache.");
				Stopwatch time = new Stopwatch();
				double start = time.elapsedTime();
				countCache2(Integer.parseInt(args[0]));
				StdOut.println ("It took " + (time.elapsedTime() - start) + " seconds!");
			}
		}
	}
}