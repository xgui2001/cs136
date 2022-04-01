# Lab 2 - Random Writing : Thought Questions

_Self Check_ Problem 3.2: What is the difference between the `add(v)` and `add(i,v)` methods of Vector?
  * add(v) adds an element to the end of the array, while add(i, v) adds an element (v) to a particular location (the index i)

_Self Check_ Problem 3.3: What is the difference between the `add(i,v)` method and the `set(i,v)` method?
  * add(i, v) adds an element to that particular location, and shift the other entries over. set(i, v) will set the value stored at i to v, and will not shift any previous entries.

_Self Check_ Problem 3.4: What is the difference between the `remove(v)` method (where `v` is an `Object` value), and the `remove(i)` (where `i` is an `int`)?
  * remove(v) removes the entry in the vector with that value. If there are multiple entries in the vector with that value, the one closest to the end will be removed. remove(i) removes the entry with that index.

Problem 3.6: This question asks about *trade-offs*. Consider a fictional class called `BitVector` that has an interface similar to `Vector`, but the values stored within the `BitVector` are all known to be `boolean` (the primitive type). Why might we choose to write a special-purpose class like `BitVector` instead of using a `Vector<Boolean>`? A good answer will (very) briefly compare any advantages a designer might gain from specialization vs. the advantages of using Java Generics.
  * Since the Vector class stores Object types and not primitive types, all of the booleans need to be converted to Objects in order to use a Vector class, so BitVector would make the process more simple. The boolean primitive type also takes up less memory than the boolean Object.
