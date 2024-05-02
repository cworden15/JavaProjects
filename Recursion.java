/*
 * There is no requirement for a file header comment for this 
 * assignment. Spend your time writing good testcases instead!
 */
import java.util.Queue;
import java.util.Stack;

public class Recursion {

	/**
	 *This method will check the initial lengths of the Strings.
	 *It will first check if s2 is empty, then
	 *if it is longer than s1. If this is true,
	 *then s2 will not be in s1 and will return -1. 
	 *This method will then recurse into a one character 
	 *smaller string and compare the substrings until 
	 *the strings are the same length. NOTE: This method
	 *will assume a capital letter and a non-capital 
	 *letter are different.
	 *
	 * @param s1
	 * @param s2
	 * @return Returns the index of the first time that
	 * 			s2 appears in s1 or -1 if s2 is not contained
	 * 			in s1.
	 */
	public static int indexOf(String s1, String s2) {
		int str2Length = s2.length();
		int str1Length = s1.length();
		// case if substring is empty
		if(str2Length==0) {
			return 0;
		}
		// case if string 2 is longer 
		// this could be an or conditional
		if(str2Length>str1Length) {
			return -1;
		}	
		else {		
			if((s1.substring(0, str2Length).equals(s2))) {
				return 0;
			}
			// this will check to make sure the substring
			// exists 
			int count = indexOf(s1.substring(1),s2);
			if(count==-1) {
				return -1;
			}else {
				return 1 + indexOf(s1.substring(1),s2);
			}
		}				
	}

	/**
	 * This method takes in 2 parameters and returns an int. 
	 * The returned value will be how many even integers were 
	 * removed. The integers will be the first k integers in 
	 * the stack. The base case is an empty stack and this method will
	 * build a new stack towards the base base. The new stack(stack
	 * made by recursing) will be the same as the original stack 
	 * but with k even ints removed. 
	 * 
	 * @param stack
	 * @param k-int
	 * @return Returns the number of elements removed from the stack.
	 */
	public static int removeEvenNumbers(Stack<Integer> stack, int k) {
		if(stack.empty()) {
			return 0;
		}else {
			int count = 0;
			// Boolean to check if a number needs to be pushed back onto 
			// the stack
			Boolean popped = false;
			int topOfStack = stack.pop(); 
			if(topOfStack % 2 == 0 && k > 0) {
				popped = true;
				k--;
				count++;
			}	
			// adding the recursive call to the counter
			count += removeEvenNumbers(stack,k);
			// will push the value not popped to the stack
			if(popped==false) {
				stack.push(topOfStack);
			}		
			return count;			
		}
	}

	/**
	 * This is a recursive method that takes an int as a parameter 
	 * and returns an int. The returned value will be the even digits of 
	 * the parameter passed to the method. Ex- if 123456 is the parameter, 
	 * this method will return 246. If the parameter is negative, 
	 * the returned int will be positive. This method's base case
	 * is the parameter being zero. This method will divide by ten 
	 * till zero and add the remainder if it is even.
	 * @param n
	 * @return The input with only the even digits remaining in the same
	 * order.
	 */
	public static int evenDigits(int n) {
		if(n<0) {
			return evenDigits(-n);
		}
		if(n==0) {
			return 0;
		}else {
			int reducedNumber = evenDigits(n/10);
			int returnReducedNumber =(n%10);
			// if number is even, adding it back
			if(returnReducedNumber % 2 == 0) {
				// will keep the numbers in order
				reducedNumber *= 10;
				reducedNumber += returnReducedNumber;
			}return reducedNumber;
		}
	}

	/**
	 * This method attempted to evaluate a q as a mathematical 
	 * expression. However, I could not figure this out. I wrote
	 * a few test cases to see how my method would alter the q.
	 * I couldn't find a solution. Will get help on this method. 
	 * 
	 * @param q
	 * @return The result of the mathematical expression.
	 */
	public static int evaluate(Queue<Character> q) {
		if(q.isEmpty()) {
			return 0;
		}
			
		return 0;
					
	}

	/**
	 * This method takes a stack as a parameter and doubles each of
	 * the ints frequency. The base case is the 
	 * empty stack, but since the method is void,
	 * the base case doesn't return anything. It instead
	 * gives the method the memory
	 * to work towards the empty stack
	 *  For example, * calling repeatStack and passing 
	 * in a stack of { 1, 2, 3} would change
	 * the stack to hold { 1, 1, 2, 2, 3, 3}. 
	 */
	public static void repeatStack(Stack<Integer> stack) {
		if(!stack.empty()) {
			// storing the popped value
			int topOfStack = stack.pop();
			repeatStack(stack);
			// pushing the last pop onto the empty stack
			stack.push(topOfStack);
			stack.push(topOfStack);
		}
	}

	/**
	 * This method calls the helper method. This method 
	 * takes in a q and doubles each element. 
	 * Example: [1,2,3] will be [2,4,6]
	 * @param q
	 */
	public static void doubleElements(Queue<Integer> q) {
		doubleHelp(q,q.size());
		}
	/**
	 * This method does what double elements does but with an
	 * added parameter to keep track of the size of the queue. 
	 * The added parameter will keep track of how many times 
	 * add is called. 
	 * 
	 * @param q
	 * @param int n
	 */
	public static void doubleHelp(Queue<Integer> q,int n) {
		if(n>0) {
			int topOfQ = q.remove();
			q.add(topOfQ*2);
			doubleHelp(q,n-1); 			
		}
	}
		

}
