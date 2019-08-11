import edu.princeton.cs.algs4.*;
public class RandomGrid{
	private static class Connection{
		int p;
		int q;
		
		public Connection (int p, int q){
			this.q = q;
			this.p = p;
		}
		public String toString (){
			return p + " " + q;
		}
		
		public Connection shuffle(){ //shuffles the p q connections
			if(StdRandom.uniform(2) == 0){
				return new Connection(this.q, this.p);
			}
			return this;
		}
	}
	
	public static Connection[] generate (int N){ //generates all the connections existing between a NxN sized grid in a random order Connection array
		RandomBag<Connection> matrix = new RandomBag<Connection>();
		for (int i = 0; i < N; i++){ //horizontal connections
			for (int j = 0; j < N - 1; j++){
				matrix.add(new Connection(j + (N * i), j + 1 + (N * i)).shuffle());
			}
		}
		for (int i = 0; i < N - 1; i++){ //vertal connections
			for (int j = 0; j < N; j++){
				matrix.add (new Connection (j + (N * i), j + (N * i) + N).shuffle());
			}
		}
		Connection[] aMatrix = new Connection[2 * N * (N - 1)];
		int i = 0;
		for (Connection s : matrix){ //iterates the RandomBag and transfers to a array
			aMatrix[i] = s;
			i++;
		}
		return aMatrix;
	}
	
	public static String[] gimmeConnection (int N){ //method returns the connections in string
		Connection[] matrix = generate (N);
		String[] sMatrix = new String[matrix.length];
		for (int i = 0; i < matrix.length; i++){
			sMatrix[i] = matrix[i].toString();
		}
		return sMatrix;
	}
	
	public static void main(String[] args){
		int N = Integer.parseInt(args[0]);
		Connection[] test = generate(N);
		for(int i = 0; i < test.length; i++){
			StdOut.println(test[i]);
		}
	}
}