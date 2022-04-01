Questions (from the text):

1. The three-parameter constructor for `DoublyLinkedNode` makes use of two `if` statements.
Suppose that you instead replace the calls to the three-parameter `DoublyLinkedNode` constructor with the one-parameter `DoublyLinkedNode` constructor,
and then you manually use `setNext` and `setPrevious` to set the appropriate references.
The `if` statements that were present in the three-parameter constructor disappear from the resulting code. Why?

    Because the setNext and setPrevious already set the next and previous node, there isnt a need to do a if loop that concerns the placement of the next or previous node in the code.

2. The `contains` method can be written making use of the `indexOf` method, but not the other way around. Why?

    Indexof method finds the first element that contains the value in the list. contains method could just call indexof and if it returns the value, contains returns true, and if it returns -1 contains returns false. However contains method just checks whether the value is in the list, it doesnt give any information about the placement of the element within the list; you'd have to do the loop again to actually find the index, which doesnt really help.

3. Notice that we could have replaced the method `insertAfter` with a similar method, `insertBefore`.
This method would insert a new value before the indicated node. Some changes would have to be made to your code, but those changes seem relatively straightforward.
There does not appear, however, to be a choice between versions of `remove`. Why is this the case?
(Hint: Do you ever pass a dummy node to `remove`?)

   You dont remove a dummy node, therefore there isnt a need to removeAfter or removeBefore (which would involve the usage of dummy nodes; eg. removeAfter dummyhead, removeBefore dummytail)

4. Even though we don’t need to have the special cases in, for example, the indexed version of `add`,
it is desirable to handle one or more cases in a special way. What are the cases, and why is it desirable?

    When you want to add first or add last because the regular add method will call head or tail which will probably cause potential confusion since these are dummyheads and their value is null

5. Which file is longer (in terms of lines of Java code, excluding comments and whitespace): your final `LinkedList.java` or the original version without "dummy nodes"?
There is no need to calculate exact an line count, just note your choice and explain why.

    The new one, because the dummy node removes the need to have special cases for dealing with the first/last elements or with empty lists; this is because they make sure that there will always be at least at least one node.
