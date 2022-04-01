
import structure5.*;

public class Recursion {

	/***** Warmup 0.2 ********************************************/

	// Note: Warmup questions are not graded, but you may wish to
	// complete & test this method since the last question builds
	// upon it.

	/**
	 * Given a set of integers and a target number, determines
	 * whethere there is a subset of those numbers that sum to the
	 * target number.
	 *
	 * @param setOfNums a set of numbers that may appear in the subset
	 * @param targetSum the target value for the subset
	 * @return true if some subset of numbers in setOfNums sums to targetSum
	 */
	public static boolean canMakeSum(int[] setOfNums, int targetSum) {
		return false;
	}


	/*****  1  ***************************************************/

/**This method returns number of cannoballs in pyramid with the given `height`.
* @param height the height of the cannonball tower
* @return the number of cannonballs in the entire tower
* @pre height is not a negative number
*/
public static int countCannonballs(int height) {
	assert height >= 0; //pre condition: height is not a negative number
	if (height == 0) {
		return 0; //basecase: if height is 0
	} else {
		return height * height + countCannonballs(height - 1); //recursively add the layers of the cannonballs
	}
}

/*****  2  ***************************************************/

/**A method that determines if a string reads the same forwards and backwards.
* @param str the string to check
* @return true if `str` is a palindrome.
* @pre string is not null
  */
public static boolean isPalindrome(String str) {
	assert str != null; //@pre string is not null
	if (str.length() == 0 || str.length() == 1) { //basecase: if string length is 1 or 0
		return true;
	} else {
		char first = str.charAt(0); //first character
		char last = str.charAt(str.length() - 1); //last character
		str = str.substring(1, str.length() - 1); //truncate the current palindrome
		return (first == last) && isPalindrome(str); //recursively check if truncated string is a palindrome
	}
}

/*****  3  ***************************************************/

/**This method checks whether `str` is a string of properly nested and matches parens, brackets, and braces.
* @param str a string of parens, brackets, and braces
* @return true if str is properly nested and matched
* @pre string is not null
*/
public static boolean isBalanced(String str) {
	assert str != null; //@pre string is not null
	if (str.length() == 0) { //basecase: if string length is 0
		return true;
	} else {
		//recursively check if string contains pair of parens, brackets, and braces, and deletes them if a pair is found
		if (str.contains("()")) {
			str = str.replace("()", "");
		} else if (str.contains("{}")) {
			str = str.replace("{}", "");
		} else if (str.contains("[]")) {
			str = str.replace("[]", "");
		} else {
			return false;
		}
	}
	return isBalanced(str);
}


/*****  4  ***************************************************/

/** A method to print all subsequences of str (order does not matter).
* @param str string to print all subsequences of
* @pre string is not null
*/
public static void subsequences(String str) {
	assert str != null; //@pre string is not null
	String soFar = "";
	subsequenceHelper(str, soFar); //call the helper method
}

/** Helper method for subsequences()
* @param `soFar` keeps track of the characters currently in the substring being built
*/
protected static void subsequenceHelper(String str, String soFar) {
	if (str.length() == 0) { //basecase: string length is 0
		System.out.println(soFar);
	} else {
		subsequenceHelper(str.substring(1), soFar); //subsequence without initial character
		subsequenceHelper(str.substring(1), soFar + str.substring(0, 1)); //subsequence with initial character
	}
}

/*****  5  ***************************************************/

/**A method to print the binary digits of a number.
* @param number the number to print in binary
* @pre assume number is non negative
*/
public static void printInBinary(int number) {
	assert number > 0; //@pre assume number is non negative
	if (number <= 1) {
		System.out.print(number); //basecase: if number is 0 or 1
	} else {
		printInBinary(number / 2); //recursively calculates the binary representation of the number
		System.out.print(number % 2);
	}
}


/*****  6a  ***************************************************/

/**helper method for printSubsetSum
* @param index keeps track of the current element in nums, sumSoFar keeps track of current sum
*/
public static boolean subsetSumHelper(int[] nums, int targetSum, int index, int sumSoFar) {
	if (sumSoFar == targetSum) { //if the targetSum is reached
		return true;
	} else {
		if (index == nums.length) {
			return false; //reached the end of the set and sumSoFar is not equal to targetSum
		} else {
			//recursive case: checking whether to include next element in or out of the sum
			boolean withNextElement = subsetSumHelper(nums, targetSum, index + 1, sumSoFar + nums[index]);
			boolean withoutNextElement = subsetSumHelper(nums, targetSum, index + 1, sumSoFar);
			return (withNextElement || withoutNextElement); //compare the two boolean to see if they match
		}
	}
}

/** This method return whether a subset of the numbers in nums add up to sum, and print them out.
* @pre set is not null
* @param nums is the set of numbers, targetSum is the sum provided
* Big-O runtime: 2^n
*/
public static boolean printSubsetSum(int[] nums, int targetSum) {
	assert nums != null; //@pre set is not null
	int index = 0;
	int sumSoFar = 0;
	return subsetSumHelper(nums, targetSum, index, sumSoFar); //run the helper method
}

/*****  6b  ***************************************************/

/**helper method for countSubsetSumSolutions
* @param index keeps track of the current element in nums, sumSoFar keeps track of current sum
*/
public static int countSubsetSumHelper(int[] nums, int targetSum, int index, int count) {
	if (nums.length == index) { //basecase: if all subsets have been checked
		if (targetSum == 0) { //basecase: if targetSum is equal to 0;
			count++;
		}
		return count;
	} else {
		//recursive case: check if the next element can be counted in the subset
		count = countSubsetSumHelper(nums, targetSum - nums[index], index + 1, count); //if the next element is counted, the remaining sum is targetSum - the next element
		count = countSubsetSumHelper(nums, targetSum, index + 1, count); //if the next element is not counted, the remaining sum is just targetSum
		return count;
	}
}

/** This method return the number of different ways elements in nums can be added together to equal sum.
* @pre set is not null
* @param nums is the set of numbers, targetSum is the sum provided
* Big-O runtime: 2^n
*/
public static int countSubsetSumSolutions(int[] nums, int targetSum) {
	assert nums != null; //@pre set is not null
	int index = 0;
	int count = 0;
	return countSubsetSumHelper(nums, targetSum, index, count); //run the helper method
}



/***********************************************************/

	/**
	 * Add testing code to main to demonstrate that each of your
	 * recursive methods works properly.
	 *
	 * Think about the so-called corner cases!
	 *
	 * Remember the informal contract we are making: as long as all
	 * predconditions are met, a method should return with all
	 * postconditions met.
	 */

	protected static void testCannonballs() {
		System.out.println("Testing cannonballs: ....");
		System.out.println(countCannonballs(3));
		System.out.println(countCannonballs(10));
	}

	protected static void testPalindrome() {
		System.out.println("Testing isPalindrome: ....");
		System.out.println(isPalindrome("mom"));
		System.out.println(isPalindrome("deeded"));
		System.out.println(isPalindrome("ablewasIereIsawelba"));
	}

	protected static void testBalanced() {
		System.out.println("Testing isBalanced: ....");
		System.out.println(isBalanced("[{[()()]}]"));
		System.out.println(isBalanced("[{[()()]}][{[()()]}]"));
		System.out.println(isBalanced("[{[()()]}{]{[()()]}]"));
	}

	protected static void testSubsequence() {
		System.out.println("Testing subsequences: ....");
		subsequences("abc");
		System.out.println();
		subsequences("CSCI136");
		System.out.println();
		subsequences("a");
		System.out.println();
		subsequences("");
		System.out.println();
	}

	protected static void testBinary() {
		System.out.println("Testing printInBinary: ....");
		printInBinary(0);
		System.out.println();
		printInBinary(30);
		System.out.println();
		printInBinary(1);
		System.out.println();
		printInBinary(110);
		System.out.println();
		printInBinary(2048);
		System.out.println();
		printInBinary(43);
		System.out.println();
    	}

	protected static void testCanMakeSum() {
		System.out.println("Testing canMakeSum: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(canMakeSum(numSet, 21));
		System.out.println(canMakeSum(numSet, 22));
		System.out.println(canMakeSum(numSet, 3));
		System.out.println(canMakeSum(numSet, 30));
	}

	protected static void testPrintSubsetSum() {
		System.out.println("Testing printSubsetSum: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(printSubsetSum(numSet, 21));
		System.out.println(printSubsetSum(numSet, 22));
		System.out.println(printSubsetSum(numSet, 3));
		System.out.println(printSubsetSum(numSet, 30));
	}

	protected static void testCountSubsetSum() {
		System.out.println("Testing countSubsetSumSolutions: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(countSubsetSumSolutions(numSet, 21));
		System.out.println(countSubsetSumSolutions(numSet, 22));
		System.out.println(countSubsetSumSolutions(numSet, 3));
		System.out.println(countSubsetSumSolutions(numSet, 30));
	}

	/**
	 * Main method that calls testing methods to verify
	 * the functionality of each lab exercise.
	 *
	 * Please supplement the testing code with additional
	 * correctness tests as needed.
	 */
	public static void main(String[] args) {
		testCannonballs();
		testPalindrome();
		testBalanced();
		testSubsequence();
		testBinary();
		testCanMakeSum();
		testPrintSubsetSum();
		testCountSubsetSum();
	}
}
