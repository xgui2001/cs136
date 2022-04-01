import structure5.*;
import java.util.Random;
import java.util.Scanner;

// The class WordGen takes in an integer and uses it to generate a random ramble based on an analysis of the frequencies each integer pair of letters occurs in the input text.
public class WordGen {

  protected int k; // The level of analysis
  protected String word; // The starting word
  protected char letter; // The next character after the substring
  protected String genWord; // The actual generated word
  protected char nextCh; // The next character to be added to the generated text
  protected Table letterTable; // Initialize an empty Table
  protected String text; // The input text

  // The constructor
  public WordGen(int k){
  }

	// The readText Method reads the text and puts it into a string
  public String readText(){
  Scanner in = new Scanner(System.in);
  	StringBuffer textBuffer = new StringBuffer();
  	while (in.hasNextLine()){ // check if the text has ended
  	    String line = in.nextLine();
  	    textBuffer.append(line); // formats the text
  	    textBuffer.append("\n");
  	}
  	text = textBuffer.toString();
    return text;
  }

  //The addToTable class populates the table based on input text
  public void addToTable(Table letterTable, int k, String text){
    for (int i = 0; i<text.length()-k; i++){
      String word = text.substring(i,i+k);
      char letter = text.charAt(i+k);
      letterTable.add(word,letter);
  }
}
  // The printLines class generates the ramble text based on text in the Table
  public String printLines(Table letterTable, int k, String text){
    String result = "";
    String key = text.substring(0,k);
    result += key;
	// Create the text based on k-number of characters
	for (int i = 0; i<text.length()-k; i++) {
    String genWord = result.substring(i,i+k);
		char nextCh = letterTable.choose(genWord);
    result += nextCh;
    if (nextCh == '\0'){ //special case; should never happen
      break;
    }
  }
  return result;
}

  // The main method runs every methods in the WordGen class
  public static void main(String[] args) {
    if (args.length == 0) {
	    // no args, so print usage line
	    System.out.println("Usage: java WordGen k");
    	} else {
        // convert the first input to k
        int k = Integer.valueOf(args[0]);
        WordGen newWordGen = new WordGen(k); // create a new instance of the WordGen class
        Table letterTable = new Table(); // create a new instance of the Table class
        String text = newWordGen.readText();
        text+=text.substring(0,k); // avoid the special case by adding starting k characters to text
        newWordGen.addToTable(letterTable,k,text);
        System.out.println(newWordGen.printLines(letterTable,k,text)); // print the resulting text
	}
    }
    }
