import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

//This class takes objects of LexiconNode and add them to the Lexicon using a Trie structure.

public class LexiconTrie implements Lexicon {
  private LexiconNode rootNode = new LexiconNode('_', false); //the root node of Lexicon
  private int num = 0; //counter for number of words in Lexicon

  /**
  * This method adds a word to the Lexicon
  * @param must have a valid String word
  * @pre word must be a string
  * @post returns true or false if word was added
  * @return returns true or false
  */
    public boolean addWord(String word) {
      LexiconNode leaf = rootNode;
      if (word.equals(null)) {
        return false;
      }
      word = word.toUpperCase();
      //iterates through each child node, if the node does not exist adds it to the Lexicon
      for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        if (leaf.getChild(ch) == null) {
          leaf.addChild(new LexiconNode(ch, false));
        }
        leaf = leaf.getChild(ch);
      }

      leaf.setWord(true); //set the word flag to true
      num++; //increment counter
      return true;
    }
    /**
    * This method adds a word from a file to the trie
    * @param must have a valid string filename
    * @pre filename must be a string
    * @post words in a file are added
    * @return returns word count of Lexicon
    */
    public int addWordsFromFile(String filename) {
      Scanner sc = new Scanner(new FileStream(filename));
      while (sc.hasNext()) {
        addWord(sc.nextLine());
      }
      return num;
    }

    /**
    * This method removes a word from the lexicon
    * @param must have a valid string word
    * @pre word must be a string
    * @post returns true or false if word was removed
    * @return returns true or false
    */
    public boolean removeWord(String word) {
      word = word.toUpperCase();
      LexiconNode leaf = rootNode;
      if (word.equals(null)) {
        return false;
      }
      if (!containsWord(word)) {
        return false;
      }
      //search through the Lexicon for each character node of word
      for (int i = 0; i < word.length(); i++) {
        char character = word.charAt(i);
        leaf = leaf.getChild(character);
      }
      leaf.setWord(false); //set the word flaf to false
      num--; //decrease the counter
      return true;
    }


    /**
    * This method counts the number of words in the lexicon
    * @post returns the number of words in the lexicon in an integer
    * @return integer representing number of words in the lexicon
    */
    public int numWords() {
      return num;
    }

    /**
    * This method checks if a word is in the lexicon
    * @param must have a valid string word
    * @pre word must be a string
    * @post returns true or false if word is contained in the Lexicon
    * @return returns true or false
    */
    public boolean containsWord(String word) {
      word = word.toUpperCase();
      return helperContains(word) != null && helperContains(word).isWord(); //use the helper method to determine whether the word exist
    }


    /**
    * This method goes through the trie and returns a LexiconNode
    * @param must have a valid string word
    * @pre word must be a string
    * @post returns the LexiconNode by looping through
    * @return returns a LexiconNode
    */
    private LexiconNode helperContains(String word) {
      LexiconNode leaf = rootNode;
      for (int i = 0; i < word.length(); i++) {
        char character = word.charAt(i);
        if (leaf.getChild(character) == null) {
          return null;
        }
        leaf = leaf.getChild(character);
      }
      return leaf;
    }

    /**
    * This method checks if a prefix is in the lexicon
    * @param must have a valid string prefix
    * @pre prefix must be a string
    * @post returns true or false if prefix is contained in the Lexicon
    * @return returns true or false
    */
    public boolean containsPrefix(String prefix) {
      String word = prefix.toUpperCase();
      return helperContains(word) != null;
    }


    /**
    * This method iterates through the words in the lexicon
    * @post returns a word from the lexicon
    * @return returns a string that is a word in the lexicon
    */
    public Iterator<String> iterator() {
      String wordString = "";
      Vector<String> wordVec = new Vector<String>(num);
      helperFunction(rootNode, wordVec, wordString);
      return wordVec.iterator();
    }

    /**
    * This method is a helper function that contructs a vector of words
    * @param must have a valid string wordString, a valid Vector<String>, and a valid lexiconNode a
    * @pre wordString must be a string, wordVec must be a vector, and LexiconNode must be a node
    * @post modifies wordVec, so it returns a vector of strings that are words
    */
    private void helperFunction(LexiconNode a, Vector<String> wordVec, String wordString) {
      // goes through characters adding them one by one to a string
      // when .isWord() is true add that string to the vector of Words
      // then iterates through the vector of words
      if (!a.equals(rootNode)) {
        wordString = wordString + a.letter;
      }
      if (a.isWord()) {
        wordVec.add(wordString);
      }
      if (!a.vec.isEmpty()) {
        Iterator<LexiconNode> iteratorHelper = a.iterator();
        while (iteratorHelper.hasNext()) {
          helperFunction(iteratorHelper.next(), wordVec, wordString);
        }
      }
    }

//--------------------------------------- OPTIONAL ---------------------------------------

    /**This method suggest corrections for input (UNIMPLEMENTED)
    */
    public Set<String> suggestCorrections(String target, int maxDistance) {
      Set<String> empty = new SetVector<String>();
      return empty;
    }

    /** This method matchs strings to each other (UNIMPLEMENTED)
    */
    public Set<String> matchRegex(String pattern) {
      Set<String> empty = new SetVector<String>();
      return empty;
    }
}
