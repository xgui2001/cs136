# Lexicon Thought Questions

 1. For each node in the trie, you need to store pointers to its children nodes. What data structure did you use to store the pointers to children nodes? Justify the choice you made.
   * Data structure choice: Vector
   * Justification: vector is memory efficient; easier to traverse through the whole list (through iterator), so we add stuff to the end of the list faster; usage of get and set method makes it easier to traverse through the vector & retrieve letters at the index. Also the vector size is dynamic and supports growing as opposed to the array, which do not support growing and have a fixed size.

2. Suppose we use an `OrderedVector` instead of a trie for storing our lexicon. Discuss how the process of searching for suggested spelling corrections would differ from our trie-based implementation. Which is more efficient? Why?
   * Searching in a trie: find the first character of the word, and walk through each child node (subsequent characters) eg: apple: a -> p -> p -> l -> e
   * Searching in an `OrderedVector`: search through the whole vector for the exact word eg. apple
   * Which is more efficient? trie; takes up less space due to only storing different characters, faster traversing through the list of words, and the possibility of implementing a suggestion/correction method.
