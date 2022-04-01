import structure5.*;
import java.util.Scanner;
import java.util.Iterator;

/**
* Class that takes a file of students, reads in their classes
* and figures out an exam schedule with no conflicts (two finals at the
* same time for a single student).
*/
public class ExamScheduler {

  /**
  * Method to read in file
  * @param fileName file being passed into Scanner
  * @return Vector<Student> with all students in file
  * @pre file fileName is formatted correctly
  * @post vector of students created with 4 classes and a name
  */
  public static Vector<Student> readInFile(String fileName) {
    //initiate vector to store students we create
    Vector<Student> students = new Vector<Student>();
    //create new scanner
    Scanner sc = new Scanner(new FileStream(fileName));
    //while there is still stuff
    while (sc.hasNext()) {
      //initalize a student
      Student s = null;
      //iterate over the next 4 lines
      for (int i = 0; i < 5; i++) {
        //you add the first line as the students name
        if (i == 0) {
          s = new Student(sc.nextLine());
          //the for 4th index is the last course for this student so you add the course
          //and then you add the student to the vector
        } else if (i == 4) {
          s.addCourse(sc.nextLine());
          students.add(s);
          //all other cases are just adding courses
        } else {
          s.addCourse(sc.nextLine());
        }
      }
    }
    return students;
  }

  /**
  * Creates a graph based on the students read in from file
  * @param students Vector<Student> with all their relevant information
  * @return GraphListUndirected<String,Integer> containing all the vertices and
  * edges as specified from our vector students
  * @pre students formatted correctly and has no nulls
  * @post graph created with correct info
  */
  public static GraphListUndirected<String, Integer> makeGraph(Vector<Student> students) {
    //initalize undirected graph list
    GraphListUndirected<String, Integer> studentGraph = new GraphListUndirected<String, Integer>();
    //add vertices
    for (int k = 0; k < students.size(); k++) {
      //set our current student to be the kth in our vector
      Student currentStudent = students.get(k);
      //get course from our current student and store in vector
      Vector<String> classes = currentStudent.getCourses();
      //iterate through currentStudent's classes
      for (int f = 0; f < classes.size(); f++) {
        //if class isn't already in graph
        if (!studentGraph.contains(classes.get(f))) {
          //we add it as a vertex
          studentGraph.add(classes.get(f));
        }
      }
      for (int i = 0; i < 4; i++) {
        //iterate past the edge we are comparing to
        for (int j = i + 1; j < 4; j++) {
          //if there isn't an edge between the two already
          if (!studentGraph.containsEdge(classes.get(i), classes.get(j))) {
            //we add one
            studentGraph.addEdge(classes.get(i), classes.get(j), 1);
          }
        }
      }
    }

    return studentGraph;
  }
  /**
  * Algorithm to calculate the exam schedule based on graph created
  * @param sGraph undirected graphList containing the vertices(classes) and edges(shared classes)
  * @return Vector<Vector<String>> of the answer vectors for each time slot
  * @pre graphlist is correct and complete
  * @post a possible solution with no conflicts is returned (not necessarily optimal though)
  */
  public static Vector<Vector<String>> greedyAlgorithm(GraphListUndirected<String, Integer> sGraph) {
    //store result
    Vector<Vector<String>> answers = new Vector<Vector<String>>();
    //while graph is not empty
    while (!sGraph.isEmpty()) {
      //create vector of strings
      Vector<String> l = new Vector<String>();
      //create iterator
      Iterator<String> graphIter = sGraph.iterator();
      //add vertex
      String vertex = graphIter.next();
      //add our vertex to our vector
      l.add(vertex);
      //iterate over our graph
      while (graphIter.hasNext()) {
        //store temp
        String temp = graphIter.next();
        boolean edgeNotContained = true;
        //for every item currently in list
        for (int j = 0; j < l.size(); j++) {
          //if the item in list shares an edge with our temp
          if (sGraph.containsEdge(l.get(j), temp)) {
            //set edge not contained to false and stop the checks
            edgeNotContained = false;
            break;
          }
        }
        //if the edge is not contained
        if (edgeNotContained) {
          //add our temp to our list
          l.add(temp);
        }

      }
      //for loop to remove all the classes we just scheduled
      for (int i = 0; i < l.size(); i++) {
        sGraph.remove(l.get(i));
      }
      //add our vector to c
      answers.add(l);
    }
    //print helper
    greedyAlgorithmPrint(answers);
    return answers;
  }

  /**
  * Print helper for our Algorithm
  * @param v Vector of vector of strings containing our schedule
  */
  public static void greedyAlgorithmPrint(Vector<Vector<String>> v) {
    System.out.println("GREEDY EXAM SCHEDULE: ");
    //iterate through out vector of vectors
    for (int i = 0; i < v.size(); i++) {
      //set a temp to be the current vector
      Vector<String> temp = v.get(i);
      //print out the slot
      System.out.println("Slot " + i + ": ");
      //print everything in the slot
      for (int j = 0; j < temp.size(); j++) {
        System.out.println(temp.get(j));
      }
      System.out.println("");
    }
  }

  /**
  * helper method for printing our Extension 1: the final exam schedule ordered by course name/number
  * @param answers Vector<Vector<String>> of the answer vectors for each time slot
  * @return answerExtension String of the comparable association between classes and timeslots
  */
  public static String sortExtension(Vector<Vector<String>> answers) {
    //make a new ordered vector for our associations between classes and timeslots
    OrderedVector<ComparableAssociation<String, Integer>> sortVector = new OrderedVector<ComparableAssociation<String, Integer>>();
    for (int k = 0; k < answers.size(); k++) {
      for (String s : answers.get(k)) {
      //make comparable association between classes and timeslots, and add then to the ordered vector
      ComparableAssociation<String, Integer> comparable = new ComparableAssociation<String, Integer>(s, k);
      sortVector.add(comparable);
    }
  }
  String answerExtension = "";
  //print out each association in a clear manner
  for (ComparableAssociation<String, Integer> c : sortVector) {
    answerExtension += c.getKey() + ": " + c.getValue() + "\n";
  }
  return answerExtension;
}

  /**
  * Main method for our class
  */
  public static void main(String[] args) {
    String fileName = args[0];

    Vector<Student> s = readInFile(fileName);

    GraphListUndirected<String, Integer> v = makeGraph(s);
    Vector<Vector<String>> answers = greedyAlgorithm(v); //all of the timeslots
    String answerExtension = sortExtension(answers);
    System.out.println("Extension 1:" + "\n" + answerExtension);
  }
}
