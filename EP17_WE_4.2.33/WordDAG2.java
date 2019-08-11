/******************************************************************************
 *  Compilation:  javac WordDAG2.java
 *  Execution:    java WordDAG2 wordlist.txt
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class WordDAG2
{
	public static boolean isNeighbor(RotatedString a, RotatedString b)
	{
		assert a.length() == b.length();
		for (int i = 0; i < a.length() - 1; i++)
			if (a.charAt(i) != b.charAt(i))
				return false;

		if (a.charAt(a.length() - 1) != b.charAt(a.length() - 1))
			return true;
		else
			return false;
	}

	public static boolean isPrefixNeighbor(RotatedString a, RotatedString b)
	{
		if (b.length() - a.length() != 1)
			return false;

		return b.startsWith(a);
	}

	public static void addSameLengthEdges(Digraph G, RotatedString[] words, int start, int end, int wordSize, IndexSET<String> indexSet)
	{
		RotatedString.resetRotation();

		for (int k = 0; k < wordSize; k++)
		{
			Arrays.sort(words, start, end);

			for (int i = start; i < end - 1; i++)
			{
				RotatedString word1 = words[i];

				int j = 1;
				do
				{
					RotatedString word2 = words[i + j];

					if (word1.length() != word2.length())
						throw new RuntimeException("Words have different lengths");

					if (isNeighbor(word1, word2))
						G.addEdge(indexSet.indexOf(word2.originalString()), indexSet.indexOf(word1.originalString()));
					j++;
				}
				while (i + j < end && isNeighbor(word1, words[i + j]));
			}

			RotatedString.rotate();
		}
	}

	public static void main(String[] args)
	{
		In in = new In(args[0]);
		IndexSET<String> indexSet = new IndexSET<String>();

		while (!in.isEmpty())
		{
			String word = in.readString();
			indexSet.add(word);
		}

		int N = indexSet.size();
		RotatedString[] words = new RotatedString[N];
		for (int i = 0; i < N; i++)
			words[i] = new RotatedString(indexSet.keyOf(i));

		System.err.println("Finished reading word list");

		Digraph G = new Digraph(N);

		// palavras de mesmo tamanho
		Arrays.sort(words, RotatedString.separatedSizeOrder());
		for (int i = 0; i < N;)
		{
			int currentSize = words[i].length();
			int j = i;
			while (j < N && words[j].length() == currentSize)
				j++;
			addSameLengthEdges(G, words, i, j, currentSize, indexSet);
			i = j;
		}	
		RotatedString.resetRotation();
		
		// palavras de tamanho diferente
		Arrays.sort(words);
		for (int i = 0; i < N - 1; i++)
		{
			int j = i + 1;
			while (j < N && words[j].startsWith(words[i]))
			{
				if (isPrefixNeighbor(words[i], words[j]))
					G.addEdge(indexSet.indexOf(words[j].originalString()), indexSet.indexOf(words[i].originalString()));
				j++;
			}
		}

		System.err.println("Finished building graph");

	   /*******************************************************************
		*  Run breadth first search
		*******************************************************************/
		while (!StdIn.isEmpty())
		{
			String from = StdIn.readString();
			String to   = StdIn.readString();

			if (!indexSet.contains(from))
				throw new RuntimeException(from + " is not in word list");
			if (!indexSet.contains(to))
				throw new RuntimeException(to   + " is not in word list");

			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, indexSet.indexOf(from));

			if (bfs.hasPathTo(indexSet.indexOf(to)))
			{
				StdOut.println("length = " + bfs.distTo(indexSet.indexOf(to)));
				for (int v : bfs.pathTo(indexSet.indexOf(to)))
					StdOut.println(indexSet.keyOf(v));
			}
			else
				StdOut.println("NOT CONNECTED");
			
			StdOut.println("Counting paths...");
			PathCounter path = new PathCounter(G);
			StdOut.println("There are " + path.paths(indexSet.indexOf(from), indexSet.indexOf(to)) + " paths.");
			StdOut.println();
		}
	}
}