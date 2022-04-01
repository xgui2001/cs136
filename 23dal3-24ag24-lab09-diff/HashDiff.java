/**
* Class to calculate the diff between two files
* */

import structure5.*;
import java.util.Scanner;

/**
* Class to calculate the diff between two files */
public class HashDiff extends Diff {

	/**Students: in task 2, add an instance variable
	 * hash table to store previously-calculated diffs  */
	 Hashtable <IntPair,Association<Integer, String>> hash;

	/**
	* Constructor for diff
	* @param 	 	file1Name is the path to the original file
	* @param 		file2Name is the path to the new version of the file
	*/
	public HashDiff(String file1Name, String file2Name) {
		super(file1Name, file2Name);
		hash = new Hashtable <IntPair,Association<Integer, String>>();
		/**Students: this constructor calls the Diff constructor
		 * You may want to also initialize your hash table*/
	}

	/** The recursive helper method for calulating the diff @pre
	 * remainingFile1, remainingFile2, and table are not null @param
	 * remainingFile1Index the first line of file 1 not yet diffed @param
	 * remainingFile2Index the first line of file 2 not yet diffed @return An
	 * association corresponding to the diff between remainingFile1 and
	 * remainingFile2.  The key is the cost of the diff (number of changes
	 * necessary).  The value is the diff output. */
	public Association<Integer, String> diffHelper(int remainingFile1Index, int
			remainingFile2Index) {
				if (remainingFile1Index == file1.size()) {
					return baseCaseHelper(remainingFile2Index, file2, ">");
				}
				if (remainingFile2Index == file2.size()) {
					return baseCaseHelper(remainingFile1Index, file1, "<");
				}
				IntPair pair = new IntPair(remainingFile1Index, remainingFile2Index);
				if(hash.containsKey(pair)){
					return hash.get(pair);
				}
				//check if first lines match
				//If so, calculate recursively the optimal result with matching lines
				//Store that result in an Association
				Association <Integer, String> bothCase = null;
				if (file2.get(remainingFile2Index).equals(file1.get(remainingFile1Index))) {
					bothCase = diffHelper(remainingFile1Index+1, remainingFile2Index+1);
				}
				//calculate the cost of removing a line from file1 (store solution in an Association)
				Association <Integer, String> firstCase = diffHelper(remainingFile1Index+1, remainingFile2Index);
				//calculate the cost of removing a line from file2 (store solution in an Association)
				Association <Integer, String> secondCase = diffHelper(remainingFile1Index, remainingFile2Index+1);
				//calculate the minimum between the three associations
				// - Don't forget to add 1 to the cost for taking a line from file1
				//or file2.
				// - Be sure to break ties as described in the lab handout
				Association<Integer, String> bestCase = null;
				if (bothCase != null && bothCase.getKey() < firstCase.getKey() && bothCase.getKey() < secondCase.getKey()) {
					bestCase = bothCase;
				}
				else if (secondCase.getKey() < firstCase.getKey()) {
					bestCase = new Association <Integer, String>(secondCase.getKey()+1, ">" + (remainingFile2Index+1) + ":" + file2.get(remainingFile2Index)+"\n"+secondCase.getValue());
				}
					else bestCase = new Association <Integer, String>(firstCase.getKey()+1, "<" + (remainingFile1Index+1) + ":"+ file1.get(remainingFile1Index)+"\n"+firstCase.getValue());
				//calculate the return value using the best recursive solution
				//and return it
				//return new Association<Integer, String>(1, "diffHelper not yet implemented!");
				hash.put(pair, bestCase);
		/**Students: implement diffHelper, using your code from Diff.java as a
		 * starting point.  This iteration of  diffHelper should use a hash
		 * table to avoid recalculating the optimal solution for a given
		 * subproblem twice.  Be sure to implement IntPair to ensure that this
		 * works. */
		return bestCase;
	}

	/**
	* main method: two command line arguments; the first is the original file,
	* the second is the new version to be compared to. */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java HashDiff <file1> <file2>");
			System.exit(1);
		}
		Diff diff = new HashDiff(args[0], args[1]);
		diff.findDiff();
	}
}
