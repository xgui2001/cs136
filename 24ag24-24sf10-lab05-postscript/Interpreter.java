import structure5.*;
import java.util.Iterator;

/**
 * An implementation of a basic PostScript interpreter.
 * STUDENTS, FILL IN THE CONTENTS OF THIS COMMENT WITH A MEANINGFUL
 * BUT CONCISE DESCRIPTION OF THE CLASS
 */
public class Interpreter {
  private StackList<Token> postStack; // constructs empty stack
  private SymbolTable postSymbols; // constructs a new SymbolTable


  public Interpreter() { // Constructor for the Intepreter class
    // initiates the stack of Tokens and symbol table object
    postStack = new StackList<Token>();
    postSymbols = new SymbolTable();
  }

/**
 * This method checks reader objects' token and returns different operations according their types
 * @param reader generated by the reader class
 * @post quit if token's symbol is quit; run doSymbol if token is a Symbol; push the token onto the stack for other cases
 */
  public void interpret(Reader reader) {
    while (reader.hasNext()) {
			Token t = reader.next();
      // checks if user wants to quit PostScript interpreter
      if (t.isSymbol() && t.getSymbol().equals("quit")) {break;}
      // checks the kind Token entered by user
      switch (t.kind()) {
      case Token.SYMBOL_KIND: // considers symbol Token

        if (postSymbols.contains(t.getSymbol().substring(1))) {
          // processes values of procedures/numbers/booleans being defined
          Reader processProcedure = new Reader(postSymbols.get(t.getSymbol().substring(1)));
          interpret(processProcedure);
        } else if (t.getSymbol().substring(0,1).equals("/")) {
            postStack.push(new Token(t.getSymbol())); // pushes /variableName onto stack
        }

      doSymbol(t.getSymbol()); // performs doSymbol operations for all other Symbols
      break;
      // pushes NUMBER_KIND, BOOLEAN_KIND, PROCEDURE_KIND Tokens on stack
      default: postStack.push(t);
      }
    }
  }

/**
  * This method executes different kind of operations on the numbers in the stack based on symbol input
  * @param symbol the symbol token string to check
  * @return true if the operation is executable; false if no symbol input
  */
  public void doSymbol(String symbol) { // helper method for operations
    switch (symbol) {
    case "pstack": pstack(); break;
    case "ptable": pTable(); break;
    case "add": add(); break;
    case "sub": sub(); break;
    case "mul": mul(); break;
    case "div": div(); break;
    case "dup": dup(); break;
    case "exch": exch(); break;
    case "eq": eq(); break;
    case "ne": ne(); break;
    case "def": def(); break;
    case "pop": pop(); break;
    case "if": doIf(); break;
    case "ls": ls(); break;
    }
  }

/**
  * This method prints a visual representation of everything in the stack
  * @post a string representation consisting of every tokens in the stack from the top to the bottom
  */
  public void pstack() {
    System.out.print("<top>" + " "); // represents the top of the stack
    for (Token t : postStack) { // iterates through StackList
      System.out.print(t + " "); // prints each Token in stack
    }
    System.out.print(" " + "<bottom>" + "\n"); // represents the bottom of the stack
  }

/**
  * This method checks whether the latest two token on the stack is of number kind
  * @pre the latest two token is of number kind
  */
  public void checkValidNum(Token lastNum, Token secLastNum) {
    Assert.pre(lastNum.isNumber() && secLastNum.isNumber(), "Provide a Number of kind Token");
  }

/**
  * This method checks the size of postStack
  * @pre @pre stack larger than 1
  */
  public void checkOpSize() {
    Assert.pre(postStack.size() > 1, "Provide another Token");
  }

/**
  * This method checks the size of postStack
  * @pre stack larger than 0
  */
  public void checkSize() {
    Assert.pre(postStack.size() > 0, "Provide another Token");
  }

/**
  * This method performs addition on the two latest number in the stack
  * @pre stack larger than 1 & two latest input is a numbers
  * @post the resulting number is pushed onto the stack
  */
  public void add() {
    checkOpSize();
    Token lastNum = postStack.pop();
    Token secLastNum = postStack.pop();
    checkValidNum(lastNum, secLastNum);
    postStack.push(new Token(lastNum.getNumber() + secLastNum.getNumber()));
  }

/**
  * This method performs subtraction on the two latest number in the stack
  * @pre stack larger than 1 & two latest input is a numbers
  * @post the resulting number is pushed onto the stack
  */
  public void sub() {
    checkOpSize();
    Token lastNum = postStack.pop();
    Token secLastNum = postStack.pop();
    checkValidNum(lastNum, secLastNum);
    postStack.push(new Token(secLastNum.getNumber() - lastNum.getNumber()));
  }

/**
  * This method performs multiplication on the two latest number in the stack
  * @pre stack larger than 1 & two latest input is a numbers
  * @post the resulting number is pushed onto the stack
  */
  public void mul() {
    checkOpSize();
    Token lastNum = postStack.pop();
    Token secLastNum = postStack.pop();
    checkValidNum(lastNum, secLastNum);
    postStack.push(new Token(lastNum.getNumber() * secLastNum.getNumber()));
  }

/**
  * This method performs division on the two latest number in the stack
  * @pre stack larger than 1 & two latest input is a numbers
  * @post the resulting number is pushed onto the stack
  */
  public void div() {
    checkOpSize();
    Token lastNum = postStack.pop();
    Token secLastNum = postStack.pop();
    checkValidNum(lastNum, secLastNum);
    postStack.push(new Token(secLastNum.getNumber() / lastNum.getNumber()));
  }

/**
  * This method duplicates the latest input token
  * @pre stack larger than 0
  * @post the resulting duplicate is pushed onto the stack
  */
  public void dup() {
    checkSize();
    Token original = postStack.pop();
    postStack.push(original);
    postStack.push(original);
  }
/**
  * This method exchanges the position of the second last input and the last input
  * @pre stack larger than 0
  * @post the position of the second last input and the last input is switched
  */
  public void exch() {
    checkSize();
    Token last = postStack.pop();
    Token secLast = postStack.pop();
    postStack.push(last);
    postStack.push(secLast);
  }
/**
  * This method checks whether the value of the latest two input is equal
  * @pre stack larger than 0
  * @post new boolean is pushed onto the stack
  */
  public void eq() {
    checkSize();
    Token last = postStack.pop();
    Token secLast = postStack.pop();
    postStack.push(new Token(last.equals(secLast)));
  }
/**
  * This method checks whether the value of the latest two input is not equal
  * @pre stack larger than 0
  * @post new boolean is pushed onto the stack
  */
  public void ne() {
    checkSize();
    Token last = postStack.pop();
    Token secLast = postStack.pop();
    postStack.push(new Token(!last.equals(secLast)));
  }
/**
  * This method defines the new input symbol
  * @pre stack larger than 0
  * @post new symbol relating to the input value is added to postSymbol
  */
  public void def() {
    checkSize();
    Token value = postStack.pop();
    Token symbol = postStack.pop();
    postSymbols.add(symbol.getSymbol().substring(1), value);
  }
/**
  * This method defines the new input symbol
  * @pre stack larger than 0
  * @post new symbol relating to the input value is added to postSymbol
  */
  public void doIf() {
    checkSize();
    Token last = postStack.pop();
    Token secLast = postStack.pop();
    if (secLast.getBoolean()) {
      interpret(new Reader(last));
    }
  }
/**
  * This method checks whether the value of the second last input is less than the value of the last input
  * @pre stack larger than 0
  * @post new boolean is pushed onto the stack
  */
  public void ls() {
    checkSize();
    Token last = postStack.pop();
    Token secLast = postStack.pop();
    postStack.push(new Token(secLast.getNumber() < last.getNumber()));
  }
/**
  * This method removes the latest input on the stack
  * @post lastest token is removed from the stack
  */
  public void pop() {
    postStack.pop();
  }
/**
  * This method prints a string representation of the table
  * @post string representation of postSymbol
  */
  public void pTable() {
    System.out.println(postSymbols); // prints string representation of table
  }

  public static void main(String[] args) { // main method to run the Intepreter class
    Interpreter interpreter = new Interpreter(); // creates Interpreter Obejct
    Reader reader = new Reader(); // processes PostScript user input
    interpreter.interpret(reader);
  }
}