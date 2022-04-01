import structure5.*;
import java.util.Random;
import java.util.Scanner;

// The Table class holds a collection of strings, each of which has an associated FrequencyList
public class Table {
  protected Vector<Association<String, FrequencyList>> tableList;

  // Construct an empty Table
  public Table() {

    String key; // The key will be each k-letter string in the text, that we will create a FrequencyList for

    tableList = new Vector<Association<String, FrequencyList>>(); // Make a new vector of the Association between the key and FrequencyList

  }

  // The add method updates the Table
  public void add(String key, char value) {
    int loc = tableList.indexOf(new Association<String,FrequencyList>(key,null)); // Find the location of the key in the table
    if (loc > -1) { // If key already exists in the table, update its FrequencyList by adding value to it
      FrequencyList locFreq = tableList.get(loc).getValue();
      locFreq.add(String.valueOf(value));
    }
    else { // Otherwise, create a new FrequencyList for the key and add value to it
      FrequencyList locFreq = new FrequencyList();
      locFreq.add(String.valueOf(value));
      tableList.add(new Association<String,FrequencyList>(key,locFreq));
    }
  }

  // The choose method returns the key in the Table from its FrequencyList with probability equal to its relative frequency
  public char choose(String key) {
      int loc = tableList.indexOf(new Association<String,FrequencyList>(key,null)); // Look through each entry in the Table
      if (loc > -1){ // Finds the key in the table
        return tableList.get(loc).getValue().choose(); // Return the key
    }
    return '\0'; //special case; should not happen
  }


  // The toString method produces a string representation of the Table
  public String toString() {
    String tableResult = "";

    for (int i = 0; i < tableList.size(); i++) {
      Association<String,FrequencyList> pair = tableList.get(i);
      tableResult += pair.getKey() + ": " + pair.getValue(); // prints out the Table in a "x:y" format
    }
    return tableResult;
  }


  // The main method is used for testing
  public static void main(String[] args) {

  }

}
