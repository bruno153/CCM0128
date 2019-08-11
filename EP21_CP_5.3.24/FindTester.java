/**********************************************************
 *	Compilation: javac-algs4 Findtester.java
 *	Execution:	 java-algs4 FindTester pattern text
 *
 *	This java program is a separate unit test to test the
 *	findAll() on the following substring search algorithms:
 *		Brute.java, RabinKarp.java, BoyerMoore.java, 
 *		KMP.java.
 *
 *	Execution example: java-algs4 FindTester momo momomomomomo
 *
 *********************************************************/



import edu.princeton.cs.algs4.*;
public class FindTester{
	public static void main(String args[]){
		System.out.println(("text:    " + args[1]));
		System.out.println(Brute.search1(args[0], args[1]));
		System.out.println("Brute: ");
		for (int offset1a : Brute.findAll(args[0], args[1])){
			System.out.print("pattern: ");
			for (int i = 0; i < offset1a; i++)
				System.out.print(" ");
			System.out.println(args[0]);
		}
		System.out.println();
		System.out.println("Rabin: ");
		RabinKarp rabs = new RabinKarp(args[0]);
		for (int offset1a : rabs.findAll(args[1])){
			System.out.print("pattern: ");
			for (int i = 0; i < offset1a; i++)
				System.out.print(" ");
			System.out.println(args[0]);
		}
		System.out.println();
		System.out.println("Boyer-Moore: ");
		BoyerMoore boys = new BoyerMoore(args[0]);
		for (int offset1a : boys.findAll(args[1])){
			System.out.print("pattern: ");
			for (int i = 0; i < offset1a; i++)
				System.out.print(" ");
			System.out.println(args[0]);
		}
		System.out.println();
		System.out.println("KMP: ");
		KMP kaps = new KMP(args[0]);
		for (int offset1a : kaps.findAll(args[1])){
			System.out.print("pattern: ");
			for (int i = 0; i < offset1a; i++)
				System.out.print(" ");
			System.out.println(args[0]);
		}
	}
}