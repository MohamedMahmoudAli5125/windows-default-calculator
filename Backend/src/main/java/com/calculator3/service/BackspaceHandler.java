package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class BackspaceHandler {

    public static CalculatorState handle(CalculatorState state) {
        // If sfr1 is true, delete the whole num1
        if (state.isSfr1()) {
            state.setNum1("0");
            state.setDisplay2("0");
            state.setDisplay1("");
            state.setSfr1(false);
            state.setRead1(true);
            return state;
        }

        // If sfr2 is true, delete the whole num2
        if (state.isSfr2()) {
            state.setNum2("0");
            state.setDisplay2("0");
            DisplayManager.updateOperatorDisplay(state);
            state.setSfr2(false);
            return state;
        }

        // If we're reading num1
        if (state.isRead1()) {
            return handleBackspaceForNumber(state, true);
        }

        // If we're reading num2
        if (!state.isRead1() && !state.isPastIsOper()) {
            return handleBackspaceForNumber(state, false);
        }

        // If pastIsOper is true (just pressed an operator), delete the operator
        if (state.isPastIsOper()) {
            state.setOperator('\0');
            state.setPastIsOper(false);
            state.setRead1(true);
            state.setDisplay2(NumberFormatter.format(Double.parseDouble(state.getNum1())));
            state.setDisplay1("");
            return state;
        }

        return state;
    }

    private static CalculatorState handleBackspaceForNumber(CalculatorState state, boolean isFirstNumber) {
        String currentNum = isFirstNumber ? state.getNum1() : state.getNum2();

        // If number has more than 1 character, delete last character
        if (currentNum.length() > 1) {
            String newNum = currentNum.substring(0, currentNum.length() - 1);

            if (isFirstNumber) {
                state.setNum1(newNum);
            } else {
                state.setNum2(newNum);
            }

            // Format the display
            if (newNum.endsWith(".")) {
                double val = Double.parseDouble(newNum.substring(0, newNum.length() - 1));
                state.setDisplay2(NumberFormatter.format(val) + ".");
            } else if (newNum.contains(".")) {
                String[] parts = newNum.split("\\.");
                double intPart = Double.parseDouble(parts[0]);
                state.setDisplay2(NumberFormatter.format(intPart) + "." + parts[1]);
            } else {
                state.setDisplay2(NumberFormatter.format(Double.parseDouble(newNum)));
            }
        } else {
            // If only one character left, set to "0"
            if (isFirstNumber) {
                state.setNum1("0");
            } else {
                state.setNum2("0");
            }
            state.setDisplay2("0");
        }

        if (isFirstNumber) {
            state.setDisplay1("");
        } else {
            DisplayManager.updateOperatorDisplay(state);
        }

        return state;
    }
}