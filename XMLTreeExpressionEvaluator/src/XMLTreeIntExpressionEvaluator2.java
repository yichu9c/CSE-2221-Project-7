import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeIntExpressionEvaluator2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator2() {
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
    private static NaturalNumber evaluate2(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber result = new NaturalNumber2(0);

        if (!exp.label().equals("number")) {
            //if root tag is an operator

            NaturalNumber expressionLeft = evaluate2(exp.child(0));
            NaturalNumber expressionRight = evaluate2(exp.child(1));

            if (exp.label().equals("times")) {
                //if root tag is operator times

                expressionLeft.multiply(expressionRight);
            } else if (exp.label().equals("divide")) {
                //if root tag is operator divide

                if (expressionRight.isZero()) {
                    Reporter.fatalErrorToConsole("Can not divide by 0");
                }

                expressionLeft.divide(expressionRight);
            } else if (exp.label().equals("plus")) {
                //if root tag is operator plus

                expressionLeft.add(expressionRight);
            } else if (exp.label().equals("minus")) {
                //if root tag is operator minus

                if (expressionRight.compareTo(expressionLeft) > 0) {
                    Reporter.fatalErrorToConsole(
                            "if right greater left becomes negative, NaturalNumber > 0");
                }

                expressionLeft.subtract(expressionRight);
            }

            result.copyFrom(expressionLeft);
        } else {
            // if root tag is just a number
            int number = Integer.parseInt(exp.attributeValue("value"));
            result = new NaturalNumber2(number);
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
            out.println(evaluate2(exp.child(0)));

            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
