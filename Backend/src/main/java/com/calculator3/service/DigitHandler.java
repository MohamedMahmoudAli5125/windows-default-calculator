package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class DigitHandler {

    public static CalculatorState process(char digit, CalculatorState state) {
        if (state.isSfr1()) {
            state.setNum1("0");
            state.setRead1(true);
        }
        if (state.isSfr2()) {
            state.setNum2("0");
            state.setRead1(false);
        }

        if (state.isRead1()) {
            return processNumberInput(digit, state, true);
        } else {
            return processNumberInput(digit, state, false);
        }
    }

    private static CalculatorState processNumberInput(char digit, CalculatorState state, boolean isFirstNumber) {
        String currentNum = isFirstNumber ? state.getNum1() : state.getNum2();

        if (digit == '.' && currentNum.contains(".")) {
            return state;
        }

        if (currentNum.length() == 16) {
            return state;
        }

        String newNum = buildNewNumber(currentNum, digit);

        if (isFirstNumber) {
            state.setNum1(newNum);
        } else {
            state.setNum2(newNum);
        }

        updateDisplayForNumber(state, newNum, isFirstNumber);
        resetStateFlags(state);

        return state;
    }

    private static String buildNewNumber(String currentNum, char digit) {
        if (currentNum.equals("0") && digit != '.') {
            return String.valueOf(digit);
        }
        return currentNum + digit;
    }

    private static void updateDisplayForNumber(CalculatorState state, String number, boolean isFirstNumber) {
        DisplayManager.updateNumberDisplay(state, number);
        if (!isFirstNumber) {
            DisplayManager.updateOperatorDisplay(state);
        }
    }

    private static void resetStateFlags(CalculatorState state) {
        state.setPastIsOper(false);
        state.setPastIsEqual(false);
        state.setSfr2(false);
        state.setSfr1(false);
    }
}