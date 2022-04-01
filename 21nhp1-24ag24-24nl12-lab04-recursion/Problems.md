# Thought Questions

Please specify the big-O running time of the following methods, along
with a brief explanation.

1. `countCannonballs(int height)`
   * O(n)
   * Explanation: linear runtime because it only calls itself n times in a recursive loop


2. `boolean isPalindrome(String str)`
   * O(n)
   * Explanation: linear runtime because it only calls itself n times in a recursive loop

3. `boolean isBalanced(String str)`
   * O(n)
   * Explanation: linear runtime because it only calls itself n times in a recursive loop (only goes through one of the if loops)

4. `void subsequences(String str)`
   * O(2^n)
   * Explanation: every run of subsequences, it calls on the helper method, which recursively calls on itself twice

5. `void printInBinary(int number)`
   * O(n)
   * Explanation: linear runtime because it only calls itself n times in a recursive loop

6. `boolean printSubSetSum(int[] nums, int targetSum)`
   * O(2^n)
   * Explanation: every run of printSubSetSum, it calls on the helper method, which recursively calls on itself twice

7. `int countSubSetSumSolutions(int[] nums, int targetSum)`
   * O(2^n)
   * Explanation: every run of countSubSetSumSolutions, it calls on the helper method, which recursively calls on itself twice
