import structure5.*;

/**
 * An iterator that yields the consecutive characters of a String in order
 */
public class CharacterIterator extends AbstractIterator<Character> {

	private char current;
	private int pos;
	private String word;

	/**
	 * Constructor that defines the variables
	 * @param str must be a string and not null
   * @pre needs a string
	 */
	public CharacterIterator(String str) {
		pos = 0; //position in string
		word = str;
		reset();
	}

	/**
	 * determines if there is a next iteration
	 * @returns a true or false whether there is a character next
	 */
	public boolean hasNext() {
		return pos < word.length();
	}

	/**
   * yields the next iteration
	 * @return iterates to the next character in string
   */
	public Character next() {
		current = get();
		pos++;
		return current;
	}

	/**
   * resets the iterator to the beginning
	 * @post reset the iterator
   */
	public void reset() {
		current = word.charAt(0);
	}

	/**
   * yields the current iteration it is considering
	 * @post return the current iteration
	 * @return the current character in the string
   */
	public Character get() {
		current = word.charAt(pos);
		return current;
	}

	/**
   * main method for testing the iteration functionality
   */
	public static void main(String[] args) {
		CharacterIterator ci = new CharacterIterator("Hello world!");
		for (char c : ci) {
				System.out.println(c);
		}
	}

}
