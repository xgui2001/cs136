//This class is an implementation of lists using doubly linked elements and dummy nodes.

import structure5.*;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E> {

	protected int count; //Number of elements within the list

	protected DoublyLinkedNode<E> head; //Reference to head of the list.

	protected DoublyLinkedNode<E> tail; //Reference to tail of the list.

	//Constructor constructs an empty list
	public LinkedList() {
		head = new DoublyLinkedNode<E>(null); //establish dummy nodes
		tail = new DoublyLinkedNode<E>(null);
		clear(); //the clear method does the same thing so just call the method instead of writing the same code
	}


	//The method determines the number of elements in the list
	public int size() {
		return count;
	}

	//The method determines whether the list is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	//The method removes all elements of the list
	public void clear() {
		head.setNext(tail); ////cut the list short by setting head and tail next to each other
		tail.setPrevious(head);
		count = 0; //deletes all other elements in the list
	}

	//This method is a private routine to add an element after a node.
	//@pre previous is non null
	//@post list contains a node following previous that contains value
	protected void insertAfter(E value, DoublyLinkedNode<E> previous) {
		Assert.pre(!previous.equals(null),"previous is null");
			DoublyLinkedNode<E> insertedNode = new DoublyLinkedNode <E> (value);
			DoublyLinkedNode<E> afterNode = previous.next(); //find the node that is originally in the place of the inserted node
			//rearrange the nodes around to the new node's position
			previous.setNext(insertedNode);
			afterNode.setPrevious(insertedNode);
			insertedNode.setNext(afterNode);
			insertedNode.setPrevious(previous);
			count++;//increment the count for the new element
	}

	//This method is a private routine to remove a node.
	//@pre node is non null
	//@post node node is removed from the list
	protected E remove(DoublyLinkedNode<E> node) {
		Assert.pre(!node.equals(null),"node is null");
		DoublyLinkedNode<E> previousNode = node.previous();
		DoublyLinkedNode<E> afterNode = node.next();
		//rearrange the nodes around the removed position
		previousNode.setNext(afterNode);
		afterNode.setPrevious(previousNode);
		count--;//subtract the element from the count
		return node.value();
	}



	//This method adds a value to the head of the list.
	//@pre value is not null
	//@post adds element to head of list
	public void addFirst(E value) {
		Assert.pre(value != null, "value is null");
		insertAfter(value,head);//call the insertAfter method to insert the node after the dummy head
	}


	//This method adds a value to the tail of the list.
	//@pre value is not null
	//@post adds new value to tail of list
	public void addLast(E value) {
		Assert.pre(value != null, "value is null");
		insertAfter(value,tail.previous());//call the insertAfter method to insert the node after the last node before the dummy tail
		}

	//This method removes a value from the head of the list.
	//@pre list is not empty
	//@post removes first value from list
	public E removeFirst() {
		Assert.pre(!isEmpty(), "List is empty.");
		remove(head.next());//call the remove method to remove the first node after the dummy head
		return head.next().value();//return the value removed from the list.
	}


	//This method removes a value from the tail of the list.
	//@pre list is not empty
	//@post removes value from tail of list
	public E removeLast() {
		Assert.pre(!isEmpty(), "List is empty.");
		remove(tail.previous());//call the remove method tp remove the last element before the dummy tail
		return tail.previous().value();//return the value removed from the list.
	}

	//This method gets a copy of the first value found in the list.
	//@pre list is not empty
	//@post returns first value in list.
	public E getFirst() {
		return head.next().value(); //return the first value after the dummy head
	}

	//This method gets a copy of the last value found in the list.
  //@pre list is not empty
	//@post returns last value in list.
	public E getLast() {
		return tail.previous().value(); //return the last value before the dummy tail
	}

	//This method get the node at the ith position.
	//@pre index is in the range
	//@post returns the indexed node
	public DoublyLinkedNode<E> indexToNode(int i){
		Assert.pre((0 <= i) && (i <= size()), "Index out of range.");
		DoublyLinkedNode<E> before = head;
		DoublyLinkedNode<E> after = head.next();
		if (i==0){
			return head.next();//return the first element if the list is empty
		} else if (i==size()){
			return tail.previous();//return the last element if the list is full
		} else {
			while (i>0){//loop through the list to find the node cooresponding to the index
				before = after;
				after = after.next();
				i--;
			}
			return after;
	}
}

	//This method inserts the value at location i.
	//@pre 0 <= i <= size()
	//@post adds the ith entry of the list to value o
	public void add(int i, E o) {
		insertAfter(o,indexToNode(i));//inserts the value after the indexed node
	}

	//This method removes and returns the value at location i.
	//@pre 0 <= i < size()
	//@post removes and returns the object found at that location.
	public E remove(int i) {
		remove(indexToNode(i));//removes the indexed node
		return indexToNode(i).value();
	}

	//This method gets the value at location i.
	//@pre 0 <= i < size()
	//@post returns the object found at that location.
	public E get(int i) {
		return indexToNode(i).value();//returns the value at the indexed node
	}

	//This method sets the value stored at location i to object o, returning the old value.
 	//@pre 0 <= i < size()
	//@post sets the ith entry of the list to value o, returns the old value.
	public E set(int i, E o) {
		E result = indexToNode(i).value(); //finds the old value at ith node
		indexToNode(i).setValue(o); //sets the ith entry of the list to value o
		return result;
	}

	//This method determines the first location of a value in the list.
	//@pre value is not null
	//@post returns the (0-origin) index of the value, or -1 if the value is not found
	public int indexOf(E value) {
		int i = 0;
		DoublyLinkedNode<E> finger = head.next();
		// search for value or end of list, counting along the way
		while (finger != null && !finger.value().equals(value)) {
			finger = finger.next();
			i++;
		}
		// Now finger points to value, i is index
		if (finger == null) {
			// value not found, return indicator
			return -1;
		} else {
			// value found, return index
			return i;
		}
	}

	//This method determines the last location of a value in the list.
	//@pre value is not null
	//@post returns the (0-origin) index of the value, or -1 if the value is not found
	public int lastIndexOf(E value) {
		int i = size() - 1;
		DoublyLinkedNode<E> finger = tail.previous();
		// search for the last matching value, result is desired index
		while ((finger != null) && !finger.value().equals(value)) {
			finger = finger.previous();
			i--;
		}

		if (finger == null) {
			// value not found, return indicator
			return -1;
		} else {
			// value found, return index
			return i;
		}
	}

	//This method checks to see if a value is within the list.
	//@pre value not null
	//@post returns true iff value is in the list
	public boolean contains(E value) {
		DoublyLinkedNode<E> finger = head.next();
		//search for the matching value within the list
		while ((finger != null) && (!finger.value().equals(value))) {
			finger = finger.next();
		}
		//value found, return true
		return finger != null;
	}

	//This method removes a value from the list.  At most one value is removed.
	//Any duplicates remain. Because comparison is done with "equals," the actual value removed is returned for inspection.
	//@pre value is not null.  List can be empty.
	//@post first element matching value is removed from list
	public E remove(E value) {
		DoublyLinkedNode<E> finger = head.next();
		//search for the matching value within the list
		while ((finger != tail) && !finger.value().equals(value)) {
			finger = finger.next();
		}
		//if value is found, call the remove method to remove the value from the list
		if (finger != tail){
			return remove(finger);
		}
		//if value is not found, return null
			return null;
	}

	//Construct an iterator to traverse the list.
	//@post returns iterator that allows the traversal of list.
	//@return An iterator that traverses the list from head to tail.
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(head,tail);
	}

	//Construct a string representation of the list.
	//@post returns a string representing list
	//@return A string representing the elements of the list.
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("<LinkedList (" + count + "):");

		Iterator<E> li = iterator();
		while (li.hasNext()) {
			s.append(" " + li.next());
		}
		s.append(">");

		return s.toString();
	}
}
