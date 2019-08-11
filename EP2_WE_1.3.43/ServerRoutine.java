/************************************************
 This program uses Server.java to simulate a 
 server load balancing algorithm from Web 
 Exercise 43, and also prints a histogram on the
 jDraw.
 
 Example:
 java-algs4 ServerRoutine 1000 1000
************************************************/
import edu.princeton.cs.algs4.*;
public class ServerRoutine{
	public static int run(int iN, boolean bPrint){ //returns the max load for the routine described on WebEx 43
		Server[] google = new Server[iN];//server list setup
		for(int i = 0; i < iN; i++){
			google[i] = new Server();
		}
		for(int i = 0; i < iN; i++){ //assign the users randomly
			int iRnd = StdRandom.uniform(iN);
			google[iRnd].add(Integer.toString(i));
		}
		double[] dLoad = new double[iN];
		for(int i = 0; i < iN; i++){ //makes the load array
			dLoad[i] = google[i].load();
		}
		if(bPrint){ //plots graph if required
			StdDraw.setYscale(0, StdStats.max(dLoad));
			StdStats.plotBars(dLoad);
		}
		return (int)StdStats.max(dLoad);
	}
	public static int run(int iN, int iM, boolean bPrint){ //overload version for distinct values of servers and users
		Server[] google = new Server[iN];
		for(int i = 0; i < iN; i++){
			google[i] = new Server();
		}
		for(int i = 0; i < iM; i++){
			int iRnd = StdRandom.uniform(iN);
			google[iRnd].add(Integer.toString(i));
		}
		double[] dLoad = new double[iN];
		for(int i = 0; i < iN; i++){
			dLoad[i] = google[i].load();
		}
		if(bPrint){
			StdDraw.setYscale(0, StdStats.max(dLoad));
			StdStats.plotBars(dLoad);
		}
		return (int)StdStats.max(dLoad);
	}
	
	public static void main(String[] args){
		int iN = Integer.parseInt(args[0]);
		int iM = Integer.parseInt(args[1]);
		StdOut.println("Max load = " + run(iN, iM, true));
	}
}