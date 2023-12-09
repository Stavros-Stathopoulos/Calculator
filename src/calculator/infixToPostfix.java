package calculator;

import java.util.Stack;


public class infixToPostfix {

    public static Stack<Character> InfixToPostfix(String Exp) {
        char ch;
        int j = 0;
        Stack<Character> inFix = new Stack<>();
        Stack<Character> postFix = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Initialize the Infix stack with the mathematical expression given
        for (int i = 0; i < Exp.length(); i++) {
            ch = Exp.charAt(i);
            inFix.push(ch);
        }

        // While the Infix stack is not empty
        while (!inFix.isEmpty()) {
            if(j <= inFix.size()-1 ){
                ch = inFix.elementAt(j);
                j++;
            }
            else{
                break;
            }

            // If the character is a  digit or a letter, push it to the Postfix stack
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                postFix.push(ch);
            }

            // If the character is an operator, pop the operators stack until an operator with lower precedence is found
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty()) {
                    if (priorityOperator(ch) <= priorityOperator(operators.peek())) {
                        postFix.push(operators.pop());
                    }
                    else {
                        break;
                    }
                }
                operators.push(ch);
            }

            // If the character is an opening parenthesis, push it to the operators stack
            else if (ch == '(') {
                operators.push(ch);
            }

            // If the character is a closing parenthesis, pop the operators stack until an opening parenthesis is found
            else if (ch == ')') {
                while (operators.peek() != '(') {
                    postFix.push(operators.pop());
                }
                operators.pop();
            }
        }

        // Pop the remaining operators from the operators stack and push them to the Postfix stack
        while (!operators.isEmpty()) {
            postFix.push(operators.pop());
        }

        return postFix;
    }

    // Method to return the priority of an operator
    private static int priorityOperator(char ch) {

        // If the character is  + or -, return its priority 1
        if (ch == '+' || ch == '-') {
            return 1;
        }

        // If the character is  * or /, return its priority 2
        else if (ch == '*' || ch == '/') {
            return 2;
        }

        // If the character is  ^, return its priority 3
        else if (ch == '^'){
            return 3;
        }

        // Otherwise, return -1
        return -1;
    }

}
