package com.calculator3.service;

import com.calculator3.Exception.CalculatorException;

public class OperationHandler {

    public static double getResult(double a, double b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new CalculatorException("Division by zero");
                return a / b;
            default: return 0;
        }
    }

    public static String getOperatorSymbol(char operator) {
        switch (operator) {
            case '+': return "+";
            case '-': return "-";
            case '*': return "×";
            case '/': return "÷";
            default: return "";
        }
    }
}