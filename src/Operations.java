import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Operations {
    Operations(){}

    public static float evaluateExpression(String expression) throws ArithmeticException {
        List<String> screenOPR = buildNumbers(expression);
        return evaluate(screenOPR);
    }

    public static List<String> buildNumbers(String nums) {
        List<String> num = new ArrayList<>();
        StringBuilder numbuffer = new StringBuilder();
        
        for(int i = 0; i < nums.length(); i++) {
            char c = nums.charAt(i);
            if(Character.isDigit(c) || c == '.') {
                numbuffer.append(c);
            }
            else if(c == '+' || c == '*' || c == '/') {
                if(numbuffer.length() > 0) {
                    num.add(numbuffer.toString());
                    numbuffer.setLength(0);
                }
                num.add(String.valueOf(c));
            }
            else if(c == '-') {
                if(numbuffer.length() > 0) {
                    num.add(numbuffer.toString());
                    numbuffer.setLength(0);
                }
                // Check if the number is negative
            
                if(i == 0 || nums.charAt(i-1) == '(' || nums.charAt(i-1) == '+' || nums.charAt(i-1) == '-' 
                || nums.charAt(i-1) == '*' || nums.charAt(i-1) == '/') {
                    numbuffer.append(c);
                } else {
                    num.add(String.valueOf(c));
                }
            }
            else if(!Character.isWhitespace(c)) {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }
        if(numbuffer.length() > 0) {
            num.add(numbuffer.toString());
        }

        return num;
    }

    public static float evaluate(List<String> num) throws ArithmeticException {

        // First pass: evaluate multiplication and division

        for(int i = 0; i < num.size(); i++) {
            String fst = num.get(i);
            if (fst.equals("*") || fst.equals("/")) {
                float left = Float.parseFloat(num.get(i - 1));
                float right = Float.parseFloat(num.get(i + 1));
                float result = fst.equals("*") ? left * right : left / right;

                if(right == 0 && fst.equals("/")) {
                    throw new ArithmeticException("Division by zero");
                }
                num.set(i - 1, String.valueOf(result));
                num.remove(i); 
                num.remove(i);
                i--;
            }
        }

        // Second pass: evaluate addition and subtraction
        float result = Float.parseFloat(num.get(0));
        for(int i = 1; i < num.size(); i += 2) {
            String op = num.get(i);
            float next = Float.parseFloat(num.get(i + 1));
            if(op.equals("+")) {
                result += next;
            }
            else if(op.equals("-")) {
                result -= next;
            }
        }

        return result;
    } 

    public static void main(String[] args) {
        String expression = "-8 / 2";
        try {
            float result = evaluateExpression(expression);
            System.out.println("Result: " + result);
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}