import java.util.Scanner;
import structure5.*;

/**
* Class to create student objects
*/
public class Student {

  //instance variables
  private String studentName;
  private Vector<String> studentCourses;

  /**
  * Constructor w/o Vector passed in
  * @param name String of students name
  */
  public Student(String name) {
    studentName = name;
    studentCourses = new Vector<String>();
  }

  /**
  * Constructor w/ Vector passed in
  * @param name String of students name
  * @param course Vector of strings with courses in it
  */
  public Student(String name, Vector<String> course) {
    studentName = name;
    studentCourses = course;
  }

  /**
  * get method for students Name
  * @return String with students name
  */
  public String getName() {
    return studentName;
  }

  /**
  * set method for students Name
  * @param name string of name
  */
  public void setName(String name) {
    studentName = name;
  }

  /**
  * get method for the Vector courses
  * @return Vector<String> with courses
  */
  public Vector<String> getCourses() {
    return studentCourses;
  }

  /**
  * adds a course to the studentCourses vector
  * @param course String of course name
  */
  public void addCourse(String course) {
    studentCourses.add(course);
  }

  /**
  * Main method for Student class
  */
  public static void main(String[] args) {
    Vector<String> v1 = new Vector<String>();
    v1.add("CS 136");
    v1.add("MATH 250");
    v1.add("HIST 200");
    v1.add("ENGL 140");

    Student s1 = new Student("Joe", v1);
    System.out.println(s1.getName());
    System.out.println(s1.getCourses());
  }
}
