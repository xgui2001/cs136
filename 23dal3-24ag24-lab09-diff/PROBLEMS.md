# Diff Thought Questions

 1. This question is about the reason why the more complex hash function in Task 3 led to a significant speedup over the simpler hash function in Task 2. Let's say that throughout all recursive calls, remainingFile1Index is always between 0 and n-1, and remainingFile2Index is always between 0 and m-1
   * In this case, how many distinct IntPairs can there be throughout the execution of the program?  (We say that two IntPairs are distinct if either of the two integers they contain are not the same---in other words, if equals() returns false.)
   * How many possible hashCodes can be output by all IntPairs? (Hint: What is the maximum value that can be output by hashCode()? What is the minimum value?) 

2. Rather than using an IntPair or a BetterIntPair in this lab, we could have used an Association<Integer, Integer>. What would be the downside of doing this? 

