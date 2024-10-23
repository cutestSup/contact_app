package com.example.calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculation {
    public double eval(String str) {
        Queue<String> postfix = to_postfix(str);
        Stack<Double> result = new Stack<>();
        while (!postfix.isEmpty()) {
            String token = postfix.poll();
            if (!isOperator(token.charAt(0))) {
                double value = Double.parseDouble(token);
                result.push(value);
            } else {
                if (result.size() < 2) {
                    throw new IllegalStateException("Insufficient operands for operator: " + token);
                }
                double b = result.pop();
                double a = result.pop();
                result.push(evaluate(token, a, b));
            }
        }
        if (result.size() != 1) {
            throw new IllegalStateException("The expression is incorrect.");
        }
        return result.pop();
    }

    private static double evaluate(String operator, double a, double b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Divide by zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private static Queue<String> to_postfix(String infix) {
        Queue<String> output = new LinkedList<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < infix.length() && (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    number.append(infix.charAt(i));
                    i++;
                }
                i--;
                output.offer(number.toString());
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    output.offer(String.valueOf(operators.pop()));
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            output.offer(String.valueOf(operators.pop()));
        }
        return output;
    }

    private static int precedence(char operator) {
        return (operator == '+' || operator == '-') ? 1 : 2;
    }

    private static boolean isOperator(char c) {
        return c == '*' ||c == '-' ||c == '+' ||c == '/';
    }

}
