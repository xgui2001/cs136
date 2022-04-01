import structure5.*;

/**
* An iterator that yields all the subsets of a vector
*/
public class SubsetIterator<E> extends AbstractIterator<Vector<E>> {

  private long currentSubset; // the int value for our binary subset
  private Vector<E> towers;

  /**
   * constructor that defines the variables
   * @param vec must be a vector and not null
   * @pre needs a vector of objects
   */
  public SubsetIterator(Vector<E> vec) {
    towers = vec;
    reset();
  }

  /**
   * resets the iterator to the beginning
   * @post reset the iterator
   */
  public void reset() {
    currentSubset = 0;
  }

  /**
   * determines if there is a next iteration
   * @pre needs to have currentSubset is an integer and not equal to null
   * @return a true or false based on whether the subset has a next object
   */
  public boolean hasNext() {
    return currentSubset < (1L << towers.size());
  }

  /**
   * yields the current iteration it is considering
   * @pre currentSubset is an int and not null
   * @return the vector of the current iteration
   */
  public Vector<E> get() {
    Vector<E> newSub = new Vector<E>();
    for (int i = 0; i < towers.size(); i++) {
      // if the number is a 1, add it to newSub
      if ((currentSubset & (1L << i)) > 0) {
        newSub.add(towers.get(i));
      }

    }
    return newSub;
  }

  /**
   * yields the next iteration
   * @pre currentSubset is an int and not null
   * @return gives the next subset in the vecotr of subsets
   */
  public Vector<E> next() {
    Vector<E> subset = get();
    currentSubset++;
    return subset;
  }

  /**
   * main method for testing the iteration functionality
   */
  public static void main(String[] args) {
    Vector<Integer> tester = new Vector<Integer>(8);
    SubsetIterator<Integer> test = new SubsetIterator<Integer>(tester);
    for (int i = 0; i < 8; i++) {
      tester.add(i);
    }
    System.out.println(tester);
    int count = 0;
    while (test.hasNext()) {
      count++;
      System.out.println(test.next());
    }
    System.out.println(count);
  }


}
