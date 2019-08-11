/******************************************************
	This java program is able to receive two vectors,
	permute their coordinates in order to minimize the
	dot product. And also calculates the dot product.
	
	Input Example:
	3
	1 2 3
	3 2 1
	In which the vectors are (1, 2, 3) and (3, 2, 1).
	
	Compilation: 
	javac-algs4 DotProduct.java
	
	Execution example:
	java-algs4 DotProduct
	5
	5 4 3 2 1
	1 2 3 4 5
	
******************************************************/


import edu.princeton.cs.algs4.*;
public class DotProduct{
	private static double dot(double[] d1, double[] d2){
		double result = 0;
		for(int i = 0; i < d1.length; i++){
			result += d1[i] * d2[i];
		}
		return result;
	}
	
	private static double[] sortAscending (double [] d){
		for (int i = 1; i < d.length; i ++){
			for (int j = i; j >= 1 && d[j] < d[j - 1]; j--){
				double tmp = d[j];
				d[j] = d[j - 1];
				d[j - 1] = tmp;
			}
		}
		return d;
	}
	private static double[] sortDescending (double [] d){
		for (int i = 1; i < d.length; i ++){
			for (int j = i; j >= 1 && d[j] > d[j - 1]; j--){
				double tmp = d[j];
				d[j] = d[j - 1];
				d[j - 1] = tmp;
			}
		}
		return d;
	}
	
	public static void main (String[] args){
		int iSize = StdIn.readInt ();
		double[] dV1 = new double[iSize];
		double[] dV2 = new double[iSize];
		
		for (int i = 0; i < iSize; i++){ //read data
			dV1[i] = StdIn.readInt ();
		}
		for (int i = 0; i < iSize; i++){
			dV2[i] = StdIn.readInt ();
		}
		
		dV1 = sortAscending (dV1);
		dV2 = sortDescending (dV2);
		
		StdOut.println ("Permutation chosen for the vectors: ");
		StdOut.println ("Vector 1 coordinates: ");
		for (double d : dV1){
			StdOut.print (d + " ");
		}
		StdOut.println();
		StdOut.println ("Vector 2 coordinates: ");
		for (double d : dV2){
			StdOut.print (d + " ");
		}
		StdOut.println ();
		StdOut.println ("Therefore, the dot product is : " + dot(dV1, dV2));
	}
}