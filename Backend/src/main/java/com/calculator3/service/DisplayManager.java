package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class DisplayManager {

    public static void updateNumberDisplay(CalculatorState state, String number) {
        if (number.endsWith(".")) {
            double val = Double.parseDouble(number.substring(0, number.length() - 1));
            state.setDisplay2(NumberFormatter.format(val) + ".");
        } else if (number.contains(".")) {
            String[] parts = number.split("\\.");
            double intPart = Double.parseDouble(parts[0]);
            state.setDisplay2(NumberFormatter.format(intPart) + "." + parts[1]);
        } else {
            state.setDisplay2(NumberFormatter.format(Double.parseDouble(number)));
        }
    }

    public static void updateOperatorDisplay(CalculatorState state) {
        if (state.getOperator() != '\0' && !state.isRead1()) {
            String num1Display = NumberFormatter.format(Double.parseDouble(state.getNum1()));
            String operSymbol = OperationHandler.getOperatorSymbol(state.getOperator());
            state.setDisplay1(num1Display + " " + operSymbol);
        } else {
            state.setDisplay1("");
        }
    }

    public static void updateFullExpressionDisplay(CalculatorState state, char mode) {
        if (mode == '1') {
            state.setDisplay1(state.getNum1());
        } else if (mode == '2') {
            String operSymbol = OperationHandler.getOperatorSymbol(state.getOperator());
            state.setDisplay1(state.getNum1() + " " + operSymbol + " " + state.getNum2());
        } else {
            updateOperatorDisplay(state);
        }
    }
}