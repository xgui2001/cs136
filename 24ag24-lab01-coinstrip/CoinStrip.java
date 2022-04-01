// This program contains the game Coinstrip, in which two players take turns moving coins to the left until there is no space left. The last player who moved the coin wins.
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// The class CoinStrip creates and prints the gameboard (which is stored as an array), prompts the user for inputs, runs the game using methods from the class, and prints strings displaying the winner.
public class CoinStrip implements TwoPlayerGame {
	//Object state: Instance variable and constants
	final private int MIN_COIN_SIZE = 3; // Smallest legal amount of coins
	final private int MAX_COIN_SIZE = 10; // Largest legal amount of coins

	final private int MIN_JUMP_SIZE = 1; // Smallest legal amount of move
	final private int MAX_JUMP_SIZE = 5; // Largest legal amount of move

	private int[] coins; //Array holding coins (board)
	private int coinsNum; //Number of coins in the game
	private int coinSpace; //Number of spaces on the board
	private int coinIndex; //Keep track of index (position) of coins
	private int turn; //Keep track of player turns

	private Random rng = new Random(); //For creating coins

	//add the constructor
	public CoinStrip(){
		//populate the array of coins
		coinsNum = MIN_COIN_SIZE + rng.nextInt(MAX_COIN_SIZE - MIN_COIN_SIZE +1);
		coins = new int[coinsNum];
		//create the first coin
		coins[0] = rng.nextInt(MAX_JUMP_SIZE);
		for (int i=1; i<coins.length; i++){
		// prevents overlap of coins position
		coins[i] = coins[i-1] + rng.nextInt(MAX_JUMP_SIZE) + 1;
	}
	coinSpace = coins[coins.length-1];
}

	public String toString() {
		//print the gameboard
		int coinIndex = 0;
		String board = "";
		for (int i=0; i<=coinSpace; i++){
			//set up a boolean value to examine if a coin exists on the board position
			boolean didIputcoin = false;
			for (int j=0; j<coins.length;j++){
				//if a coin exists in the array, put the coin on the board
				if (coins[j] == i){
					board += j;
					didIputcoin = true;
			 }
			}
			//populate the board with spaces if a coin doesn't exist
			if (!didIputcoin){
				board += "_";
	  }
	}
		return board;
}


	// This method checks whether the user input could be executed or not according to the game's rules.
	// @param whichCoin the number of the coin to move
	// @param numSpaces number of spaces to move the coin (left)
	// @return true if the move is valid
	public boolean isValidMove(int whichCoin, int numSpaces) {
		//Check if a move is valid
		//Check if there is space on the board to move the coin
		if(numSpaces <= 0){
			return false;
		}
		//Check if whichCoin is a valid input
		if (whichCoin >= coins.length){
			return false;
		}
		//Check if numSpaces is a valid input
		int coinIndex = coins[whichCoin];
		if (coinIndex - numSpaces < 0){
			return false;
		}
		//Check if the coin will overlap(=) or jump over(<) the previous coin
		if (whichCoin!=0){
			if (coinIndex - numSpaces <= coins[whichCoin-1]){
				return false;
			}
		}
		return true;
	}

	// This method updates the gameboard to reflect a move.
	public void makeMove(int whichCoin, int numSpaces) {
		//moves the coin by subtracting numSpaces from its original position
		coins[whichCoin]=coins[whichCoin]-numSpaces;
	}

	// This method checks if the game is over.
	public boolean isGameOver() {
		//check if rightmost coin's position corresponds to its index
		if (coins[coins.length-1] == coins.length-1){
			return true;
			}
		return false;
	}

	// Returns the number of coins on the CoinStrip gameboard.
	public int getNumCoins() {
		return coinsNum;
	}

	// Returns the 0-indexed location of a specific coin.
	public int getCoinPosition(int coinNum) {
		return coins[coinNum];
	}

    // This main method implements the functionality to play the Coinstrip game.
		public static void main(String[] args) {
	    System.out.println("Welcome to the Silver Dollar Game!");
			CoinStrip newGame = new CoinStrip();
			System.out.println(newGame);
			int turn = 0; //initialize the turns variable to keep track of player turns
			while(!newGame.isGameOver()){
				int whichCoin, numSpaces;
				if (turn % 2 == 0){
					System.out.println("Player 1, your move!");
				} else {
						System.out.println("Player 2, your move!");
				}
				Scanner in = new Scanner(System.in);
				System.out.println("Enter the number of the coin you wish to move");
				whichCoin = in.nextInt();
				System.out.println("Enter the number of spaces you wish to move");
				numSpaces = in.nextInt();

				if(newGame.isValidMove(whichCoin, numSpaces)){
					newGame.makeMove(whichCoin, numSpaces);
					turn++;
					System.out.println(newGame);
				} else {
					System.out.println("Illegal move, try again :(");
				}
			}
			if(newGame.isGameOver()){
				if (turn % 2 == 1){
					System.out.println("Game Over! Player 1 wins");
				} else {
					System.out.println("Game Over! Player 2 wins");
				}
			}
		}
	}
