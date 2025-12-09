package com.calculator3.service;

import com.calculator3.model.CalculatorState;
import com.calculator3.Exception.CalculatorException;

public class EqualsHandler {

    public static CalculatorState handle(CalculatorState state) {
        if (state.isPastIsEqual()) {
            return state;
        }

        if (!state.isRead1()) {
            calculateFinalResult(state);
        } else {
            displaySingleNumberResult(state);
        }

        resetAfterEquals(state);
        return state;
    }

    private static void calculateFinalResult(CalculatorState state) {
        state.setPastIsEqual(true);
        double a = Double.parseDouble(state.getNum1());
        double b = Double.parseDouble(state.getNum2());

        String num1Formatted = NumberFormatter.format(a);
        String num2Formatted = NumberFormatter.format(b);
        String operSymbol = OperationHandler.getOperatorSymbol(state.getOperator());
        state.setDisplay1(num1Formatted + " " + operSymbol + " " + num2Formatted + " =");

        double result = OperationHandler.getResult(a, b, state.getOperator());
        state.setNum1(String.valueOf(result));
        state.setDisplay2(NumberFormatter.format(result));
    }

    private static void displaySingleNumberResult(CalculatorState state) {
        state.setDisplay1(state.getNum1() + " =");
        state.setDisplay2(state.getNum1());
    }

    private static void resetAfterEquals(CalculatorState state) {
        state.setNum2("0");
        state.setRead1(false);
        state.setPastIsOper(false);
        state.setSfr2(false);
        state.setSfr1(true);
    }
}