import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Yihone Chu
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int result = 0;

        if (!exp.label().equals("number")) {
            //if root tag is an operator
            int expressionLeft = evaluate(exp.child(0));
            int expressionRight = evaluate(exp.child(1));

            if (exp.label().equals("times")) {
                //if root tag is operator times

                result = expressionLeft * expressionRight;
            } else if (exp.label().equals("divide")) {
                //if root tag is operator divide
                if (expressionRight == 0) {
                    Reporter.fatalErrorToConsole("Can not divide by 0");
                }

                result = expressionLeft / expressionRight;
            } else if (exp.label().equals("plus")) {
                //if root tag is operator plus

                result = expressionLeft + expressionRight;
            } else if (exp.label().equals("minus")) {
                //if root tag is operator minus

                result = expressionLeft - expressionRight;
            }
        } else {
            //if root tag is a number
            result = Integer.parseInt(exp.attributeValue("value"));
        }

        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));

            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
