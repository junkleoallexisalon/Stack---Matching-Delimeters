/* Description: Checks whether (), [] and {} delimiters in a user-entered
 *              sequence are properly matched and nested, using a stack.
 *              Other characters are ignored.
 *
 * Programmed by: Jun Kleo Allexis Alon BSIT 48079 Data Structures and Algorithms
 *
 * Last Modified: 2026-10-30
 *
 * Version: 1.0
 *
 
 */
import java.util.Scanner;
import java.util.Stack;
 
public class DelimiterChecker {
 
    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }
 
    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }
 
    private static char opener(char closing) {
        switch (closing) {
            case ')': return '(';
            case ']': return '[';
            default:  return '{';
        }
    }
 
  
    public static String check(String input) {
        Stack<Character> stack = new Stack<>();
 
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
 
            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return "closing delimiter '" + c + "' at position " + (i + 1)
                         + " has no matching opening delimiter";
                }
                char top = stack.peek();
                if (top != opener(c)) {
                    return "mismatched delimiters: '" + c + "' at position " + (i + 1)
                         + " does not match '" + top + "' on top of the stack";
                }
                stack.pop();
            }
        }
 
        if (!stack.isEmpty()) {
            return "unmatched opening delimiter remains: '" + stack.peek() + "'";
        }
        return null;
    }
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = in.hasNextLine() ? in.nextLine() : "";
 
        String reason = check(input);
        if (reason == null) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
            System.out.println("Reason: " + reason);
        }
    }
}
 
