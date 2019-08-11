/*****************************************************
  This java class is designed to randomize its contents
  everytime it iterates. API is as follows:
  
  RandomBag() - Creates a empty Randombag
  
  boolean - isEmpty() - Returns true if the RandomBag is
  empty.
  
  int - size() - Returns the number of Items inside the
  bag.
  
  void - add(Item item) - Adds the Item inside the bag
*****************************************************/

import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public RandomBag (){
		first = null;
		N = 0;
	}
	
	public void add (Item newitem){
		Node oldfirst = this.first;
		this.first = new Node ();
		this.first.item = newitem;
		this.first.next = oldfirst;
		N++;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return N;
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first; //notes the first node
		private Item[] randomItem = (Item[])bagShuffle(); //the random values generated to iterate
		private int index = 0;
		
		public Object bagShuffle(){ //this funcions randomizes the contents before iteration
			Object[] content = new Object[size()];
			Node currentNode = first;	
			for(int i = 0; i < size(); i++){ //transfer the items inside the bag into an array
				content[i] = currentNode.item;
				if(hasNext())
					currentNode = currentNode.next;
			}
			StdRandom.shuffle(content); //shuffles the array
			return content;
		}
		public boolean hasNext (){
			return current != null;
		}
		public void remove (){
			
		}
		public Item next (){
			Item returnItem = randomItem[index];
			index++;
			current = current.next;
			return returnItem;
		}
	}
	
	public static void main(String[] args) { //test client
        RandomBag<String> bag = new RandomBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}