import structure5.*;
import java.util.Comparator;

public class MyVector<E> extends Vector<E> {

  /**
  * Constructor for MyVector
  */
  public MyVector() {
    super();
  }


  /**
  * Swap method that is later used in sort
  * @param i int index for first value being swapped
  * @param j int index for second value being swapped
  * @pre vector has at least i or j indices depending on which is bigger
  * @post values at position i and j are swapped in vector
  */
  public void swap(int i, int j) {
    //set temp to store j for swap
    E temp = this.get(j);
    //swap values for i and j
    this.set(j, this.get(i));
    this.set(i, temp);
  }

  /**
  * Finds the position of the max value in a Vector
  * Used to help in our sort
  * @param c comparator used to compare info in the Vector
  * @param last last index
  * @pre c not null
  * @post returns max position our current vector
  * @return int index of the max position
  */
  public int findPosOfMax(Comparator<E> c, int last) {
    int maxPos = 0;
    //iterates through our vector
    for (int i = 1; i <= last; i++) {
      //if value is greater than our current max
      if (c.compare(this.get(maxPos), this.get(i)) < 0) {
        //set maxPos to be the new index i
        maxPos = i;
      }
    }
    return maxPos;
  }

  /**
  * Selection sort method for our vector
  * @param c a comparator that is used to order objects in this Vector
	* @pre c is non-null
	* @post this vector's contents are ordered as determined by c
  */
  public void sort(Comparator<E> c) {
    for (int i = this.size() - 1; i > 0; i--) {
      int big = findPosOfMax(c, i);
      swap(i, big);
    }
  }




  /**
  * Main method
  */
  public static void main(String[] args) {
    //initialize vector of students
    MyVector<Student> v = new MyVector<Student>();

    //create a bunch of test students
    Student s1 = new Student("Tom", "10 Hopkins Drive", 10, 2759, 10);
    Student s2 = new Student("Jeffery", "10 Hopkins Drive", 10, 2000, 10);
    Student s3 = new Student("William", "10 Hopkins Drive", 10, 3000, 10);

    //add to our vector
    v.add(s1);
    v.add(s2);
    v.add(s3);

    //sort by Name and then print our sorted vector
    System.out.println("Name Comparison: ");
    v.sort(new NameComparator());
    System.out.println(v);
    System.out.println("\n");
    //sort by SuBox and then print our sorted vector
    System.out.println("Su Box Comparison: ");
    v.sort(new SuBoxComparator());
    System.out.println(v);
    System.out.println("\n");
    //sort by vowel number and then print sorted vector
    System.out.println("Vowel Comparison: ");
    v.sort(new VowelComparator());
    System.out.println(v);
    System.out.println("\n");
  }

}
/**
* Comparator that compares names in alphabetical order
*/
class NameComparator implements Comparator<Student> {
  public int compare(Student a, Student b) {
    String firstName = a.getName().toLowerCase();
    String secondName = b.getName().toLowerCase();
    return firstName.compareTo(secondName);
  }
}
/**
* Comparator for SU box numbers
*/
class SuBoxComparator implements Comparator<Student> {
  public int compare(Student a, Student b) {
    Integer aNum = a.getSuBox();
    Integer bNum = b.getSuBox();
    return aNum.compareTo(bNum);
  }
}
/**
* Comparator for vowels
*/
class VowelComparator implements Comparator<Student> {
  public int compare(Student a, Student b) {
    Integer countA = vowelCounter(a.getName().toLowerCase());
    Integer countB = vowelCounter(b.getName().toLowerCase());
    return countA.compareTo(countB);
    }
  /**
  * helper method for VowelComparator
  */
  public int vowelCounter(String name) {
    int count = 0;
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == 'a' || name.charAt(i) == 'e' || name.charAt(i) == 'i' || name.charAt(i) == 'o' || name.charAt(i) == 'u' ) {
        count += 1;
      }
    }
    return count;
  }
}
