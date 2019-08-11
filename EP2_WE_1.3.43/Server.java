import edu.princeton.cs.algs4.*;
public class Server {
    private Queue<String> list = new Queue<String>();      // list of users
    private int load;                                      // load

    // add a new user to the list
    public void add(String user) {
        list.enqueue(user);
        load++;
    }

    // string representation
    public String toString() {
        String s = String.format("%5d:  ", load);
        //String s = "";
        for (String user : list)
            s += user + " ";
        return s;
    }
	
	public int load(){
		return load;
	}

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
     
        Server[] servers = new Server[N];
        for (int i = 0; i < N; i++)
            servers[i] = new Server();

        // generate N random jobs and assign to a random processor
        for (int j = 0; j < N; j++) {
            String user = "user" + j;
            int i = StdRandom.uniform(N);
            servers[i].add(user);
        }

        // see how even the distribution is by printing out the
        // contents of each server
        for (int i = 0; i < N; i++)
            StdOut.println(i + ": " + servers[i]);
    }
}