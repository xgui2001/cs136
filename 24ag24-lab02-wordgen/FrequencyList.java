// This class creates a vector of FrequencyList that stores a set of characters, each of which has an associated integer frequency.

import structure5.*;
import java.util.Random;

public class FrequencyList {
  protected Vector<Association<String, Integer>> tableLetters; // Store the frequencies of the letters
  protected int total; //Keeping track of the number of times the key appears in the text
  protected Random r; // Random number generator

  //Construct an empty FrequencyList
  public FrequencyList() {
    r = new Random();

    total = 0; // Start the count at 0

    tableLetters = new Vector<Association<String, Integer>>(); // Make a new vector
    String ch; // The character we will be adding to the Vector
  }

  // The add method increments String ch's associated frequency if ch is in the FrequencyList, otherwise adds ch to FrequencyList with frequency 1
  public void add(String ch) {

    Association<String,Integer> letterInfo = new Association<String,Integer>(ch,1);// Build a new association starting at letter ch

    // Search for character in tableLetters
    // If found, update its frequency
    int loc = tableLetters.indexOf(letterInfo);
    if(loc > -1) {
      // get the association
      letterInfo = tableLetters.get(loc);
      // update the getValue
      letterInfo.setValue(letterInfo.getValue() + 1);
      total+=1;
    } else { // otherwise, add it to tableLetters vector
      tableLetters.add(letterInfo);
      total+=1;
    }
  }


  // The choose method returns a character from the FrequencyList with probability equal to its relative frequency
  public char choose() {
    int num; // Initialize random number
    num = r.nextInt(total)+1; // Pick a random number less than or equal to the number of times each new char appeared in the phrase
    String ch = "n"; // Initialize ch
    int sum = 0; // Initialize sum of value of frequency

    for (int i = 0; i < tableLetters.size(); i++) { // Look through each entry in the FrequencyList
      sum += tableLetters.get(i).getValue(); // Increment the sum by each entry's frequency values
      if (sum >= num) {
        ch = tableLetters.get(i).getKey(); // Use the character if the frequency associated with the entry is larger than or equal to the sum
        break;
      }
    }
    return ch.charAt(0);
  }

  // The toString method produces a string representation of the FrequencyList
  public String toString() {
    String output = "";
    for (Association<String,Integer> letterInfo : tableLetters) {
      output = output + letterInfo.getKey()+" occurs "+letterInfo.getValue()+" times."; // print out the accumulated word frequencies in a "x occurs y times" format
    }
    return output;
  }

  // The main method is used for testing
  public static void main(String[] args) {

  }

}
