/************************************************************
*	This program is able to store items on a list.
*	Check the API on Creative Problem 3.5.27 (List; Algs4);
*
*	Compilation:
*	javac-algs4 List.java
*
*	Execution example:
*	java-algs4 List < ListText.txt
*
*************************************************************/


import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class List<Item extends Comparable<Item>> implements Iterable<Item>{
	private RedBlackBST<Double, Item> Order;                    //easy to find a specific rank key
	private RedBlackBST<Item, RedBlackBST<Double, Item>> Key;   //easy to find a specific key
	
	public List (){                                             //creates an empty List
		Order = new RedBlackBST<Double, Item>();
		Key = new RedBlackBST<Item, RedBlackBST<Double, Item>>();
	}
	
	/****************************************
	*	ADD FUNCTIONS
	****************************************/
	public void addFront (Item item){                           //adds an item on the first position
		add(0, item);
	}
	
	public void addBack (Item item){                            //adds an item on the last position
		add(Order.size (), item);
	}
	
	public void add (int iRank, Item item){
		if (iRank < 0 || iRank > Order.size ()){
			throw new NoSuchElementException("Invalid position to add");
		}
		if (Order.isEmpty()){               //if the list is empty, adds the first item
			putBoth(item, 0.0);
			return;
		}
		double dValue;
		if (iRank == 0){                    //add the item on the first place
			dValue = Order.min() - 1;
		}
		else if (iRank == Order.size()){    //add the item on the last place
			dValue = Order.max() + 1;
		}
		else {                              //add the item on the middle
			dValue = ((Order.select(iRank - 1) + Order.select(iRank))/2);
		}
		putBoth(item, dValue);
		return;
	}
	
	private void putBoth(Item item, double dValue){   //this function puts the key-double pair on both trees
		Order.put(dValue, item);
		if (!Key.contains(item)){                     //if item is first added on the list
			Key.put(item, new RedBlackBST<Double, Item>());
		}
		Key.get(item).put(dValue, item);
		return;
	}
	
	/****************************************
	*	DELETE FUNCTIONS
	****************************************/
	public void delete(Item item){
		if (!Key.contains(item)){
			throw new NoSuchElementException("No such element on symbol table");
		}
		double dValue = Key.get(item).min();     //gets the first ocurrence
		Key.get(item).deleteMin();               //deletes the first ocurrence
		if (Key.get(item).isEmpty()){            //delete the node if it is the only ocurrence
			Key.delete(item);
		}
		Order.delete(dValue);
	}
	
	public Item delete(int iRank){
		if (Key.isEmpty()){
			throw new NoSuchElementException("Symbol table underflow");
		}
		if (iRank >= size()){
			throw new NoSuchElementException("Invalid rank");
		}
		double dValue = Order.select(iRank);
		Item item = Order.get(dValue);
		Order.delete(dValue);			//deletes on the Order BST
		Key.get(item).delete(dValue);   //deletes on the Key BST
		if (Key.get(item).isEmpty()){
			Key.delete(item);
		}
		return item;
	}
	
	public Item deleteFront(){
		if (Key.isEmpty()){
			throw new NoSuchElementException("Symbol table underflow");
		}
		Item item = Order.get(Order.min());			//deletes on the Order BST
		Order.deleteMin();
		Key.get(item).deleteMin(); 					//deletes on the Keys BST
		if (Key.get(item).isEmpty()){
			Key.delete(item);
		}
		return item;
	}
	
	public Item deleteBack(){
		if (Key.isEmpty()){
			throw new NoSuchElementException("Symbol table underflow");
		}
		Item item = Order.get(Order.max()); 		//deletes on the Order BST
		Order.deleteMax();
		Key.get(item).deleteMax(); 					//deletes on the Keys BST
		if (Key.get(item).isEmpty()){
			Key.delete(item);
		}
		return item;
	}
	
	/****************************************
	*	GET FUNCTION
	****************************************/
	
	Item get(int iRank){
		return Order.get(Order.select(iRank));
	}
	
	/****************************************
	*	ITERABLE INTERFACE
	****************************************/
	public Iterator<Item> iterator()  {
        return new ListIterator<Item>();  
    }
	
	private class ListIterator<Item> implements Iterator<Item>{
        private int iRank;

        public ListIterator(){
            iRank = 0;
        }

        public boolean hasNext(){ 
			return iRank < Order.size();
		}
        public void remove(){ 
			throw new UnsupportedOperationException();
		}

        public Item next(){
            if (!hasNext()){ 
				throw new NoSuchElementException();
			}
            iRank++;
			return (Item)Order.get(Order.select(iRank - 1));
        }
    }
	
	/****************************************
	*	MISC FUNCTIONS
	****************************************/
	public boolean contains(Item item){
		return Key.contains(item);
	}
	
	public boolean isEmpty(){
		return Order.isEmpty();
	}
	
	public int size(){
		return Order.size();
	}
	
	public void status(){ //prints the whole list and size
		if (isEmpty()){
			StdOut.println("The list is empty!\n");
			return;
		}
		StdOut.println("Size: " + size());
		for (Item s : this){
			StdOut.println(s);
		}
		StdOut.println();
	}
	/****************************************
	*	TUNIT TEST
	****************************************/
	public static void main (String[] args){
		List<String> list = new List<String>();
		while (!StdIn.isEmpty()){
			list.addFront(StdIn.readString());
		}
		
		String tmp = "";
		int iRank;
		
		list.status();
		tmp = list.delete(list.size()/2);
		StdOut.println("Deleted the item on the middle: " + tmp);
		StdOut.println(list.get(0) + " is the first element on the list.");
		StdOut.println();
		
		list.status();
		list.addFront("HUE");
		list.addBack("HEU");
		StdOut.println("Added \"HUE\" on the front and \"HEU\" on the back.");
		if (list.contains("HEU")){
			StdOut.println("The list contains \"HEU\".");
		}
		else{
			StdOut.println("The list does not contain \"HEU\".");
		}
		StdOut.println();
		
		list.status();
		tmp = list.deleteBack();
		StdOut.println("Deleted the item on the back: " + tmp);
		if (list.contains("HEU")){
			StdOut.println("The list still contains \"HEU\".");
		}
		else{
			StdOut.println("The list does not contain \"HEU\" anymore.");
		}
		list.delete("HUE");
		StdOut.println("Deleted the item \"HUE\".");
		StdOut.println();
		
		list.status();
		iRank = list.size()/2;
		list.add(iRank, "LOL");
		StdOut.println("Added \"LOL\" on the middle.");
		
		list.status();
		while (!list.isEmpty()){
			list.deleteFront();
		}
		StdOut.println("Deleted everything from the front.");
		
		list.status();
	}
}




