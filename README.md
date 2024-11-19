Project: XMLTree Expression Evaluator
Objectives
Familiarity with using recursion to evaluate arithmetic expressions.
Familiarity with using XMLTree objects and methods.
Familiarity with using NaturalNumber objects and methods.
The Problem
Your job is to implement two versions of the evaluate static method to recursively evaluate arithmetic expressions represented as XMLTrees. One version of the method evaluates the expression with ints and the other version uses NaturalNumbers. The expressions are stored in XML format (see below) so the program can load them as XMLTrees.

Arithmetic Expressions
The program supports arithmetic expressions with integer operands, binary operators +, –, *, and /, and parentheses (). The precedence rules are the standard ones from arithmetic (i.e., parenthesized expressions are evaluated first, then multiplicative operators, and finally additive operators).

Generating XMLTree expressions
To generate the XML input files for your program, you can use a program that allows the user to enter an arithmetic expression and save it into the corresponding XML document. You can run this program by importing it into Eclipse. First you need to download the project archive, xml-expression-generator.zip, to your computer. Click on this download link and save the file somewhere on your hard drive where you can easily find it. Make sure that you do not expand this archive. If your browser automatically expands downloaded zip archives, that's OK too. Just pay attention to the special instructions in the following few steps. You may want to make a note of where you saved it.

Import your new project in Eclipse by following these steps:

From the File menu select Import....
In the new window, expand General and select Existing Projects into Workspace. Click Next.
Click on the radio button next to Select archive file and then click the Browse... button. (If the archive was expanded when you downloaded the file to your own computer, click on Select root directory... instead.)
In the file selection window, find the xml-expression-generator.zip file and select it. (If your browser expanded the archive, find the xml-expression-generator directory instead.) Click OK.
Click Finish.
To run the program, find the RunGenerator.java file in the src folder in the project and run it as usual as a Java Application. Once you start the program, you can enter an expression (the program only allows the user to enter characters that are valid in an expression) and save it to an XML document on the local drive (the Save XML... button is active only when the text entered is a valid arithmetic expression).

Format of the Input Expression XML Document
Here is an example of the expression XML document representing the expression 12*(7-3)/6+8:

<expression>
  <plus>
    <divide>
      <times>
        <number value="12" />
        <minus>
          <number value="7" />
          <number value="3" />
        </minus>
      </times>
      <number value="6" />
    </divide>
    <number value="8" />
  </plus>
</expression>
The top-level tag is <expression>. Nested inside there can be 5 different tags: <plus>, <minus>, <times>, <divide>, and <number>. The first four represent the (binary) operators and the last one represents the operands whose value is specified in the required attribute value (assume operands can only be non-negative integers). Each of the operator tags has exactly two nested tags and no attributes. There are no text nodes. Note that the parentheses in the original expression have disappeared because the XML document already "encodes" the correct order of evaluation of the operators in the given expression.

Setup
Create a new Eclipse project by copying ProjectTemplate or a previous project you have created, naming the new project XMLTreeExpressionEvaluator.
Open the src folder of this project and then open (default package). As a starting point you can use any of the Java files. Rename it XMLTreeIntExpressionEvaluator and delete the other files from the project.
Follow the link to XMLTreeIntExpressionEvaluator.java, select all the code on that page and copy it to the clipboard; then open the XMLTreeIntExpressionEvaluator.java file in Eclipse and paste the code to replace the file contents. Save the file.
Method
Edit XMLTreeIntExpressionEvaluator.java to implement the first version of the evaluate method using recursion. This is the one that uses int for the operand type and computes the value of the expression with Java's built-in integer operators. Here is the contract:
    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires      * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * 
     * @ensures evaluate = [the value of the expression]
     */
     private static int evaluate(XMLTree exp) {...}
As the requires clause states, you can assume that the given XMLTree is a well-formed XML expression and you do not need to check for errors. You can use the Integer.parseInt(String) static method to convert a String value into an integer.
Run the XMLTreeIntExpressionEvaluator program to test your implementation of evaluate.
Copy XMLTreeIntExpressionEvaluator.java to create XMLTreeNNExpressionEvaluator.java.
Edit XMLTreeNNExpressionEvaluator.java to implement the second version of the evaluate method using recursion. This is the one that uses NaturalNumber for the operand type and computes the value of the expression with NaturalNumber's methods. Make sure you update the contract and the implementation to match the following:
    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires      * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * 
     * @ensures evaluate = [the value of the expression]
     */
     private static NaturalNumber evaluate(XMLTree exp) {...}
Run the XMLTreeNNExpressionEvaluator program to test your implementation of evaluate.
Add checks for the preconditions (requires clause) of all the NaturalNumber methods you invoke in your solution that have a precondition and make sure you output descriptive error messages. Note that even though you can assume the given XMLTree is a well-formed XML expression, it is possible that one or more of the NaturalNumber methods may be called with a violated precondition (e.g., divide by 0). If you find any violated precondition, you should stop the evaluation of the expression and report a fatal error. Use the components.utilities.Reporter's fatalErrorToConsole method to report the error and make the program terminate.
Select your Eclipse project XMLTreeExpressionEvaluator (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.
Additional Activities
Here are some possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

Both the XML expression generator program and the XML expression document format support three extra operations: mod (%), power (^), and root (v). For example, the expression (2*17v3+4)^(31%5) results in the following XML document:
<expression>
  <power>
    <plus>
      <times>
        <number value="2" />
        <root>
          <number value="17" />
          <number value="3" />
        </root>
      </times>
      <number value="4" />
    </plus>
    <mod>
      <number value="31" />
      <number value="5" />
    </mod>
  </power>
</expression>
Update the evaluate method in XMLTreeNNExpressionEvaluator.java so that it supports these operations as well.
