/*********************************************
	This java program is able to find the 
	solutions to the following Diophantine
	Equation: a + 2*b^2 = 3*c^3 + 4*d^4*
	
	Compilation:
	javac-algs4 DioEquation.java
	
	Execution example:
	java-algs4 DioEquation 1000
*********************************************/

import edu.princeton.cs.algs4.*;
public class DioEquation implements Comparable <DioEquation> {
	private final int iA;
	private final int iB;
	private final long iValue;
	private final boolean bLaw;
	
	public DioEquation (int iA, int iB, boolean bLaw){
		this.iA = iA;
		this.iB = iB;
		this.bLaw = bLaw;
		if (bLaw)
			this.iValue = (long) iA + (long) 2*iB*iB;
		else
			this.iValue = (long) 3*iA*iA*iA + (long) 4*iB*iB*iB*iB;
	}
	
	public int getA (){
		return iA;
	}
	
	public int getB (){
		return iB;
	}
	
	public long getValue (){
		return iValue;
	}
	
	public int compareTo (DioEquation that){
		if (this.iValue > that.iValue)
			return 1;
		if (this.iValue < that.iValue)
			return -1;
		return 0;
	}
	
	public String toString (){
		if (bLaw)
			return iA + " + 2*" + iB + "^2"; 
		else
			return "3*" + iA + "^3 + 4*" + iB + "^4";
	}
	
	public static void main (String[] args){
		int iN = Integer.parseInt(args[0]);
		boolean bStop = false; //flag to avoid overflow
		int iAns = 0;
		
		MinPQ<DioEquation> PQ1 = new MinPQ<DioEquation> ();
		MinPQ<DioEquation> PQ2 = new MinPQ<DioEquation> ();
		DioEquation EQMax = new DioEquation (iN, iN, true); //the cap value for EQ for PQ2
		
		for (int i = 0; i <= iN; i++){ //makes the initial queue
			PQ1.insert (new DioEquation (0, i, true));
			if (!bStop && new DioEquation (0, i, false).iValue > EQMax.iValue){ //checks if the PQ2 equations aren't too big
				bStop = true; //raise flag
			}
			if (!bStop) //only puts the equation if it the flag hasn't been raised
				PQ2.insert (new DioEquation (0, i, false));
		}
		bStop = false; //reset flag
		
		int iRun = 1; //variable used to keep the same answers on the line
		DioEquation EQ1 = PQ1.delMin ();
		DioEquation EQ2 = PQ2.delMin ();
		DioEquation LastEQ = EQ1; //EQ1 is sentinel value for LastEQ
		while (!(PQ1.isEmpty ()) && !(PQ2.isEmpty ())) {
			//StdOut.println ("EQ1 = " + EQ1);
			//StdOut.println ("EQ2 = " + EQ2);
			if (EQ1.iValue == EQ2.iValue) {
				iAns++;
				if (iRun == 1) {
					StdOut.print (EQ1 + " = " + EQ2);
					iRun++;
				}
				else { //if it's the same equallity, only prints the last term
					StdOut.print (" = " + LastEQ);
				}
			}
			else {
				if (iRun > 1){
					StdOut.println ();
				}
				iRun = 1;
			}
			if (EQ2.iValue < EQ1.iValue || PQ2.min().iValue == EQ1.iValue) {
				if ((EQ2.iA < iN) && (new DioEquation (EQ2.iA + 1, EQ2.iB, false).iValue <= EQMax.iValue)){
					PQ2.insert (new DioEquation (EQ2.iA + 1, EQ2.iB, false));
				}
				EQ2 = PQ2.delMin ();
				LastEQ = EQ2;
			}
			else {
				if (EQ1.iA < iN){
					PQ1.insert (new DioEquation (EQ1.iA + 1, EQ1.iB, true));
				}
				EQ1 = PQ1.delMin ();
				LastEQ = EQ1;
			}
		}
		StdOut.println ("Answers: " + iAns);
	}
}