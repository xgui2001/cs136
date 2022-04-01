import structure5.*;
import java.util.Iterator;

/*
* Creates a LexiconNode class that will be the basis for LexiconTrie for storage of word characters
* You may add helper methods and member variables as you see fit.
*/
class LexiconNode implements Comparable<LexiconNode> {

    /* single letter stored in this node */
    protected char letter;

    /* true if this node ends some path that defines a valid word */
    protected boolean isWord;

    /* a vector that keeps track of the children of this LexiconNode */
    protected Vector<LexiconNode> vec;

    /**
    * Constructor
    * @param letter, isWord
    * @post initiate a LexiconNode object
    */
    public LexiconNode(char letter, boolean isWord) {
      this.letter = letter;
      this.isWord = isWord;
      this.vec = new Vector<LexiconNode>();
    }
    /**
    * This method sets a nodes flag to true or false
    * @param boolean t
    * @pre t must be a bolean
    * @post sets isWord as true or false
    */
    public void setWord(boolean t) {
      isWord = t;
    }

    /**
    * This method checks if a node is a word or not
    * @param
    * @pre must have a valid node
    * @post returns a boolean based on the node flag
    * @return returns true or false
    */
    public boolean isWord() {
      return isWord;
    }
    /**
    * This method compares this LexiconNode to another.
    * @param o
    * @pre o is not null
    * @post returns an int that indicates their position based on their index
    */
    public int compareTo(LexiconNode o) {
      //compares a node character to another node character based on index
      if (o == null) {
        return 0;
      } else {
        return this.letter - o.letter;
      }
     }

     /**
     * This method compares two LexiconNodes.
     * @param o, a
     * @pre o and a are both not null
     * @post returns an int that indicates their position based on their index
     */
     private int realCompareTo(LexiconNode o, LexiconNode a) {
       //compares a node character to another node character based on index
       if (o == null || a == null) {
         return 0;
       }
       return a.letter - o.letter;
     }

    /**
    * This method adds LexiconNode child to correct position in child data structure.
    * @param ln
    * @pre ln is not null
    * @post the LexiconNode child is added to the correct position in the vector
    */
    public void addChild(LexiconNode ln) {
      if (ln != null) {
        int i;
        for (i = 0; i < vec.size(); i++) {
          if (realCompareTo(ln, vec.get(i)) > 0) {
            break;
          }
        }
        vec.add(i, ln);
      }
    }

    /**
    * This method returns a child based on the character
    * @param a valid character
    * @pre ch is not null
    * @post returns the node based in the character
    * @ return resturns the node, which contains the character ch
    */
    public LexiconNode getChild(char ch) {
      // if the child is not already in the vec dont add it
      for (int a = 0; a < vec.size(); a++) {
        LexiconNode node = vec.get(a);
        if (node.letter == ch) {
          return node;
        }
      }
      return null;
    }

    /**
    * This method removes a LexiconNode child with the character ch from the child data structure
    * @param ch
    * @pre ch is not null
    * @post LexiconNode child is removed from the vector
    */
    public void removeChild(char ch) {
      vec.remove(getChild(ch));
    }

    /**
    * This method creates an Iterator that iterates over children in alphabetical order
    * @post iterator that iterates through the children in alphabetical order is created
    */
    public Iterator<LexiconNode> iterator() {
      //use compareTo to sort vector alphabetically
       return vec.iterator();
     }
}
