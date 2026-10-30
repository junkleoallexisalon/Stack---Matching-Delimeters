/* Description: Stack-based matching-delimiter checker for (), [] and {} that
 *              also prints every stack operation (PUSH / PEEK / POP), the
 *              current stack contents, and the reason for any failure.
 *
 * Programmed by: Jun Kleo Allexis Alon BSIT 48079 Data Structures and Algorithms
 *
 * Last Modified: 2026-10-30
 *
 * Version: 2.0 (adds execution trace to version 1.0)
 *
 */
import java.util.Scanner;
import java.util.Stack;
 
public class DelimiterCheckerTrace {
 
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
 
    private static String show(Stack<Character> s) {
        if (s.isEmpty()) return "(empty)";
        StringBuilder sb = new StringBuilder();
        for (char c : s) sb.append(c).append(' ');   
        return sb.toString().trim();
    }
 
    public static boolean check(String input) {
        Stack<Character> stack = new Stack<>();
        System.out.println("Input: " + (input.isEmpty() ? "(empty)" : input));
 
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isOpening(c) && !isClosing(c)) continue;   
 
            System.out.println();
            System.out.println("Read: " + c);
 
            if (isOpening(c)) {
                stack.push(c);
                System.out.println("Operation: PUSH " + c);
                System.out.println("Stack: " + show(stack));
            } else {
                if (stack.isEmpty()) {
                    System.out.println("Operation: isEmpty -> true");
                    System.out.println("Invalid: closing delimiter '" + c
                            + "' has no matching opening delimiter");
                    return false;
                }
                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);
                if (top != opener(c)) {
                    System.out.println("Invalid: mismatched delimiters, '" + c
                            + "' does not match '" + top + "'");
                    System.out.println("Stack: " + show(stack));
                    return false;
                }
                stack.pop();
                System.out.println("Operation: POP " + top);
                System.out.println("Stack: " + show(stack));
            }
        }
 
        System.out.println();
        if (!stack.isEmpty()) {
            System.out.println("Invalid: unmatched opening delimiter remains: "
                    + show(stack));
            return false;
        }
        System.out.println("Stack is empty after processing all characters.");
        return true;
    }
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = in.hasNextLine() ? in.nextLine() : "";
        System.out.println(check(input) ? "Valid" : "Invalid");
    }
}
 
