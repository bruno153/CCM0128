/************************************************
 Palindrome Checker is a java program designed
 to verify wether the string on the command line
 is a palindrome or not. It ignores punctuation
 space and also special carachters.
 
 Example:
 java-algs4 PalindromeChecker Stop! Nine myriad murmur put up rum rum dairymen in pots.
************************************************/

import edu.princeton.cs.algs4.*;
public class PalindromeChecker{
	public static void main(String[] args){
		String sentence = ""; //creates the first empty string
		for(int i = 0; i < args.length; i++){ //concatenates the String array into one string
			sentence = sentence.concat(args[i]);
		}
		sentence = sentence.toLowerCase().replaceAll("[-+.^:,!]",""); //remove space,  punctuation and uppercases
		Stack<String> stack = new Stack<String>(); //creates the stack and queue
		Queue<String> queue = new Queue<String>();
		for(int i = 0; i < sentence.length(); i++){ //stacks and queues each letter
			String sub = sentence.substring(i, i+1);
			stack.push(sub);
			queue.enqueue(sub);
		}
		while(!stack.isEmpty()){ //verifies each element stacked and queued
			if(!stack.pop().equals(queue.dequeue())){
				System.out.println("This string is not a palindrome!");
				return;
			}
		}
		System.out.println("This string is indeed palindrome!");
	}
}