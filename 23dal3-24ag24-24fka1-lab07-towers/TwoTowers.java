
/*
* A class that solves the two towers problem by printing the
* height divided by two then find all subsets that are == or less h/2
* find tallest
*/
import structure5.*;
import java.util.Scanner;

public class TwoTowers {

  private static double height; // the h/2 height
  private static Vector<Double> best = new Vector<Double>(); //the best subset
  private static Vector<Double> second = new Vector<Double>(); //the second best subset


  /**
   * determines the desired height of the stacks h/2
   * @param blocks must be an int
   * @pre must have a valid integer of blocks
   * @return the desired h/2 height as a double
   */
  private static double height(int blocks) {
    // base case no blocks
    if (blocks == 0) {
      return 0;
    } else {
      // recursively return the desired height for the stacks
      return (Math.sqrt(blocks) * 0.5) + height(blocks - 1);
    }
  }

  /**
   * converts the face values of the blocks
   * to their side lengths
   * @param blocks must be an int
   * @pre must have a valid integer of blocks
   * @return a vector of doubles containing the side lengths
   */
  private static Vector<Double> intToVec(int blocks) {
    // Iterator Input cause Iterator takes A Vector
    Vector<Double> sideLen = new Vector<Double>();
    // calculate the side lengths of the blocks
    for (int i = 1; i <= blocks; i++) {
      sideLen.add(Math.sqrt(i));
    }
    // return a vector full of side length of the blocks
    return sideLen;
  }

  /**
   * computes the sum of the values in the vector
   * @param vector must be of type double and not null
   * @pre must have a vector of type double
   * @return the sum of the vector as the height of the tower
   */
  private static Double summation(Vector<Double> vector) {
    double sum = 0;
    // calculate the sum of the blocks' side lengths
    for (int i = 0; i < vector.size(); i++) {
      sum += vector.get(i);
    }
    // return the sum of the blocks' side lengths in the subset vector
    return sum;
  }


  /**
   * finds the best and second best (left) stack
   * @param sides must be a vector of type double
   * @pre needs a non null vector of doubles
   * @return an association of vectors with the best as
   * the key and second best as the value
   */
  private static Association<Vector<Double>, Vector<Double>> stack(Vector<Double> sides) {
    // create the subset iterator to yield the next subsets
    SubsetIterator<Double> subset = new SubsetIterator<Double>(sides);
    // max height we have seen so far
    double maxSoFar = 0;
    // the subset vector we compare with
    Vector<Double> current = subset.next();
    // check all the subsets
    while (subset.hasNext()) {
      // check conditions that determines the best subset
      if (summation(current) <= height && summation(current) >= maxSoFar) {
        maxSoFar = summation(current);
        second = best;
        best = current;
      // check conditions that determine the second best subset
      } else if (summation(current) <= height && summation(second) < summation(current)) {
        second = current;
      }
      // check the next subset
      current = subset.next();
    }
    Association<Vector<Double>, Vector<Double>> pair = new Association<Vector<Double>, Vector<Double>>(best, second);
    // return the two best subsets stored in the pair association
    return pair;

  }

  /**
   * converts the side lengths in the vector to
   * the original integer values
   * @param answer vector as a vector of type double and not null
   * @pre answer must be a vector of type doubles and not null
   * @return a vector with integers representing the blocks'
   * original values
   *
   */
  private static Vector<Integer> ans(Vector<Double> answer) {
    // vector of integers representing the block area
    Vector<Integer> blockFace = new Vector<Integer>();
    // turn the doubles into integers for their areas
    for (Double block : answer) {
      double face = block * block;
      blockFace.add((int) Math.round(face));
    }
    return blockFace;
  }

  /**
   * prints a string representation of the stack
   * @param answer must be a vector of integers and not null
   * @pre answer is a vector of integers and not null
   * @return returns a string representation of block tower
   */
  private static String printAns(Vector<Integer> answer) {
    String string = "|";
    for (int i = 0; i < answer.size(); i++) {
      string += answer.get(i) + "|";
    }
    return string;
  }



  /**
   * main method to print the final output
   *
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num = 0; //in.nextInt();
    if (args.length < 1) {
      System.out.println("Provide a number of blocks in the command line");
      System.exit(0);
    } else {
      num = Integer.parseInt(args[0]);
    }
    height = height(num);
    best = stack(intToVec(num)).getKey();
    second = stack(intToVec(num)).getValue();
    System.out.println("There are " + num + " total blocks.");
    System.out.println("The half height (h/2) is: " + height);
    System.out.println("The best subset (left stack) is: " + printAns(ans(best)) + " = " + summation(best));
    System.out.println("The second best subset (left stack) is: " + printAns(ans(second)) + " = " + summation(second));

  }


}
