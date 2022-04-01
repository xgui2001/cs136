# Thought Questions

1. What strategy might you use to pick a random number of coins such
that there is a 50 percent chance of a game with three coins, a 25
percent chance of a game with four coins, a 12.5 percent chance of a
game with five coins, and so on? (_Note_: You are not required to
implement this strategy in your Coinstrip game, but after you’ve
concretely described the strategy in text, writing the code to
implement it is often the easy part)
  * Set a limit on the random number generator for the coins, so that the distribution would yield the % chance. Eg. for a 50% chance, the random number generator for CoinsNum would pick between 3-4, for 25% it will pick between 3-6, 12.5% it will pick between 3-10.


2. How might you ensure that the initial games your program generates
are not immediate wins? Can you describe a property of the game state
that would guarantee that it is possible for players to select a
sequence of _N_ moves before the game is won (you would be able to
choose the moves)?
  * When populating the game board, have a random number generator that places each of the coins a random distance at least 1 space away from each other. For the second question, since each coin that is more than 1 space away from the closest coin to its left will generate an additional move (1 gap = 1 move), set an int to keep track of the number of gaps (not spaces) between the coins.


3. Suppose the computer could occasionally provide good hints. What
opportunities appear easy to recognize? (Some strategies may be hard
for the computer to determine, but are there “obvious” cases where the
computer should always make a particular move?)
  * The computer can scan the game board, and calculate which player was going to win based on the least number of moves (tracked above in question 2), then the computer could give a hint for the losing player to make a move in which they don't move a coin all the way to left. Therefore a new array could be established to keep track of the coins' final position based on their initial position in the original array (coins)


4. How might you write a method, computerPlay, where the computer
plays to win? You __do not__ need to write the method, but give a
high-level idea of how you might go about implementing it.
  * Based on the array established in question 3, the computer will calculate the outcome of the game every time the player makes a move (assuming that the player would move the coin all the way to the left), and make its move based on the player's move. If the player is going to win, the computer needs to delay moving a coin all the way to the left. The computer maintains its win by maintaining an even number of turns left after each of its decisions.


5. A similar game, called Welter’s Game (after C. P. Welter, who
analyzed the game), allows the coins to pass each other. Would this
modification of the rules change your implementation significantly?
  * No because I can just change the 81st line in my isValidMove method so that it only returns false when the two coins overlap (coinIndex - numSpaces = coins[whichCoin-1) (basically remove the < sign)
