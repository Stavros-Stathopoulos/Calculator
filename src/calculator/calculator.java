package calculator;

import java.util.Stack;


public class calculator{

    public static String checkExp(String exp){
       String newExp = "";
       for( int i = 0; i < exp.length(); i++){
           if (exp.charAt(i) == ' '){
               continue;
           }
           else{
               newExp += exp.charAt(i) + " ";
           }

       }
       return newExp;
    }

    public static boolean hasLowerPrecedence(String char1, String char2){
        return precedence(char1) < precedence(char2);
    }

    public static int precedence(String ch){
        return switch (ch) {
            case "+", "-" -> 1;
            case "*", "/", "(", ")","^" -> 2;
            default -> -1;
        };
    }

    public static boolean isOperator(String ch){
        return switch (ch) {
            case "+", "-", "*", "/", "(", ")","^" -> true;
            default -> false;
        };
    }

    public static boolean isParenthesis(String ch){
        return switch (ch) {
            case "(", ")" -> true;
            default -> false;
        };
    }

    public static String postFixConverter(String expr){
        Stack<String> operators = new Stack<>();
        Stack<String> postFix = new Stack<>();
        Stack<String> parenthesis = new Stack<>();
        String exp = checkExp(expr);

        for( String c : exp.split(" ")){
            if(isOperator(c)){
                if(c.equals(")")){
                    while(!operators.peek().equals("(") && !operators.isEmpty()){
                        postFix.push(operators.pop());
                    }
                    continue;
                }
                else {
                    while (!operators.isEmpty() && hasLowerPrecedence(c, operators.peek()) && !operators.peek().equals("(")) {

                        postFix.push(operators.pop());
                    }
                    operators.push(c);
                }
            }
            else {
                postFix.push(String.valueOf(c));
            }
        }
        while(!operators.isEmpty()){
            postFix.push(operators.pop());
        }
        System.out.println(postFix);
        return String.join(" ", postFix);
    }

    public static boolean isNumber(String c){
        try{
            Integer.parseInt(c);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static int evaluatePostFix(String postFix){
        Stack<Integer> numbers = new Stack<>();
        for(String c: postFix.split(" ")){
            if (isNumber(c)){
                numbers.push(Integer.parseInt(c));
            }
            else if (isOperator(c) && !isParenthesis(c)){
                if (c.equals("!")){
                    numbers.push(fact(numbers.pop()));
                    continue;
                }
                int numb1 = numbers.pop();
                int numb2 = numbers.pop();

                switch (c){
                    case "+":
                        numbers.push(numb2 + numb1);
                        break;
                    case "-":
                        numbers.push(numb2 - numb1);
                        break;
                    case "*":
                        numbers.push(numb2 * numb1);
                        break;
                    case "/":
                        numbers.push(numb2 / numb1);
                        break;
                    case"^":
                        numbers.push((int) Math.pow(numb2, numb1));
                        break;

                }
            }
            else{
                continue;
            }
        }
        return numbers.pop();
    }

    public static Integer fact(int numb1) {
        if(numb1 == 0){
            return 1;
        }
        else{
            return numb1 * fact(numb1 - 1);
        }
    }
}
