1. (1 point) What is the best solution to the 15-block problem?
The best subset (left stack) is: |4|5|6|10|11|12|13| = 20.23411306140849


2. (3 points) How long does it take your program to find the answer to the 20-block
problem? Based on the time taken to solve the 20-block problem, about how long
do you expect it would take to solve the 21-block problem? What is the actual
time? How about the 25-block problem? Do these agree with your expectations,
given the time complexity of the problem? What about the 40- and 50-block
problems? (These will take a very long time. Just estimate based on the run
times of the smaller problems). To answer this question run each program 3 times and fill in the table below with the *median*
(you may wish to edit this file directly on GitLab so that you can view the
table rendered using markdown formatting---use "preview" to see how it looks)

| Problem Size  | Estimated time | Actual Time |
| :------------ | :------------: | :---------: |
| 20 blocks     |       24s      |    26.020s  |
| 21 blocks     |       52s      |    54.937s  |
| 22 blocks     |      1m44s     |   1m55.664s |
| 25 blocks     |      13m52s    |  17m4.484s  |
| 40 blocks     |    25165824s   |     --      |
| 50 blocks     |  25769803776s  |     --      |

  * Explain how you arrived at your estimates.
    * each estimate is based on the additional number of subsets created. With each additional block, it is added 2^n subsets for n being the difference between blocks in the starting set and the new amount. The estimate is previous time x 2^n. For each additional block the time doubles.
  * Do the actual timings agree with your expectations, given the time complexity of the problem?
    * It's close, however as block number increases the actual timings start to grow larger than the estimates.
    * The standard deviation of the time returned causes the prediction of future run times error to be exponentially greater.

3. (1 point) This method of exhaustively checking the subsets of blocks will not work for
very large problems. Consider, for example, the problem with 50 blocks: there
are 2^50 different subsets. One approach is to repeatedly pick and evaluate
random subsets of blocks (e.g., stop the computation after 1 second of elapsed
time, printing the best subset found). How would you implement `randomSubset`, a
new `SubsetIterator` method that returns a random subset? (Describe your
strategy. You do not need to actually implement it.)
  * Instead of currentSubset equal to 0, we could make the current subset equal to a random  number and that is then used as the number we get the subset for. Then in our next method, rather than incrementing currentSubset by 1 each time, we set currentSubset equal to an entirely new random number.
