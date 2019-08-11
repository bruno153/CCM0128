/************************************************
 This program uses Server.java to simulate a 
 server load balancing algorithm from Web 
 Exercise 44, and also draws a histogram on 
 jDraw.
 
 Example:
 java-algs4 ServerRoutine2 1000 1000
************************************************/
import edu.princeton.cs.algs4.*;
public class ServerRoutine2{
	public static int run(int iN, boolean bPrint){
		Server[] google = new Server[iN];
		for(int i = 0; i < iN; i++){ //server list setup
			google[i] = new Server();
		}
		for(int i = 0; i < iN; i++){
			int iRnd1 = StdRandom.uniform(iN); //random number generation
			int iRnd2 = StdRandom.uniform(iN);
			while(iRnd1 == iRnd2) //gurantees that both servers are distinct
				iRnd2 = StdRandom.uniform(iN);
			if(google[iRnd1].load() < google[iRnd2].load()) //checks server load
				google[iRnd1].add(Integer.toString(i));
			else
				google[iRnd2].add(Integer.toString(i));
		}
		double[] dLoad = new double[iN];
		for(int i = 0; i < iN; i++){
			dLoad[i] = google[i].load();
		}
		if(bPrint){ //plots graph if requested
			StdDraw.setYscale(0, StdStats.max(dLoad));
			StdStats.plotBars(dLoad);
		}
		return (int)StdStats.max(dLoad);
	}
	public static int run(int iN, int iM, boolean bPrint){ //overload for distinc values of servers and users
		Server[] google = new Server[iN];
		for(int i = 0; i < iN; i++){ //server list setup
			google[i] = new Server();
		}
		for(int i = 0; i < iM; i++){
			int iRnd1 = StdRandom.uniform(iN); //random number generation
			int iRnd2 = StdRandom.uniform(iN);
			while(iRnd1 == iRnd2) //gurantees that both servers are distinct
				iRnd2 = StdRandom.uniform(iN);
			if(google[iRnd1].load() < google[iRnd2].load()) //checks server load
				google[iRnd1].add(Integer.toString(i));
			else
				google[iRnd2].add(Integer.toString(i));
		}
		double[] dLoad = new double[iN];
		for(int i = 0; i < iN; i++){
			dLoad[i] = google[i].load();
		}
		if(bPrint){ //plots the load graph if required
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