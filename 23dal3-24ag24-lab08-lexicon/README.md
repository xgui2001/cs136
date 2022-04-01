# CS136 Lab: Super Lexicon

## Useful Links
* [Course Homepage](https://williams-cs.github.io/cs136-s21-www/index.html) (with TA schedule)
* [Lab Webpage](https://williams-cs.github.io/cs136-s21-www/labs/lexicon.html)
* [structure5](http://www.cs.williams.edu/~bailey/JavaStructures/doc/structure5/index.html) documentation
* [GitHub markdown](https://guides.github.com/features/mastering-markdown/) syntax


## Repository Contents
This repository contains the starter files for writing and testing your trie-based Lexicon.

## Using Main.java to Test

```
$ java Main
```

If you hit the `Enter` key, you will be given a list of options.

|shortcut key|command|syntax|description|
|-|-|-|-|
|`a`|`add`|Add &lt;word&gt;|Add word to lexicon|
|`c`|`contains`|Contains &lt;str&gt;|Search lexicon for word/prefix|
|`rem`|`remove`|Remove &lt;word&gt;|Removes word from lexicon|
|`rea`|`read`|Read &lt;filename&gt;|Add words from named file to lexicon|
|`p`|`print`|Print|Print all words in lexicon|
|`s`|`suggest`|Suggest &lt;target&gt; &lt;dist&gt;|Find suggestions for target within distance|
|`m`|`match`|Match &lt;regex&gt;|Find matches for pattern|
|`q`|`quit`|Quit|Quit the program|
|`i`|`iter`|iter|test iter|

After selecting a command, `Main` will execute the corresponding methods in your `LexiconTrie` code. (This is why it is important to have "stubs" for functions that are not yet implemented.) Test your functionality
incrementally!
